package org.morozko.java.mod.tools.dbex.qo;

import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.morozko.java.core.log.BasicLogObject;

public abstract class QueryOutputFun extends BasicLogObject implements Serializable {
	
	private int rowCount;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4056010493180829297L;

	public abstract void start( OutputStream os ) throws Exception;
	
	public abstract void end() throws Exception;
	
	public abstract void outputRow( String[] row ) throws Exception;
	
	public void flush() throws Exception {
		
	}
	
	private void prepare() {
		this.rowCount = 0;
	}
	
	public void output( Connection conn, String sql, OutputStream os ) throws Exception {
		this.prepare();
		this.start( os );
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			String[] buffer = new String[ cols ];
			for ( int k=0; k<cols; k++ ) {
				buffer[k] = rsmd.getColumnLabel( (k+1) );
			}
			this.outputRow( buffer );
			this.rowCount++;
			while ( rs.next() ) {
				for ( int k=0; k<cols; k++ ) {
					String v = String.valueOf( rs.getObject( (k+1) ) );
					buffer[k] = v;
				}
				this.outputRow( buffer );
				this.rowCount++;
				if ( this.rowCount % 10000 == 0 ) {
					this.flush();
				}
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.end();
			conn.close();
		}
	}

	public int getCurrentRow() {
		return rowCount;
	}
	
	
	
}
