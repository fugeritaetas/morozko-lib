/*******************************************************
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
*******************************************************/
/*
 * @(#)ListTagHelper.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.list
 * @creation   : 28/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class ListTagHelper extends TagSupportHelper {

		/**
		 *	<jdl:section>
		 * 		<jdl:text lang='it'>.</jdl:text>
		 * 		<jdl:text lang='en'>.</jdl:text>  
		 *	</jdl:section>
		 */	
	private static final long serialVersionUID = -4411985589911979611L;

	public List findList() throws JspException {
		Object obj = findObject( this.getName() , this.getProperty(), null );
		List list = null;
		if ( obj == null  ) {
			throw  new JspException ( "NULL VALUE" );
		} else if ( obj instanceof java.util.List ) {
			list = (List)obj;
		} else {
			throw  new JspException ( "WRONG TYPE" );
		}
		return list;
	}
	
	public Object findElement() throws JspException {
		return findObject( this.getElementName(), this.getElementProperty(), null );
	}
	
	private String elementName;
	
	private String elementProperty;

	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	/**
	 * @return the elementProperty
	 */
	public String getElementProperty() {
		return elementProperty;
	}

	/**
	 * @param elementProperty the elementProperty to set
	 */
	public void setElementProperty(String elementProperty) {
		this.elementProperty = elementProperty;
	}
	
	public int findIndex() throws NumberFormatException, JspException {
		return Integer.parseInt( String.valueOf( this.findObject( this.getIndexName(), this.getIndexProperty(), this.getIndexValue() ) ) );
	}
	
	private String indexName;
	
	private String indexProperty;
	
	private String indexValue;

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	/**
	 * @return the indexProperty
	 */
	public String getIndexProperty() {
		return indexProperty;
	}

	/**
	 * @param indexProperty the indexProperty to set
	 */
	public void setIndexProperty(String indexProperty) {
		this.indexProperty = indexProperty;
	}

	/**
	 * @return the indexValue
	 */
	public String getIndexValue() {
		return indexValue;
	}

	/**
	 * @param indexValue the indexValue to set
	 */
	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}
	
}
