/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2008 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)ListPositionTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.list
 * @creation	: 16/gen/08
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ListPositionTag extends ListTagHelper {

	private String compareString;
	
	private String containsName;
	
	private String containsProperty;
	
	private String containsValue;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2558799706842889786L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.tld.list.ListTagHelper#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = EVAL_PAGE;
		List list = this.findList();
		Object contains = findObject( this.getContainsName(), this.getContainsProperty(), this.getContainsValue() );
		TagUtilsHelper.setAttibute( this.pageContext, this.getToScope(), this.getId(), new Integer( list.indexOf( contains ) ) );
		return result;
	}

	/**
	 * @return the compareString
	 */
	public String getCompareString() {
		return compareString;
	}

	/**
	 * @param compareString the compareString to set
	 */
	public void setCompareString(String compareString) {
		this.compareString = compareString;
	}

	/**
	 * @return the containsName
	 */
	public String getContainsName() {
		return containsName;
	}

	/**
	 * @param containsName the containsName to set
	 */
	public void setContainsName(String containsName) {
		this.containsName = containsName;
	}

	/**
	 * @return the containsProperty
	 */
	public String getContainsProperty() {
		return containsProperty;
	}

	/**
	 * @param containsProperty the containsProperty to set
	 */
	public void setContainsProperty(String containsProperty) {
		this.containsProperty = containsProperty;
	}

	/**
	 * @return the containsValue
	 */
	public String getContainsValue() {
		return containsValue;
	}

	/**
	 * @param containsValue the containsValue to set
	 */
	public void setContainsValue(String containsValue) {
		this.containsValue = containsValue;
	}
	
}
