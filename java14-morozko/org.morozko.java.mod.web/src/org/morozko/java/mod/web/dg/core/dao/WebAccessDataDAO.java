/*
 * @(#)WebAccessDataDAO.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.core.dao
 * @creation   : 03/10/2007 08/00/13
 */
package org.morozko.java.mod.web.dg.core.dao;

import org.morozko.java.mod.web.dg.core.dao.helpers.WebAccessDataDAOHelper;

/**
 * <p>Classe WebAccessDataDAO.</p>
 *
 * @author Morozko
 */
public class WebAccessDataDAO extends WebAccessDataDAOHelper {

	private final static long serialVersionUID = 119139121396212L;

    public WebAccessDataDAO(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
