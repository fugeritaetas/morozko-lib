package org.morozko.java.mod.parser.ds.helpers;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.parser.ds.DataSource;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.ProcessResult;
import org.morozko.java.mod.parser.ds.RecordIterator;
import org.w3c.dom.Element;

public abstract class AbstractDataSource extends BasicLogObject implements DataSource {

	public AbstractDataSource() {
		
	}
	
	public void setId(String id) {
		this.id = id;
	}

	private String id;
	
	@Override
	public String getId() {
		return this.id;
	}


}
