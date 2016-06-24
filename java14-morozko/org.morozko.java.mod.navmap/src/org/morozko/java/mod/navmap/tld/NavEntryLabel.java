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
 * @(#)NavEntryLabel.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap.tld
 * @creation	: 11/set/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap.tld;

import javax.servlet.jsp.JspException;

import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavEntryLabel extends NavEntryTagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2788196448040362347L;

	/**
	 * Tag definito per la visualizzazione del titolo di una pagina HTML in un NavEntry
	 */
	public int doStartTag() throws JspException {
		this.println( this.getLabel() );
		return EVAL_PAGE;
	}
	
}
