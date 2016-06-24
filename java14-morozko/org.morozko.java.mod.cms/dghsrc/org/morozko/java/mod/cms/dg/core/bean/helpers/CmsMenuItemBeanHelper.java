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
 * @(#)CmsMenuItemBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsMenuItemModel;

/**
 * <p>Bean per oggetti di tipo CmsMenuItemModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuItemBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 114854733593721L;

	private String idCmsMenuItem;

    /** 
     * <p>Restituisce il valore di idCmsMenuItem</p> 
     * 
     * @return      restituisce il valore di idCmsMenuItem
     */ 
    public String getIdCmsMenuItem() {
        return this.idCmsMenuItem;
    }
    /** 
     * <p>Imposta il valore di idCmsMenuItem</p> 
     * 
     * @param      idCmsMenuItem il valore di idCmsMenuItem da impostare
     */ 
    public void setIdCmsMenuItem( String idCmsMenuItem ) {
        this.idCmsMenuItem = idCmsMenuItem;
    }
	private String menuItemType;

    /** 
     * <p>Restituisce il valore di menuItemType</p> 
     * 
     * @return      restituisce il valore di menuItemType
     */ 
    public String getMenuItemType() {
        return this.menuItemType;
    }
    /** 
     * <p>Imposta il valore di menuItemType</p> 
     * 
     * @param      menuItemType il valore di menuItemType da impostare
     */ 
    public void setMenuItemType( String menuItemType ) {
        this.menuItemType = menuItemType;
    }
	private String idCmsPage;

    /** 
     * <p>Restituisce il valore di idCmsPage</p> 
     * 
     * @return      restituisce il valore di idCmsPage
     */ 
    public String getIdCmsPage() {
        return this.idCmsPage;
    }
    /** 
     * <p>Imposta il valore di idCmsPage</p> 
     * 
     * @param      idCmsPage il valore di idCmsPage da impostare
     */ 
    public void setIdCmsPage( String idCmsPage ) {
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
	private String idCmsMenu;

    /** 
     * <p>Restituisce il valore di idCmsMenu</p> 
     * 
     * @return      restituisce il valore di idCmsMenu
     */ 
    public String getIdCmsMenu() {
        return this.idCmsMenu;
    }
    /** 
     * <p>Imposta il valore di idCmsMenu</p> 
     * 
     * @param      idCmsMenu il valore di idCmsMenu da impostare
     */ 
    public void setIdCmsMenu( String idCmsMenu ) {
        this.idCmsMenu = idCmsMenu;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsMenuItemModel getModel() {
        CmsMenuItemModel model = new CmsMenuItemModel();
        model.setIdCmsMenuItem( toLong(idCmsMenuItem) );
        model.setMenuItemType( toInteger(menuItemType) );
        model.setIdCmsPage( toLong(idCmsPage) );
        model.setDisplay( toString(display) );
        model.setHref( toString(href) );
        model.setTarget( toString(target) );
        model.setIdCmsMenu( toLong(idCmsMenu) );
        return model;
    }

}
