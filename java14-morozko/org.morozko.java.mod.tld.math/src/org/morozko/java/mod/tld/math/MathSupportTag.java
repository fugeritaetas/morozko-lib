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
 * @(#)MathSupportTag.java
 *
 * @project     : org.morozko.java.mod.tld.math
 * @package     : org.morozko.java.mod.tld.math
 * @creation	: 12/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.tld.math;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class MathSupportTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913952612655582441L;
	
	
	public Double getValue( String name, String property, String value ) throws JspException {
		Double d = null;
		Object o = findObject( name , property, value );
		if ( o instanceof java.lang.Number ) {
			d = new Double( ((Number)o).doubleValue() );
		} else if ( o != null ) {
			try {
				d = Double.valueOf( String.valueOf( o ) );	
			} catch ( NumberFormatException nfe ) {
				throw new JspException( "Number retrivial error : "+o, nfe );
			}
		} else {
			throw new JspException( "Number retrivial error : null value" );
		} 
		return d;
	}
	
	public Double getFistDouble() throws JspException {
		return getValue( this.getName(), this.getProperty(), this.getValue() );
	}
	
	public Double getSecondDouble() throws JspException {
		return getValue( this.getNameTo(), this.getPropertyTo(), this.getValueTo() );
	}		
	
	private String nameTo;
	
	private String propertyTo;
	
	private String valueTo;
	
	private String value;
	
	private String type;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the nameTo
	 */
	public String getNameTo() {
		return nameTo;
	}

	/**
	 * @param nameTo the nameTo to set
	 */
	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	/**
	 * @return the propertyTo
	 */
	public String getPropertyTo() {
		return propertyTo;
	}

	/**
	 * @param propertyTo the propertyTo to set
	 */
	public void setPropertyTo(String propertyTo) {
		this.propertyTo = propertyTo;
	}

	/**
	 * @return the valueTo
	 */
	public String getValueTo() {
		return valueTo;
	}

	/**
	 * @param valueTo the valueTo to set
	 */
	public void setValueTo(String valueTo) {
		this.valueTo = valueTo;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
