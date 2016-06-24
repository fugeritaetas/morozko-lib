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
 * @(#)MapWriteTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 11-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

/**
 * <p>Tag per stampare un oggetto contenuto in una <code>java.util.Map</code>
 * all'interno di una pagina JSP.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oimap:write name='bean' keyName='chiave'/&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; java.util.Map mappa = ((java.util.Map)pageContext.findAttribute( "bean" ));<br/>
 *				&nbsp;&nbsp; Object chiave = pageContext.findAttribute( "chiave" );<br/>
 *				&nbsp;&nbsp; out.print( mappa.get( chiave ) );<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapWriteTag extends MapTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1539665147009766542L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Map map = this.findMap();
		Object key = this.findObject( this.getKeyName(), this.getKeyProperty(), this.getKeyValue() );
		Object element = map.get( key );
		this.print( element );
		return EVAL_PAGE;
	}

}
