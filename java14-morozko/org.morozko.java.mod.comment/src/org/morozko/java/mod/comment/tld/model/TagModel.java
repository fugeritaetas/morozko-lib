package org.morozko.java.mod.comment.tld.model;

import java.util.ArrayList;
import java.util.List;

public class TagModel {

	public TagModel() {
		this.attributeList = new ArrayList();
	}

	private String comment;
	
	private List attributeList;
	
	private String name;
	
	private String tagclass;
	
	private String bodycontent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagclass() {
		return tagclass;
	}

	public void setTagclass(String tagclass) {
		this.tagclass = tagclass;
	}

	public String getBodycontent() {
		return bodycontent;
	}

	public void setBodycontent(String bodycontent) {
		this.bodycontent = bodycontent;
	}

	public List getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List attributeList) {
		this.attributeList = attributeList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
