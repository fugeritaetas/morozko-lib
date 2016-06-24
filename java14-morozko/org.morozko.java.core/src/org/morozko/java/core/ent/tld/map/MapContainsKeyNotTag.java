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
 * @(#)MapContainsKeyNotTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 17-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

/**
 * <p>Tag che esegue il codice in esso contenuto solo se una mappa non contiene un elemento con chiave data.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oimap:containsKeyNot name='bean' keyName='chiave'&gt;<br/>
 *				No Element
 *			&lt;/oimap:containsKeyNot name='bean' keyName='chiave'&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; java.util.Map mappa = ((java.util.Map)pageContext.findAttribute( "bean" ));<br/>
 *				&nbsp;&nbsp; if ( !mappa.containsKey( pageContext.findAttribute( "chiave" ) ) ) {;<br/>
 *				&nbsp;&nbsp;&nbsp; out.print( "No element" );<br/>
 *				&nbsp;&nbsp; }<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapContainsKeyNotTag extends MapTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4993915865294737769L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = SKIP_BODY;
		Map map = this.findMap();
		Object key = this.findObject( this.getKeyName(), this.getKeyProperty(), this.getKeyValue() );
		if ( !map.containsKey( key ) ) {
			result = EVAL_PAGE;
		}
		return result;
	}	
	
}
