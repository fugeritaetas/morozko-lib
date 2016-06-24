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
 * @(#)ListGetTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.list
 * @creation	: 08/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class ListGetTag extends ListTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770906082424855275L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.tld.list.ListTagHelper#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = EVAL_PAGE;
		List list = this.findList();
		TagUtilsHelper.setAttibute( this.pageContext, this.getToScope(), this.getId(), list.get( this.findIndex() ) );
		return result;
	}
	
}
