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
 * @(#)CmsPageBeanHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean.helpers
 * @creation   : 29/08/2006 08/27/11
 */
package org.morozko.java.mod.cms.dg.core.bean.helpers;

import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;

/**
 * <p>Bean per oggetti di tipo CmsPageModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 115683283148417L;

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
	private String cmsPageCode;

    /** 
     * <p>Restituisce il valore di cmsPageCode</p> 
     * 
     * @return      restituisce il valore di cmsPageCode
     */ 
    public String getCmsPageCode() {
        return this.cmsPageCode;
    }
    /** 
     * <p>Imposta il valore di cmsPageCode</p> 
     * 
     * @param      cmsPageCode il valore di cmsPageCode da impostare
     */ 
    public void setCmsPageCode( String cmsPageCode ) {
        this.cmsPageCode = cmsPageCode;
    }
	private String cmsPageType;

    /** 
     * <p>Restituisce il valore di cmsPageType</p> 
     * 
     * @return      restituisce il valore di cmsPageType
     */ 
    public String getCmsPageType() {
        return this.cmsPageType;
    }
    /** 
     * <p>Imposta il valore di cmsPageType</p> 
     * 
     * @param      cmsPageType il valore di cmsPageType da impostare
     */ 
    public void setCmsPageType( String cmsPageType ) {
        this.cmsPageType = cmsPageType;
    }
	private String cmsPageState;

    /** 
     * <p>Restituisce il valore di cmsPageState</p> 
     * 
     * @return      restituisce il valore di cmsPageState
     */ 
    public String getCmsPageState() {
        return this.cmsPageState;
    }
    /** 
     * <p>Imposta il valore di cmsPageState</p> 
     * 
     * @param      cmsPageState il valore di cmsPageState da impostare
     */ 
    public void setCmsPageState( String cmsPageState ) {
        this.cmsPageState = cmsPageState;
    }
	private String saveDate;

    /** 
     * <p>Restituisce il valore di saveDate</p> 
     * 
     * @return      restituisce il valore di saveDate
     */ 
    public String getSaveDate() {
        return this.saveDate;
    }
    /** 
     * <p>Imposta il valore di saveDate</p> 
     * 
     * @param      saveDate il valore di saveDate da impostare
     */ 
    public void setSaveDate( String saveDate ) {
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
	private String idCmsPageParent;

    /** 
     * <p>Restituisce il valore di idCmsPageParent</p> 
     * 
     * @return      restituisce il valore di idCmsPageParent
     */ 
    public String getIdCmsPageParent() {
        return this.idCmsPageParent;
    }
    /** 
     * <p>Imposta il valore di idCmsPageParent</p> 
     * 
     * @param      idCmsPageParent il valore di idCmsPageParent da impostare
     */ 
    public void setIdCmsPageParent( String idCmsPageParent ) {
        this.idCmsPageParent = idCmsPageParent;
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
	private String kidsCount;

    /** 
     * <p>Restituisce il valore di kidsCount</p> 
     * 
     * @return      restituisce il valore di kidsCount
     */ 
    public String getKidsCount() {
        return this.kidsCount;
    }
    /** 
     * <p>Imposta il valore di kidsCount</p> 
     * 
     * @param      kidsCount il valore di kidsCount da impostare
     */ 
    public void setKidsCount( String kidsCount ) {
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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public CmsPageModel getModel() {
        CmsPageModel model = new CmsPageModel();
        model.setIdCmsPage( toDAOID(idCmsPage) );
        model.setCmsPageCode( toDAOID(cmsPageCode) );
        model.setCmsPageType( toInteger(cmsPageType) );
        model.setCmsPageState( toInteger(cmsPageState) );
        model.setSaveDate( toTimestamp(saveDate) );
        model.setDisplay( toString(display) );
        model.setTitle( toString(title) );
        model.setDescription( toString(description) );
        model.setPageUrl( toString(pageUrl) );
        model.setIdCmsPageParent( toDAOID(idCmsPageParent) );
        model.setIdCmsFilterChain( toDAOID(idCmsFilterChain) );
        model.setKidsCount( toLong(kidsCount) );
        model.setHtmlData( toString(htmlData) );
        return model;
    }

}
