package org.morozko.java.mod.ws.manager.config;

import java.io.Serializable;

public class InfoResult implements Serializable {

	public InfoResult(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5937427971447588654L;

	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
