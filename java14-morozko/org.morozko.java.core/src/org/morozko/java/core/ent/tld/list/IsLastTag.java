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
 * @(#)IsLastTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.list
 * @creation	: 28/ago/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class IsLastTag extends ListTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984349218041493686L;

	private String not;
	

	/**
	 * @return the not
	 */
	public String getNot() {
		return not;
	}

	/**
	 * @param not the not to set
	 */
	public void setNot(String not) {
		this.not = not;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = EVAL_PAGE;
		List list = this.findList();
		Object element = this.findElement();
		boolean notCheck = Boolean.valueOf( this.getNot() ).booleanValue();
		boolean mainCheck = list.get( list.size()-1 ).equals( element );
		if ( notCheck ) {
			mainCheck = !mainCheck;
		}
		if ( !mainCheck ) {
			result = SKIP_BODY;
		}
		return result;
	}
	
}
