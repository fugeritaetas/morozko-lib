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
 * @(#)ColumnModel.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.metadata
 * @creation   : 22-mag-2006
 */
package org.morozko.java.mod.db.metadata;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ColumnModel {

	public static final int NULLABLE_FALSE = 0;
	public static final int NULLABLE_TRUE = 1;
	public static final int NULLABLE_UNKNOWN = -1;
	
	public String toString() {
		return this.getClass().getName()+"[name:"+this.getName()+";typeSql:"+this.getTypeSql()+";typeName:"+this.typeName+"]";
	}
	
	private String name;
	
	private int typeSql;
	
	private int nullable;
	
	private int size;
	
	private String typeName;
	
	private String comment;
	
	private String extra;

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @return Restituisce il valore di name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name il valore di name da impostare.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Restituisce il valore di typeName.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName il valore di typeName da impostare.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return Restituisce il valore di typeSql.
	 */
	public int getTypeSql() {
		return typeSql;
	}

	/**
	 * @param typeSql il valore di typeSql da impostare.
	 */
	public void setTypeSql(int typeSql) {
		this.typeSql = typeSql;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the nullable
	 */
	public int getNullable() {
		return nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
}
