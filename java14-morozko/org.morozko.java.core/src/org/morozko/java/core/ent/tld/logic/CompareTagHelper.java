/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

	Copyright (c) 2006 Morozko

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
 * @(#)CompareTagHelper.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.logic
 * @creation   : 12-mag-2006
 */
package org.morozko.java.core.ent.tld.logic;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class CompareTagHelper extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146044516562693529L;

	private String compareString;
	
	private String nameTo;
	
	private String propertyTo;

	/**
	 * @return Restituisce il valore di compareString.
	 */
	public String getCompareString() {
		return compareString;
	}

	/**
	 * @param compareString il valore di compareString da impostare.
	 */
	public void setCompareString(String compareString) {
		this.compareString = compareString;
	}

	/**
	 * @return Restituisce il valore di nameTo.
	 */
	public String getNameTo() {
		return nameTo;
	}

	/**
	 * @param nameTo il valore di nameTo da impostare.
	 */
	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	/**
	 * @return Restituisce il valore di propertyTo.
	 */
	public String getPropertyTo() {
		return propertyTo;
	}

	/**
	 * @param propertyTo il valore di propertyTo da impostare.
	 */
	public void setPropertyTo(String propertyTo) {
		this.propertyTo = propertyTo;
	}
	
	protected boolean compare() throws JspException {
		boolean result = false;
		Object o1 = this.getObject1();
		Object o2 = this.getObject2();
		if ( "true".equalsIgnoreCase( this.getCompareString() ) ) {
			o1 = String.valueOf( o1 );
			o2 = String.valueOf( o2 );
		}
		if ( o1.equals( o2 ) ) {
			result = true;
		}
		return result;
	}	

	protected Object getObject1() throws JspException {
		return TagUtilsHelper.findAttibute( this.pageContext, this.getScope(), this.getName(), this.getProperty() );
	}
	
	protected Object getObject2() throws JspException {
		return TagUtilsHelper.findAttibute( this.pageContext, this.getScope(), this.getNameTo(), this.getPropertyTo() );
	}	

	protected int compareNumbers() throws JspException {
		int result = 0;
		String s1 = String.valueOf( this.getObject1() );
		String s2 = String.valueOf( this.getObject2() );
		Double d1 = Double.valueOf( s1 );
		Double d2 = Double.valueOf( s2 );
		if ( d1.doubleValue()>d2.doubleValue() ) {
			result = 1;
		} else if ( d1.doubleValue()<d2.doubleValue() ) {
			result = -1;
		}
		return result;
	}
	
}
