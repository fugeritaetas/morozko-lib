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
 * @(#)CmsFilterChainBean.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.bean
 * @creation   : 25/08/2006 13/51/52
 */
package org.morozko.java.mod.cms.dg.core.bean;

import org.morozko.java.mod.cms.dg.core.bean.helpers.CmsFilterChainBeanHelper;

/**
 * <p>Classe CmsFilterChainBean.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterChainBean extends CmsFilterChainBeanHelper {

	/**
	 * <p>Creates a new instance of CmsFilterChainBean.</p>
	 *
	 */
	public CmsFilterChainBean() {
		super();
		this.setIdCmsFilterChain( "0" );
	}

	private final static long serialVersionUID = 115650671250050L;

}
