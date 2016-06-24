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
 * @(#)CmsFilterHandlerDAO.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao
 * @creation   : 25/08/2006 13/51/52
 */
package org.morozko.java.mod.cms.dg.core.dao;

import org.morozko.java.mod.cms.dg.core.dao.helpers.CmsFilterHandlerDAOHelper;

/**
 * <p>Classe CmsFilterHandlerDAO.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterHandlerDAO extends CmsFilterHandlerDAOHelper {

	private final static long serialVersionUID = 115650671253184L;

    public CmsFilterHandlerDAO(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
