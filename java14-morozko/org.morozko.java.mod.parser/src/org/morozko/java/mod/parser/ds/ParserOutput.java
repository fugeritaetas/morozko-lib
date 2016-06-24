package org.morozko.java.mod.parser.ds;

public class ParserOutput {

	private RecordIterator records;

	public RecordIterator getRecords() {
		return records;
	}

	public ParserOutput(RecordIterator records) {
		super();
		this.records = records;
	}

	public void setRecords(RecordIterator records) {
		this.records = records;
	}
	
}
