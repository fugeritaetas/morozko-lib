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
 * @(#)CmsRelPmBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 25/05/2006 10/56/34
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsRelPmModel;

/**
 * <p>Bean per oggetti di tipo CmsRelPmModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPmBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 114854739407896L;

	private String idCmsRelPm;

    /** 
     * <p>Restituisce il valore di idCmsRelPm</p> 
     * 
     * @return      restituisce il valore di idCmsRelPm
     */ 
    public String getIdCmsRelPm() {
        return this.idCmsRelPm;
    }
    /** 
     * <p>Imposta il valore di idCmsRelPm</p> 
     * 
     * @param      idCmsRelPm il valore di idCmsRelPm da impostare
     */ 
    public void setIdCmsRelPm( String idCmsRelPm ) {
        this.idCmsRelPm = idCmsRelPm;
    }
	private String menuOrder;

    /** 
     * <p>Restituisce il valore di menuOrder</p> 
     * 
     * @return      restituisce il valore di menuOrder
     */ 
    public String getMenuOrder() {
        return this.menuOrder;
    }
    /** 
     * <p>Imposta il valore di menuOrder</p> 
     * 
     * @param      menuOrder il valore di menuOrder da impostare
     */ 
    public void setMenuOrder( String menuOrder ) {
        this.menuOrder = menuOrder;
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
    public CmsRelPmModel getModel() {
        CmsRelPmModel model = new CmsRelPmModel();
        model.setIdCmsRelPm( toLong(idCmsRelPm) );
        model.setMenuOrder( toInteger(menuOrder) );
        model.setIdCmsPage( toLong(idCmsPage) );
        model.setIdCmsMenu( toLong(idCmsMenu) );
        return model;
    }

}
