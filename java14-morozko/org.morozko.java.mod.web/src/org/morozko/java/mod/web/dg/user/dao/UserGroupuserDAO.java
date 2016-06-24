/*
 * @(#)UserGroupuserDAO.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao
 * @creation   : 21/08/2009 15/37/28
 */
package org.morozko.java.mod.web.dg.user.dao;

import org.morozko.java.mod.web.dg.user.dao.helpers.UserGroupuserDAOHelper;

/**
 * <p>Classe UserGroupuserDAO.</p>
 *
 * @author Morozko
 */
public class UserGroupuserDAO extends UserGroupuserDAOHelper {

	private final static long serialVersionUID = 125086184889597L;

    public UserGroupuserDAO(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
