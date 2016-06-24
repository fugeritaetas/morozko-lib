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
 * @(#)CmsPageModelHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model.helpers
 * @creation   : 29/08/2006 08/27/11
 */
package org.morozko.java.mod.cms.dg.core.model.helpers;

import org.morozko.java.mod.cms.dg.core.bean.CmsPageBean;

/**
 * <p>Oggetto di modello per CmsPage.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 115683283143721L;


	public static final String ATT_NAME = "cmsPageModel";

    // campi relativi alla tabella - START 

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

	private org.morozko.java.mod.db.dao.DAOID cmsPageCode;

    /** 
     * <p>Restituisce il valore di cmsPageCode</p> 
     * 
     * @return      restituisce il valore di cmsPageCode
     */ 
    public org.morozko.java.mod.db.dao.DAOID getCmsPageCode() {
        return this.cmsPageCode;
    }
    /** 
     * <p>Imposta il valore di cmsPageCode</p> 
     * 
     * @param      cmsPageCode il valore di cmsPageCode da impostare
     */ 
    public void setCmsPageCode( org.morozko.java.mod.db.dao.DAOID cmsPageCode ) {
        this.cmsPageCode = cmsPageCode;
    }

	private Integer cmsPageType;

    /** 
     * <p>Restituisce il valore di cmsPageType</p> 
     * 
     * @return      restituisce il valore di cmsPageType
     */ 
    public Integer getCmsPageType() {
        return this.cmsPageType;
    }
    /** 
     * <p>Imposta il valore di cmsPageType</p> 
     * 
     * @param      cmsPageType il valore di cmsPageType da impostare
     */ 
    public void setCmsPageType( Integer cmsPageType ) {
        this.cmsPageType = cmsPageType;
    }

	private Integer cmsPageState;

    /** 
     * <p>Restituisce il valore di cmsPageState</p> 
     * 
     * @return      restituisce il valore di cmsPageState
     */ 
    public Integer getCmsPageState() {
        return this.cmsPageState;
    }
    /** 
     * <p>Imposta il valore di cmsPageState</p> 
     * 
     * @param      cmsPageState il valore di cmsPageState da impostare
     */ 
    public void setCmsPageState( Integer cmsPageState ) {
        this.cmsPageState = cmsPageState;
    }

	private java.sql.Timestamp saveDate;

    /** 
     * <p>Restituisce il valore di saveDate</p> 
     * 
     * @return      restituisce il valore di saveDate
     */ 
    public java.sql.Timestamp getSaveDate() {
        return this.saveDate;
    }
    /** 
     * <p>Imposta il valore di saveDate</p> 
     * 
     * @param      saveDate il valore di saveDate da impostare
     */ 
    public void setSaveDate( java.sql.Timestamp saveDate ) {
        this.saveDate = saveDate;
    }

	private String display;

    /** 
     * <p>Restituisce il valore di display</p> 
     * 
     * @return      restituisce il valore di display
     */ 
    public String getDisplay() {
        return this.display;
    }
    /** 
     * <p>Imposta il valore di display</p> 
     * 
     * @param      display il valore di display da impostare
     */ 
    public void setDisplay( String display ) {
        this.display = display;
    }

	private String title;

    /** 
     * <p>Restituisce il valore di title</p> 
     * 
     * @return      restituisce il valore di title
     */ 
    public String getTitle() {
        return this.title;
    }
    /** 
     * <p>Imposta il valore di title</p> 
     * 
     * @param      title il valore di title da impostare
     */ 
    public void setTitle( String title ) {
        this.title = title;
    }

	private String description;

    /** 
     * <p>Restituisce il valore di description</p> 
     * 
     * @return      restituisce il valore di description
     */ 
    public String getDescription() {
        return this.description;
    }
    /** 
     * <p>Imposta il valore di description</p> 
     * 
     * @param      description il valore di description da impostare
     */ 
    public void setDescription( String description ) {
        this.description = description;
    }

	private String pageUrl;

    /** 
     * <p>Restituisce il valore di pageUrl</p> 
     * 
     * @return      restituisce il valore di pageUrl
     */ 
    public String getPageUrl() {
        return this.pageUrl;
    }
    /** 
     * <p>Imposta il valore di pageUrl</p> 
     * 
     * @param      pageUrl il valore di pageUrl da impostare
     */ 
    public void setPageUrl( String pageUrl ) {
        this.pageUrl = pageUrl;
    }

	private org.morozko.java.mod.db.dao.DAOID idCmsPageParent;

    /** 
     * <p>Restituisce il valore di idCmsPageParent</p> 
     * 
     * @return      restituisce il valore di idCmsPageParent
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdCmsPageParent() {
        return this.idCmsPageParent;
    }
    /** 
     * <p>Imposta il valore di idCmsPageParent</p> 
     * 
     * @param      idCmsPageParent il valore di idCmsPageParent da impostare
     */ 
    public void setIdCmsPageParent( org.morozko.java.mod.db.dao.DAOID idCmsPageParent ) {
        this.idCmsPageParent = idCmsPageParent;
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

	private Long kidsCount;

    /** 
     * <p>Restituisce il valore di kidsCount</p> 
     * 
     * @return      restituisce il valore di kidsCount
     */ 
    public Long getKidsCount() {
        return this.kidsCount;
    }
    /** 
     * <p>Imposta il valore di kidsCount</p> 
     * 
     * @param      kidsCount il valore di kidsCount da impostare
     */ 
    public void setKidsCount( Long kidsCount ) {
        this.kidsCount = kidsCount;
    }

	private String htmlData;

    /** 
     * <p>Restituisce il valore di htmlData</p> 
     * 
     * @return      restituisce il valore di htmlData
     */ 
    public String getHtmlData() {
        return this.htmlData;
    }
    /** 
     * <p>Imposta il valore di htmlData</p> 
     * 
     * @param      htmlData il valore di htmlData da impostare
     */ 
    public void setHtmlData( String htmlData ) {
        this.htmlData = htmlData;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel cmsFilterChain;

    /** 
     * <p>Restituisce il valore di cmsFilterChain</p> 
     * 
     * @return      restituisce il valore di cmsFilterChain
     */ 
    public org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel getCmsFilterChain() {
        return this.cmsFilterChain;
    }

    /** 
     * <p>Imposta il valore di cmsFilterChain</p> 
     * 
     * @param      cmsFilterChain il valore di cmsFilterChain da impostare
     */ 
    public void setCmsFilterChain( org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel cmsFilterChain ) {
        this.cmsFilterChain = cmsFilterChain;
    }

    // campi relativi a relazioni - END 

    public CmsPageBean getBean() {
        CmsPageBean bean = new CmsPageBean();
        bean.setIdCmsPage( formatObject(idCmsPage) );
        bean.setCmsPageCode( formatObject(cmsPageCode) );
        bean.setCmsPageType( formatObject(cmsPageType) );
        bean.setCmsPageState( formatObject(cmsPageState) );
        bean.setSaveDate( formatObject(saveDate) );
        bean.setDisplay( formatObject(display) );
        bean.setTitle( formatObject(title) );
        bean.setDescription( formatObject(description) );
        bean.setPageUrl( formatObject(pageUrl) );
        bean.setIdCmsPageParent( formatObject(idCmsPageParent) );
        bean.setIdCmsFilterChain( formatObject(idCmsFilterChain) );
        bean.setKidsCount( formatObject(kidsCount) );
        bean.setHtmlData( formatObject(htmlData) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idCmsPage=" );
        buffer.append( this.idCmsPage );
        buffer.append( "; " );
        buffer.append( "cmsPageCode=" );
        buffer.append( this.cmsPageCode );
        buffer.append( "; " );
        buffer.append( "cmsPageType=" );
        buffer.append( this.cmsPageType );
        buffer.append( "; " );
        buffer.append( "cmsPageState=" );
        buffer.append( this.cmsPageState );
        buffer.append( "; " );
        buffer.append( "saveDate=" );
        buffer.append( this.saveDate );
        buffer.append( "; " );
        buffer.append( "display=" );
        buffer.append( this.display );
        buffer.append( "; " );
        buffer.append( "title=" );
        buffer.append( this.title );
        buffer.append( "; " );
        buffer.append( "description=" );
        buffer.append( this.description );
        buffer.append( "; " );
        buffer.append( "pageUrl=" );
        buffer.append( this.pageUrl );
        buffer.append( "; " );
        buffer.append( "idCmsPageParent=" );
        buffer.append( this.idCmsPageParent );
        buffer.append( "; " );
        buffer.append( "idCmsFilterChain=" );
        buffer.append( this.idCmsFilterChain );
        buffer.append( "; " );
        buffer.append( "kidsCount=" );
        buffer.append( this.kidsCount );
        buffer.append( "; " );
        buffer.append( "htmlData=" );
        buffer.append( this.htmlData );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
