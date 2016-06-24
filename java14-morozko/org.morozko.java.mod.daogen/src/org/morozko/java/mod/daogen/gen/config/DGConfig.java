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
 * @(#)DGConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.mod.db.connect.ConnectionFactory;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DGConfig {
	
	private ConnectionFactory connectionFactory;
	
	public DGConfig() {
		this.tableConfigList = new ArrayList();
		this.generalProps = new Properties();
		this.errorList = new ArrayList();
		this.warningList = new ArrayList();
		this.customTypeHandlers = new HashMap();
		this.idGeneratorMap = new HashMap();
	}
	
	public TableConfig getTable( String name ) {
		TableConfig tableConfig = null;
		Iterator it = this.getTableConfigList().iterator();
		while ( it.hasNext() && tableConfig==null ) {
			TableConfig current = (TableConfig)it.next();
			if ( current.getTableName().equals( name ) ) {
				tableConfig = current;
			}
		}
		return tableConfig;
	}
	
	private Properties excludeProps;
	
	private Properties generalProps;
	
	private List tableConfigList;
	
	private List errorList;
	
	private List warningList;
	
	private Map customTypeHandlers;
	
	private Map idGeneratorMap;

	/**
	 * @return Restituisce il valore di customTypeHandlers.
	 */
	public Map getCustomTypeHandlers() {
		return customTypeHandlers;
	}

	/**
	 * @param customTypeHandlers il valore di customTypeHandlers da impostare.
	 */
	public void setCustomTypeHandlers(Map customTypeHandlers) {
		this.customTypeHandlers = customTypeHandlers;
	}

	/**
	 * @return Restituisce il valore di tableConfigList.
	 */
	public List getTableConfigList() {
		return tableConfigList;
	}

	/**
	 * @return Restituisce il valore di errorList.
	 */
	public List getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList il valore di errorList da impostare.
	 */
	public void setErrorList(List errorList) {
		this.errorList = errorList;
	}

	/**
	 * @return Restituisce il valore di warningList.
	 */
	public List getWarningList() {
		return warningList;
	}

	/**
	 * @param warningList il valore di warningList da impostare.
	 */
	public void setWarningList(List warningList) {
		this.warningList = warningList;
	}

	/**
	 * @param tableConfigList il valore di tableConfigList da impostare.
	 */
	public void setTableConfigList(List tableConfigList) {
		this.tableConfigList = tableConfigList;
	}

	/**
	 * @return Restituisce il valore di generalProps.
	 */
	public Properties getGeneralProps() {
		return generalProps;
	}

	/**
	 * @param generalProps il valore di generalProps da impostare.
	 */
	public void setGeneralProps(Properties generalProps) {
		this.generalProps = generalProps;
	}

	/**
	 * @return Restituisce il valore di connectionFactory.
	 */
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * @param connectionFactory il valore di connectionFactory da impostare.
	 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * @return Restituisce il valore di excludeProps.
	 */
	public Properties getExcludeProps() {
		return excludeProps;
	}

	/**
	 * @param excludeProps il valore di excludeProps da impostare.
	 */
	public void setExcludeProps(Properties excludeProps) {
		this.excludeProps = excludeProps;
	}

	/**
	 * @return the idGeneratorMap
	 */
	public Map getIdGeneratorMap() {
		return idGeneratorMap;
	}
	
}
