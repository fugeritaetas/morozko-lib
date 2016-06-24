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
 * @(#)CmsRelPfModelRSEHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 07/06/2006 14/39/20
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti CmsRelPfModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.cms.dg.core.model.CmsRelPfModel;

public class CmsRelPfModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 114968396076570L;

    public Object extractNext(ResultSet rs) throws SQLException {
        CmsRelPfModel model = new CmsRelPfModel();
        model.setIdCmsRelPf( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_rel_pf") ) );
        model.setIdCmsPage( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_page") ) );
        model.setIdCmsFragment( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_fragment") ) );
        return model;
    }

}
