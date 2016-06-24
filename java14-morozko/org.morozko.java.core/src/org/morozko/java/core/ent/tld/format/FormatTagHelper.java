/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)FormatTagHelper.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.format
 * @creation	: 04/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.format;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class FormatTagHelper extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7842456998200293629L;

	public String getFormat() throws JspException {
		return (String)findObject( this.getFormatName() , this.getFormatProperty(), this.getFormatValue() );
	}
	
	public Object getObject() throws JspException {
		return (Object)findObject( this.getName() , this.getProperty(), null );
	}	
		
	private String formatName;
	
	private String formatProperty;
	
	private String formatValue;

	/**
	 * @return the formatName
	 */
	public String getFormatName() {
		return formatName;
	}

	/**
	 * @param formatName the formatName to set
	 */
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	/**
	 * @return the formatProperty
	 */
	public String getFormatProperty() {
		return formatProperty;
	}

	/**
	 * @param formatProperty the formatProperty to set
	 */
	public void setFormatProperty(String formatProperty) {
		this.formatProperty = formatProperty;
	}

	/**
	 * @return the formatValue
	 */
	public String getFormatValue() {
		return formatValue;
	}

	/**
	 * @param formatValue the formatValue to set
	 */
	public void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}
	
}
