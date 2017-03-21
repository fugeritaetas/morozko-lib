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
 * @(#)QueryTableXML.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.table
 * @creation   : 28/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.dao.table;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.helpers.XMLConfigurableObject;
import org.fugerit.java.core.db.connect.ConnectionFacade;
import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.dao.BasicDAOFactory;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.log.LogFacade;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableXML extends XMLConfigurableObject {

	private QueryTableDAO queryTableDAO;
	
	private String query;
	
	private List queryparams;
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure(Element config) throws ConfigException {
		try {
			SearchDOM searchDOM = SearchDOM.newInstance( true , true );
			// basic conf
			Element queryTag = searchDOM.findTag( config ,"query-table-config" );
			Properties queryAtts = DOMUtils.attributesToProperties( queryTag );
			String cfName = queryAtts.getProperty( "cf-name" );
			ConnectionFactory cf = ConnectionFacade.getFactory( cfName );
			BasicDAOFactory basicDAOFactory = new BasicDAOFactory( cf );
			this.queryTableDAO = new QueryTableDAO( basicDAOFactory );
			// query
			Element querySqlTag = searchDOM.findTag( queryTag ,"query-table-sql" );
			this.query = searchDOM.findText( querySqlTag );
			// params
			Element queryParamListTag = searchDOM.findTag( queryTag ,"query-table-param-list" );
			Iterator itQueryParam = searchDOM.findAllTags( queryParamListTag , "query-table-param" ).iterator();
			this.queryparams = new ArrayList();
			while ( itQueryParam.hasNext() ) {
				Element queryParamTag = (Element)itQueryParam.next();
				Properties queryParamAtts = DOMUtils.attributesToProperties( queryParamTag );
				String column = queryParamAtts.getProperty( "column" );
				String type = queryParamAtts.getProperty( "type" );
				String value = queryParamAtts.getProperty( "value" );
				if ( type.equals( "java.lang.Integer" ) ) {
					queryparams.add( QueryTableParam.newInstance( column , Integer.valueOf( value ) ) );
				} else if ( type.equals( "java.lang.Double" ) ) {
					queryparams.add( QueryTableParam.newInstance( column , Double.valueOf( value ) ) );
				} else {
					queryparams.add( QueryTableParam.newInstance( column , value ) );
				}
			}	
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
	}
	
	public List extract() throws DAOException {
		return this.queryTableDAO.query( this.query , this.queryparams );
	}
	
	public static void main( String[] args ) {
		try {

			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@26.2.225.58:1521:A", "secit", "secit" );
			
			ConnectionFacade.registerFactory( "def" , cf );
			
			
			String xml = "<query-table-config cf-name='def'>"+
						"<query-table-sql >SELECT * FROM bilanci WHERE cf = ?</query-table-sql>"+
						"<query-table-param-list>"+
						"<query-table-param column='cf' value='75940254' type='java.lang.String'/>"+
						"</query-table-param-list>"+
						"</query-table-config>";
			
			QueryTableXML queryTableXML = new QueryTableXML();
			queryTableXML.configure( DOMIO.loadDOMDoc( new ByteArrayInputStream( xml.getBytes() ) ).getDocumentElement() );
			
			List r = queryTableXML.extract();
			
			for ( int k=0; k<r.size(); k++ ) {
				QueryTableModel qtm = (QueryTableModel)r.get( k );
				System.out.println( qtm.getQueryColumn( "AVL30" ).getNumberValue() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	

}
