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
 * @(#)CmsPageModelRSEHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 29/08/2006 08/27/11
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti CmsPageModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;

public class CmsPageModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 11568328314842L;

    public Object extractNext(ResultSet rs) throws SQLException {
        CmsPageModel model = new CmsPageModel();
        model.setIdCmsPage( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_page") ) );
        model.setCmsPageCode( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("cms_page_code") ) );
        model.setCmsPageType( new Integer( rs.getInt("cms_page_type") ) );
        model.setCmsPageState( new Integer( rs.getInt("cms_page_state") ) );
        model.setSaveDate( rs.getTimestamp("save_date") );
        model.setDisplay( rs.getString("display") );
        model.setTitle( rs.getString("title") );
        model.setDescription( rs.getString("description") );
        model.setPageUrl( rs.getString("page_url") );
        model.setIdCmsPageParent( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_page_parent") ) );
        model.setIdCmsFilterChain( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_filter_chain") ) );
        model.setKidsCount( new Long( rs.getLong("kids_count" ) ) );
        return model;
    }

}
