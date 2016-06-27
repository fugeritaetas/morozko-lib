package org.morozko.java.mod.tools.dbex.qo;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.opencsv.CSVWriter;


public class QueryOutputFunCSV extends QueryOutputFun {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4881263802285769359L;

	private CSVWriter writer;
	
	public void start( OutputStream os ) throws Exception {
		OutputStreamWriter streamWriter = new OutputStreamWriter( os );
		this.writer = new CSVWriter( streamWriter , ';' );
	}

	public void end() throws Exception {
		this.writer.close();
	}

	public void outputRow( String[] row ) throws Exception {
		this.writer.writeNext( row );	
	}

	public void flush() throws Exception {
		this.writer.flush();
	}

}
