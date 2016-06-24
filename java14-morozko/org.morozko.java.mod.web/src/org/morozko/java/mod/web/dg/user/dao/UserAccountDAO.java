/*
 * @(#)UserAccountDAO.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao
 * @creation   : 21/08/2009 15/37/28
 */
package org.morozko.java.mod.web.dg.user.dao;

import org.morozko.java.mod.web.dg.user.dao.helpers.UserAccountDAOHelper;

/**
 * <p>Classe UserAccountDAO.</p>
 *
 * @author Morozko
 */
public class UserAccountDAO extends UserAccountDAOHelper {

	private final static long serialVersionUID = 125086184880594L;

    public UserAccountDAO(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
