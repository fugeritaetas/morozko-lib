package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordIterator;
import org.morozko.java.mod.dbsrc.data.Record;

public class JdbcRecordIterator extends BasicRecordIterator {

	private LoadResult lr;
	
	public JdbcRecordIterator( JdbcColumn[] columns, LoadResult lr ) {
		super( columns );
		this.lr = lr;
	}
	
	public boolean end() throws DbsrcException {
		boolean ok = true;
		try {
			lr.end();
		} catch (DAOException e) {
			throw new DbsrcException( e );
		}
		return ok;
	}

	public boolean hasNext() throws DbsrcException {
		boolean ok = true;
		try {
			ok = lr.hasNext();
		} catch (DAOException e) {
			throw new DbsrcException( e );
		}
		return ok;
	}

	public Record next() throws DbsrcException {
		Record record = null;
		try {
			record = (Record) lr.getNext();
		} catch (DAOException e) {
			throw new DbsrcException( e.getMessage(), e );
		}		
		return record;
	}

	public boolean start() throws DbsrcException {
		boolean ok = true;
		try {
			lr.start();
		} catch (DAOException e) {
			throw new DbsrcException( e );
		}		
		return ok;
	}

}
