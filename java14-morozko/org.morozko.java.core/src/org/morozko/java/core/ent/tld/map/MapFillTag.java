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
 * @(#)MapFillTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.map
 * @creation	: 08/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class MapFillTag extends MapTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3577203466699006198L;

	
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		Map map = this.findMap();
		if ( "true".equalsIgnoreCase( this.getPar() ) ) {
			Enumeration e = request.getParameterNames();
			while ( e.hasMoreElements() ) {
				String k = (String)e.nextElement();
				map.put( k, request.getParameter( k ) );
			}
		}
		if ( "true".equalsIgnoreCase( this.getReq() ) ) {
			Enumeration e = request.getAttributeNames();
			while ( e.hasMoreElements() ) {
				String k = (String)e.nextElement();
				map.put( k, request.getAttribute( k ) );
			}
		}
		if ( "true".equalsIgnoreCase( this.getSes() ) ) {
			Enumeration e = request.getSession().getAttributeNames();
			while ( e.hasMoreElements() ) {
				String k = (String)e.nextElement();
				map.put( k, request.getSession().getAttribute( k ) );
			}
		}
		if ( "true".equalsIgnoreCase( this.getCon() ) ) {
			Enumeration e = this.pageContext.getServletContext().getAttributeNames();
			while ( e.hasMoreElements() ) {
				String k = (String)e.nextElement();
				map.put( k, this.pageContext.getServletContext().getAttribute( k ) );
			}
		}
		return EVAL_PAGE;
	}

	private String par;
	
	private String req;
	
	private String ses;
	
	private String con;

	/**
	 * @return the con
	 */
	public String getCon() {
		return con;
	}

	/**
	 * @param con the con to set
	 */
	public void setCon(String con) {
		this.con = con;
	}

	/**
	 * @return the par
	 */
	public String getPar() {
		return par;
	}

	/**
	 * @param par the par to set
	 */
	public void setPar(String par) {
		this.par = par;
	}

	/**
	 * @return the req
	 */
	public String getReq() {
		return req;
	}

	/**
	 * @param req the req to set
	 */
	public void setReq(String req) {
		this.req = req;
	}

	/**
	 * @return the ses
	 */
	public String getSes() {
		return ses;
	}

	/**
	 * @param ses the ses to set
	 */
	public void setSes(String ses) {
		this.ses = ses;
	}
	
}
