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
 * @(#)CmsFileDescriptionBean.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean
 * @creation   : 10/08/2006 10/13/49
 */
package org.morozko.java.mod.cms.dg.core.bean;

import org.apache.struts.upload.FormFile;
import org.morozko.java.mod.cms.dg.core.bean.helpers.CmsFileDescriptionBeanHelper;

/**
 * <p>Classe CmsFileDescriptionBean.</p>
 *
 * @author Matteo Franci
 */
public class CmsFileDescriptionBean extends CmsFileDescriptionBeanHelper {

	private FormFile fileData;
	
	/**
	 * @return the fileData
	 */
	public FormFile getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(FormFile fileData) {
		this.fileData = fileData;
	}	
	
	public CmsFileDescriptionBean() {
		this.setIdCmsFile( "0" );
	}
	
	private final static long serialVersionUID = 115519762950039L;

}
