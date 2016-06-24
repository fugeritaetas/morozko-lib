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
 * @(#)ListGetTag.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.tld.list
 * @creation	: 08/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.tld.list;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class ListMapGetTag extends ListTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770906082424855275L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.tld.list.ListTagHelper#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = EVAL_PAGE;
		List list = this.findList();
		Object keyValue = TagUtilsHelper.findAttibute( pageContext, this.getKeyScope(), this.getKeyName(), this.getKeyProperty() );
		if ( keyValue == null ) {
			keyValue = this.getKeyValue();
		}
		try {
			Class[] mArgs = { String.class };
			Method getMethod = list.getClass().getMethod( "get"  , mArgs );
			String[] mParams = { keyValue.toString() };
			Object value = getMethod.invoke( list , mParams );
			TagUtilsHelper.setAttibute( this.pageContext, this.getToScope() , this.getId(), value );
		} catch (Exception e) {
			throw new JspException( e );
		}
		return result;
	}
	
	private String keyName;
	private String keyProperty;
	private String keyValue;
	private String keyScope;

	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyProperty() {
		return keyProperty;
	}
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getKeyScope() {
		return keyScope;
	}
	public void setKeyScope(String keyScope) {
		this.keyScope = keyScope;
	}
	
	
	
	
}
