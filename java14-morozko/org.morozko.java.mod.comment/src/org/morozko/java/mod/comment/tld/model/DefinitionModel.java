package org.morozko.java.mod.comment.tld.model;

import java.util.ArrayList;
import java.util.List;

public class DefinitionModel {

	public DefinitionModel() {
		this.tagList = new ArrayList();
	}
	
	private String comment;
	
	private List tagList;
	
	private String tlibversion;
	
	private String jspversion;
	
	private String shortname;
	
	private String uri;

	public String getTlibversion() {
		return tlibversion;
	}

	public void setTlibversion(String tlibversion) {
		this.tlibversion = tlibversion;
	}

	public String getJspversion() {
		return jspversion;
	}

	public void setJspversion(String jspversion) {
		this.jspversion = jspversion;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List getTagList() {
		return tagList;
	}

	public void setTagList(List tagList) {
		this.tagList = tagList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
