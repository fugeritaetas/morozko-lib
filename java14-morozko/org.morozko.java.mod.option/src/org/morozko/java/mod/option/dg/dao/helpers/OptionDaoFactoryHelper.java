/*
 * @(#)OptionDaoFactoryHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.dao.helpers;

import org.morozko.java.mod.option.dg.dao.OptionCatalogDAO;
import org.morozko.java.mod.option.dg.dao.OptionItemDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionDaoFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 120646245580120L;

	private OptionCatalogDAO optionCatalogDAO;
	private OptionItemDAO optionItemDAO;

	public OptionDaoFactoryHelper( org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.optionCatalogDAO = new OptionCatalogDAO(daoFactory);
		this.optionCatalogDAO.setModuleDaoFactory(this);
		this.optionItemDAO = new OptionItemDAO(daoFactory);
		this.optionItemDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di optionCatalogDAO</p> 
     * 
     * @return      restituisce il valore di optionCatalogDAO
     */ 
    public OptionCatalogDAO getOptionCatalogDAO() {
        return this.optionCatalogDAO;
    }
    /** 
     * <p>Restituisce il valore di optionItemDAO</p> 
     * 
     * @return      restituisce il valore di optionItemDAO
     */ 
    public OptionItemDAO getOptionItemDAO() {
        return this.optionItemDAO;
    }

}
