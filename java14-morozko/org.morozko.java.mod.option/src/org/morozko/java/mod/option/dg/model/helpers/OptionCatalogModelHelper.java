/*
 * @(#)OptionCatalogModelHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.model.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.model.helpers;

import org.morozko.java.mod.option.dg.bean.OptionCatalogBean;

/**
 * <p>Oggetto di modello per OptionCatalog.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionCatalogModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 120646245570091L;


	public static final String ATT_NAME = "optionCatalogModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idOptionCatalog;

    /** 
     * <p>Restituisce il valore di idOptionCatalog</p> 
     * 
     * @return      restituisce il valore di idOptionCatalog
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdOptionCatalog() {
        return this.idOptionCatalog;
    }
    /** 
     * <p>Imposta il valore di idOptionCatalog</p> 
     * 
     * @param      idOptionCatalog il valore di idOptionCatalog da impostare
     */ 
    public void setIdOptionCatalog( org.morozko.java.mod.db.dao.DAOID idOptionCatalog ) {
        this.idOptionCatalog = idOptionCatalog;
    }

	private String catalogKey;

    /** 
     * <p>Restituisce il valore di catalogKey</p> 
     * 
     * @return      restituisce il valore di catalogKey
     */ 
    public String getCatalogKey() {
        return this.catalogKey;
    }
    /** 
     * <p>Imposta il valore di catalogKey</p> 
     * 
     * @param      catalogKey il valore di catalogKey da impostare
     */ 
    public void setCatalogKey( String catalogKey ) {
        this.catalogKey = catalogKey;
    }

	private String description;

    /** 
     * <p>Restituisce il valore di description</p> 
     * 
     * @return      restituisce il valore di description
     */ 
    public String getDescription() {
        return this.description;
    }
    /** 
     * <p>Imposta il valore di description</p> 
     * 
     * @param      description il valore di description da impostare
     */ 
    public void setDescription( String description ) {
        this.description = description;
    }

	private org.morozko.java.mod.db.dao.DAOID catalogType;

    /** 
     * <p>Restituisce il valore di catalogType</p> 
     * 
     * @return      restituisce il valore di catalogType
     */ 
    public org.morozko.java.mod.db.dao.DAOID getCatalogType() {
        return this.catalogType;
    }
    /** 
     * <p>Imposta il valore di catalogType</p> 
     * 
     * @param      catalogType il valore di catalogType da impostare
     */ 
    public void setCatalogType( org.morozko.java.mod.db.dao.DAOID catalogType ) {
        this.catalogType = catalogType;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listOptionItem;

    /** 
     * <p>Restituisce il valore di listOptionItem</p> 
     * 
     * @return      restituisce il valore di listOptionItem
     */ 
    public java.util.List getListOptionItem() {
        return this.listOptionItem;
    }

    /** 
     * <p>Imposta il valore di listOptionItem</p> 
     * 
     * @param      listOptionItem il valore di listOptionItem da impostare
     */ 
    public void setListOptionItem( java.util.List listOptionItem ) {
        this.listOptionItem = listOptionItem;
    }

    // campi relativi a relazioni - END 

    public OptionCatalogBean getBean() {
        OptionCatalogBean bean = new OptionCatalogBean();
        bean.setIdOptionCatalog( formatObject(idOptionCatalog) );
        bean.setCatalogKey( formatObject(catalogKey) );
        bean.setDescription( formatObject(description) );
        bean.setCatalogType( formatObject(catalogType) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idOptionCatalog=" );
        buffer.append( this.idOptionCatalog );
        buffer.append( "; " );
        buffer.append( "catalogKey=" );
        buffer.append( this.catalogKey );
        buffer.append( "; " );
        buffer.append( "description=" );
        buffer.append( this.description );
        buffer.append( "; " );
        buffer.append( "catalogType=" );
        buffer.append( this.catalogType );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
