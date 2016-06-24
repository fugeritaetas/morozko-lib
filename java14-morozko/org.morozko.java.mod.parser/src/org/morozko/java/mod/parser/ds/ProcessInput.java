package org.morozko.java.mod.parser.ds;

public class ProcessInput {

	private RecordIterator records;

	public RecordIterator getRecords() {
		return records;
	}

	public ProcessInput(RecordIterator records) {
		super();
		this.records = records;
	}

	public void setRecords(RecordIterator records) {
		this.records = records;
	}
	
}
