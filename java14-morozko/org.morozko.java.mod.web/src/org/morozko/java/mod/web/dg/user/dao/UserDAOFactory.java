/*
 * @(#)UserDAOFactory.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao
 * @creation   : 21/08/2009 15/34/35
 */
package org.morozko.java.mod.web.dg.user.dao;

import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.helpers.UserDAOFactoryHelper;

/**
 * <p>Classe UserDAOFactory.</p>
 *
 * @author Morozko
 */
public class UserDAOFactory extends UserDAOFactoryHelper {

	public UserDAOFactory(WebDAOFactory daoFactory) {
		super(daoFactory);
	}

	private final static long serialVersionUID = 125086167552691L;

}
