package org.morozko.java.mod.parser.ds.pos;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.parser.model.MetadataDescription;

public class PositionalMetadata implements MetadataDescription {

	public PositionalMetadata() {
		this.recordDescriptionList = new ArrayList<PositionalRecordDescription>();
	}
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private List<PositionalRecordDescription> recordDescriptionList;

	public List<PositionalRecordDescription> getRecordDescriptionList() {
		return recordDescriptionList;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
