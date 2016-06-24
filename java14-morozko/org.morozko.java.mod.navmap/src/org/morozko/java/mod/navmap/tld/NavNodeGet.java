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

import org.morozko.java.mod.navmap.NavEntry;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

public class NavNodeGet extends NavEntryTagSupportHelper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2735250150835507314L;

	/**
	 * Tag definito per la visualizzazione di un NavEntry 
	 */
	public int doStartTag() throws JspException {
		NavEntry navEntry = this.findNavEntry();
		NavMap map = NavMap.getFromContext( this.pageContext.getServletContext() );
		NavNode navNode = map.getNavNodeByNavUrl( navEntry.getUrl() );
		this.pageContext.setAttribute( this.id , navNode );
		return EVAL_PAGE;
	}
	

}
