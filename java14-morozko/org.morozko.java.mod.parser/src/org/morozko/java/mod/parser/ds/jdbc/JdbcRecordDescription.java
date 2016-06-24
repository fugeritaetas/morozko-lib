package org.morozko.java.mod.parser.ds.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.parser.model.RecordDescription;

public class JdbcRecordDescription implements RecordDescription {

	public JdbcRecordDescription() {
		this.fieldDescriptionList = new ArrayList<JdbcFieldDescription>();
	}
	
	public List<JdbcFieldDescription> getFieldDescriptionList() {
		return fieldDescriptionList;
	}
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private List<JdbcFieldDescription> fieldDescriptionList;
	
	public JdbcFieldDescription getFieldDescription( String id ) {
		JdbcFieldDescription field = null;
		for ( int k=0; field == null && k<this.getFieldDescriptionList().size(); k++ ) {
			JdbcFieldDescription current = this.getFieldDescriptionList().get( k );
			if ( current.getId().equals( id ) ) {
				field = current;
			}
		}
		return field;
	}
	

	
}
