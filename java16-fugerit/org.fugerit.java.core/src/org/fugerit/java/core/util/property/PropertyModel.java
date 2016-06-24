package org.fugerit.java.core.util.property;

import java.io.Serializable;

public class PropertyModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3053915487540844538L;

	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PropertyModel(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
}
