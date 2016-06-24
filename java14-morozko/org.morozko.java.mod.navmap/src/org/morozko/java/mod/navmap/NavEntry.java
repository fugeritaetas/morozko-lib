/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
 * @(#)NavEntry.java
 *
 * @project	   : simoss
 * @package	   : org.morozko.java.mod.navmap
 * @creation   : 15-giu-2005 7.36.32
 */

package org.morozko.java.mod.navmap;

import java.util.Properties;

/**
 * <p>Classe che restituisce le NavEntry di ogni singolo NavNode </p>
 *
 * @author asacca
 *
 */
public class NavEntry extends NavId {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3362183285780938404L;

	/**
	 * 
	 * <p>Metodo che copia una singola NavEntry.</p>
	 * 
	 * @return un nuovo oggetto NavEntry impostato con i valori del NavEntry
	 */
	public NavEntry copy() {
		NavEntry navEntry = new NavEntry();
		navEntry.setModule( this.getModule() );
		navEntry.setName( this.getName() );
		navEntry.setAuth( this.getAuth() );
		navEntry.setContext( this.getContext() );
		navEntry.setDisplay( this.getDisplay() );
		navEntry.setTitle( this.getTitle() );
		navEntry.setLabel( this.getLabel() );
		navEntry.setAbsolute( this.getAbsolute() );
		navEntry.setLink( this.getLink() );
		navEntry.setRenderLink( this.getRenderLink() );
		navEntry.setNavHandler( this.getNavHandler() );
		navEntry.setDirectDisplay( this.getDirectDisplay() );
		navEntry.setDirectTitle( this.getDirectTitle() );
		navEntry.setDirectLabel( this.getDirectLabel() );
		return navEntry;
	}		

	public String toString() {
		return this.getClass().getName()+"["+
					"module="+this.getModule()+";"+
					"name="+this.getName()+";"+
					"auth="+this.getAuth()+";"+
					"context="+this.getContext()+";"+
					"display="+this.getDisplay()+";"+
					"title="+this.getTitle()+";"+
					"label="+this.getLabel()+";"+
					"absolute="+this.getAbsolute()+";"+
					"]";
	}
	
	 
	private String auth;
	
	private String context;
	
	private String display;
	
	private String label;
	
	private String title;
	 
	private String link;
	
	private String target;
	
	private Boolean absolute;
	
	private Boolean renderLink;
	
	private String navHandler;
	
	private Boolean directTitle;
	
	private Boolean directDisplay;
	
	private Boolean directLabel;
	
	/**
	 * @return Restituisce il valore di navHandler.
	 */
	public String getNavHandler() {
		return navHandler;
	}

	/**
	 * @param navHandler il valore di navHandler da impostare.
	 */
	public void setNavHandler(String navHandler) {
		this.navHandler = navHandler;
	}

	/**
	 * @return Restituisce il valore di renderLink.
	 */
	public Boolean getRenderLink() {
		return renderLink;
	}

	/**
	 * @param renderLink il valore di renderLink da impostare.
	 */
	public void setRenderLink(Boolean renderLink) {
		this.renderLink = renderLink;
	}

	/**
	* <p>Restituisce il valore di absolute in formato stringa.</p>
	*
	* @return ritorna il valore di absolute in stringa.
	*/
	public Boolean getAbsolute() {
		return  this.absolute ;
	}

	/**
	 * <p>Imposta il valore di absolute.</p>
	 *
	 * @param absolute il valore da impostare.
	 */
	public void setAbsolute(Boolean absolute) {
		this.absolute = absolute;
	}

	/**
	 * <p>Restituisce il valore di auth.</p>
	 *
	 * @return il valore di auth.
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * <p>Imposta il valore di auth.</p>
	 *
	 * @param auth il valore da impostare.
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * <p>Restituisce il valore di context.</p>
	 *
	 * @return il valore di context.
	 */
	public String getContext() {
		return context;
	}

	/**
	 * <p>Imposta il valore di context.</p>
	 *
	 * @param context il valore da impostare.
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * <p>Restituisce il valore di display.</p>
	 *
	 * @return il valore di display.
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * <p>Imposta il valore di display.</p>
	 *
	 * @param display il valore da impostare.
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * <p>Restituisce il valore di title.</p>
	 *
	 * @return il valore di title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <p>Imposta il valore di title.</p>
	 *
	 * @param title il valore da impostare.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Restituisce il valore di target.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target il valore di target da impostare.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return Restituisce il valore di link.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link il valore di link da impostare.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return Restituisce il valore di directDisplay.
	 */
	public Boolean getDirectDisplay() {
		return directDisplay;
	}

	/**
	 * @param directDisplay il valore di directDisplay da impostare.
	 */
	public void setDirectDisplay(Boolean directDisplay) {
		this.directDisplay = directDisplay;
	}

	/**
	 * @return Restituisce il valore di directTitle.
	 */
	public Boolean getDirectTitle() {
		return directTitle;
	}

	/**
	 * @param directTitle il valore di directTitle da impostare.
	 */
	public void setDirectTitle(Boolean directTitle) {
		this.directTitle = directTitle;
	}
	
	private Properties parameters;

	/**
	 * @return Restituisce il valore di parameters.
	 */
	public Properties getParameters() {
		return parameters;
	}

	/**
	 * @param parameters il valore di parameters da impostare.
	 */
	public void setParameters(Properties parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the directLabel
	 */
	public Boolean getDirectLabel() {
		return directLabel;
	}

	/**
	 * @param directLabel the directLabel to set
	 */
	public void setDirectLabel(Boolean directLabel) {
		this.directLabel = directLabel;
	}
	
}
