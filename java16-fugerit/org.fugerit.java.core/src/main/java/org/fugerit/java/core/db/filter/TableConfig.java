/*******************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
*******************************************************/
/*
 * @(#)TableConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.filter
 * @creation   : 15/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.filter;


/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public class TableConfig {

	private String name;
	
	private DataFilter filter;
	
	private String[] columnsName;
	
	private String[] columnsKeyName;

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo columnsKeyName.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of columnsKeyName.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo columnsKeyName.</jdl:text>
	 *         		<jdl:text lang='en'>the value of columnsKeyName.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String[] getColumnsKeyName() {
		return columnsKeyName;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo columnsKeyName.</jdl:text>
	 * 		<jdl:text lang='en'>Sets columnsKeyName.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>columnsKeyName il valore di columnsKeyName da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>columnsKeyName the columnsKeyName to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setColumnsKeyName(String[] columnsKeyName) {
		this.columnsKeyName = columnsKeyName;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo columnsName.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of columnsName.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo columnsName.</jdl:text>
	 *         		<jdl:text lang='en'>the value of columnsName.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String[] getColumnsName() {
		return columnsName;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo columnsName.</jdl:text>
	 * 		<jdl:text lang='en'>Sets columnsName.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>columnsName il valore di columnsName da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>columnsName the columnsName to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setColumnsName(String[] columnsName) {
		this.columnsName = columnsName;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo name.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of name.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo name.</jdl:text>
	 *         		<jdl:text lang='en'>the value of name.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo name.</jdl:text>
	 * 		<jdl:text lang='en'>Sets name.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>name il valore di name da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>name the name to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo filter.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of filter.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo filter.</jdl:text>
	 *         		<jdl:text lang='en'>the value of filter.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public DataFilter getFilter() {
		return filter;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo filter.</jdl:text>
	 * 		<jdl:text lang='en'>Sets filter.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>filter il valore di filter da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>filter the filter to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setFilter(DataFilter filter) {
		this.filter = filter;
	}
	
	
	
}
