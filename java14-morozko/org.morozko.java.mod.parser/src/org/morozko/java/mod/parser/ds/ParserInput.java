package org.morozko.java.mod.parser.ds;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ParserInput {

	public ParserInput( String input, String metadata ) {
		super();
		this.input = input;
		this.metadata = metadata;
		this.parameters = new Properties();
	}

	private String input;
	
	private String metadata;
	
	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	private Properties parameters;

	public Properties getParameters() {
		return parameters;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	public InputStream getInputStream() throws ParserFatalException {
		InputStream is = null;
		try {
			is = new FileInputStream( this.getInput() );
		} catch (Exception e) {
			throw new ParserFatalException( e );
		}
		return is;
	}
	
}
