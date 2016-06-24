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
 * @(#)CmsFileDescriptionBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 11/08/2006 15/13/08
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsFileDescriptionModel;

/**
 * <p>Bean per oggetti di tipo CmsFileDescriptionModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFileDescriptionBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 115530198843780L;

	private String idCmsFile;

    /** 
     * <p>Restituisce il valore di idCmsFile</p> 
     * 
     * @return      restituisce il valore di idCmsFile
     */ 
    public String getIdCmsFile() {
        return this.idCmsFile;
    }
    /** 
     * <p>Imposta il valore di idCmsFile</p> 
     * 
     * @param      idCmsFile il valore di idCmsFile da impostare
     */ 
    public void setIdCmsFile( String idCmsFile ) {
        this.idCmsFile = idCmsFile;
    }
	private String filePath;

    /** 
     * <p>Restituisce il valore di filePath</p> 
     * 
     * @return      restituisce il valore di filePath
     */ 
    public String getFilePath() {
        return this.filePath;
    }
    /** 
     * <p>Imposta il valore di filePath</p> 
     * 
     * @param      filePath il valore di filePath da impostare
     */ 
    public void setFilePath( String filePath ) {
        this.filePath = filePath;
    }
	private String fileDisplay;

    /** 
     * <p>Restituisce il valore di fileDisplay</p> 
     * 
     * @return      restituisce il valore di fileDisplay
     */ 
    public String getFileDisplay() {
        return this.fileDisplay;
    }
    /** 
     * <p>Imposta il valore di fileDisplay</p> 
     * 
     * @param      fileDisplay il valore di fileDisplay da impostare
     */ 
    public void setFileDisplay( String fileDisplay ) {
        this.fileDisplay = fileDisplay;
    }
	private String fileDescription;

    /** 
     * <p>Restituisce il valore di fileDescription</p> 
     * 
     * @return      restituisce il valore di fileDescription
     */ 
    public String getFileDescription() {
        return this.fileDescription;
    }
    /** 
     * <p>Imposta il valore di fileDescription</p> 
     * 
     * @param      fileDescription il valore di fileDescription da impostare
     */ 
    public void setFileDescription( String fileDescription ) {
        this.fileDescription = fileDescription;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsFileDescriptionModel getModel() {
        CmsFileDescriptionModel model = new CmsFileDescriptionModel();
        model.setIdCmsFile( toDAOID(idCmsFile) );
        model.setFilePath( toString(filePath) );
        model.setFileDisplay( toString(fileDisplay) );
        model.setFileDescription( toString(fileDescription) );
        return model;
    }

}
