package org.morozko.java.mod.tools.dbex.qo;

import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.NumberFormat;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.log.LogFacade;

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
	
	public static final int DEFAULT_FLUSH_FRAME = 10*1000;
	
	private void prepare() {
		this.rowCount = 0;
	}
	
	private static String format( long number ) {
		return NumberFormat.getInstance().format( number );
	}
	
	public void output( Connection conn, String sql, OutputStream os ) throws Exception {
		this.output( conn, sql, os, DEFAULT_FLUSH_FRAME );
	}
	
	public void output( Connection conn, String sql, OutputStream os, int flushFrame ) throws Exception {
		this.prepare();
		this.start( os );
		long time = System.currentTimeMillis();
		long newTime = time;
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
				if ( this.rowCount % flushFrame == 0 ) {
					long oldTime = newTime;
					newTime = System.currentTimeMillis();
					this.flush();
					os.flush();
					LogFacade.getLog().info( "row count : "+format( rowCount )+", frame time (ms) : "+format( (newTime-oldTime) )+", total time (ms) : "+format( (newTime-time) ) );
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
