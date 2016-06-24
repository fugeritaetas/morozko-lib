/*
 * @(#)UserGroupDAO.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao
 * @creation   : 21/08/2009 15/37/28
 */
package org.morozko.java.mod.web.dg.user.dao;

import org.morozko.java.mod.web.dg.user.dao.helpers.UserGroupDAOHelper;

/**
 * <p>Classe UserGroupDAO.</p>
 *
 * @author Morozko
 */
public class UserGroupDAO extends UserGroupDAOHelper {

	private final static long serialVersionUID = 125086184882573L;

    public UserGroupDAO(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
