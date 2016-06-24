package org.morozko.java.mod.parser.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.mod.parser.model.ErrorDescriptor;
import org.morozko.java.mod.parser.model.FieldModel;
import org.morozko.java.mod.parser.model.ParserException;
import org.morozko.java.mod.parser.model.RecordDescription;
import org.morozko.java.mod.parser.model.RecordModel;

public class RecordModelImpl implements RecordModel {

	public RecordModelImpl() {
		this.listField = new ArrayList<FieldModel>();
		this.mapField = new HashMap<String, FieldModel>();
		this.recordErrors = new ArrayList<ErrorDescriptor>();
	}
	
	public String content;

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#getContent()
	 */
	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	private List<FieldModel> listField;
	
	private HashMap<String, FieldModel> mapField;
	
	private List<ErrorDescriptor> recordErrors;
	
	private RecordDescription recordDescription;
	
	public RecordDescription getRecordDescription() {
		return recordDescription;
	}

	public void setRecordDescription(RecordDescription recordDescription) {
		this.recordDescription = recordDescription;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#getFields()
	 */
	@Override
	public Iterator<FieldModel> getFields() {
		return new Iterator<FieldModel>() {
			private Iterator<FieldModel> wrapped = listField.iterator();
			private FieldModel current = null;
			@Override
			public boolean hasNext() {
				return this.wrapped.hasNext();
			}
			@Override
			public FieldModel next() {
				this.current = this.wrapped.next();
				return current;
			}
			@Override
			public void remove() {
				this.wrapped.remove();
				mapField.remove( current.getName() );
			}
		};
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#getField(java.lang.String)
	 */
	@Override
	public FieldModel getField( String name ) {
		return this.mapField.get( name );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#containsField(java.lang.String)
	 */
	@Override
	public boolean containsField( String name ) {
		return this.mapField.containsKey( name );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#remove(org.morozko.java.mod.parser.model.FieldModel)
	 */
	@Override
	public FieldModel remove( FieldModel field ) {
		FieldModel result = this.mapField.get( field.getName() );
		if ( result != null ) {
			this.listField.remove( result );
		}
		return result;
	}
	
	public List<ErrorDescriptor> getRecordErrors() {
		return recordErrors;
	}

	public List<ErrorDescriptor> getFieldErrors() {
		List<ErrorDescriptor> fieldErrors = new ArrayList<ErrorDescriptor>();
		Iterator<FieldModel> it = this.getFields();
		while ( it.hasNext() ) {
			fieldErrors.addAll( it.next().getErrors() );
		}
		return fieldErrors;
	}
	
	@Override
	public boolean isValid() {
		return this.getRecordErrors().isEmpty() && this.getFieldErrors().isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.RecordModel#addField(org.morozko.java.mod.parser.model.FieldModel)
	 */
	@Override
	public void addField( FieldModel field ) throws ParserException {
		String name = field.getName();
		if ( this.containsField( name ) ) {
			throw new ParserException( "Another field with the same name is already present : "+name );
		} else {
			this.mapField.put( name , field );
			this.listField.add( field );
		}
	}
	
}
