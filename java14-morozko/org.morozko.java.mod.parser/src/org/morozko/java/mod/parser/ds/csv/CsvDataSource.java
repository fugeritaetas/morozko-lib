package org.morozko.java.mod.parser.ds.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.ParserOutput;
import org.morozko.java.mod.parser.ds.ProcessInput;
import org.morozko.java.mod.parser.ds.ProcessOutput;
import org.morozko.java.mod.parser.ds.RecordIterator;
import org.morozko.java.mod.parser.ds.RenderInput;
import org.morozko.java.mod.parser.ds.RenderOutput;
import org.morozko.java.mod.parser.ds.helpers.AbstractDataSource;
import org.morozko.java.mod.parser.model.FieldModel;
import org.morozko.java.mod.parser.model.RecordModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csvreader.CsvWriter;

public class CsvDataSource extends AbstractDataSource {

	@Override
	public void configure(Element config) throws ParserFatalException {
		// TODO Auto-generated method stub

	}

	@Override
	public ParserOutput parse(ParserInput input) throws ParserFatalException {
		if ( true ) {
			throw new ParserFatalException( "Not implemented" );
		}
		return null;
	}

	@Override
	public ProcessOutput process(ProcessInput input) throws ParserFatalException {
		if ( true ) {
			throw new ParserFatalException( "Not implemented" );
		}
		return null;
	}

	@Override
	public RenderOutput render(RenderInput input) throws ParserFatalException {

		RenderOutput output = new RenderOutput();
		
		try {
			
			OutputStream stream = new FileOutputStream( new File( "C:/test.csv" ) );
			
			CsvWriter writer = new CsvWriter( stream, ';', Charset.defaultCharset() );
			
			RecordIterator ri = input.getRecords();
			ri.open();
			
			String[] blank = new String[0];
			if ( ri.hasNext() ) {
				RecordModel record = ri.getNext();
				Iterator<FieldModel> fields = record.getFields();
				ArrayList<String> current = new ArrayList<String>();
				ArrayList<String> head = new ArrayList<String>();
				while ( fields.hasNext() ) {
					FieldModel field = fields.next();
					head.add( field.getFieldDescription().getId() );
					current.add( field.getValue() );
				}
				writer.writeRecord( head.toArray( blank ) );
				writer.writeRecord( current.toArray( blank ) );
			}
			while ( ri.hasNext() ) {
				RecordModel record = ri.getNext();
				Iterator<FieldModel> fields = record.getFields();
				ArrayList<String> current = new ArrayList<String>();
				while ( fields.hasNext() ) {
					FieldModel field = fields.next();
					current.add( field.getValue() );
				}
				writer.writeRecord( current.toArray( blank ) );
			}
						
			ri.close();
			writer.close();
			stream.close();
			
			
		} catch (Exception e) {
			throw new ParserFatalException( e );
		}
		
		return output;
	}

}
