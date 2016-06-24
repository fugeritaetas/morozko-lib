/*
 * @(#)OptionItemModelHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.model.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.model.helpers;

import org.morozko.java.mod.option.dg.bean.OptionItemBean;

/**
 * <p>Oggetto di modello per OptionItem.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionItemModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 120646245575026L;


	public static final String ATT_NAME = "optionItemModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idOptionItem;

    /** 
     * <p>Restituisce il valore di idOptionItem</p> 
     * 
     * @return      restituisce il valore di idOptionItem
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdOptionItem() {
        return this.idOptionItem;
    }
    /** 
     * <p>Imposta il valore di idOptionItem</p> 
     * 
     * @param      idOptionItem il valore di idOptionItem da impostare
     */ 
    public void setIdOptionItem( org.morozko.java.mod.db.dao.DAOID idOptionItem ) {
        this.idOptionItem = idOptionItem;
    }

	private String itemValue;

    /** 
     * <p>Restituisce il valore di itemValue</p> 
     * 
     * @return      restituisce il valore di itemValue
     */ 
    public String getItemValue() {
        return this.itemValue;
    }
    /** 
     * <p>Imposta il valore di itemValue</p> 
     * 
     * @param      itemValue il valore di itemValue da impostare
     */ 
    public void setItemValue( String itemValue ) {
        this.itemValue = itemValue;
    }

	private org.morozko.java.mod.db.dao.DAOID enabled;

    /** 
     * <p>Restituisce il valore di enabled</p> 
     * 
     * @return      restituisce il valore di enabled
     */ 
    public org.morozko.java.mod.db.dao.DAOID getEnabled() {
        return this.enabled;
    }
    /** 
     * <p>Imposta il valore di enabled</p> 
     * 
     * @param      enabled il valore di enabled da impostare
     */ 
    public void setEnabled( org.morozko.java.mod.db.dao.DAOID enabled ) {
        this.enabled = enabled;
    }

	private org.morozko.java.mod.db.dao.DAOID ordering;

    /** 
     * <p>Restituisce il valore di ordering</p> 
     * 
     * @return      restituisce il valore di ordering
     */ 
    public org.morozko.java.mod.db.dao.DAOID getOrdering() {
        return this.ordering;
    }
    /** 
     * <p>Imposta il valore di ordering</p> 
     * 
     * @param      ordering il valore di ordering da impostare
     */ 
    public void setOrdering( org.morozko.java.mod.db.dao.DAOID ordering ) {
        this.ordering = ordering;
    }

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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public OptionItemBean getBean() {
        OptionItemBean bean = new OptionItemBean();
        bean.setIdOptionItem( formatObject(idOptionItem) );
        bean.setItemValue( formatObject(itemValue) );
        bean.setEnabled( formatObject(enabled) );
        bean.setOrdering( formatObject(ordering) );
        bean.setIdOptionCatalog( formatObject(idOptionCatalog) );
        bean.setCatalogKey( formatObject(catalogKey) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idOptionItem=" );
        buffer.append( this.idOptionItem );
        buffer.append( "; " );
        buffer.append( "itemValue=" );
        buffer.append( this.itemValue );
        buffer.append( "; " );
        buffer.append( "enabled=" );
        buffer.append( this.enabled );
        buffer.append( "; " );
        buffer.append( "ordering=" );
        buffer.append( this.ordering );
        buffer.append( "; " );
        buffer.append( "idOptionCatalog=" );
        buffer.append( this.idOptionCatalog );
        buffer.append( "; " );
        buffer.append( "catalogKey=" );
        buffer.append( this.catalogKey );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
