/*
 * @(#)PropsCatalogBeanHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.bean.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.bean.helpers;

import org.morozko.java.mod.db.props.dg.model.PropsCatalogModel;

/**
 * <p>Bean per oggetti di tipo PropsCatalogModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsCatalogBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 120783766642134L;

	private String idPropsCatalog;

    /** 
     * <p>Restituisce il valore di idPropsCatalog</p> 
     * 
     * @return      restituisce il valore di idPropsCatalog
     */ 
    public String getIdPropsCatalog() {
        return this.idPropsCatalog;
    }
    /** 
     * <p>Imposta il valore di idPropsCatalog</p> 
     * 
     * @param      idPropsCatalog il valore di idPropsCatalog da impostare
     */ 
    public void setIdPropsCatalog( String idPropsCatalog ) {
        this.idPropsCatalog = idPropsCatalog;
    }
	private String propCat;

    /** 
     * <p>Restituisce il valore di propCat</p> 
     * 
     * @return      restituisce il valore di propCat
     */ 
    public String getPropCat() {
        return this.propCat;
    }
    /** 
     * <p>Imposta il valore di propCat</p> 
     * 
     * @param      propCat il valore di propCat da impostare
     */ 
    public void setPropCat( String propCat ) {
        this.propCat = propCat;
    }
	private String propDescription;

    /** 
     * <p>Restituisce il valore di propDescription</p> 
     * 
     * @return      restituisce il valore di propDescription
     */ 
    public String getPropDescription() {
        return this.propDescription;
    }
    /** 
     * <p>Imposta il valore di propDescription</p> 
     * 
     * @param      propDescription il valore di propDescription da impostare
     */ 
    public void setPropDescription( String propDescription ) {
        this.propDescription = propDescription;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PropsCatalogModel getModel() {
        PropsCatalogModel model = new PropsCatalogModel();
        model.setIdPropsCatalog( toDAOID(idPropsCatalog) );
        model.setPropCat( toString(propCat) );
        model.setPropDescription( toString(propDescription) );
        return model;
    }

}
