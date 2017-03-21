/*****************************************************************
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
*****************************************************************/
/*
 * @(#)CompareConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.compare
 * @creation   : 20/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.compare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

/**
 * <p>test configuration</p>
 * 
 * 

<compare-config>

	<cf-from-config
		db-cf-mode="DC"
		db-mode-dc-drv="com.mysql.jdbc.Driver"
		db-mode-dc-url="jdbc:mysql://localhost/test1"
		db-mode-dc-usr="root"
		db-mode-dc-pwd="r00t"
	/>
	
	<cf-to-config
		db-cf-mode="DC"
		db-mode-dc-drv="com.mysql.jdbc.Driver"
		db-mode-dc-url="jdbc:mysql://localhost/test1"
		db-mode-dc-usr="root"
		db-mode-dc-pwd="r00t"
	/>	
	
	<entry-list>
		<entry type="table">
			<query-from>table_a</query-from>
			<query-to>table_b</query-to>
		</entry>
	</entry-list> 

</compare-config>

 * 
 *
 * @author mfranci
 *
 */
public class CompareConfig {

	private ConnectionFactory factoryFrom;
	
	private ConnectionFactory factoryTo;
	
	private List compareList;
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure( Element config ) throws ConfigException {
		try {
			SearchDOM searchDOM = SearchDOM.newInstance( true , true );
			
			Element backupConfig = searchDOM.findTag( config , "compare-config" );
			
			Element factoryFromTag = searchDOM.findTag( backupConfig , "cf-from-config" );
			this.factoryFrom = ConnectionFactoryImpl.newInstance( DOMUtils.attributesToProperties( factoryFromTag ) );
			
			Element factoryToTag = searchDOM.findTag( backupConfig , "cf-to-config" );
			this.factoryTo = ConnectionFactoryImpl.newInstance( DOMUtils.attributesToProperties( factoryToTag ) );
			
			this.compareList = new ArrayList();
			
			Element entryListTag = searchDOM.findTag( backupConfig , "entry-list" );
			if ( entryListTag != null ) {
				List entryList = searchDOM.findAllTags( entryListTag , "entry" );
				Iterator entryIt = entryList.iterator();
				while ( entryIt.hasNext() ) {
					Element entry = (Element)entryIt.next();
					Properties entryAtts = DOMUtils.attributesToProperties( entry );
					String type = entryAtts.getProperty( "type" );
					Element queryFromTag = searchDOM.findTag( entry , "query-from" );
					Element queryToTag = searchDOM.findTag( entry , "query-to" );
					String queryFrom = searchDOM.findText( queryFromTag );
					String queryTo = searchDOM.findText( queryToTag );
					if ( "table".equalsIgnoreCase( type ) ) {
						queryFrom = " SELECT * FROM "+queryFrom+" ";
						queryTo = " SELECT * FROM "+queryTo+" ";
					}
					CompareEntry compareEntry = new CompareEntry();
					compareEntry.setQueryFrom( queryFrom );
					compareEntry.setQueryTo( queryTo );
					this.compareList.add( compareEntry );
				}
			}
			
			
//			Element fromQueryTag = searchDOM.findTag( backupConfig , "from-query" );
//			
//			this.fromQuery = searchDOM.findText( fromQueryTag );
//			
//			Element toQueryTag = searchDOM.findTag( backupConfig , "to-query" );
//			
//			this.toQuery = searchDOM.findText( toQueryTag );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the factoryFrom
	 */
	public ConnectionFactory getFactoryFrom() {
		return factoryFrom;
	}

	/**
	 * @return the factoryTo
	 */
	public ConnectionFactory getFactoryTo() {
		return factoryTo;
	}

	public Iterator entries() {
		return this.compareList.iterator();
	}

}
