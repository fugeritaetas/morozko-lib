package org.morozko.java.core.xml.config;

public class Property {
	
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

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
