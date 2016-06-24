/*
 * @(#)OptionItemBeanHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.bean.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.bean.helpers;

import org.morozko.java.mod.option.dg.model.OptionItemModel;

/**
 * <p>Bean per oggetti di tipo OptionItemModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionItemBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 120646245576084L;

	private String idOptionItem;

    /** 
     * <p>Restituisce il valore di idOptionItem</p> 
     * 
     * @return      restituisce il valore di idOptionItem
     */ 
    public String getIdOptionItem() {
        return this.idOptionItem;
    }
    /** 
     * <p>Imposta il valore di idOptionItem</p> 
     * 
     * @param      idOptionItem il valore di idOptionItem da impostare
     */ 
    public void setIdOptionItem( String idOptionItem ) {
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
	private String enabled;

    /** 
     * <p>Restituisce il valore di enabled</p> 
     * 
     * @return      restituisce il valore di enabled
     */ 
    public String getEnabled() {
        return this.enabled;
    }
    /** 
     * <p>Imposta il valore di enabled</p> 
     * 
     * @param      enabled il valore di enabled da impostare
     */ 
    public void setEnabled( String enabled ) {
        this.enabled = enabled;
    }
	private String ordering;

    /** 
     * <p>Restituisce il valore di ordering</p> 
     * 
     * @return      restituisce il valore di ordering
     */ 
    public String getOrdering() {
        return this.ordering;
    }
    /** 
     * <p>Imposta il valore di ordering</p> 
     * 
     * @param      ordering il valore di ordering da impostare
     */ 
    public void setOrdering( String ordering ) {
        this.ordering = ordering;
    }
	private String idOptionCatalog;

    /** 
     * <p>Restituisce il valore di idOptionCatalog</p> 
     * 
     * @return      restituisce il valore di idOptionCatalog
     */ 
    public String getIdOptionCatalog() {
        return this.idOptionCatalog;
    }
    /** 
     * <p>Imposta il valore di idOptionCatalog</p> 
     * 
     * @param      idOptionCatalog il valore di idOptionCatalog da impostare
     */ 
    public void setIdOptionCatalog( String idOptionCatalog ) {
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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public OptionItemModel getModel() {
        OptionItemModel model = new OptionItemModel();
        model.setIdOptionItem( toDAOID(idOptionItem) );
        model.setItemValue( toString(itemValue) );
        model.setEnabled( toDAOID(enabled) );
        model.setOrdering( toDAOID(ordering) );
        model.setIdOptionCatalog( toDAOID(idOptionCatalog) );
        model.setCatalogKey( toString(catalogKey) );
        return model;
    }

}
