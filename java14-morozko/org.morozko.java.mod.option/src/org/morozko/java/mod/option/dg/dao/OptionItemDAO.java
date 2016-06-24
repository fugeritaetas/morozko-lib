/*
 * @(#)OptionItemDAO.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao
 * @creation   : 29/02/2008 18/10/10
 */
package org.morozko.java.mod.option.dg.dao;

import org.morozko.java.mod.option.dg.dao.helpers.OptionItemDAOHelper;

/**
 * <p>Classe OptionItemDAO.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionItemDAO extends OptionItemDAOHelper {

	private final static long serialVersionUID = 120430501021837L;

    public OptionItemDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
