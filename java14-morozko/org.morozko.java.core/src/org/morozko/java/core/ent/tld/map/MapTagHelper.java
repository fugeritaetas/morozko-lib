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
 * @(#)MapTagHelper.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.map
 * @creation   : 11-mag-2006
 */
package org.morozko.java.core.ent.tld.map;

import java.util.Map;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;

/**
 * <p>Classe di supporto per la creazione di tag personalizzata dedicati
 * all' accesso e la manipolazione di <code>java.util.Map</code>
 * all'interno di pagine JSP.</p>
 *
 * <p>I tag che fanno parte di questa tag library possono avere accesso a tre diversi
 * set di proprietà : 
 * 	<ul>
 * 		<li>Proprietà di accesso alla mappa.
 * 			<ul>
 * 				<li>name - Il nome del bean che dove cercare la mappa.</li>
 * 				<li>property - La proprietà da chiamare sul bean individuato da 'name'.</li>
 * 			</ul>
 * 		</li>
 * 		<li>Proprietà per l'individuazione di una chiave della mappa.
 * 			<ul>
 * 				<li>keyName - Il nome di un bean da utilizzare come valore.</li>
 * 				<li>keyProperty - La proprietà da chiamare sul bean individuato dalla 'keyName'.</li>
 * 				<li>keyValue - Un valore da usare nel caso 'keyName' non sia definito.</li>
 * 			</ul>
 * 		</li>
 * 		<li>Proprietà per l'individuazione di un valore della mappa.
 * 			<ul>
 * 				<li>elementName - Il nome di un bean da utilizzare come valore.</li>
 * 				<li>elementProperty - La proprietà da chiamare sul bean individuato dalla 'elementName'.</li>
 * 				<li>elementValue - Un valore da usare nel caso 'elementName' non sia definito.</li>
 * 			</ul>
 * 		</li>
 * 	</ul>
 * </p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class MapTagHelper extends TagSupportHelper {

	protected Map findMap() throws JspException {
		Map map = null;
		map = (Map)TagUtilsHelper.findAttibute( this.pageContext, this.getScope(), this.getName(), this.getProperty() );
		return map;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5582392733380240361L;

	private String keyName;
	
	private String keyProperty;
	
	private String keyValue;	
	
	private String elementName;
	
	private String elementProperty;
	
	private String elementValue;
	
	private String elementParameter;

	/**
	 * @return Restituisce il valore di elementName.
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * @param elementName il valore di elementName da impostare.
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	/**
	 * @return Restituisce il valore di elementProperty.
	 */
	public String getElementProperty() {
		return elementProperty;
	}

	/**
	 * @param elementProperty il valore di elementProperty da impostare.
	 */
	public void setElementProperty(String elementProperty) {
		this.elementProperty = elementProperty;
	}

	/**
	 * @return Restituisce il valore di elementValue.
	 */
	public String getElementValue() {
		return elementValue;
	}

	/**
	 * @param elementValue il valore di elementValue da impostare.
	 */
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	/**
	 * @return Restituisce il valore di keyName.
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName il valore di keyName da impostare.
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return Restituisce il valore di keyProperty.
	 */
	public String getKeyProperty() {
		return keyProperty;
	}

	/**
	 * @param keyProperty il valore di keyProperty da impostare.
	 */
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	/**
	 * @return Restituisce il valore di keyValue.
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue il valore di keyValue da impostare.
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}	
	
	/**
	 * @return Restituisce il valore di elementParameter.
	 */
	public String getElementParameter() {
		return elementParameter;
	}

	/**
	 * @param elementParameter il valore di elementParameter da impostare.
	 */
	public void setElementParameter(String elementParameter) {
		this.elementParameter = elementParameter;
	}		
	
}
