package org.morozko.java.mod.comment.tld.model;

public class AttributeModel {

	private String comment;
	
	private String name;
	
	private String required;
	
	private String rtexprvalue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getRtexprvalue() {
		return rtexprvalue;
	}

	public void setRtexprvalue(String rtexprvalue) {
		this.rtexprvalue = rtexprvalue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
