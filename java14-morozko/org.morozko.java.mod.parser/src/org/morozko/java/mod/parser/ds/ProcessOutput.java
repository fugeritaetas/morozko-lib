package org.morozko.java.mod.parser.ds;

import java.util.Properties;

public class ProcessOutput {

	private String output;
	
	private Properties parameters;

	public ProcessOutput(String output) {
		super();
		this.output = output;
		this.parameters = new Properties();
	}

	public Properties getParameters() {
		return parameters;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
}
