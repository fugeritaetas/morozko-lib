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
 * @(#)CmsFilterHandlerModelRSEHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti CmsFilterHandlerModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.cms.dg.core.model.CmsFilterHandlerModel;

public class CmsFilterHandlerModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 11565134782187L;

    public Object extractNext(ResultSet rs) throws SQLException {
        CmsFilterHandlerModel model = new CmsFilterHandlerModel();
        model.setIdCmsFilterHandler( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_filter_handler") ) );
        model.setHandlerType( rs.getString("handler_type") );
        model.setHandlerConfig( rs.getString("handler_config") );
        model.setHandlerDescription( rs.getString("handler_description") );
        model.setIdCmsFilterChain( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_filter_chain") ) );
        return model;
    }

}
