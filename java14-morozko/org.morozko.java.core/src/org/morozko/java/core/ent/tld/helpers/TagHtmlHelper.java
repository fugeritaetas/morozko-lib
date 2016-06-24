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
 * @(#)TagHtmlHelper.java
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
public class TagHtmlHelper extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2724394475924138256L;

	private String style;
	
	private String styleClass;
	
	private String styleId;

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the styleClass
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @param styleClass the styleClass to set
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @return the styleId
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @param styleId the styleId to set
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	
	public String renderHtml() {
		StringBuffer html = new StringBuffer();
		html.append( renderAttribute( "id" , this.getStyleId() ) );
		html.append( renderAttribute( "class" , this.getStyleClass() ) );
		html.append( renderAttribute( "style" , this.getStyle() ) );
		return html.toString();
	}
	
}
