package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.text.regex.ParamFinder;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOUtils;
import org.morozko.java.mod.db.dao.FieldFactory;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.GenericDAO;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.RSExtractor;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Record;

public class AnalyzeRecordHandler extends BasicRecordHandler {

	private final static ParamFinder PARAM_FINDER = ParamFinder.newFinder();
	
	public AnalyzeRecordHandler(ConfigTag config, BasicDAOFactory basicDAOFactory, String tableName ) {
		super(config);
		this.basicDAOFactory = basicDAOFactory;
		this.tableName = tableName;
		this.insertCount = 0;
		this.updateCount = 0;
		this.counter = 0;
	}

	private int counter;
	
	private List<String> keyCheckList;
	
	private BasicDAOFactory basicDAOFactory;
	
	private String[] tableKeyCols;
	
	private int[] sourceKeyColsIndex;
	
	private List<ColMappingEntry> colMappingList = null;
	
	public String[] getTableKeyCols() {
		return tableKeyCols;
	}

	private String sqlUpdate;
	
	private String sqlInsert;
	
	private String tableName;
	
	private int insertCount;
	
	private int updateCount;
	
	public void setTableKeyCols(String[] tableKeyCols) throws Exception {
		this.tableKeyCols = tableKeyCols;
		KeyRSE rse = new KeyRSE( tableKeyCols );
		DAOUtils daoUtils = this.basicDAOFactory.getDaoUtils();
		this.keyCheckList = new ArrayList<String>();
		String query = "SELECT DISTINCT "+this.tableKeyCols[0]+" FROM "+this.tableName;
		for ( int k=1; k<this.tableKeyCols.length; k++ ) {
			query+= ", "+this.tableKeyCols[k];
		}
		LogFacade.getLog().debug( "AnalyzeRecordHandler : key list query : "+query );
		daoUtils.getGenericDAO().loadAll( this.keyCheckList, query, rse );
		LogFacade.getLog().debug( "AnalyzeRecordHandler : keyCheckList : "+this.keyCheckList );
	}

	public int[] getSourceKeyColsIndex() {
		return sourceKeyColsIndex;
	}

	public void setSourceKeyColsIndex(int[] sourceKeyColsIndex) {
		this.sourceKeyColsIndex = sourceKeyColsIndex;
	}

	private JdbcColumn[] columns;
	
	public JdbcColumn[] getColumns() {
		return columns;
	}

	public void setColumns(JdbcColumn[] columns) {
		this.columns = columns;
	}
	
	public void setColMapping() throws Exception {
		this.colMappingList = new ArrayList<ColMappingEntry>( this.columns.length );
		StringBuffer update = new StringBuffer();
		StringBuffer insert1 = new StringBuffer();
		StringBuffer insert2 = new StringBuffer();
		update.append( " UPDATE "+tableName+" SET " );
		insert1.append( " INSERT INTO "+tableName+" ( " );
		insert2.append( " ) VALUES ( " );
		int colNumber = this.columns.length;
		for ( int k=0; k<colNumber; k++ ) {
			LogFacade.getLog().debug( "AnalyzeRecordHandler : ColMappingEntry : "+this.columns[k].getName() );
			ColMappingEntry cme = new ColMappingEntry( this.columns[k], this.getConfig() );
			this.colMappingList.add( cme );
			// update select
			if ( k==colNumber-1 ) {
				update.append( cme.getColumn()+" = ? WHERE " );
				update.append( this.tableKeyCols[0] + " = ? " );
				for ( int i=1; i<this.tableKeyCols.length; i++ ) {
					update.append( " AND "+this.tableKeyCols[1] + " = ? " );	
				}
			} else {
				update.append( cme.getColumn()+" = ? , " );
			}
			// insert select
			if ( k==0 ) {
				insert1.append( cme.getColumn() );
				insert2.append( "?" );
			} else {
				insert1.append( ", "+cme.getColumn() );
				insert2.append( ", ?" );
			}			
		}
		insert2.append( ")" );
		this.sqlInsert = insert1.toString()+insert2.toString();
		this.sqlUpdate = update.toString();
		LogFacade.getLog().debug( "AnalyzeRecordHandler : sqlInsert : "+this.sqlInsert );
		LogFacade.getLog().debug( "AnalyzeRecordHandler : sqlUpdate : "+this.sqlUpdate );
	}

	@Override
	public int release() throws DbsrcException {
		LogFacade.getLog().info( "AnalyzeRecordHandler : insertCount : "+this.insertCount );
		LogFacade.getLog().info( "AnalyzeRecordHandler : updateCount : "+this.updateCount );
		return this.counter;
	}

	private void handleCME( ColMappingEntry cme, Record record, FieldList fl, GenericDAO genericDAO ) throws DAOException {
		if ( cme.getMappingType() == ColMappingEntry.MAPPING_SOURCE_INDEX ) {
			Object data = record.getField( cme.getMapTo() ).getData();
			LogFacade.getLog().debug( "AnalyzeRecordHandler : data : "+data );
			fl.addField( data );
		} else if ( cme.getMappingType() == ColMappingEntry.MAPPING_FIXED_VALUE ) {
			fl.addField( cme.getFixedValue() );
		} else if ( cme.getMappingType() == ColMappingEntry.MAPPING_QUERY ) {
			FieldList flQuery = new FieldList( new FieldFactory() );
			String sql = cme.getQuery();
			List<String> paramList = PARAM_FINDER.getParamList( sql );
			Iterator<String> paramIt = paramList.iterator();
			while ( paramIt.hasNext() ) {
				String param = paramIt.next();
				int current = Integer.parseInt( param );
				sql = PARAM_FINDER.substitute( sql , param, "?" );
				flQuery.addField( record.getField( current ).getData() );
			}
			List<Object> list = new ArrayList<Object>();
			genericDAO.loadAll( list, sql, flQuery, ObjectRSE.DEFAULT );
			if ( list.isEmpty() ) {
				fl.addField( "" );
			} else {
				fl.addField( list.get( 0 ) );
			}
		}
	}
	
	@Override
	public void setRecord(Record record) throws DbsrcException {
		LogFacade.getLog().debug( "index "+this.sourceKeyColsIndex[0] );
		String sourceKey = String.valueOf( record.getField( this.sourceKeyColsIndex[0] ).getData() ).trim();
		LogFacade.getLog().debug( "index "+ record.getField( this.sourceKeyColsIndex[0] ) );
		for ( int k=1; k<this.sourceKeyColsIndex.length; k++ ) {
			sourceKey+= String.valueOf( record.getField( this.sourceKeyColsIndex[k] ).getData() ).trim();	
		}
		LogFacade.getLog().debug( "sourceKey "+sourceKey );
		OpDAO opDao = new OpDAO();
		GenericDAO genericDAO = this.basicDAOFactory.getDaoUtils().getGenericDAO();
		FieldList fl = new FieldList( new FieldFactory() );
		try {
			if ( this.keyCheckList.contains( sourceKey ) ) {
				opDao.setSql( this.sqlUpdate );
				for ( int k=0; k<this.columns.length; k++ ) {
					ColMappingEntry cme = this.colMappingList.get( k );
					this.handleCME(cme, record, fl, genericDAO);
				}
				for ( int k=0; k<tableKeyCols.length; k++ ) {
					Object data = record.getField( this.getSourceKeyColsIndex()[k] ).getData();
					LogFacade.getLog().debug( "AnalyzeRecordHandler : data : "+data+" : "+data.getClass().getName() );
					fl.addField( data );
				}
				this.updateCount++;
			} else {
				opDao.setSql( this.sqlInsert );
				for ( int k=0; k<this.columns.length; k++ ) {
					ColMappingEntry cme = this.colMappingList.get( k );
					this.handleCME(cme, record, fl, genericDAO);
				}
				this.insertCount++;
			}
			opDao.setFieldList( fl );
			LogFacade.getLog().debug( "sql : "+opDao.getSql() );		
			genericDAO.update( opDao );
		} catch (DAOException e) {
			throw ( new DbsrcException( e ) );
		}
		this.counter++;
	}

}

class ColMappingEntry {
	
	public static final int MAPPING_SOURCE_INDEX = 1;
	
	public static final int MAPPING_FIXED_VALUE = 2;
	
	public static final int MAPPING_QUERY = 3;
	
	private String column;
	
	private int mappingType;
	
	private int mapTo;
	
	private String fixedValue;
	
	public String getColumn() {
		return column;
	}
	
	private String query;

	public String getQuery() {
		return query;
	}

	public ColMappingEntry( JdbcColumn columnDes, ConfigTag config ) throws Exception {
		this.column = columnDes.getName();
		ConfigTag columnConfig = config.getConfigMap().getConfig( this.column );
		String mappingType = columnConfig.getProps().getProperty( "mapping-type" );
		String mappingValue = columnConfig.getProps().getProperty( "mapping-value" );
		if ( "source-index".equalsIgnoreCase( mappingType ) ) {
			this.mapTo = Integer.parseInt( mappingValue );
			this.mappingType = MAPPING_SOURCE_INDEX;
		} else if ( "query".equalsIgnoreCase( mappingType ) ) {
			this.query = mappingValue;
			this.mappingType = MAPPING_QUERY;			
		} else if ( "fixed-value".equalsIgnoreCase( mappingType ) ) {
			this.fixedValue = mappingValue;
			this.mappingType = MAPPING_FIXED_VALUE;
		} else throw ( new ConfigException( "Unknown mapping type : "+mappingType+" for column : "+this.column ) );
	}	

	public String getFixedValue() {
		return fixedValue;
	}

	public int getMappingType() {
		return mappingType;
	}

	public int getMapTo() {
		return mapTo;
	}
	
}

class KeyRSE implements RSExtractor  {

	private String[] cols;
	
	public KeyRSE( String[] cols ) {
		this.cols = cols;
	}
	
	public Object extractNext(ResultSet rs) throws SQLException {
		StringBuffer buffer = new StringBuffer();
		for ( int k=0; k<cols.length; k++ ) {
			buffer.append( String.valueOf( rs.getObject( cols[k] ) ).trim() );	
		}
		return buffer.toString().trim();
	}
	
}

class ObjectRSE implements RSExtractor {

	public static final ObjectRSE DEFAULT = new ObjectRSE();
	
	public Object extractNext(ResultSet rs) throws SQLException {
		return rs.getObject( 1 );
	}
}