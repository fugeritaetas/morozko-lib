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
 * @(#)CmsRelPfModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 07/06/2006 14/39/20
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsRelPfBean;

/**
 * <p>Oggetto di modello per CmsRelPf.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPfModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 114968396075019L;

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idCmsRelPf;

    /** 
     * <p>Restituisce il valore di idCmsRelPf</p> 
     * 
     * @return      restituisce il valore di idCmsRelPf
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsRelPf() {
        return this.idCmsRelPf;
    }
    /** 
     * <p>Imposta il valore di idCmsRelPf</p> 
     * 
     * @param      idCmsRelPf il valore di idCmsRelPf da impostare
     */ 
    public void setIdCmsRelPf( org.morozko.java.mod.db.dao.DAOID idCmsRelPf ) {
        this.idCmsRelPf = idCmsRelPf;
    }

	private org.morozko.java.mod.db.dao.DAOID idCmsPage;

    /** 
     * <p>Restituisce il valore di idCmsPage</p> 
     * 
     * @return      restituisce il valore di idCmsPage
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsPage() {
        return this.idCmsPage;
    }
    /** 
     * <p>Imposta il valore di idCmsPage</p> 
     * 
     * @param      idCmsPage il valore di idCmsPage da impostare
     */ 
    public void setIdCmsPage( org.morozko.java.mod.db.dao.DAOID idCmsPage ) {
        this.idCmsPage = idCmsPage;
    }

	private org.morozko.java.mod.db.dao.DAOID idCmsFragment;

    /** 
     * <p>Restituisce il valore di idCmsFragment</p> 
     * 
     * @return      restituisce il valore di idCmsFragment
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsFragment() {
        return this.idCmsFragment;
    }
    /** 
     * <p>Imposta il valore di idCmsFragment</p> 
     * 
     * @param      idCmsFragment il valore di idCmsFragment da impostare
     */ 
    public void setIdCmsFragment( org.morozko.java.mod.db.dao.DAOID idCmsFragment ) {
        this.idCmsFragment = idCmsFragment;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsRelPfBean getBean() {
        CmsRelPfBean bean = new CmsRelPfBean();
        bean.setIdCmsRelPf( formatObject(idCmsRelPf) );
        bean.setIdCmsPage( formatObject(idCmsPage) );
        bean.setIdCmsFragment( formatObject(idCmsFragment) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsRelPf=" );
        buffer.append( this.idCmsRelPf );
        buffer.append( "; " );
        buffer.append( "idCmsPage=" );
        buffer.append( this.idCmsPage );
        buffer.append( "; " );
        buffer.append( "idCmsFragment=" );
        buffer.append( this.idCmsFragment );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
