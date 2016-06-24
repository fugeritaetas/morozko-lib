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
 * @(#)TagSupportHelper.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.helpers
 * @creation   : 10-mag-2006
 */
package org.morozko.java.core.ent.tld.helpers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Classe base per la definizione di Tag Personalizzati.</p>
 * 
 * <p>Fornisce supporto per la gestione di tag con le seguenti propriet&agrave; :
 * 	<ul>
 * 		<li>name</li>
 * 		<li>property</li>
 * 		<li>scope</li>
 * 		<li>toScope</li>
 * 		<li>styleClass</li>
 * 		<li>styleId</li>
 * 	</ul>
 * </p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class TagSupportHelper extends TagSupport {

	protected void renderValue( String v ) throws JspException {
		if ( this.getId() == null ) {
			this.print( v );	
		} else {
			TagUtilsHelper.setAttibute(this.pageContext, this.getScope(), this.getId(), v );
		}
	}
	
	protected Object findDefaultObject( String value ) throws JspException {
		return this.findObject( this.getName() , this.getProperty(), value );
	}
	
	/**
	 *	<p>Stampa un oggetto sull'output del tag.</p>
	 * 
	 * @param message	l'oggetto da stampare
	 * @throws JspException		se si verificano problemi durante la stampa.
	 */
	public void print( Object message ) throws JspException {
		try {
			this.pageContext.getOut().print( message );
		} catch (IOException e) {
			throw ( new JspException( e ) );
		}
	}
	
	private String styleClass;
	
	private String styleId;
	
	private String scope;
	
	private String toScope;
	
	private String name;
	
	private String property;
	
	protected String getStyleData() {
		String sc = "";
		if (this.getStyleClass()!=null) {
			sc = " class='"+this.getStyleClass()+"' ";
		}
		String si = "";
		if (this.getStyleId()!=null) {
			sc = " id='"+this.getStyleId()+"' ";
		}
		return sc+si;
	}	
	
	/**
	 * <p>Trova un oggetto.</p>
	 * 
	 * @param name			il nome dell'oggetto da trovare
	 * @param property		la proprietà da invocare sull'oggetto
	 * @param value			il valore eventualmente da usare per l'oggetto ( se name è <code>null</code>)
	 * @return				l'oggetto
	 * @throws JspException	se si verificano problemi durante la ricerca
	 */
	protected Object findObject( String name, String property, String value ) throws JspException {
		return this.findObject( name, property, value, null );
	}
	
	/**
	 * <p>Trova un oggetto.</p>
	 * 
	 * @param name			il nome dell'oggetto da trovare
	 * @param property		la proprietà da invocare sull'oggetto
	 * @param value			il valore eventualmente da usare per l'oggetto ( se name è <code>null</code>)
	 * @param parameter		il parametro della richiesta eventualmente utilizzato come valore.
	 * @return				l'oggetto
	 * @throws JspException	se si verificano problemi durante la ricerca
	 */
	protected Object findObject( String name, String property, String value, String parameter ) throws JspException {
		Object obj = null;
		if ( name != null ) {
			obj = TagUtilsHelper.findAttibute( this.pageContext, this.getScope(), name, property );
		} else if ( value != null ) {
			obj = value;
		} else if ( parameter != null ) {
			obj= ((HttpServletRequest)this.pageContext.getRequest()).getParameter( parameter );
		}
		return obj;
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 565826836474874032L;

	/**
	 * @return Restituisce il valore di scope.
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope il valore di scope da impostare.
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return Restituisce il valore di styleClass.
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @param styleClass il valore di styleClass da impostare.
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @return Restituisce il valore di styleId.
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @param styleId il valore di styleId da impostare.
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * @return Restituisce il valore di toScope.
	 */
	public String getToScope() {
		return toScope;
	}

	/**
	 * @param toScope il valore di toScope da impostare.
	 */
	public void setToScope(String toScope) {
		this.toScope = toScope;
	}

	/**
	 * @return Restituisce il valore di name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name il valore di name da impostare.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Restituisce il valore di property.
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property il valore di property da impostare.
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	
	protected static String renderAttribute( String name, String value ) {
		String result = " ";
		if ( value!= null ) {
			result+= name+"='"+value+"'";
		}
		return result;
	}
	
	protected static boolean isTrue( String value ) {
		return "true".equalsIgnoreCase( value ) || "1".equalsIgnoreCase( value );
	}
	
}
