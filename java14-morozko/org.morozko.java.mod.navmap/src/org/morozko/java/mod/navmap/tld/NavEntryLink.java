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
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

public class NavEntryLink extends NavEntryTagSupportHelper {

	private String target;
	
	private String parameter;
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 *  Tag definito per la renderizzazione di un link HTML in un NavEntry
	 */
	private static final long serialVersionUID = -665604548318109931L;

	public int doStartTag() throws JspException {
		//NavNode navNode = this.findNavNode();
		NavEntry navEntry = this.findNavEntry();
		String target = "";
		
		if (  navEntry.getTarget() != null ) {
			target=" target='"+navEntry.getTarget()+"' ";
		}
		
		if (  this.getTarget() != null ) {
			target=" target='"+this.getTarget()+"' ";
		}
		
		String link = this.getDisplay( navEntry );
		if ( navEntry.getRenderLink().booleanValue()  ) {
			NavNode navNode = this.findNavNode();
			if ( navNode != null ) {
				String href = this.createRealLink( navNode, "true".equalsIgnoreCase( this.getParameter() ) );
				link = "<a "+target+" href='"+href+"'"+this.getStyleData()+">"+this.getDisplay( navEntry )+"</a>";
			} else {
				String href = this.createRealLink( navEntry );
				link = "<a "+target+" href='"+href+"'"+this.getStyleData()+">"+this.getDisplay( navEntry )+"</a>";
			}
		} else {
			link = "<span "+this.getStyleData()+">"+this.getDisplay( navEntry )+"</span>";
		}
		this.println( link );			
		return EVAL_PAGE;
	}

	/**
	 * @return Restituisce il valore di target.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target il valore di target da impostare.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

}
