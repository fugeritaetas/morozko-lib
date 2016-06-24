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
 * @(#)CmsRelPmModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 25/05/2006 10/56/34
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsRelPmBean;

/**
 * <p>Oggetto di modello per CmsRelPm.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPmModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 114854739407861L;

    // campi relativi alla tabella - START 

	private Long idCmsRelPm;

    /** 
     * <p>Restituisce il valore di idCmsRelPm</p> 
     * 
     * @return      restituisce il valore di idCmsRelPm
     */ 
    public Long getIdCmsRelPm() {
        return this.idCmsRelPm;
    }
    /** 
     * <p>Imposta il valore di idCmsRelPm</p> 
     * 
     * @param      idCmsRelPm il valore di idCmsRelPm da impostare
     */ 
    public void setIdCmsRelPm( Long idCmsRelPm ) {
        this.idCmsRelPm = idCmsRelPm;
    }

	private Integer menuOrder;

    /** 
     * <p>Restituisce il valore di menuOrder</p> 
     * 
     * @return      restituisce il valore di menuOrder
     */ 
    public Integer getMenuOrder() {
        return this.menuOrder;
    }
    /** 
     * <p>Imposta il valore di menuOrder</p> 
     * 
     * @param      menuOrder il valore di menuOrder da impostare
     */ 
    public void setMenuOrder( Integer menuOrder ) {
        this.menuOrder = menuOrder;
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
    public CmsRelPmBean getBean() {
        CmsRelPmBean bean = new CmsRelPmBean();
        bean.setIdCmsRelPm( formatObject(idCmsRelPm) );
        bean.setMenuOrder( formatObject(menuOrder) );
        bean.setIdCmsPage( formatObject(idCmsPage) );
        bean.setIdCmsMenu( formatObject(idCmsMenu) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsRelPm=" );
        buffer.append( this.idCmsRelPm );
        buffer.append( "; " );
        buffer.append( "menuOrder=" );
        buffer.append( this.menuOrder );
        buffer.append( "; " );
        buffer.append( "idCmsPage=" );
        buffer.append( this.idCmsPage );
        buffer.append( "; " );
        buffer.append( "idCmsMenu=" );
        buffer.append( this.idCmsMenu );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
