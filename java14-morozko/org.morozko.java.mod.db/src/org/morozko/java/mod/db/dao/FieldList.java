/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)FieldList.java
 *
 * @project	   : simoss
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 27-mag-2005 19.50.12
 */
package org.morozko.java.mod.db.dao;

import java.util.Vector;

/**
 * <p>.</p>
 *
 * @author tux2
 */
public class FieldList {
	 
	private FieldFactory fieldFactory;
	
    private Vector list;
    
    public Field getField(int index) {
        return (Field)this.list.get(index);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public void addField(Field f) {
        this.list.add(f);
    }
    
    /**
     * <p>Crea una nuova istanza di FieldList.</p>
     *
     * 
     */
    public FieldList( FieldFactory fieldFactory ) {
        super();
        this.list = new Vector();
        this.fieldFactory = fieldFactory;
    }
    
    /**
     * <p>Crea una nuova istanza di FieldList.</p>
     *
     * 
     */
    public FieldList(FieldFactory fieldFactory, Field f) {
        this( fieldFactory );
        this.addField(f);
    }

	public void addField(DAOID value) {
		this.addField( value.longValue() );
	}

	public Field newField(int value, int type) {
		return fieldFactory.newField(value, type);
	}

	public void addField(int value) {
		this.addField( fieldFactory.newField(value) );
	}

	public void addField(long value, int type) {
		this.addField( fieldFactory.newField(value, type) );
	}

	public void addField(long value) {
		this.addField( fieldFactory.newField(value) );
	}

	public void addField(Object value, int type) {
		this.addField( fieldFactory.newField(value, type) );
	}

	public void addField(Object value) {
		this.addField( fieldFactory.newField(value) );
	}

	public void addField(String value, int type) {
		this.addField( fieldFactory.newField(value, type) );
	}

	public void addField(String value) {
		this.addField( fieldFactory.newField(value) );
	}

	public void addNullField(int type) {
		this.addField( fieldFactory.nullField(type) );
	}    

}
