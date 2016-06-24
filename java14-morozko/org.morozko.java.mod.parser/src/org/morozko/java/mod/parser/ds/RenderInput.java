package org.morozko.java.mod.parser.ds;

public class RenderInput {

	private String output;
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	private RecordIterator records;

	public RecordIterator getRecords() {
		return records;
	}

	public RenderInput(RecordIterator records) {
		super();
		this.records = records;
	}

	public void setRecords(RecordIterator records) {
		this.records = records;
	}
	
}
