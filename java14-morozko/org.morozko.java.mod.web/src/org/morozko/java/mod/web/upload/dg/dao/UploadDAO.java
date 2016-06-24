/*
 * @(#)UploadDAO.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.dao
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.dao;

import org.morozko.java.mod.web.upload.dg.dao.helpers.UploadDAOHelper;

/**
 * <p>Classe UploadDAO.</p>
 *
 * @author Morozko
 */
public class UploadDAO extends UploadDAOHelper {

	private final static long serialVersionUID = 131637056794185L;

    public UploadDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
