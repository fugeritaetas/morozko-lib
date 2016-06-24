/*
 * @(#)OptionCatalogBeanHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.bean.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.bean.helpers;

import org.morozko.java.mod.option.dg.model.OptionCatalogModel;

/**
 * <p>Bean per oggetti di tipo OptionCatalogModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionCatalogBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 120646245571027L;

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
	private String catalogType;

    /** 
     * <p>Restituisce il valore di catalogType</p> 
     * 
     * @return      restituisce il valore di catalogType
     */ 
    public String getCatalogType() {
        return this.catalogType;
    }
    /** 
     * <p>Imposta il valore di catalogType</p> 
     * 
     * @param      catalogType il valore di catalogType da impostare
     */ 
    public void setCatalogType( String catalogType ) {
        this.catalogType = catalogType;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public OptionCatalogModel getModel() {
        OptionCatalogModel model = new OptionCatalogModel();
        model.setIdOptionCatalog( toDAOID(idOptionCatalog) );
        model.setCatalogKey( toString(catalogKey) );
        model.setDescription( toString(description) );
        model.setCatalogType( toDAOID(catalogType) );
        return model;
    }

}
