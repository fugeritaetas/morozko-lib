package org.morozko.java.mod.codegen;

import java.util.ArrayList;
import java.util.List;

public class NavForm {

	public static NavForm newNavForm( String name ) {
		return new NavForm( name );
	}
	
	public NavForm(String name) {
		this.fieldList = new ArrayList<NavFormField>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public List<NavFormField> getFieldList() {
		return fieldList;
	}

	private List<NavFormField> fieldList;
	
	private String name;
	
	private String daogen;

	public String getDaogen() {
		return daogen;
	}

	public void setDaogen(String daogen) {
		this.daogen = daogen;
	}
	
}
