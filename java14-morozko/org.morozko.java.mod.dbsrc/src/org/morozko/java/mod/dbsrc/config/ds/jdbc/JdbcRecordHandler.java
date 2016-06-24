package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.FieldFactory;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

public class JdbcRecordHandler extends BasicRecordHandler {
	
	private int counter;
	
	private JdbcColumn[] columns;
	
	private String update;
	
	private BasicDAOFactory basicDAOFactory;
	
	private int commitOn;
	
	private List<OpDAO> bufferOp;
	
	public JdbcRecordHandler(ConfigTag config, JdbcColumn[] columns, String update, BasicDAOFactory basicDAOFactory ) {
		super(config);
		this.columns = columns;
		this.update = update;
		this.basicDAOFactory = basicDAOFactory;
		this.counter = 0;
		this.commitOn = Integer.parseInt( config.getProps().getProperty( "commit-on", "1" ) );
		this.bufferOp = new ArrayList<OpDAO>( this.commitOn );
	}

	@Override
	public int release() throws DbsrcException {
		try {
			this.flushBuffer();
		} catch (DAOException e) {
			throw ( new DbsrcException( e ) );
		}
		return this.counter;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		try {
			FieldList fl = new FieldList( new FieldFactory() );
			LogFacade.getLog().debug( "record : "+record );
			for ( int k=0; k<record.getColumns().length; k++ ) {
				JdbcColumn col = columns[k];
				if ( col.getType() == Field.TYPE_STRING ) {
					fl.addField( record.getField( k ).getData(), Types.VARCHAR );
				} else if ( col.getType() == Field.TYPE_LONG ) {
					fl.addField( record.getField( k ).getData(), Types.BIGINT );
				} else if ( col.getType() == Field.TYPE_DOUBLE ) {
					fl.addField( record.getField( k ).getData(), Types.DOUBLE );
				} else if ( col.getType() == Field.TYPE_DATE ) {
					fl.addField( record.getField( k ).getData() , Types.TIMESTAMP );						
				} else {
					throw ( new DbsrcException( "Invalid mapping" ) );
				}
			}
			OpDAO opDAO = new OpDAO();
			opDAO.setSql( this.update.toString() );
			//LogFacade.getLog().info( "update sql : "+this.update );
			opDAO.setFieldList( fl );
			if ( this.commitOn > 1 ) {
				this.bufferOp.add( opDAO );
				if ( this.bufferOp.size() >= this.commitOn ) {
					this.flushBuffer();
				}
			} else {
				this.basicDAOFactory.getDaoUtils().getGenericDAO().update( opDAO );
				this.counter++;
			}
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
	}
	
	private void flushBuffer() throws DAOException {
		this.basicDAOFactory.getDaoUtils().getGenericDAO().updateTransaction( this.bufferOp );
		this.counter+= this.bufferOp.size();
		this.bufferOp.clear();
	}

}
