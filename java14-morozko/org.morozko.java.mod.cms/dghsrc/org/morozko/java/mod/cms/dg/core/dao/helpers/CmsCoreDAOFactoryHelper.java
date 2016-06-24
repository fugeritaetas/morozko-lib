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
 * @(#)CmsCoreDAOFactoryHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 29/08/2006 08/27/11
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import org.morozko.java.mod.cms.dg.core.dao.CmsFileDescriptionDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsFilterChainDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsFilterHandlerDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsMenuDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsMenuItemDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsRelPfDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsRelPmDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Matteo Franci
 */
public class CmsCoreDAOFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 115683283154625L;

	private CmsFilterChainDAO cmsFilterChainDAO;
	private CmsFilterHandlerDAO cmsFilterHandlerDAO;
	private CmsFileDescriptionDAO cmsFileDescriptionDAO;
	private CmsMenuDAO cmsMenuDAO;
	private CmsMenuItemDAO cmsMenuItemDAO;
	private CmsPageDAO cmsPageDAO;
	private CmsRelPfDAO cmsRelPfDAO;
	private CmsRelPmDAO cmsRelPmDAO;

	public CmsCoreDAOFactoryHelper( org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.cmsFilterChainDAO = new CmsFilterChainDAO(daoFactory);
		this.cmsFilterChainDAO.setModuleDaoFactory(this);
		this.cmsFilterHandlerDAO = new CmsFilterHandlerDAO(daoFactory);
		this.cmsFilterHandlerDAO.setModuleDaoFactory(this);
		this.cmsFileDescriptionDAO = new CmsFileDescriptionDAO(daoFactory);
		this.cmsFileDescriptionDAO.setModuleDaoFactory(this);
		this.cmsMenuDAO = new CmsMenuDAO(daoFactory);
		this.cmsMenuDAO.setModuleDaoFactory(this);
		this.cmsMenuItemDAO = new CmsMenuItemDAO(daoFactory);
		this.cmsMenuItemDAO.setModuleDaoFactory(this);
		this.cmsPageDAO = new CmsPageDAO(daoFactory);
		this.cmsPageDAO.setModuleDaoFactory(this);
		this.cmsRelPfDAO = new CmsRelPfDAO(daoFactory);
		this.cmsRelPfDAO.setModuleDaoFactory(this);
		this.cmsRelPmDAO = new CmsRelPmDAO(daoFactory);
		this.cmsRelPmDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di cmsFilterChainDAO</p> 
     * 
     * @return      restituisce il valore di cmsFilterChainDAO
     */ 
    public CmsFilterChainDAO getCmsFilterChainDAO() {
        return this.cmsFilterChainDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsFilterHandlerDAO</p> 
     * 
     * @return      restituisce il valore di cmsFilterHandlerDAO
     */ 
    public CmsFilterHandlerDAO getCmsFilterHandlerDAO() {
        return this.cmsFilterHandlerDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsFileDescriptionDAO</p> 
     * 
     * @return      restituisce il valore di cmsFileDescriptionDAO
     */ 
    public CmsFileDescriptionDAO getCmsFileDescriptionDAO() {
        return this.cmsFileDescriptionDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsMenuDAO</p> 
     * 
     * @return      restituisce il valore di cmsMenuDAO
     */ 
    public CmsMenuDAO getCmsMenuDAO() {
        return this.cmsMenuDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsMenuItemDAO</p> 
     * 
     * @return      restituisce il valore di cmsMenuItemDAO
     */ 
    public CmsMenuItemDAO getCmsMenuItemDAO() {
        return this.cmsMenuItemDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsPageDAO</p> 
     * 
     * @return      restituisce il valore di cmsPageDAO
     */ 
    public CmsPageDAO getCmsPageDAO() {
        return this.cmsPageDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsRelPfDAO</p> 
     * 
     * @return      restituisce il valore di cmsRelPfDAO
     */ 
    public CmsRelPfDAO getCmsRelPfDAO() {
        return this.cmsRelPfDAO;
    }
    /** 
     * <p>Restituisce il valore di cmsRelPmDAO</p> 
     * 
     * @return      restituisce il valore di cmsRelPmDAO
     */ 
    public CmsRelPmDAO getCmsRelPmDAO() {
        return this.cmsRelPmDAO;
    }

}
