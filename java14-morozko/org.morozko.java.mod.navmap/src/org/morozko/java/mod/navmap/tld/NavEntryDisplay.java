/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
package org.morozko.java.mod.navmap.tld;

import javax.servlet.jsp.JspException;

import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

/**
 *
 * @author asacca
 */
public class NavEntryDisplay extends NavEntryTagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7751137991378802428L;

	/**
	 * Tag definito per la visualizzazione del titolo di una pagina HTML in un NavEntry
	 */
	public int doStartTag() throws JspException {
		this.println( this.getDisplay() );
		return EVAL_PAGE;
	}
	

}
