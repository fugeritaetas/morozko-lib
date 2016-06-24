/*
 * @(#)OptionCatalogDAO.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao
 * @creation   : 29/02/2008 18/10/10
 */
package org.morozko.java.mod.option.dg.dao;

import org.morozko.java.mod.option.dg.dao.helpers.OptionCatalogDAOHelper;

/**
 * <p>Classe OptionCatalogDAO.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionCatalogDAO extends OptionCatalogDAOHelper {

	private final static long serialVersionUID = 120430501008825L;

    public OptionCatalogDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
