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
 * @(#)ListDefine.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.list
 * @creation   : 28/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.list;

import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;
import org.morozko.java.core.lang.helpers.ClassHelper;

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
public class ListDefineTag extends ListTagHelper {

		/**
		 *	<jdl:section>
		 * 		<jdl:text lang='it'>.</jdl:text>
		 * 		<jdl:text lang='en'>.</jdl:text>  
		 *	</jdl:section>
		 */	
	private static final long serialVersionUID = -187408487350662445L;

	private String type;

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo type.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of type.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo type.</jdl:text>
	 *         		<jdl:text lang='en'>the value of type.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String getType() {
		return type;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo type.</jdl:text>
	 * 		<jdl:text lang='en'>Sets type.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>type il valore di type da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>type the type to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String type = this.getType();
		String name = this.getName();
		String property = this.getProperty();
		List list = null;
		if ( type != null && name != null ) {
			throw ( new JspException( "type and name cannot both be specified" ) );
		} else if ( type == null && name == null ) {
			throw ( new JspException( "type or name must be specified" ) );
		} else if ( type != null ) {
			try {
				list = (List)ClassHelper.newInstance( type );
			} catch (Exception e) {
				throw ( new JspException( e ) );
			}
		} else {
			Object obj = findObject( name , property , null );
			if ( obj instanceof java.util.List ) {
				list = (List) obj;
			} else if ( obj instanceof Object[] ) {
				Object[] array = (Object[])  obj;
				list = Arrays.asList( array );
			} else {
				throw ( new JspException( "Invalid type for object : "+obj ) );
			}
		}
		TagUtilsHelper.setAttibute( this.pageContext , this.getToScope() , this.getId() , list );
		return EVAL_PAGE;
	}
	
}
