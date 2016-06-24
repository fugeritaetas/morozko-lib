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
 * @(#)CmsFilterHandlerBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsFilterHandlerModel;

/**
 * <p>Bean per oggetti di tipo CmsFilterHandlerModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterHandlerBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 115651347820331L;

	private String idCmsFilterHandler;

    /** 
     * <p>Restituisce il valore di idCmsFilterHandler</p> 
     * 
     * @return      restituisce il valore di idCmsFilterHandler
     */ 
    public String getIdCmsFilterHandler() {
        return this.idCmsFilterHandler;
    }
    /** 
     * <p>Imposta il valore di idCmsFilterHandler</p> 
     * 
     * @param      idCmsFilterHandler il valore di idCmsFilterHandler da impostare
     */ 
    public void setIdCmsFilterHandler( String idCmsFilterHandler ) {
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
	private String idCmsFilterChain;

    /** 
     * <p>Restituisce il valore di idCmsFilterChain</p> 
     * 
     * @return      restituisce il valore di idCmsFilterChain
     */ 
    public String getIdCmsFilterChain() {
        return this.idCmsFilterChain;
    }
    /** 
     * <p>Imposta il valore di idCmsFilterChain</p> 
     * 
     * @param      idCmsFilterChain il valore di idCmsFilterChain da impostare
     */ 
    public void setIdCmsFilterChain( String idCmsFilterChain ) {
        this.idCmsFilterChain = idCmsFilterChain;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsFilterHandlerModel getModel() {
        CmsFilterHandlerModel model = new CmsFilterHandlerModel();
        model.setIdCmsFilterHandler( toDAOID(idCmsFilterHandler) );
        model.setHandlerType( toString(handlerType) );
        model.setHandlerConfig( toString(handlerConfig) );
        model.setHandlerDescription( toString(handlerDescription) );
        model.setIdCmsFilterChain( toDAOID(idCmsFilterChain) );
        return model;
    }

}
