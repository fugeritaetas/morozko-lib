package org.morozko.java.mod.parser.model;

public class ErrorDescriptor {

	public static final int POSITION_NOTSET = -1;
	
	public ErrorDescriptor(String code, String description, ParserException exception) {
		super();
		this.code = code;
		this.description = description;
		this.exception = exception;
		this.line = POSITION_NOTSET;
		this.column = POSITION_NOTSET;
	}

	public void setPosition( int line, int column ) {
		this.line = line;
		this.column = column;
	}
	
	public ErrorDescriptor(String code, String description) {
		this( code, description, null );
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ParserException getException() {
		return exception;
	}

	public void setException(ParserException exception) {
		this.exception = exception;
	}

	private String code;
	
	private String description;
	
	private ParserException exception;
	
	private int line;
	
	private int column;

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
