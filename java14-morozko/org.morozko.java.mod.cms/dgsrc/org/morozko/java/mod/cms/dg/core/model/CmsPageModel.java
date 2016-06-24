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
 * @(#)CmsPageModel.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.model
 * @creation   : 22/05/2006 11/43/58
 */
package org.morozko.java.mod.cms.dg.core.model;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.helpers.CmsPageModelHelper;

/**
 * <p>Classe CmsPageModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageModel extends CmsPageModelHelper {

	public static final String ATT_NAME_DATA = "CmsPageModel.DATA";
	
//	public String getHtmlData() {
//		StringBuffer data = new StringBuffer();
//		Iterator it = this.getListCmsFragment().iterator();
//		while ( it.hasNext() ) {
//			CmsFragmentModel cmsFragmentModel = (CmsFragmentModel)it.next();
//			data.append( cmsFragmentModel.getHtmlContent() );
//		}
//		return data.toString();
//	}
	
	public String getDataFilePath() {
		return "/cms/pages/"+this.getIdCmsPage()+".html";
	}
	
	/**
	 * <p>Verifica se il campo pageUrl è impostato.</p>
	 * 
	 * <p>Se tale campo non è impostato gli viene assegnato il valore del campo idCmsPage con l'aggiunta del suffisso .html</p>
	 *
	 */
	public void checkPageUrl() {
		if ( this.getPageUrl() == null || this.getPageUrl().equals( "" ) ) {
			this.setPageUrl( String.valueOf( this.getIdCmsPage() )+".html" );
		}
	}	
	
	private final static long serialVersionUID = 114829103842156L;

	private List listKids;

	/**
	 * @return Restituisce il valore di listKids.
	 */
	public List getListKids() {
		return listKids;
	}

	/**
	 * @param listKids il valore di listKids da impostare.
	 */
	public void setListKids(List listKids) {
		this.listKids = listKids;
	}
	
}
