package org.morozko.java.mod.parser.ds.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

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
import org.w3c.dom.Element;

public class XmlDataSource extends AbstractDataSource {

	@Override
	public void configure(Element config) throws ParserFatalException {
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
		ProcessOutput output = null;
		return output;
	}

	private static String prepareTagName( String name ) {
		return name;
	}
	
	private static String prepareXmlAttribute( String fieldValue ) {
		StringBuffer result = new StringBuffer();
		for ( int k=0; k<fieldValue.length(); k++ ) {
			char c = fieldValue.charAt( k );
			if ( Character.isDigit( c ) || Character.isLetter( c ) || Character.isWhitespace( c )  ) {
				result.append( c );
			} else {
				result.append("&#" + (int) c + ";");
			}
		} 
//		result = result.replaceAll( "&" , "&amp;" );
//		result = result.replaceAll( "<" , "&lt;" );
//		result = result.replaceAll( ">" , "&gt;" );
//		result = result.replaceAll( "\"" , "&quot;" );
//		result = result.replaceAll( "/" , "&quot;" );
		return result.toString();
	}	
	
	@Override
	public RenderOutput render(RenderInput input) throws ParserFatalException {
		RenderOutput output = new RenderOutput();
		try {
			OutputStream stream = new FileOutputStream( new File( input.getOutput() ) );
			RecordIterator ri = input.getRecords();
			ri.open();
			String encoding = "iso-8859-15";
			XMLStreamWriter writer = XMLOutputFactory.newFactory().createXMLStreamWriter( stream, encoding );
			writer.writeStartDocument( encoding, "1.0" );
			writer.writeCharacters( ""+'\n' );
			writer.writeStartElement( prepareTagName( "metadata" ) );
			if ( ri.getMetadataDescription().getName() != null ) {
				writer.writeAttribute( prepareTagName( "name" ) , ri.getMetadataDescription().getName() );
			}
			writer.writeCharacters( ""+'\n' );
			while ( ri.hasNext() ) {
				RecordModel record = ri.getNext();
				writer.writeEmptyElement( prepareTagName( record.getRecordDescription().getId() ) );
				Iterator<FieldModel> fields = record.getFields();
				while ( fields.hasNext() ) {
					FieldModel field = fields.next();
					String fieldValue = field.getValue();
					if ( fieldValue == null ) {
						fieldValue = "";
					} else {
						fieldValue = fieldValue.trim();
					}
					writer.writeAttribute( prepareTagName( field.getName() ) , prepareXmlAttribute( fieldValue ) );
				}
				writer.writeCharacters( ""+'\n' );
			}
			writer.writeEndElement();
			writer.writeEndDocument();
			ri.close();
			writer.close();
			stream.close();
		} catch (Exception e) {
			throw new ParserFatalException( e );
		}
		return output;
	}

}

