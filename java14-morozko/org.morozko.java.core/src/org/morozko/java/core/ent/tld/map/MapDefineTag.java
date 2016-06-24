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
 * @(#)MapDefineTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 11-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.lang.helpers.StringHelper;

/**
 * <p>Tag per la creazione di una nuova istanza di java.util.Map.</p>
 *
 *	<p>
 *		Ad esempio il seguente estratto di codice : <br/>
 *		<div class="codejsp"> 
 *			&lt;oimap:define id='mappa' name='bean' property='campo'/&gt;<br/>
 *		</div>
 *		<br/>
 *		<br/>
 *		E' equivalente a scrivere in una pagina JSP :<br/>
 *		<div class="codejsp">
 *			&lt;%<br/>
 *				&nbsp;&nbsp; java.util.Map mappa = ((MioOggetto)pageContext.findAttribute( "bean" )).getCampo();<br/>
 *				&nbsp;&nbsp; pageContext.setAttribute( "mappa", mappa );<br/>
 *			%&gt;<br/>
 *		</div>
 *	</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapDefineTag extends MapTagHelper {

	public final static String DEFAULT_TYPE = "java.util.HashMap";
	
	private String type;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5141354305207984203L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		String classType = StringHelper.value( this.getType(), DEFAULT_TYPE );
		try {
			Map map = (Map)ClassHelper.newInstance( classType );
			TagUtilsHelper.setAttibute( this.pageContext, this.getToScope(), this.getId(), map );
		} catch (Exception e) {
			throw ( new JspException( e ) );
		}
		return EVAL_PAGE;
	}

	/**
	 * @return Restituisce il valore di type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type il valore di type da impostare.
	 */
	public void setType(String type) {
		this.type = type;
	}

}
