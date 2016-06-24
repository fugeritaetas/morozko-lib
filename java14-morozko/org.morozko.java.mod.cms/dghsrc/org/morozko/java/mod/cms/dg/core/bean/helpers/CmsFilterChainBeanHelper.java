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
 * @(#)CmsFilterChainBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel;

/**
 * <p>Bean per oggetti di tipo CmsFilterChainModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterChainBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 115651347807825L;

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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsFilterChainModel getModel() {
        CmsFilterChainModel model = new CmsFilterChainModel();
        model.setIdCmsFilterChain( toDAOID(idCmsFilterChain) );
        model.setChainName( toString(chainName) );
        model.setChainDescription( toString(chainDescription) );
        return model;
    }

}
