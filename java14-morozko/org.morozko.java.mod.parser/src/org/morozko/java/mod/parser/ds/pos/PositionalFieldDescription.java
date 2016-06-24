package org.morozko.java.mod.parser.ds.pos;

import org.morozko.java.mod.parser.model.FieldDescription;

public class PositionalFieldDescription implements FieldDescription {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private int startPosition;
	
	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private int length;
	
}
