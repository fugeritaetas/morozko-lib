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
 * @(#)CmsPageDAO.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao
 * @creation   : 22/05/2006 11/43/58
 */
package org.morozko.java.mod.cms.dg.core.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.mod.cms.dg.core.dao.helpers.CmsPageDAOHelper;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.FieldList;

/**
 * <p>Classe CmsPageDAO.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageDAO extends CmsPageDAOHelper {

	public void saveData( CmsPageModel cmsPageModel ) throws DAOException {
		if ( cmsPageModel != null ) {
			JFile jfile = CmsConfig.getInstance().getJvfs().getJFile( cmsPageModel.getDataFilePath() );
			try {
				StreamIO.pipeStream( new ByteArrayInputStream( cmsPageModel.getHtmlData().getBytes() ) , jfile.getOutputStream() , StreamIO.MODE_CLOSE_BOTH );
			} catch (IOException e) {
				throw ( new DAOException( e ) );
			}
		}
	}
	
	public CmsPageModel loadData( CmsPageModel cmsPageModel ) throws DAOException {
		if ( cmsPageModel != null ) {
			JFile jfile = CmsConfig.getInstance().getJvfs().getJFile( cmsPageModel.getDataFilePath() );
			try {
				if ( jfile.exists() ) {
					cmsPageModel.setHtmlData( StreamIO.readString( jfile.getInputStream() ) );
				} else {
					cmsPageModel.setHtmlData( "" );
				}
			} catch (IOException e) {
				throw ( new DAOException( e ) );
			}
		}
		return cmsPageModel;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.dg.core.dao.helpers.CmsPageDAOHelper#loadOneCmsPagePk(org.morozko.java.mod.db.dao.DAOID)
	 */
	public CmsPageModel loadOneCmsPagePk(DAOID id_cms_page) throws DAOException {
		return this.loadData( super.loadOneCmsPagePk(id_cms_page) );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.dg.core.dao.helpers.CmsPageDAOHelper#loadOneCmsPageUn2(java.lang.String)
	 */
	public CmsPageModel loadOneCmsPageUn2(String page_url) throws DAOException {
		return this.loadData( super.loadOneCmsPageUn2(page_url) );
	}

	private final static long serialVersionUID = 114829103842197L;
	
    public CmsPageDAO(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

    public List loadKids( DAOID idCmsPage ) throws DAOException {
    	List list = this.newList();
    	String sql = " SELECT v.* FROM ( "+this.queryView+" ) v WHERE v.id_cms_page_parent=?";
    	FieldList fl = this.newFieldList();
    	fl.addField( idCmsPage );
    	this.loadAll( list, sql , fl );
    	return list;
    }
    
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.dg.core.dao.helpers.CmsPageDAOHelper#loadAllRelations(org.morozko.java.mod.cms.dg.core.model.CmsPageModel)
	 */
	public void loadAllRelations(CmsPageModel model) throws DAOException {
		super.loadAllRelations(model);
		if ( model != null ) {
			model.setListKids( this.loadKids( model.getIdCmsPage() ) );
		}
	}

}
