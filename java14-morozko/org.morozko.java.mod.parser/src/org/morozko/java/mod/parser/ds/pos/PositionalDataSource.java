package org.morozko.java.mod.parser.ds.pos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.ParserOutput;
import org.morozko.java.mod.parser.ds.ProcessInput;
import org.morozko.java.mod.parser.ds.ProcessOutput;
import org.morozko.java.mod.parser.ds.RenderInput;
import org.morozko.java.mod.parser.ds.RenderOutput;
import org.morozko.java.mod.parser.ds.helpers.AbstractDataSource;
import org.morozko.java.mod.parser.facade.ConfigReader;
import org.w3c.dom.Element;

public class PositionalDataSource extends AbstractDataSource {

	public PositionalDataSource() {
		this.metadataMap = new HashMap<String, PositionalMetadata>();
	}
	
	private HashMap<String, PositionalMetadata> metadataMap;
	
	@Override
	public void configure(Element config) throws ParserFatalException {
		this.getLog().info( "config> start" );
		List<Element> metadataTagList = ConfigReader.SEARCH_DOM.findAllTags( config , "metadata" );
		Iterator<Element> metadataTagIt = metadataTagList.iterator();
		while ( metadataTagIt.hasNext() ) {
			Element currentMetadataTag = metadataTagIt.next();
			Properties currentMetadataProps = DOMUtils.attributesToProperties( currentMetadataTag );
			String currentMetadataId = currentMetadataProps.getProperty( "id" );
			this.getLog().info( "metadata : "+currentMetadataId );
			PositionalMetadata metadata = new PositionalMetadata();
			metadata.setId( currentMetadataId );
			this.metadataMap.put( currentMetadataId , metadata );
			List<Element> recordTagList = ConfigReader.SEARCH_DOM.findAllTags( currentMetadataTag , "record-description" );
			Iterator<Element> recordTagIt = recordTagList.iterator();
			while ( recordTagIt.hasNext() ) {
				Element currentRecordTag = recordTagIt.next();
				int currentRecordLength = 0;
				Properties currentRecordProps = DOMUtils.attributesToProperties( currentRecordTag );
				String currentRecordId = currentRecordProps.getProperty( "id" );
				this.getLog().info( "record : "+currentRecordId );
				PositionalRecordDescription record = new PositionalRecordDescription();
				record.setId( currentRecordId );
				record.setRecordLength( Integer.parseInt( currentRecordProps.getProperty( "recordLength" ) ) );
				record.setMatchField( currentRecordProps.getProperty( "matchField" ) );
				record.setMatchValue( currentRecordProps.getProperty( "matchValue" ) );
				metadata.getRecordDescriptionList().add( record );
				List<Element> fieldTagList = ConfigReader.SEARCH_DOM.findAllTags( currentRecordTag , "field-description" );
				Iterator<Element> fieldTagIt = fieldTagList.iterator();
				while ( fieldTagIt.hasNext() ) {
					Element currentFieldTag = fieldTagIt.next();
					Properties currentFieldProps = DOMUtils.attributesToProperties( currentFieldTag );
					String currentFieldId = currentFieldProps.getProperty( "id" );
					this.getLog().info( "field : "+currentFieldId );
					PositionalFieldDescription field = new PositionalFieldDescription();
					field.setId( currentFieldId );
					record.getFieldDescriptionList().add( field );
					int fieldLength = Integer.parseInt( currentFieldProps.getProperty( "length" ) );
					field.setLength( fieldLength );
					field.setStartPosition( currentRecordLength );
					currentRecordLength+= fieldLength;
				}
				this.getLog().info( "check record length : "+currentRecordLength+" / "+record.getRecordLength() );
				if ( currentRecordLength != record.getRecordLength() ) {
					throw new ParserFatalException( "Invalid record length configuration" );
				}
			}
		}
		this.getLog().info( "config> end" );
	}

	public PositionalMetadata getMetadata( String id ) {
		return this.metadataMap.get( id );
	}
	
	@Override
	public ParserOutput parse( ParserInput input ) throws ParserFatalException {
		PositionalRecordIterator pri = new PositionalRecordIterator( input, this );
		ParserOutput output = new ParserOutput( pri );
		return output;
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
		if ( true ) {
			throw new ParserFatalException( "Not implemented" );
		}
		return null;
	}	

}
