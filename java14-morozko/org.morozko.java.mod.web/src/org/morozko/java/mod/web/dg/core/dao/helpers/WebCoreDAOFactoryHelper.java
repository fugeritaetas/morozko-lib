/*
 * @(#)WebCoreDAOFactoryHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.core.dao.helpers
 * @creation   : 03/10/2007 08/35/38
 */
package org.morozko.java.mod.web.dg.core.dao.helpers;

import org.morozko.java.mod.web.dg.core.dao.WebAccessDataDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Morozko
 */
public class WebCoreDAOFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 119139333878458L;

	private WebAccessDataDAO webAccessDataDAO;

	public WebCoreDAOFactoryHelper( org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.webAccessDataDAO = new WebAccessDataDAO(daoFactory);
		this.webAccessDataDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di webAccessDataDAO</p> 
     * 
     * @return      restituisce il valore di webAccessDataDAO
     */ 
    public WebAccessDataDAO getWebAccessDataDAO() {
        return this.webAccessDataDAO;
    }

}
