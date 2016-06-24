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
 * @(#)ListSizeTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.list
 * @creation   : 30/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

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
public class ListSizeTag extends ListTagHelper {

		/**
		 *	<jdl:section>
		 * 		<jdl:text lang='it'>.</jdl:text>
		 * 		<jdl:text lang='en'>.</jdl:text>  
		 *	</jdl:section>
		 */	
	private static final long serialVersionUID = 6040612447292602684L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.tld.list.ListTagHelper#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = EVAL_PAGE;
		List list = this.findList();
		TagUtilsHelper.setAttibute( this.pageContext, this.getToScope(), this.getId(), new Integer( list.size() ) );
		return result;
	}	
	
}
