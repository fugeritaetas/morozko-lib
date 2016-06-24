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
 * @(#)CmsMenuBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsMenuModel;

/**
 * <p>Bean per oggetti di tipo CmsMenuModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 114854733589057L;

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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsMenuModel getModel() {
        CmsMenuModel model = new CmsMenuModel();
        model.setIdCmsMenu( toLong(idCmsMenu) );
        model.setName( toString(name) );
        model.setDescription( toString(description) );
        return model;
    }

}
