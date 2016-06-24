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
 * @(#)CmsFileDescriptionModelRSEHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 11/08/2006 15/13/08
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti CmsFileDescriptionModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.cms.dg.core.model.CmsFileDescriptionModel;

public class CmsFileDescriptionModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 11553019884378L;

    public Object extractNext(ResultSet rs) throws SQLException {
        CmsFileDescriptionModel model = new CmsFileDescriptionModel();
        model.setIdCmsFile( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_cms_file") ) );
        model.setFilePath( rs.getString("file_path") );
        model.setFileDisplay( rs.getString("file_display") );
        model.setFileDescription( rs.getString("file_description") );
        return model;
    }

}
