package org.morozko.java.mod.web.bean;

public class OptionBean {

	public static OptionBean newOption( String value ) {
		return new OptionBean( value, value );
	}
	
	public static OptionBean newOption( String value, String label ) {
		return new OptionBean( label, value );
	}
	
	private OptionBean(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	private String label;
	
	private String value;

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}
	
}
