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
 * @(#)QueryColumn.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.util.table
 * @creation   : 22/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.query.table;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableColumn {

	public static final int TYPE_INTEGER = 1;
	public static final int TYPE_LONG = 2;
	public static final int TYPE_DOUBLE = 3;
	public static final int TYPE_FLOAT = 4;
	public static final int TYPE_STRING = 5;
	public static final int TYPE_DATE = 6;
	public static final int TYPE_TIME = 7;
	public static final int TYPE_TIMESTAMP = 8;
	public static final int TYPE_NUMBER = 9;
	
	private Object value;

	private int type;

	/**
	 * <p>Creates a new instance of QueryColumn.</p>
	 *
	 * @param value
	 * @param type
	 */
	public QueryTableColumn(Object value, int type) {
		super();
		this.value = value;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	public Timestamp getTimestampValue() {
		return (Timestamp)this.getValue();
	}	
	
	public Time getTimeValue() {
		return (Time)this.getValue();
	}	
	
	public Date getDateValue() {
		return (Date)this.getValue();
	}
	
	public Float getFloatValue() {
		return (Float)this.getValue();
	}	
	
	public Double getDoubleValue() {
		return (Double)this.getValue();
	}	
	
	public Long getLongValue() {
		return (Long)this.getValue();
	}
	
	public Integer getIntegerValue() {
		return (Integer)this.getValue();
	}
	
	public String getStringValue() {
		return (String)this.getValue();
	}
	
	public Number getNumberValue() {
		return (Number)this.getValue();
	}	
	
}
