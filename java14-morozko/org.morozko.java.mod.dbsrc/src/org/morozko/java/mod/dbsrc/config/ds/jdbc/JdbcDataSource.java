package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOUtils;
import org.morozko.java.mod.db.dao.FieldFactory;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.BaseDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.config.generic.EmptyRecordIterator;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class JdbcDataSource extends BaseDataSource {
	
	private BasicDAOFactory basicDAOFactory;
	
	public void configure(ConfigTag config) throws ConfigException {
		Properties props = config.getProps();
		try {
			ConnectionFactory connectionFactory = ConnectionFactoryImpl.newInstance( props );
			this.basicDAOFactory = new BasicDAOFactory( connectionFactory );
		} catch (DAOException e) {
			throw ( new ConfigException( e ) );
		}
	}

	public RecordIterator getRecords(ConfigTag config) throws DbsrcException {
		Properties opProps = config.getProps();
		RecordIterator ri = null;
		String sqlop = opProps.getProperty( "sql-op" );
		String sqlquery = opProps.getProperty( "sql-query" );
		this.getLog().debug( "sql-op    : "+sqlop );
		this.getLog().debug( "sql-query : "+sqlquery );
		if ( "SELECT".equalsIgnoreCase( sqlop )  ) {
			DAOUtils daoUtils = this.basicDAOFactory.getDaoUtils();
			
			try {
				JdbcColumn[] columns = describe( sqlquery, this.basicDAOFactory.getConnectionFactory() );
				DbsrcRSE rse = DbsrcRSE.newRSE( columns );
				LoadResult lr = daoUtils.getGenericDAO().loadAllResult( sqlquery ,new FieldList( new FieldFactory() ), rse );
				ri = new JdbcRecordIterator( columns, lr );
			} catch (Exception e) {
				throw ( new DbsrcException( e ) );
			}
		} else if ( "UPDATE".equalsIgnoreCase( sqlop )  ) {
			ri = new EmptyRecordIterator();
			DAOUtils daoUtils = this.basicDAOFactory.getDaoUtils();
			try {
				int result = daoUtils.getGenericDAO().update( sqlquery );
				this.getLog().info( "UPDATE result : "+result );
			} catch (DAOException e) {
				throw ( new DbsrcException( e ) );
			}
		} else if ( "MULTI-UPDATE".equalsIgnoreCase( sqlop )  ) {
			ri = new EmptyRecordIterator();
			int counter = 1;
			String currentQuery = opProps.getProperty( "sql-query-"+counter );
			while ( currentQuery != null ) {
				DAOUtils daoUtils = this.basicDAOFactory.getDaoUtils();
				try {
					long startTime = System.currentTimeMillis();
					this.getLog().debug( "UPDATE query  n."+currentQuery );
					int result = daoUtils.getGenericDAO().update( currentQuery );
					long endTime = System.currentTimeMillis();
					this.getLog().info( "UPDATE result n."+counter+" : "+result+" ( "+(endTime-startTime)/1000+"."+(endTime-startTime)%1000+" )" );
				} catch (DAOException e) {
					throw ( new DbsrcException( e ) );
				}
				counter++;
				currentQuery = opProps.getProperty( "sql-query-"+counter );
			}
		}
		return ri;
	}
	
	private RecordHandler handleAnalyze( ConfigTag config ) throws Exception {
		Properties props = config.getProps();
		// setting source and table key mapping
		String tableName = props.getProperty( "table" );
		AnalyzeRecordHandler rh = new AnalyzeRecordHandler( config, this.basicDAOFactory, tableName );
		String sourceKey = props.getProperty( "source-key" );
		String tableKey = props.getProperty( "table-key" );
		String[] sourceKeyCols = sourceKey.split( ";" );
		String[] tableKeyCols = tableKey.split( ";" );
		int[] sourceKeyColIndex = new int[ sourceKeyCols.length ];
		for ( int k=0; k<sourceKeyColIndex.length; k++ ) {
			sourceKeyColIndex[k] = Integer.parseInt( sourceKeyCols[k] );
		}
		rh.setSourceKeyColsIndex( sourceKeyColIndex );
		rh.setTableKeyCols( tableKeyCols );
		// columns setup
		String describe = props.getProperty( "describe" );
		JdbcColumn[] columns  = describe( describe, this.basicDAOFactory.getConnectionFactory() );
		rh.setColumns( columns );
		// col mapping
		rh.setColMapping();		
		return rh;
	}
	
	public RecordHandler setRecords(ConfigTag config) throws DbsrcException {
		RecordHandler handler = null;
		Properties props = config.getProps();
		String mode = props.getProperty( "mode" );
		if ( "direct-insert".equalsIgnoreCase( mode ) ) {
			StringBuffer sql = new StringBuffer();
			try {
				String table = props.getProperty( "table" );
				String sqlTable = props.getProperty( "sql" );
				if ( sqlTable == null ) {
					sqlTable = " SELECT * FROM "+table;
				}
				this.getLog().info( "sql : "+sqlTable );
				JdbcColumn[] columns = describe( sqlTable, this.basicDAOFactory.getConnectionFactory() );
				sql.append( " INSERT INTO "+table+" ( "+columns[0].getName() );
				for ( int k=1; k<columns.length; k++ ) {
					sql.append( ", "+columns[k].getName() );
				}
				sql.append( " ) VALUES ( ? " );
				for ( int k=1; k<columns.length; k++ ) {
					sql.append( ", ? " );
				}
				sql.append( " ) " );		
				handler = new JdbcRecordHandler( config, columns, sql.toString(), this.basicDAOFactory );
			} catch (Exception e) {
				throw new DbsrcException( "update value : "+sql.toString(), e );
			}
		} else if ( "analyze".equalsIgnoreCase( mode ) ) {
			try {
				handler = handleAnalyze( config );
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbsrcException( e );
			}
		}
		return handler;
	}	
	
	public static JdbcColumn[] describe( String sql, ConnectionFactory cf ) throws SQLException {
		Connection conn = null;
		JdbcColumn[] columns = null;
		try {
			conn = cf.getConnection();
			sql = " SELECT v.* FROM ( "+sql+" ) v WHERE 1=0 ";
			sql = sql.replaceAll( "''", "'" );
			LogFacade.getLog().debug( "sql : "+sql );
			//ParamFinder pf = ParamFinder.newFinder();
			//sql = pf.substitute( sql , "ciao", "ciao" );
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			columns = describe( rs.getMetaData() );
			rs.close();
			stm.close();
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			if ( conn != null ) {
				conn.close();
			}
		}
		return columns;
	}
	
	public static JdbcColumn[] describe( ResultSetMetaData rsmd ) throws SQLException {
		JdbcColumn[] columns = new JdbcColumn[ rsmd.getColumnCount() ];
		for ( int k=0; k<rsmd.getColumnCount(); k++ ) {
			String name = rsmd.getColumnName( k+1 );
			int type = rsmd.getColumnType( k+1 );
			int dbsrcType;
			if ( type == Types.VARCHAR 
						|| type == Types.LONGVARCHAR
						|| type == Types.CHAR ) {
				dbsrcType = Field.TYPE_STRING;
			} else if ( type == Types.TIMESTAMP
						|| type == Types.TIME
						|| type == Types.DATE ) {
				dbsrcType = Field.TYPE_DATE;
			} else if ( type == Types.TINYINT
					|| type == Types.SMALLINT
					|| type == Types.INTEGER
					|| type == Types.BIGINT ) {
				dbsrcType = Field.TYPE_LONG;
			} else if ( type == Types.FLOAT
					|| type == Types.DOUBLE
					|| type == Types.NUMERIC
					|| type == Types.DECIMAL ) {
				dbsrcType = Field.TYPE_DOUBLE;
			} else {
				throw ( new SQLException( 
						"Undefinied mapping for jdbc type : "+
						type+" ( "+rsmd.getColumnTypeName( (k+1) )+" )" ) );
			}
			columns[k] = new JdbcColumn(
						name, dbsrcType, 
						rsmd.getColumnType( k+1 ),
						rsmd.getColumnTypeName( k+1 ),
						rsmd.getColumnDisplaySize( k+1 )
					);
		}
		return columns;
	}

}
