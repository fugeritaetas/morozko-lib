package org.morozko.java.mod.parser.ds.pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.morozko.java.mod.parser.ds.BasicRecordIterator;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.RecordIterator;
import org.morozko.java.mod.parser.model.MetadataDescription;
import org.morozko.java.mod.parser.model.ParserException;
import org.morozko.java.mod.parser.model.RecordModel;
import org.morozko.java.mod.parser.model.impl.FieldModelImpl;
import org.morozko.java.mod.parser.model.impl.RecordModelImpl;

public class PositionalRecordIterator extends BasicRecordIterator implements RecordIterator {

	private ParserInput input;
	
	private PositionalDataSource dataSource;
	
	private BufferedReader reader;

	private PositionalMetadata metadata;

	@Override
	protected RecordModel findNextWorker() throws ParserFatalException {
		return this.findNextRecord();
	}

	public MetadataDescription getMetadataDescription() {
		return metadata;
	}

	int currentRow = 0;
	
	public PositionalRecordIterator(ParserInput input,
			PositionalDataSource dataSource) {
		super();
		this.input = input;
		this.dataSource = dataSource;
	}

	@Override
	public boolean open() throws ParserFatalException {
		this.reader = new BufferedReader( new InputStreamReader( input.getInputStream() ) );
		this.metadata = this.dataSource.getMetadata( this.input.getMetadata() );
		System.out.println( "METADATA : "+this.metadata );
		if ( metadata == null ) {
			throw new ParserFatalException( "Null metadata" );
		}
		return true;
	}

	private RecordModelImpl findNextRecord() throws ParserFatalException {
		RecordModelImpl record = null;
		try {
			String current = this.reader.readLine();
			this.currentRow++;
			if ( current != null ) {
				Iterator<PositionalRecordDescription> itDes = this.metadata.getRecordDescriptionList().iterator();
				while ( itDes.hasNext() ) {
					PositionalRecordDescription recordDes = itDes.next();
					PositionalFieldDescription keyField = recordDes.getFieldDescription( recordDes.getMatchField() );
					String value = current.substring( keyField.getStartPosition(), keyField.getStartPosition()+keyField.getLength() );
					//System.out.println( "value > "+value );
					if ( value.equals( recordDes.getMatchValue() ) ) {
						record = new RecordModelImpl();
						record.setRecordDescription( recordDes );
						for ( int k=0; k<recordDes.getFieldDescriptionList().size(); k++ ) {
							PositionalFieldDescription currentFieldDes = recordDes.getFieldDescriptionList().get( k );
							String idField = currentFieldDes.getId();
							String valueField = current.substring( currentFieldDes.getStartPosition(), currentFieldDes.getStartPosition()+currentFieldDes.getLength() );
							FieldModelImpl field = new FieldModelImpl( idField , valueField );
							try {
								record.addField( field );
								field.setFieldDescription( currentFieldDes );
							} catch (ParserException e) {
								throw new ParserFatalException( "Error on row "+this.currentRow , e );
							}
						}
					} else {
						record = findNextRecord();	
					}
				}				
			}
		} catch (Exception e) {
			throw new ParserFatalException( "Error on row "+this.currentRow , e );
		}
		return record;
	}


	@Override
	public boolean close() throws ParserFatalException {
		try {
			this.reader.close();
		} catch (IOException e) {
			throw new ParserFatalException( e );
		}
		return true;
	}


}
