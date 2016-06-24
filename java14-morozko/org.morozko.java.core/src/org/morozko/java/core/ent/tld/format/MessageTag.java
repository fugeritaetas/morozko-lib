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
 * @(#)MessageTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.format
 * @creation	: 04/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.format;

import java.text.MessageFormat;

import javax.servlet.jsp.JspException;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class MessageTag extends FormatTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8844312130138802470L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		MessageFormat format = new MessageFormat( this.getFormat() );
		Object[] args = { this.getObject() };
		this.print( format.format( args ) );
		return EVAL_PAGE;
	}

}
