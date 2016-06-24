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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.mod.navmap.NavEntry;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

public class NavNodeIsCurrent extends NavEntryTagSupportHelper {

	/**
	 * Tag definito per il riconoscimento del nodo corrente all'interno della NavMap
	 */
	private static final long serialVersionUID = 4051708016381425183L;
	
	public int doStartTag() throws JspException {
		NavEntry test = this.findNavEntry();
		boolean ok = false;
		try {
			ok = NavMap.getFromContext( this.pageContext.getServletContext() ).isCurrent( test, (HttpServletRequest)this.pageContext.getRequest() );
		} catch (Exception e ) {
			throw ( new JspException( e ) );
		}
		if ( ok ) { 
			this.pageContext.setAttribute( this.id, "true" );
		} else {
			this.pageContext.setAttribute( this.id, "false" );
		}
		return EVAL_PAGE;
	}

}
