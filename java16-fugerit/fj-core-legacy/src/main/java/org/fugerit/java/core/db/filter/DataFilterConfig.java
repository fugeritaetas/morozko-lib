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
 * @(#)DataFilterConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.filter
 * @creation   : 15/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.filter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.log.LogFacade;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

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
public class DataFilterConfig {

	private ConnectionFactory connectionFactory;
	
	private List tableList;
	
	private Map filterMap;
	
	private DataFilterConfig() {
		this.tableList = new ArrayList();
		this.filterMap = new HashMap();
	}
	
	public void filter() throws DAOException {
		Iterator it = this.tableList.iterator();
		while ( it.hasNext() ) {
			TableConfig tableConfig = (TableConfig)it.next();
			String table = tableConfig.getName();
			String[] columnsName = tableConfig.getColumnsName();
			String[] columnsKeyName = tableConfig.getColumnsKeyName();
			tableConfig.getFilter().applyFilter( this.connectionFactory , table, columnsName, columnsKeyName );
		}
	}
	
	public static DataFilterConfig parse( InputSource source ) throws DAOException {
		DataFilterConfig dataFilterConfig = new DataFilterConfig();
		try {
			Document doc = DOMIO.loadDOMDoc( source );
			SearchDOM searchDOM = SearchDOM.newInstance(true, true);
			Element root = doc.getDocumentElement();
			// connection factory
			Element connectionFactoryTag = searchDOM.findTag( root , "cf-config" );
			dataFilterConfig.connectionFactory = ConnectionFactoryImpl.newInstance( DOMUtils.attributesToProperties( connectionFactoryTag ) );
			// filter list
			Element filterListTag = searchDOM.findTag( root , "data-filter-list" );
			List filterTags = searchDOM.findAllTags( filterListTag , "data-filter" );
			Iterator filterTagIt = filterTags.iterator();
			while ( filterTagIt.hasNext() ) {
				Element filterTag = (Element) filterTagIt.next();
				Properties filterTagProps = DOMUtils.attributesToProperties( filterTag );
				String name = filterTagProps.getProperty( "name" );
				String type = filterTagProps.getProperty( "type" );
				LogFacade.getLog().info( "DataFilterConfig.parse() filter name : "+name );
				LogFacade.getLog().info( "DataFilterConfig.parse() filter type : "+type );
				dataFilterConfig.filterMap.put( name , ClassHelper.newInstance( type ) );
			}
			// table list
			Element tableListTag = searchDOM.findTag( root , "table-list" );
			List tableTags = searchDOM.findAllTags( tableListTag , "table" );
			Iterator tableTagsIt = tableTags.iterator();
			while ( tableTagsIt.hasNext() ) {
				Element tableTag = (Element) tableTagsIt.next();
				Properties tableTagProps = DOMUtils.attributesToProperties( tableTag );
				String name = tableTagProps.getProperty( "name" );
				String columns = tableTagProps.getProperty( "columns" );
				String keyColumns = tableTagProps.getProperty( "key-columns" );
				String filter = tableTagProps.getProperty( "filter" );
				LogFacade.getLog().info( "DataFilterConfig.parse() table name       : "+name );
				LogFacade.getLog().info( "DataFilterConfig.parse() table columns    : "+columns );
				LogFacade.getLog().info( "DataFilterConfig.parse() table keyColumns : "+keyColumns );
				LogFacade.getLog().info( "DataFilterConfig.parse() table filter     : "+filter );
				TableConfig tableConfig = new TableConfig();
				tableConfig.setFilter( (DataFilter)dataFilterConfig.filterMap.get( filter ) );
				tableConfig.setColumnsName( columns.split( ";" ) );
				tableConfig.setColumnsKeyName( keyColumns.split( ";" ) );
				tableConfig.setName( name );
				dataFilterConfig.tableList.add( tableConfig );
			}
		} catch (Exception e) {
 			throw new DAOException( e );
		}
		return dataFilterConfig;
	}
	
	public static void main( String[] args ) {
		try {

			DataFilterConfig dataFilterConfig = parse( new InputSource( new FileInputStream( new File( "data/filter/data-filter.xml" ) ) ) );
			dataFilterConfig.filter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
