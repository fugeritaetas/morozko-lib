/*
 * @(#)PropsCatalogModelHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.model.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.model.helpers;

import org.morozko.java.mod.db.props.dg.bean.PropsCatalogBean;

/**
 * <p>Oggetto di modello per PropsCatalog.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsCatalogModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 120783766635177L;


	public static final String ATT_NAME = "propsCatalogModel";

    // campi relativi alla tabella - START 

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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public PropsCatalogBean getBean() {
        PropsCatalogBean bean = new PropsCatalogBean();
        bean.setIdPropsCatalog( formatObject(idPropsCatalog) );
        bean.setPropCat( formatObject(propCat) );
        bean.setPropDescription( formatObject(propDescription) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPropsCatalog=" );
        buffer.append( this.idPropsCatalog );
        buffer.append( "; " );
        buffer.append( "propCat=" );
        buffer.append( this.propCat );
        buffer.append( "; " );
        buffer.append( "propDescription=" );
        buffer.append( this.propDescription );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
