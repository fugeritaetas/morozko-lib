/*
 * @(#)UserDAOFactoryHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

import org.morozko.java.mod.web.dg.user.dao.UserAccountDAO;
import org.morozko.java.mod.web.dg.user.dao.UserGroupDAO;
import org.morozko.java.mod.web.dg.user.dao.UserGroupuserDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Morozko
 */
public class UserDAOFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 125112004106176L;

	private UserAccountDAO userAccountDAO;
	private UserGroupDAO userGroupDAO;
	private UserGroupuserDAO userGroupuserDAO;

	public UserDAOFactoryHelper( org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.userAccountDAO = new UserAccountDAO(daoFactory);
		this.userAccountDAO.setModuleDaoFactory(this);
		this.userGroupDAO = new UserGroupDAO(daoFactory);
		this.userGroupDAO.setModuleDaoFactory(this);
		this.userGroupuserDAO = new UserGroupuserDAO(daoFactory);
		this.userGroupuserDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di userAccountDAO</p> 
     * 
     * @return      restituisce il valore di userAccountDAO
     */ 
    public UserAccountDAO getUserAccountDAO() {
        return this.userAccountDAO;
    }
    /** 
     * <p>Restituisce il valore di userGroupDAO</p> 
     * 
     * @return      restituisce il valore di userGroupDAO
     */ 
    public UserGroupDAO getUserGroupDAO() {
        return this.userGroupDAO;
    }
    /** 
     * <p>Restituisce il valore di userGroupuserDAO</p> 
     * 
     * @return      restituisce il valore di userGroupuserDAO
     */ 
    public UserGroupuserDAO getUserGroupuserDAO() {
        return this.userGroupuserDAO;
    }

}
