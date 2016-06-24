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

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

public class NavEntryUrl extends NavEntryTagSupportHelper {
	
	private String parameter;
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2622968657829392810L;

	/**
	 * Tag definito per la visualizzazione di un URL HTML in un NavEntry
	 */
	public int doStartTag() throws JspException {
		NavNode navNode = this.findNavNode();
		boolean params = ( this.getParameter() == null || "true".equalsIgnoreCase( this.getParameter() ) );
		String href = this.createRealLink( navNode, params );
		try {
			this.pageContext.getOut().println( href );
		} catch (IOException e) {}		
		return EVAL_PAGE;
	}
	
	
}
