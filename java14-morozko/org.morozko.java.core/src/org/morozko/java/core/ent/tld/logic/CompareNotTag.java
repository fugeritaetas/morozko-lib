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
 * @(#)CompareNotTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.logic
 * @creation   : 10-mag-2006
 */
package org.morozko.java.core.ent.tld.logic;

import javax.servlet.jsp.JspException;

/**
 * <p>Tag che esegue il codice in esso contenuto solo se il risultato della comparazione tra due oggetti restituisce <code>false</code>.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oilogic:compareNot name='bean' nameTo='beanTo'&gt;<br/>
 *				TEST
 *			&lt;/oilogic:compareNot name='bean' nameTo='beanTo'&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; Object obj1 = this.pageContext.findAttribute( "bean" );<br/>
 * 				&nbsp;&nbsp; Object obj2 = this.pageContext.findAttribute( "beanTo" );<br/>
 *				&nbsp;&nbsp; if ( !obj1.equals( obj2 ) ) {;<br/>
 *				&nbsp;&nbsp;&nbsp; out.print( "TEST" );<br/>
 *				&nbsp;&nbsp; }<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class CompareNotTag extends CompareTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8462253702998829884L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int eval = EVAL_PAGE;
		if ( compare() ) {
			eval = SKIP_BODY;
		}
		return eval;
	}	
	
}
