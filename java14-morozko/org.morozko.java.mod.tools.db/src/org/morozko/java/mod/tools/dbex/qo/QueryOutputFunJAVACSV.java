package org.morozko.java.mod.tools.dbex.qo;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.csvreader.CsvWriter;


public class QueryOutputFunJAVACSV extends QueryOutputFun {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4881263802285769359L;

	private CsvWriter writer;
	
	public void start( OutputStream os ) throws Exception {
		OutputStreamWriter streamWriter = new OutputStreamWriter( os );
		this.writer = new CsvWriter( streamWriter , ';' );
	}

	public void end() throws Exception {
		this.writer.close();
	}

	public void outputRow( String[] row ) throws Exception {
		this.writer.writeRecord( row );	
	}

	public void flush() throws Exception {
		this.writer.flush();
	}

}
