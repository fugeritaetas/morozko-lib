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
 * @(#)MapRemoveTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 25-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p>Tag per rimuovere un oggetto da una java.util.Map specificata.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oimap:remove name='bean' keyName='chiave'/&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; java.util.Map mappa = ((java.util.Map)pageContext.findAttribute( "bean" ));<br/>
 *				&nbsp;&nbsp; Object chiave = pageContext.findAttribute( "chiave" );<br/>
 *				&nbsp;&nbsp; mappa.remove( chiave );<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapRemoveTag extends MapTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 591967297875560617L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Map map = (Map)TagUtilsHelper.findAttibute( this.pageContext, this.getScope(), this.getName(), this.getProperty() );
		Object key = this.findObject( this.getKeyName(), this.getKeyProperty(), this.getKeyValue() );
		map.remove( key );
		return EVAL_PAGE;
	}	
	
}
