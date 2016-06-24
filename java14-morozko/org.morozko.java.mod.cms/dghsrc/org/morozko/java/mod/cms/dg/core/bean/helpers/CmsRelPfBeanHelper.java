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
 * @(#)CmsRelPfBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 07/06/2006 14/39/20
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsRelPfModel;

/**
 * <p>Bean per oggetti di tipo CmsRelPfModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPfBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 114968396076538L;

	private String idCmsRelPf;

    /** 
     * <p>Restituisce il valore di idCmsRelPf</p> 
     * 
     * @return      restituisce il valore di idCmsRelPf
     */ 
    public String getIdCmsRelPf() {
        return this.idCmsRelPf;
    }
    /** 
     * <p>Imposta il valore di idCmsRelPf</p> 
     * 
     * @param      idCmsRelPf il valore di idCmsRelPf da impostare
     */ 
    public void setIdCmsRelPf( String idCmsRelPf ) {
        this.idCmsRelPf = idCmsRelPf;
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
	private String idCmsFragment;

    /** 
     * <p>Restituisce il valore di idCmsFragment</p> 
     * 
     * @return      restituisce il valore di idCmsFragment
     */ 
    public String getIdCmsFragment() {
        return this.idCmsFragment;
    }
    /** 
     * <p>Imposta il valore di idCmsFragment</p> 
     * 
     * @param      idCmsFragment il valore di idCmsFragment da impostare
     */ 
    public void setIdCmsFragment( String idCmsFragment ) {
        this.idCmsFragment = idCmsFragment;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsRelPfModel getModel() {
        CmsRelPfModel model = new CmsRelPfModel();
        model.setIdCmsRelPf( toDAOID(idCmsRelPf) );
        model.setIdCmsPage( toDAOID(idCmsPage) );
        model.setIdCmsFragment( toDAOID(idCmsFragment) );
        return model;
    }

}
