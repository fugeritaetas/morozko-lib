package org.morozko.java.mod.parser.ds.pos;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.parser.model.RecordDescription;

public class PositionalRecordDescription implements RecordDescription {

	public PositionalRecordDescription() {
		this.fieldDescriptionList = new ArrayList<PositionalFieldDescription>();
	}
	
	public List<PositionalFieldDescription> getFieldDescriptionList() {
		return fieldDescriptionList;
	}

	private int recordLength;
	
	public int getRecordLength() {
		return recordLength;
	}

	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String matchField;
	
	private String matchValue;
	
	public String getMatchField() {
		return matchField;
	}

	public void setMatchField(String matchField) {
		this.matchField = matchField;
	}

	public String getMatchValue() {
		return matchValue;
	}

	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
	}

	private List<PositionalFieldDescription> fieldDescriptionList;
	
	public PositionalFieldDescription getFieldDescription( String id ) {
		PositionalFieldDescription field = null;
		for ( int k=0; field == null && k<this.getFieldDescriptionList().size(); k++ ) {
			PositionalFieldDescription current = this.getFieldDescriptionList().get( k );
			if ( current.getId().equals( id ) ) {
				field = current;
			}
		}
		return field;
	}
	
}
