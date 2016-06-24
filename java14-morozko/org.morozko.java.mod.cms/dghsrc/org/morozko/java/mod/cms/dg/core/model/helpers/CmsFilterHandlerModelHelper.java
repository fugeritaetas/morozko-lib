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
 * @(#)CmsFilterHandlerModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsFilterHandlerBean;

/**
 * <p>Oggetto di modello per CmsFilterHandler.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterHandlerModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 115651347818716L;


	public static final String ATT_NAME = "cmsFilterHandlerModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idCmsFilterHandler;

    /** 
     * <p>Restituisce il valore di idCmsFilterHandler</p> 
     * 
     * @return      restituisce il valore di idCmsFilterHandler
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsFilterHandler() {
        return this.idCmsFilterHandler;
    }
    /** 
     * <p>Imposta il valore di idCmsFilterHandler</p> 
     * 
     * @param      idCmsFilterHandler il valore di idCmsFilterHandler da impostare
     */ 
    public void setIdCmsFilterHandler( org.morozko.java.mod.db.dao.DAOID idCmsFilterHandler ) {
        this.idCmsFilterHandler = idCmsFilterHandler;
    }

	private String handlerType;

    /** 
     * <p>Restituisce il valore di handlerType</p> 
     * 
     * @return      restituisce il valore di handlerType
     */ 
    public String getHandlerType() {
        return this.handlerType;
    }
    /** 
     * <p>Imposta il valore di handlerType</p> 
     * 
     * @param      handlerType il valore di handlerType da impostare
     */ 
    public void setHandlerType( String handlerType ) {
        this.handlerType = handlerType;
    }

	private String handlerConfig;

    /** 
     * <p>Restituisce il valore di handlerConfig</p> 
     * 
     * @return      restituisce il valore di handlerConfig
     */ 
    public String getHandlerConfig() {
        return this.handlerConfig;
    }
    /** 
     * <p>Imposta il valore di handlerConfig</p> 
     * 
     * @param      handlerConfig il valore di handlerConfig da impostare
     */ 
    public void setHandlerConfig( String handlerConfig ) {
        this.handlerConfig = handlerConfig;
    }

	private String handlerDescription;

    /** 
     * <p>Restituisce il valore di handlerDescription</p> 
     * 
     * @return      restituisce il valore di handlerDescription
     */ 
    public String getHandlerDescription() {
        return this.handlerDescription;
    }
    /** 
     * <p>Imposta il valore di handlerDescription</p> 
     * 
     * @param      handlerDescription il valore di handlerDescription da impostare
     */ 
    public void setHandlerDescription( String handlerDescription ) {
        this.handlerDescription = handlerDescription;
    }

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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsFilterHandlerBean getBean() {
        CmsFilterHandlerBean bean = new CmsFilterHandlerBean();
        bean.setIdCmsFilterHandler( formatObject(idCmsFilterHandler) );
        bean.setHandlerType( formatObject(handlerType) );
        bean.setHandlerConfig( formatObject(handlerConfig) );
        bean.setHandlerDescription( formatObject(handlerDescription) );
        bean.setIdCmsFilterChain( formatObject(idCmsFilterChain) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsFilterHandler=" );
        buffer.append( this.idCmsFilterHandler );
        buffer.append( "; " );
        buffer.append( "handlerType=" );
        buffer.append( this.handlerType );
        buffer.append( "; " );
        buffer.append( "handlerConfig=" );
        buffer.append( this.handlerConfig );
        buffer.append( "; " );
        buffer.append( "handlerDescription=" );
        buffer.append( this.handlerDescription );
        buffer.append( "; " );
        buffer.append( "idCmsFilterChain=" );
        buffer.append( this.idCmsFilterChain );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
