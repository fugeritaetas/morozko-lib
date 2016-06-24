/*
 * @(#)UploadDAOFactoryHelper.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.dao.helpers
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.dao.helpers;

import org.morozko.java.mod.web.upload.dg.dao.UploadDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Morozko
 */
public class UploadDAOFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 13163705679880L;

	private UploadDAO uploadDAO;

	public UploadDAOFactoryHelper( org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.uploadDAO = new UploadDAO(daoFactory);
		this.uploadDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di uploadDAO</p> 
     * 
     * @return      restituisce il valore di uploadDAO
     */ 
    public UploadDAO getUploadDAO() {
        return this.uploadDAO;
    }

}
