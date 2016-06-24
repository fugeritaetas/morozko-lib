/*
 * @(#)PropsValuesDAO.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao
 * @creation   : 03/04/2008 15/09/17
 */
package org.morozko.java.mod.db.props.dg.dao;

import org.morozko.java.mod.db.props.dg.dao.helpers.PropsValuesDAOHelper;

/**
 * <p>Classe PropsValuesDAO.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsValuesDAO extends PropsValuesDAOHelper {

	private final static long serialVersionUID = 120722815724677L;

    public PropsValuesDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
