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
 * @(#)CmsMenuModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsMenuBean;

/**
 * <p>Oggetto di modello per CmsMenu.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 114854733589020L;

    // campi relativi alla tabella - START 

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

	private String name;

    /** 
     * <p>Restituisce il valore di name</p> 
     * 
     * @return      restituisce il valore di name
     */ 
    public String getName() {
        return this.name;
    }
    /** 
     * <p>Imposta il valore di name</p> 
     * 
     * @param      name il valore di name da impostare
     */ 
    public void setName( String name ) {
        this.name = name;
    }

	private String description;

    /** 
     * <p>Restituisce il valore di description</p> 
     * 
     * @return      restituisce il valore di description
     */ 
    public String getDescription() {
        return this.description;
    }
    /** 
     * <p>Imposta il valore di description</p> 
     * 
     * @param      description il valore di description da impostare
     */ 
    public void setDescription( String description ) {
        this.description = description;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listCmsMenuItem;

    /** 
     * <p>Restituisce il valore di listCmsMenuItem</p> 
     * 
     * @return      restituisce il valore di listCmsMenuItem
     */ 
    public java.util.List getListCmsMenuItem() {
        return this.listCmsMenuItem;
    }

    /** 
     * <p>Imposta il valore di listCmsMenuItem</p> 
     * 
     * @param      listCmsMenuItem il valore di listCmsMenuItem da impostare
     */ 
    public void setListCmsMenuItem( java.util.List listCmsMenuItem ) {
        this.listCmsMenuItem = listCmsMenuItem;
    }

    private java.util.List listCmsRelPm;

    /** 
     * <p>Restituisce il valore di listCmsRelPm</p> 
     * 
     * @return      restituisce il valore di listCmsRelPm
     */ 
    public java.util.List getListCmsRelPm() {
        return this.listCmsRelPm;
    }

    /** 
     * <p>Imposta il valore di listCmsRelPm</p> 
     * 
     * @param      listCmsRelPm il valore di listCmsRelPm da impostare
     */ 
    public void setListCmsRelPm( java.util.List listCmsRelPm ) {
        this.listCmsRelPm = listCmsRelPm;
    }

    // campi relativi a relazioni - END 

    public CmsMenuBean getBean() {
        CmsMenuBean bean = new CmsMenuBean();
        bean.setIdCmsMenu( formatObject(idCmsMenu) );
        bean.setName( formatObject(name) );
        bean.setDescription( formatObject(description) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsMenu=" );
        buffer.append( this.idCmsMenu );
        buffer.append( "; " );
        buffer.append( "name=" );
        buffer.append( this.name );
        buffer.append( "; " );
        buffer.append( "description=" );
        buffer.append( this.description );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
