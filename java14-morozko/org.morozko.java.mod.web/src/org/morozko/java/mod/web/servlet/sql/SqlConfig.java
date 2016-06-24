package org.morozko.java.mod.web.servlet.sql;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SqlConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -144709501669419525L;

	public static SqlConfig configure( InputStream configData ) throws Exception {
		Document configXml = DOMIO.loadDOMDoc( configData );
		Element root = configXml.getDocumentElement();
		SearchDOM search = SearchDOM.newInstance( true , true );
		SqlConfig sqlConfig = new SqlConfig();
		// connection factory
		Element cfTag = search.findTag( root, "connection-factory" );
		Properties props = DOMUtils.attributesToProperties( cfTag );
		ConnectionFactory cf = ConnectionFactoryImpl.newInstance( props );
		sqlConfig.setConnectionFactory( cf );
		// query list
		List listQuery = search.findAllTags( root , "query" );
		Iterator itQuery = listQuery.iterator();
		while ( itQuery.hasNext() ) { 
			Element queryTag = (Element)itQuery.next();
			SqlQuery query = new SqlQuery();
			String name = queryTag.getAttribute( "name" );
			String key = queryTag.getAttribute( "key" );
			String type = queryTag.getAttribute( "type" );
			String description = queryTag.getAttribute( "description" );
			query.setName( name );
			query.setType( type );
			query.setKey( key );
			query.setDescription( description );
			Element sqlTag = search.findTag( queryTag , "sql" );
			String sql = search.findText( sqlTag );
			query.setSql( sql );
			// add update field
			List updateList = search.findAllTags( queryTag , "update" );
			Iterator updateIt = updateList.iterator();
			while ( updateIt.hasNext() ) {
				Element updateTag = (Element)updateIt.next();
				String column = updateTag.getAttribute( "column" );
				String field = updateTag.getAttribute( "field" );
				String table = updateTag.getAttribute( "table" );
				String sqlType = updateTag.getAttribute( "type" );
				SqlUpdate update = new SqlUpdate(field, column, table, sqlType);
				query.getUpdateMap().put( update.getField() , update );
			}
			sqlConfig.addQuery( query );
		}
		// setting config
		LogFacade.getLog().info( "SqlConfig.configure() INIT END >>>>>>>>>> OK" );
		return sqlConfig;
	}
	
	
	private ConnectionFactory connectionFactory;
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	private List listQuery;
	
	private Map mapQuery;
	
	public SqlConfig() {
		this.listQuery = new ArrayList();
		this.mapQuery = new HashMap();
	}
	
	public void addQuery( SqlQuery query ) {
		this.listQuery.add( query );
		this.mapQuery.put( query.getName() , query );
	}
	
	public Collection getQueryCollection() {
		return this.listQuery;
	}
	
	public SqlQuery getQuery( String name ) {
		return (SqlQuery)this.mapQuery.get( name );
	
	}
	
}
