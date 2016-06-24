package org.morozko.java.mod.parser.ds;

import org.morozko.java.mod.parser.filter.FilterChain;
import org.morozko.java.mod.parser.model.RecordModel;

public abstract class BasicRecordIterator implements RecordIterator {

	@Override
	public int getCountSkipped() {
		return this.countSkipped;
	}

	@Override
	public int getCountTotal() {
		return this.countTotal;
	}

	@Override
	public int getCountAccepted() {
		return this.getCountTotal()-this.getCountSkipped();
	}

	private int countTotal;
	
	private int countSkipped;
	
	public BasicRecordIterator() {
		this.currentRecord = null;
		this.countSkipped = 0;
		this.countTotal = 0;
	}
	
	private FilterChain recordFilterChain;

	public FilterChain getRecordFilterChain() {
		return recordFilterChain;
	}

	public void setRecordFilterChain(FilterChain recordFilterChain) {
		this.recordFilterChain = recordFilterChain;
	}
	
	private RecordModel currentRecord;
	
	protected abstract RecordModel findNextWorker() throws ParserFatalException;

	@Override
	public boolean hasNext() throws ParserFatalException {
		RecordModel checkRecord = findNextWorker();
		if ( this.getRecordFilterChain() != null ) {
			while ( checkRecord != null && !this.getRecordFilterChain().accept( checkRecord ) ) {
				checkRecord = findNextWorker();
				this.countSkipped++;
				this.countTotal++;
			}
		}
		if ( checkRecord != null ) {
			this.countTotal++;
		}
		this.currentRecord = checkRecord;
		return this.currentRecord != null;
	}

	@Override
	public RecordModel getNext() throws ParserFatalException {
		return this.currentRecord;
	}

	
}

