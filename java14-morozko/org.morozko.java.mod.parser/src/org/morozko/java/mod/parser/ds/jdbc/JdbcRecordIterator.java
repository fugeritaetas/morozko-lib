package org.morozko.java.mod.parser.ds.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.parser.ds.BasicRecordIterator;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.RecordIterator;
import org.morozko.java.mod.parser.model.MetadataDescription;
import org.morozko.java.mod.parser.model.RecordModel;
import org.morozko.java.mod.parser.model.impl.FieldModelImpl;
import org.morozko.java.mod.parser.model.impl.RecordModelImpl;

public class JdbcRecordIterator extends BasicRecordIterator implements RecordIterator {

	@Override
	protected RecordModel findNextWorker() throws ParserFatalException {
		// TODO Auto-generated method stub
		return null;
	}

	private ParserInput input;
	
	private JdbcDataSource dataSource;
	
	private JdbcMetadata metadata;
	
	private int currentRecord;

	private Connection conn;
	
	private Statement stm;
	
	private ResultSet rs;
	
	public MetadataDescription getMetadataDescription() {
		return metadata;
	}

	int currentRow = 0;
	
	public JdbcRecordIterator(ParserInput input,
			JdbcDataSource dataSource) {
		super();
		this.input = input;
		this.dataSource = dataSource;
		this.currentRecord = 0;
	}

	@Override
	public boolean open() throws ParserFatalException {
		this.metadata = this.dataSource.getMetadata( this.input.getMetadata() );
		System.out.println( "METADATA : "+this.metadata );
		if ( metadata == null ) {
			throw new ParserFatalException( "Null metadata" );
		} else {
			try {
				ConnectionFactory cf = this.metadata.getConnectionFactory();  
				this.conn = cf.getConnection();
				this.stm = conn.createStatement();
				this.rs = stm.executeQuery( this.metadata.getSql() );
			} catch (Exception e) {
				throw new ParserFatalException( "Failed to get connection", e );
			}
			
		}
		return true;
	}
	
	@Override
	public boolean hasNext() throws ParserFatalException {
		boolean next = false;
		this.currentRecord++;
		try {
			next = rs.next();
		} catch (Exception e) {
			throw new ParserFatalException( "Failed to manage connection", e );
		}
		return next;
	}

	@Override
	public RecordModel getNext() throws ParserFatalException {
		RecordModelImpl record = new RecordModelImpl();
		JdbcRecordDescription recordDes = this.metadata.getRecordDescriptionList().get( 0 );
		record.setRecordDescription( recordDes );
		for ( int k=0; k<recordDes.getFieldDescriptionList().size(); k++ ) {
			JdbcFieldDescription fieldDes = recordDes.getFieldDescriptionList().get( k );
			try {
				String fieldName = fieldDes.getId();
				String fieldValue = rs.getString( fieldName );
				if ( fieldValue == null ) {
					fieldValue = "";
				}
				FieldModelImpl field = new FieldModelImpl( fieldName.toLowerCase(), fieldValue );
				record.addField( field );
				field.setFieldDescription( fieldDes );
			} catch (Exception e) {
				throw new ParserFatalException( "Error on row "+this.currentRow , e );
			}
		}
		return record;
	}

	@Override
	public boolean close() throws ParserFatalException {
		try {
			this.rs.close();
			this.stm.close();
			this.conn.close();
		} catch (Exception e) {
			throw new ParserFatalException( "Failed to manage connection", e );
		}
		return true;
	}


}
