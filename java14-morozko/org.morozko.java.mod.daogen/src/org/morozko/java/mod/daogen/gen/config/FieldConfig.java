/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)FieldConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import org.morozko.java.mod.daogen.gen.config.types.SQLType;
import org.morozko.java.mod.daogen.gen.config.types.TypeHandler;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class FieldConfig {

	/**
	 * @param fieldName
	 * @param fieldType
	 */
	public FieldConfig(String fieldName, SQLType fieldType, TypeHandler typeHandler) {
		super();
		this.fieldName = fieldName.toLowerCase();
		this.fieldNameOriginal = fieldName;
		this.fieldType = fieldType;
		this.typeHandler = typeHandler;
		this.fake = false;
		this.unsafe = false;
		this.excludeRse = false;
	}	
	
	private String fieldName;
	
	private String fieldNameOriginal;
	
	private SQLType fieldType;

	private TypeHandler typeHandler;
	
	private boolean fake;
	
	private boolean unsafe;
	
	private boolean excludeRse;

	public boolean isExcludeRse() {
		return excludeRse;
	}

	public void setExcludeRse(boolean excludeRse) {
		this.excludeRse = excludeRse;
	}

	public boolean isUnsafe() {
		return unsafe;
	}

	public void setUnsafe(boolean unsafe) {
		this.unsafe = unsafe;
	}

	private String fakeDefault;
	
	/**
	 * @return Restituisce il valore di fakeDefault.
	 */
	public String getFakeDefault() {
		return fakeDefault;
	}

	/**
	 * @param fakeDefault il valore di fakeDefault da impostare.
	 */
	public void setFakeDefault(String fakeDefault) {
		this.fakeDefault = fakeDefault;
	}

	public String getJavaFieldName() {
		return this.getTypeHandler().getJavaFieldName( this.getFieldName() );
	}
	
	public String getJavaFieldType() {
		return this.getTypeHandler().getJavaType();
	}
	
	/**
	 * @return Restituisce il valore di fieldName.
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName il valore di fieldName da impostare.
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return Restituisce il valore di fieldType.
	 */
	public SQLType getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType il valore di fieldType da impostare.
	 */
	public void setFieldType(SQLType fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return Restituisce il valore di typeHandler.
	 */
	public TypeHandler getTypeHandler() {
		return typeHandler;
	}

	/**
	 * @param typeHandler il valore di typeHandler da impostare.
	 */
	public void setTypeHandler(TypeHandler typeHandler) {
		this.typeHandler = typeHandler;
	}

	/**
	 * @return Restituisce il valore di fake.
	 */
	public boolean isFake() {
		return fake;
	}

	/**
	 * @param fake il valore di fake da impostare.
	 */
	public void setFake(boolean fake) {
		this.fake = fake;
	}

	public String getFieldNameOriginal() {
		return fieldNameOriginal;
	}
	
}
