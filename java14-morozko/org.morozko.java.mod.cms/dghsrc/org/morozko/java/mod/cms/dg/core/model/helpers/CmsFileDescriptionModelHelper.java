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
 * @(#)CmsFileDescriptionModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 11/08/2006 15/13/08
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsFileDescriptionBean;

/**
 * <p>Oggetto di modello per CmsFileDescription.</p>
 *
 * @author Matteo Franci
 */
public class CmsFileDescriptionModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 115530198842172L;

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idCmsFile;

    /** 
     * <p>Restituisce il valore di idCmsFile</p> 
     * 
     * @return      restituisce il valore di idCmsFile
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsFile() {
        return this.idCmsFile;
    }
    /** 
     * <p>Imposta il valore di idCmsFile</p> 
     * 
     * @param      idCmsFile il valore di idCmsFile da impostare
     */ 
    public void setIdCmsFile( org.morozko.java.mod.db.dao.DAOID idCmsFile ) {
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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsFileDescriptionBean getBean() {
        CmsFileDescriptionBean bean = new CmsFileDescriptionBean();
        bean.setIdCmsFile( formatObject(idCmsFile) );
        bean.setFilePath( formatObject(filePath) );
        bean.setFileDisplay( formatObject(fileDisplay) );
        bean.setFileDescription( formatObject(fileDescription) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsFile=" );
        buffer.append( this.idCmsFile );
        buffer.append( "; " );
        buffer.append( "filePath=" );
        buffer.append( this.filePath );
        buffer.append( "; " );
        buffer.append( "fileDisplay=" );
        buffer.append( this.fileDisplay );
        buffer.append( "; " );
        buffer.append( "fileDescription=" );
        buffer.append( this.fileDescription );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
