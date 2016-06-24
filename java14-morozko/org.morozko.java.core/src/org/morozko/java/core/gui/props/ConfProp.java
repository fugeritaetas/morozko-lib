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
 * @(#)ConfProp.java
 *
 * @project	   : xfbclient
 * @package	   : it.finanze.sanita.simoss.lib.ui.swing.props
 * @creation   : 15-giu-2005 8.48.58
 */
package org.morozko.java.core.gui.props;

import java.io.File;

/**
 * <p>ConfProp definisce gli oggetti proprieta' di configurazione, differenziandone i tipi in: <br>
 * INTERO <br>
 * STRINGA <br> 
 * FILE <br>
 * BOOLEANO <br>
 *.</p>
 * @author tux2
 */
public class ConfProp {

    /**
     * Crea una nuova istanza di ConfProp di tipo <code>FILE</code> con nome <code>name</code> e valore <code>val</code>.
     * 
     * @param name Nome della proprieta' di configurazione
     * @param value Valore della proprieta' 
     * @return Proprieta' di configurazione di tipo File
     */
    public static ConfProp newInstance( String name, File value ) {
        return new ConfProp( name, value.getAbsolutePath(), TYPE_FILE );
    }    
    
    /**
     * Crea una nuova istanza di ConfProp di tipo <code>STRINGA</code> con nome <code>name</code> e valore <code>value</code>.
     * 
     * @param name Nome della proprieta' di configurazione
     * @param value Valore della proprieta' 
     * @return Proprieta' di configurazione di tipo Stringa
     */
    public static ConfProp newInstance( String name, String value ) {
        return newInstance( name, value, false );
    }
    
	public static ConfProp newInstance( String name, String value, boolean password ) {
		int type = TYPE_STRING;
		if (password) {
			type = TYPE_PASSWORD;
		}
		return new ConfProp( name, value, type );
	}        
    
    /**
     * Crea una nuova istanza di ConfProp di tipo <code>INTERO</code> con nome <code>name</code> e valore <code>value</code>
     * 
     * @param name Nome della proprieta' di configurazione
     * @param value Valore della proprieta'
     * @return Proprieta' di configurazione di tipo Intero
     */
    public static ConfProp newInstance( String name, int value ) {
        return new ConfProp( name, String.valueOf( value ), TYPE_INT );
    }
    
    /**
     * Crea una nuova istanza di ConfProp di tipo <code>BOOLEANO</code> con nome <code>name</code> e valore <code>value</code>
     * 
     * @param name Nome della proprieta' di configurazione
     * @param value Valore della proprietà (<code>true</code> o <code>false</code>).
     * @return Proprieta' di configurazione di tipo Booleano
     */
    public static ConfProp newInstance( String name, boolean value ) {
        return new ConfProp( name, String.valueOf( value ), TYPE_BOOL );
    }
    
	public final static int TYPE_PASSWORD = 5;
    
    /**
     * Tipo <code>intero</code> della proprieta' di configurazione
     */
    public final static int TYPE_INT = 1;
    /**
     * Tipo <code>stringa</code> della proprietà di configurazione
     */
    public final static int TYPE_STRING = 2;
    /**
     * Tipo <code>file</code> della proprietà di configurazione
     */
    public final static int TYPE_FILE = 3;
    /**
     * Tipo <code>boolean</code> della proprietà di configurazione
     */
    public final static int TYPE_BOOL = 4;
    
    private String value;
    
    private String name;
    
    private int type;
    
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
     * <p>Restituisce il valore di value.</p>
     *
     * @return il valore di value.
     */
    public String getValue() {
        return value;
    }
    /**
     * <p>Imposta il valore di value.</p>
     *
     * @param value il valore da impostare.
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * <p>Restituisce il valore di name.</p>
     *
     * @return il valore di name.
     */
    public String getName() {
        return name;
    }
    /**
     * <p>Restituisce il valore di type.</p>
     *
     * @return il valore di type.
     */
    public int getType() {
        return type;
    }


    private ConfProp( String name, String value, int type ) {
        super();
        this.name = name;
        this.value = value;
        this.type = type;
    }

}
