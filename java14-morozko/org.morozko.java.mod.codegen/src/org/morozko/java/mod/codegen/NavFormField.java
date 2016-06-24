package org.morozko.java.mod.codegen;

public class NavFormField {

	private String name;
	
	private String type;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	private NavFormField(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public static NavFormField newField( String name, String type ) {
		return new NavFormField( name, type );
	}
	
}
