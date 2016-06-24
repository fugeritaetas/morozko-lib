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
 * @(#)CompareTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.logic
 * @creation   : 10-mag-2006
 */
package org.morozko.java.core.ent.tld.logic;

import javax.servlet.jsp.JspException;

/**
 * <p>Tag che esegue il codice in esso contenuto solo se il risultato della comparazione tra due oggetti restituisce <code>true</code>.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oilogic:compare name='bean' nameTo='beanTo'&gt;<br/>
 *				TEST
 *			&lt;/oilogic:compare name='bean' nameTo='beanTo'&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; Object obj1 = this.pageContext.findAttribute( "bean" );<br/>
 * 				&nbsp;&nbsp; Object obj2 = this.pageContext.findAttribute( "beanTo" );<br/>
 *				&nbsp;&nbsp; if ( obj1.equals( obj2 ) ) {;<br/>
 *				&nbsp;&nbsp;&nbsp; out.print( "TEST" );<br/>
 *				&nbsp;&nbsp; }<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class CompareTag extends CompareTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7672182284045487519L;

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
		int eval = EVAL_PAGE;
		if ( !compare() ) {
			eval = SKIP_BODY;
		}
		return eval;
	}	

}
