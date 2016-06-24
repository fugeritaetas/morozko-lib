/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.app.cms 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)CmsMenuItemModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsMenuItemBean;

/**
 * <p>Oggetto di modello per CmsMenuItem.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuItemModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 114854733593744L;

    // campi relativi alla tabella - START 

	private Long idCmsMenuItem;

    /** 
     * <p>Restituisce il valore di idCmsMenuItem</p> 
     * 
     * @return      restituisce il valore di idCmsMenuItem
     */ 
    public Long getIdCmsMenuItem() {
        return this.idCmsMenuItem;
    }
    /** 
     * <p>Imposta il valore di idCmsMenuItem</p> 
     * 
     * @param      idCmsMenuItem il valore di idCmsMenuItem da impostare
     */ 
    public void setIdCmsMenuItem( Long idCmsMenuItem ) {
        this.idCmsMenuItem = idCmsMenuItem;
    }

	private Integer menuItemType;

    /** 
     * <p>Restituisce il valore di menuItemType</p> 
     * 
     * @return      restituisce il valore di menuItemType
     */ 
    public Integer getMenuItemType() {
        return this.menuItemType;
    }
    /** 
     * <p>Imposta il valore di menuItemType</p> 
     * 
     * @param      menuItemType il valore di menuItemType da impostare
     */ 
    public void setMenuItemType( Integer menuItemType ) {
        this.menuItemType = menuItemType;
    }

	private Long idCmsPage;

    /** 
     * <p>Restituisce il valore di idCmsPage</p> 
     * 
     * @return      restituisce il valore di idCmsPage
     */ 
    public Long getIdCmsPage() {
        return this.idCmsPage;
    }
    /** 
     * <p>Imposta il valore di idCmsPage</p> 
     * 
     * @param      idCmsPage il valore di idCmsPage da impostare
     */ 
    public void setIdCmsPage( Long idCmsPage ) {
        this.idCmsPage = idCmsPage;
    }

	private String display;

    /** 
     * <p>Restituisce il valore di display</p> 
     * 
     * @return      restituisce il valore di display
     */ 
    public String getDisplay() {
        return this.display;
    }
    /** 
     * <p>Imposta il valore di display</p> 
     * 
     * @param      display il valore di display da impostare
     */ 
    public void setDisplay( String display ) {
        this.display = display;
    }

	private String href;

    /** 
     * <p>Restituisce il valore di href</p> 
     * 
     * @return      restituisce il valore di href
     */ 
    public String getHref() {
        return this.href;
    }
    /** 
     * <p>Imposta il valore di href</p> 
     * 
     * @param      href il valore di href da impostare
     */ 
    public void setHref( String href ) {
        this.href = href;
    }

	private String target;

    /** 
     * <p>Restituisce il valore di target</p> 
     * 
     * @return      restituisce il valore di target
     */ 
    public String getTarget() {
        return this.target;
    }
    /** 
     * <p>Imposta il valore di target</p> 
     * 
     * @param      target il valore di target da impostare
     */ 
    public void setTarget( String target ) {
        this.target = target;
    }

	private Long idCmsMenu;

    /** 
     * <p>Restituisce il valore di idCmsMenu</p> 
     * 
     * @return      restituisce il valore di idCmsMenu
     */ 
    public Long getIdCmsMenu() {
        return this.idCmsMenu;
    }
    /** 
     * <p>Imposta il valore di idCmsMenu</p> 
     * 
     * @param      idCmsMenu il valore di idCmsMenu da impostare
     */ 
    public void setIdCmsMenu( Long idCmsMenu ) {
        this.idCmsMenu = idCmsMenu;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsMenuItemBean getBean() {
        CmsMenuItemBean bean = new CmsMenuItemBean();
        bean.setIdCmsMenuItem( formatObject(idCmsMenuItem) );
        bean.setMenuItemType( formatObject(menuItemType) );
        bean.setIdCmsPage( formatObject(idCmsPage) );
        bean.setDisplay( formatObject(display) );
        bean.setHref( formatObject(href) );
        bean.setTarget( formatObject(target) );
        bean.setIdCmsMenu( formatObject(idCmsMenu) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsMenuItem=" );
        buffer.append( this.idCmsMenuItem );
        buffer.append( "; " );
        buffer.append( "menuItemType=" );
        buffer.append( this.menuItemType );
        buffer.append( "; " );
        buffer.append( "idCmsPage=" );
        buffer.append( this.idCmsPage );
        buffer.append( "; " );
        buffer.append( "display=" );
        buffer.append( this.display );
        buffer.append( "; " );
        buffer.append( "href=" );
        buffer.append( this.href );
        buffer.append( "; " );
        buffer.append( "target=" );
        buffer.append( this.target );
        buffer.append( "; " );
        buffer.append( "idCmsMenu=" );
        buffer.append( this.idCmsMenu );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
