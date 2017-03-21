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
 * @(#)JspTag.java
 *
 * @project     : org.fugerit.java.core.doc
 * @package     : org.fugerit.java.core.doc.tlds
 * @creation	: 15/ago/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.doc.tlds;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

import org.fugerit.java.core.web.tld.helpers.TagSupportHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class JspTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4312310531274216168L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return super.doEndTag();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}

}


