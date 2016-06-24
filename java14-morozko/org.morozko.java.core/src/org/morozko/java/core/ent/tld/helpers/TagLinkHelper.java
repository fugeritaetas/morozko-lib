/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
/*
 * @(#)TagLinkHelper.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.helpers
 * @creation   : 08/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.helpers;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class TagLinkHelper extends TagHtmlHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160411675460087983L;

	private String target;
	
	private String border;

	/**
	 * @return the border
	 */
	public String getBorder() {
		return border;
	}

	/**
	 * @param border the border to set
	 */
	public void setBorder(String border) {
		this.border = border;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String renderLinkHtml() {
		StringBuffer html = new StringBuffer();
		html.append( this.renderHtml() );
		html.append( renderAttribute( "target" , this.getTarget() ) );
		html.append( renderAttribute( "border" , this.getBorder() ) );
		return html.toString();
	}
	
}
