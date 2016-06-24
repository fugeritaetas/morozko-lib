/*
 * @(#)PropsValuesBeanHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.bean.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.bean.helpers;

import org.morozko.java.mod.db.props.dg.model.PropsValuesModel;

/**
 * <p>Bean per oggetti di tipo PropsValuesModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsValuesBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 120783766657132L;

	private String idPropsValue;

    /** 
     * <p>Restituisce il valore di idPropsValue</p> 
     * 
     * @return      restituisce il valore di idPropsValue
     */ 
    public String getIdPropsValue() {
        return this.idPropsValue;
    }
    /** 
     * <p>Imposta il valore di idPropsValue</p> 
     * 
     * @param      idPropsValue il valore di idPropsValue da impostare
     */ 
    public void setIdPropsValue( String idPropsValue ) {
        this.idPropsValue = idPropsValue;
    }
	private String propKey;

    /** 
     * <p>Restituisce il valore di propKey</p> 
     * 
     * @return      restituisce il valore di propKey
     */ 
    public String getPropKey() {
        return this.propKey;
    }
    /** 
     * <p>Imposta il valore di propKey</p> 
     * 
     * @param      propKey il valore di propKey da impostare
     */ 
    public void setPropKey( String propKey ) {
        this.propKey = propKey;
    }
	private String propValue;

    /** 
     * <p>Restituisce il valore di propValue</p> 
     * 
     * @return      restituisce il valore di propValue
     */ 
    public String getPropValue() {
        return this.propValue;
    }
    /** 
     * <p>Imposta il valore di propValue</p> 
     * 
     * @param      propValue il valore di propValue da impostare
     */ 
    public void setPropValue( String propValue ) {
        this.propValue = propValue;
    }
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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PropsValuesModel getModel() {
        PropsValuesModel model = new PropsValuesModel();
        model.setIdPropsValue( toDAOID(idPropsValue) );
        model.setPropKey( toString(propKey) );
        model.setPropValue( toString(propValue) );
        model.setIdPropsCatalog( toDAOID(idPropsCatalog) );
        model.setPropCat( toString(propCat) );
        return model;
    }

}
