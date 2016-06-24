/*
 * @(#)PropsValuesModelHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.model.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.model.helpers;

import org.morozko.java.mod.db.props.dg.bean.PropsValuesBean;

/**
 * <p>Oggetto di modello per PropsValues.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsValuesModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 12078376665410L;


	public static final String ATT_NAME = "propsValuesModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idPropsValue;

    /** 
     * <p>Restituisce il valore di idPropsValue</p> 
     * 
     * @return      restituisce il valore di idPropsValue
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPropsValue() {
        return this.idPropsValue;
    }
    /** 
     * <p>Imposta il valore di idPropsValue</p> 
     * 
     * @param      idPropsValue il valore di idPropsValue da impostare
     */ 
    public void setIdPropsValue( org.morozko.java.mod.db.dao.DAOID idPropsValue ) {
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

	private org.morozko.java.mod.db.dao.DAOID idPropsCatalog;

    /** 
     * <p>Restituisce il valore di idPropsCatalog</p> 
     * 
     * @return      restituisce il valore di idPropsCatalog
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPropsCatalog() {
        return this.idPropsCatalog;
    }
    /** 
     * <p>Imposta il valore di idPropsCatalog</p> 
     * 
     * @param      idPropsCatalog il valore di idPropsCatalog da impostare
     */ 
    public void setIdPropsCatalog( org.morozko.java.mod.db.dao.DAOID idPropsCatalog ) {
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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listPropsCatalog;

    /** 
     * <p>Restituisce il valore di listPropsCatalog</p> 
     * 
     * @return      restituisce il valore di listPropsCatalog
     */ 
    public java.util.List getListPropsCatalog() {
        return this.listPropsCatalog;
    }

    /** 
     * <p>Imposta il valore di listPropsCatalog</p> 
     * 
     * @param      listPropsCatalog il valore di listPropsCatalog da impostare
     */ 
    public void setListPropsCatalog( java.util.List listPropsCatalog ) {
        this.listPropsCatalog = listPropsCatalog;
    }

    // campi relativi a relazioni - END 

    public PropsValuesBean getBean() {
        PropsValuesBean bean = new PropsValuesBean();
        bean.setIdPropsValue( formatObject(idPropsValue) );
        bean.setPropKey( formatObject(propKey) );
        bean.setPropValue( formatObject(propValue) );
        bean.setIdPropsCatalog( formatObject(idPropsCatalog) );
        bean.setPropCat( formatObject(propCat) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPropsValue=" );
        buffer.append( this.idPropsValue );
        buffer.append( "; " );
        buffer.append( "propKey=" );
        buffer.append( this.propKey );
        buffer.append( "; " );
        buffer.append( "propValue=" );
        buffer.append( this.propValue );
        buffer.append( "; " );
        buffer.append( "idPropsCatalog=" );
        buffer.append( this.idPropsCatalog );
        buffer.append( "; " );
        buffer.append( "propCat=" );
        buffer.append( this.propCat );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
