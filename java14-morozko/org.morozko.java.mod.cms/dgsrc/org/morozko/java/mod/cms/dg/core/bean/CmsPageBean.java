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
 * @(#)CmsPageBean.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean
 * @creation   : 22/05/2006 11/43/58
 */
package org.morozko.java.mod.cms.dg.core.bean;

import org.morozko.java.mod.cms.dg.core.bean.helpers.CmsPageBeanHelper;

/**
 * <p>Classe CmsPageBean.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageBean extends CmsPageBeanHelper {

	private final static long serialVersionUID = 114829103842152L;

	public CmsPageBean() {
		this.setIdCmsPage( "0" );
		this.setIdCmsPageParent( "1" );
		this.setCmsPageType( "1" );
		this.setCmsPageState( "1" );
	}
	
}
