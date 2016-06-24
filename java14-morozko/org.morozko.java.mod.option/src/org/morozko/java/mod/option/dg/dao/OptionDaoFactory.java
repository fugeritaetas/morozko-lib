/*
 * @(#)OptionDaoFactory.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao
 * @creation   : 29/02/2008 18/10/10
 */
package org.morozko.java.mod.option.dg.dao;

import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.option.dg.dao.helpers.OptionDaoFactoryHelper;

/**
 * <p>Classe OptionDaoFactory.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionDaoFactory extends OptionDaoFactoryHelper {

	private final static long serialVersionUID = 120430501007816L;

	private static OptionDaoFactory instance;
	
	public OptionDaoFactory(BasicDAOFactory daoFactory) {
		super(daoFactory);
		instance = this;
	}

	public static OptionDaoFactory getInstance() {
		return instance;
	}
	
}
