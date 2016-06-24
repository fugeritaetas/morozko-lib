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
 * @(#)SumTag.java
 *
 * @project     : org.morozko.java.mod.tld.math
 * @package     : org.morozko.java.mod.tld.math
 * @creation	: 12/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.tld.math;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class OperationTag extends MathSupportTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913952612655582441L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Double l1 = this.getFistDouble();
		Double l2 = this.getSecondDouble();
		Number r = null;
		if ( "sum".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()+l2.doubleValue() );
		} else if ( "mul".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()*l2.doubleValue() );
		} else if ( "div".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()/l2.doubleValue() );
		} else if ( "dif".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()-l2.doubleValue() );			
		} else if ( "res".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()%l2.doubleValue() );					
		} else if ( "perc".equalsIgnoreCase( this.getOperation() ) ) {
			r = new Double( l1.doubleValue()/l2.doubleValue()*100 );
		}
		if ( "integer".equalsIgnoreCase( this.getType() ) ) {
			r = new Integer( r.intValue() );
		} else if ( "long".equalsIgnoreCase( this.getType() ) ) {
			r = new Long( r.longValue() );
		} else if ( "short".equalsIgnoreCase( this.getType() ) ) {
			r = new Short( r.shortValue() );
		} else if ( "byte".equalsIgnoreCase( this.getType() ) ) {
			r = new Byte( r.byteValue() );			
		} else if ( "float".equalsIgnoreCase( this.getType() ) ) {
			r = new Float( r.floatValue() );			
		}
		TagUtilsHelper.setAttibute( this.pageContext , this.getToScope() , this.getResult() , r );
		return EVAL_PAGE;
	}

	private String result;
	
	private String operation;

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
}
