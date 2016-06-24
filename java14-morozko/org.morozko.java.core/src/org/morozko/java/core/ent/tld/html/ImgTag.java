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
 * @(#)ImgTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.html
 * @creation   : 17-lug-2006
 */
package org.morozko.java.core.ent.tld.html;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ImgTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5098789929047955876L;

	private String src;
	
	private String border;

	private String webapp;
	
	private String alt;
	
	private String altKey;
	
	private String width;
	
	private String height;
	
	private String styleClass;
	
	private String styleId;
	
	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the altKey
	 */
	public String getAltKey() {
		return altKey;
	}

	/**
	 * @param altKey the altKey to set
	 */
	public void setAltKey(String altKey) {
		this.altKey = altKey;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return Restituisce il valore di border.
	 */
	public String getBorder() {
		return border;
	}

	/**
	 * @param border il valore di border da impostare.
	 */
	public void setBorder(String border) {
		this.border = border;
	}

	/**
	 * @return Restituisce il valore di src.
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src il valore di src da impostare.
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String src = this.getSrc();
		if ( isTrue( this.getWebapp() ) ) {
			src = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath()+src;
		}
		String a = "";
		if ( this.alt != null ) {
			a = this.getAlt();;
		} else if ( this.getAltKey() != null ) {
			ResourceBundle bundle = (ResourceBundle)this.pageContext.getServletContext().getAttribute( "org.morozko.java.core.ent.tld.html.message" );
			a = bundle.getString( this.getAltKey() );
		}
		this.print( "<img "+renderAttribute( "width", this.width )+renderAttribute( "height", this.height )+renderAttribute( "id", this.styleId )+renderAttribute( "class", this.styleClass )+renderAttribute( "border", this.border )+renderAttribute( "src", src )+renderAttribute( "alt" , a)+"/>" );
		return EVAL_PAGE;
	}

	/**
	 * @return Restituisce il valore di webapp.
	 */
	public String getWebapp() {
		return webapp;
	}

	/**
	 * @param webapp il valore di webapp da impostare.
	 */
	public void setWebapp(String webapp) {
		this.webapp = webapp;
	} 
	
}
