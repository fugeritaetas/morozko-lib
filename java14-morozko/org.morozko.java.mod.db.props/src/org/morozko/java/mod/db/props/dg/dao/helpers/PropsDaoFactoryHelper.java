/*
 * @(#)PropsDaoFactoryHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.dao.helpers;

import org.morozko.java.mod.db.props.dg.dao.PropsCatalogDAO;
import org.morozko.java.mod.db.props.dg.dao.PropsValuesDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsDaoFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 120783766659157L;

	private PropsCatalogDAO propsCatalogDAO;
	private PropsValuesDAO propsValuesDAO;

	public PropsDaoFactoryHelper( org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.propsCatalogDAO = new PropsCatalogDAO(daoFactory);
		this.propsCatalogDAO.setModuleDaoFactory(this);
		this.propsValuesDAO = new PropsValuesDAO(daoFactory);
		this.propsValuesDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di propsCatalogDAO</p> 
     * 
     * @return      restituisce il valore di propsCatalogDAO
     */ 
    public PropsCatalogDAO getPropsCatalogDAO() {
        return this.propsCatalogDAO;
    }
    /** 
     * <p>Restituisce il valore di propsValuesDAO</p> 
     * 
     * @return      restituisce il valore di propsValuesDAO
     */ 
    public PropsValuesDAO getPropsValuesDAO() {
        return this.propsValuesDAO;
    }

}
