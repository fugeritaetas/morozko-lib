/*
 * @(#)PropsDaoFactory.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao
 * @creation   : 03/04/2008 15/09/17
 */
package org.morozko.java.mod.db.props.dg.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.props.dg.dao.helpers.PropsDaoFactoryHelper;
import org.morozko.java.mod.db.props.dg.model.PropsValuesModel;

/**
 * <p>Classe PropsDaoFactory.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsDaoFactory extends PropsDaoFactoryHelper {

	private static PropsDaoFactory instance;
	
	public PropsDaoFactory(BasicDAOFactory daoFactory) {
		super(daoFactory);
		instance = this;
	}

	private final static long serialVersionUID = 120722815716626L;

	public static PropsDaoFactory getInstance() {
		return instance;
	}
	
	/**
	 * <p>Loads a set of properties with a specified PROP_CAT key.</p>
	 * 
	 * @param cat	the PROP_CAT key of the Catalog to load
	 * @return		A java.util.Properties object containing the corresponding set of properties
	 * @throws DAOException		if something unexpected happens during loading
	 */
	public Properties loadProps( String cat ) throws DAOException {
		PropsValuesDAO propsValuesDAO = this.getPropsValuesDAO();
		List list = propsValuesDAO.loadAllByCatalog( cat );
		Properties props = new Properties();
		Iterator it = list.iterator();
		while ( it.hasNext() ) {
			PropsValuesModel propsValuesModel = (PropsValuesModel) it.next();
			props.setProperty( propsValuesModel.getPropKey() , propsValuesModel.getPropValue() );
		}
		return props;
	}

}
