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
 * @(#)CmsFilterChainModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsFilterChainBean;

/**
 * <p>Oggetto di modello per CmsFilterChain.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterChainModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 115651347806259L;


	public static final String ATT_NAME = "cmsFilterChainModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idCmsFilterChain;

    /** 
     * <p>Restituisce il valore di idCmsFilterChain</p> 
     * 
     * @return      restituisce il valore di idCmsFilterChain
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsFilterChain() {
        return this.idCmsFilterChain;
    }
    /** 
     * <p>Imposta il valore di idCmsFilterChain</p> 
     * 
     * @param      idCmsFilterChain il valore di idCmsFilterChain da impostare
     */ 
    public void setIdCmsFilterChain( org.morozko.java.mod.db.dao.DAOID idCmsFilterChain ) {
        this.idCmsFilterChain = idCmsFilterChain;
    }

	private String chainName;

    /** 
     * <p>Restituisce il valore di chainName</p> 
     * 
     * @return      restituisce il valore di chainName
     */ 
    public String getChainName() {
        return this.chainName;
    }
    /** 
     * <p>Imposta il valore di chainName</p> 
     * 
     * @param      chainName il valore di chainName da impostare
     */ 
    public void setChainName( String chainName ) {
        this.chainName = chainName;
    }

	private String chainDescription;

    /** 
     * <p>Restituisce il valore di chainDescription</p> 
     * 
     * @return      restituisce il valore di chainDescription
     */ 
    public String getChainDescription() {
        return this.chainDescription;
    }
    /** 
     * <p>Imposta il valore di chainDescription</p> 
     * 
     * @param      chainDescription il valore di chainDescription da impostare
     */ 
    public void setChainDescription( String chainDescription ) {
        this.chainDescription = chainDescription;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listCmsFilterHandler;

    /** 
     * <p>Restituisce il valore di listCmsFilterHandler</p> 
     * 
     * @return      restituisce il valore di listCmsFilterHandler
     */ 
    public java.util.List getListCmsFilterHandler() {
        return this.listCmsFilterHandler;
    }

    /** 
     * <p>Imposta il valore di listCmsFilterHandler</p> 
     * 
     * @param      listCmsFilterHandler il valore di listCmsFilterHandler da impostare
     */ 
    public void setListCmsFilterHandler( java.util.List listCmsFilterHandler ) {
        this.listCmsFilterHandler = listCmsFilterHandler;
    }

    // campi relativi a relazioni - END 

    public CmsFilterChainBean getBean() {
        CmsFilterChainBean bean = new CmsFilterChainBean();
        bean.setIdCmsFilterChain( formatObject(idCmsFilterChain) );
        bean.setChainName( formatObject(chainName) );
        bean.setChainDescription( formatObject(chainDescription) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsFilterChain=" );
        buffer.append( this.idCmsFilterChain );
        buffer.append( "; " );
        buffer.append( "chainName=" );
        buffer.append( this.chainName );
        buffer.append( "; " );
        buffer.append( "chainDescription=" );
        buffer.append( this.chainDescription );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
