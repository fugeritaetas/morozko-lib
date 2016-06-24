/*
 * @(#)PropsCatalogDAO.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao
 * @creation   : 03/04/2008 15/09/17
 */
package org.morozko.java.mod.db.props.dg.dao;

import org.morozko.java.mod.db.props.dg.dao.helpers.PropsCatalogDAOHelper;

/**
 * <p>Classe PropsCatalogDAO.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsCatalogDAO extends PropsCatalogDAOHelper {

	private final static long serialVersionUID = 120722815716680L;

    public PropsCatalogDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
