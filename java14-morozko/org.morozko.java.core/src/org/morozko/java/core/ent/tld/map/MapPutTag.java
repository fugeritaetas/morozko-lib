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
 * @(#)MapPutTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 11-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

/**
 * <p>Tag per inserire un oggetto in una <code>java.util.Map</code> specificata.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oimap:put id='elemento' name='bean' keyName='chiave' elementName="elemento"/&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; java.util.Map mappa = ((java.util.Map)pageContext.findAttribute( "bean" ));<br/>
 *				&nbsp;&nbsp; Object chiave = pageContext.findAttribute( "chiave" );<br/>
 *				&nbsp;&nbsp; Object elemento = pageContext.findAttribute( "elemento" );<br/>
 *				&nbsp;&nbsp; mappa.put( chiave, elemento );<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapPutTag extends MapTagHelper {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 981554075142584148L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Map map = this.findMap();
		Object key = this.findObject( this.getKeyName(), this.getKeyProperty(), this.getKeyValue() );
		Object element = this.findObject( this.getElementName(), this.getElementProperty(), this.getElementValue(), this.getElementParameter() );
		map.put( key, element );
		return EVAL_PAGE;
	}

}
