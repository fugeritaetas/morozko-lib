/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)PropMap.java
 *
 * @project	   : xfbclient
 * @package	   : it.finanze.sanita.simoss.lib.ui.swing.props
 * @creation   : 15-giu-2005 8.53.18
 */
package org.morozko.java.core.gui.props;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * <p>PropMap contiene un insieme di proprietà di configurazione di tipo ConfProp per la creazione di finestre di dialogo.</p>
 * 
 * @author tux2
 */
public class PropMap {
    
    //
    private boolean confirm;
    
    /**
     * <p>Restituisce il valore di confirm.</p>
     *
     * @return il valore di confirm.
     */
    public boolean isConfirm() {
        return confirm;
    }
    /**
     * <p>Imposta il valore di confirm.</p>
     *
     * @param confirm il valore da impostare.
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
//

    /**
     * Restituisce un iteratore sull'insieme delle chiavi delle proprieta'
     * 
     * @return Iteratore sulle chiavi di PropMap
     */
    public Iterator keys() {
        return this.keys.iterator();
    }
    
    /**
     * Restituisce la proprietà di configurazione che ha per nome <code>name</code>
     * 
     * @param name Nome della proprieta' di configurazione da ricavare
     * @return Proprieta' di configurazione
     */
    public ConfProp get(String name) {
        return (ConfProp)this.map.get( name );
    }
    
    /**
     * Aggiunge all'insieme delle proprieta' la proprieta' <code>prop</code>
     * 
     * @param prop Proprieta' di configurazione da aggiungere
     */
    public void add(ConfProp prop) {
        this.keys.add( prop.getName() );
        this.map.put( prop.getName(), prop );
    }
    
    private Hashtable map;
    private Vector keys;
    private String name;
    
    /**
     * Restituisce il nome dell'insieme di proprieta' di configurazione
     * 
     * @return Nome dell'insieme delle proprieta'
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Restituisce la dimensione dell'insieme delle proprieta' di configurazione
     * 
     * @return Dimensione dell'insieme delle proprieta'
     */
    public int getSize() {
        return keys.size();
    }
    
    /**
     * Restituisce le chiavi dell'insieme delle proprieta' 
     * 
     * @return Vettore delle chiavi identificative
     */
    public Vector getKeys() {
        return this.keys;
    }
    /**
     * <p>Crea una nuova istanza di PropMap con nome <code>name</code>.</p>
     *
     * @param name Nome dell'insieme di proprieta'
     */
    public PropMap(String name) {
        super();
        this.map = new Hashtable();
        this.keys = new Vector();
        this.name = name;
    }

}
