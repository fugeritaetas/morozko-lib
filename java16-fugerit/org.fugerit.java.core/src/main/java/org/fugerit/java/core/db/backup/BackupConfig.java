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
 * @(#)BackupConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.helpers.XMLConfigurableObject;
import org.fugerit.java.core.db.connect.CfConfig;
import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.util.CheckUtils;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class BackupConfig extends XMLConfigurableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387090603156260057L;

	public static final String PROP_DELETE_FIRST = "delete-first";
	
	public BackupConfig() {
		this.tableList = new ArrayList<TableConfig>();
		this.sequenceList = new ArrayList<SequenceConfig>();
		this.preSqlToList = new ArrayList<String>();
		this.postSqlToList = new ArrayList<String>();
		this.preSqlFromList = new ArrayList<String>();
		this.postSqlFromList = new ArrayList<String>();
		this.adaptorMap = new HashMap<String, BackupAdaptor>(); 
		
	}
	
	private CfConfig cfConfig;
	
	public CfConfig getCfConfig() {
		return cfConfig;
	}

	public void setCfConfig(CfConfig cfConfig) {
		this.cfConfig = cfConfig;
	}

	private Map<String, BackupAdaptor> adaptorMap;
	
	private Properties generalProperties;
	
	private TableBackup tableBackup;
	
	private ConnectionFactory factoryFrom;
	
	private ConnectionFactory factoryTo;
	
	private List<TableConfig> tableList;
	
	private List<SequenceConfig> sequenceList;
	
	private List<String> preSqlToList;
	
	private List<String> postSqlToList;
	
	private List<String> preSqlFromList;
	
	private List<String> postSqlFromList;

	
	
	public Map<String, BackupAdaptor> getAdaptorMap() {
		return adaptorMap;
	}

	private static void popoulateSqlList( SearchDOM searchDOM, Element tag, List<String> list ) {
		if ( tag != null ) {
			List<Element> tags = searchDOM.findAllTags( tag , "sql" );
			Iterator<Element> tagIt = tags.iterator();
			while ( tagIt.hasNext() ) {
				Element currentTag = (Element)tagIt.next();
				String sql = searchDOM.findText( currentTag );
				list.add( sql );
			}
		}
	}
	
	public ConnectionFactory configureConnectionFactory( Element factoryConfig, CfConfig cfConfig, String type ) throws DAOException {
		Properties props = DOMUtils.attributesToProperties( factoryConfig );
		String refid = props.getProperty( "refid", "" );
		ConnectionFactory cf = null;
		if ( refid.equals( "" ) ) {
			cf = ConnectionFactoryImpl.newInstance( props );
		} else {
			cf = (ConnectionFactory)cfConfig.getCfMap().get( refid );
			if ( cf == null ) {
				throw new DAOException( "No connection factory with id '"+refid+"'" );
			}
		}
		try {
			Connection conn = cf.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			this.getLogger().info( "Database '"+type+"' : "+dbmd.getDatabaseProductName()+" "+dbmd.getDatabaseProductVersion() );
			this.getLogger().info( "Driver   '"+type+"' : "+dbmd.getDriverName()+" "+dbmd.getDriverVersion() );
			conn.close();
		} catch (Exception e) {
			this.getLogger().warn( "Error logging driver data '"+type+"'" );
		}
		return cf;
	}
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure(Element config) throws ConfigException {
		try {
			SearchDOM searchDOM = SearchDOM.newInstance( true , true );
			
			this.tableList.clear();
			
			this.sequenceList.clear();
			
			this.adaptorMap.clear();
			
			Element backupConfig = searchDOM.findTag( config , "backup-config" );
			Properties props = DOMUtils.attributesToProperties( backupConfig );
			
			//System.err.println( "backupConfig: "+props );
			
			this.generalProperties = props;
			
			String type = props.getProperty( "type" );
			if ( type != null ) {
				this.tableBackup = (TableBackup)ClassHelper.newInstance( type );	
			} else {
				this.tableBackup = new DefaultTableBackup();
			}
			
			String commitOn = props.getProperty( TableBackup.PROP_COMMIT_ON );
			if ( commitOn != null ) {
				this.tableBackup.setProperty( TableBackup.PROP_COMMIT_ON, commitOn );
			}
			
			String insetMode = props.getProperty( TableBackup.PROP_INSERT_MODE );
			if ( insetMode != null ) {
				this.tableBackup.setProperty( TableBackup.PROP_INSERT_MODE, insetMode );
			}
			
			this.tableBackup.setProperty( TableBackup.PROP_STATEMENT_MODE , props.getProperty( TableBackup.PROP_STATEMENT_MODE, TableBackup.PROP_STATEMENT_MODE_BATCH ) );
			this.tableBackup.setProperty( TableBackup.PROP_ADAPTOR_FROM , props.getProperty( TableBackup.PROP_ADAPTOR_FROM, TableBackup.PROP_ADAPTOR_VALUE_DEFAULT ) );
			this.tableBackup.setProperty( TableBackup.PROP_ADAPTOR_TO , props.getProperty( TableBackup.PROP_ADAPTOR_TO, TableBackup.PROP_ADAPTOR_VALUE_DEFAULT ) );
			this.tableBackup.setProperty( TableBackup.PROP_COLUMN_CHECK_MODE , props.getProperty( TableBackup.PROP_COLUMN_CHECK_MODE, TableBackup.PROP_COLUMN_CHECK_MODE_VALUE_COMPLETE ) );
			
			Element factoryFromTag = searchDOM.findTag( backupConfig , "cf-from-config" );
			this.factoryFrom = configureConnectionFactory( factoryFromTag, this.getCfConfig(), "from" );
			
			Element factoryToTag = searchDOM.findTag( backupConfig , "cf-to-config" );
			this.factoryTo = configureConnectionFactory( factoryToTag, this.getCfConfig(), "to" );
			
			
			Element adaptorListTag = searchDOM.findTag( backupConfig , "adaptor-list" );
			if ( adaptorListTag != null ) {
				List<Element> adaptorTags = searchDOM.findAllTags( adaptorListTag , "adaptor" );
				for ( int k=0; k<adaptorTags.size(); k++ ) {
					Element currentTag = adaptorTags.get( k );
					String idAdatpor = currentTag.getAttribute( "id" );
					String typeAdatpor = currentTag.getAttribute( "type" );
					BackupAdaptor adaptor = (BackupAdaptor)ClassHelper.newInstance( typeAdatpor );
					this.adaptorMap.put( idAdatpor , adaptor );
					adaptor.configure( currentTag );
				}
			}
			
			
			Element sequenceListTag = searchDOM.findTag( backupConfig , "sequence-list" );
			if ( sequenceListTag != null ) {
				List<Element> sequenceTags = searchDOM.findAllTags( sequenceListTag , "sequence" );
				Iterator<Element> squenceTagsIt = sequenceTags.iterator();
				while ( squenceTagsIt.hasNext() ) {
					Element sequenceTag = squenceTagsIt.next();
					String sequenceName = searchDOM.findText( sequenceTag );
					SequenceConfig sequenceConfig = new SequenceConfig();
					sequenceConfig.setSequenceName(sequenceName);
					this.sequenceList.add( sequenceConfig );
				}
			}
			
			// execute on destination connection before and post
			Element preSqlToTag = searchDOM.findTag( backupConfig , "pre-sql-to" );
			popoulateSqlList( searchDOM , preSqlToTag, this.preSqlToList );
			Element postSqlToTag = searchDOM.findTag( backupConfig , "post-sql-to" );
			popoulateSqlList( searchDOM , postSqlToTag, this.postSqlToList );

			// execute on source connection before and post
			Element preSqlFromTag = searchDOM.findTag( backupConfig , "pre-sql-from" );
			popoulateSqlList( searchDOM , preSqlFromTag, this.preSqlFromList );
			Element postSqlFromTag = searchDOM.findTag( backupConfig , "post-sql-from" );
			popoulateSqlList( searchDOM , postSqlFromTag, this.postSqlFromList );
			
			
			Element tableListTag = searchDOM.findTag( backupConfig , "table-list" );
			
			List<Element> tableTags = searchDOM.findAllTags( tableListTag , "table" );
			Iterator<Element> tableTagsIt = tableTags.iterator();
			while ( tableTagsIt.hasNext() ) {
				//System.out.println( "START ITERATE" );
				Element tableTag = tableTagsIt.next();
				Properties tableAtts = DOMUtils.attributesToProperties( tableTag );
				String table = searchDOM.findText( tableTag );
				//System.out.println( "E: "+tableTag.getNodeName() );
				//System.out.println( "T: "+table );
				TableConfig tableConfig = new TableConfig();
				tableConfig.setTable(table);
				String select = tableAtts.getProperty( "select" );
//				System.err.println( "TABLE  : "+table );
//				System.err.println( "PROPS  : "+tableAtts );
//				System.err.println( "SELECT : "+select );
				tableConfig.setSelect( select );
				String meta = tableAtts.getProperty( "meta" );
				tableConfig.setMeta( meta );
				String adaptorFrom = tableAtts.getProperty( "adaptor-from" );
				String adaptorTo = tableAtts.getProperty( "adaptor-to" );
				if ( adaptorFrom != null ) {
					tableConfig.setAdaptorFrom( (BackupAdaptor)this.getAdaptorMap().get( adaptorFrom ) );
				}
				if ( adaptorTo != null ) {
					tableConfig.setAdaptorTo( (BackupAdaptor)this.getAdaptorMap().get( adaptorTo ) );
				}
				this.tableList.add( tableConfig );
				
				//System.out.println( "END ITERATE" );
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteFirst() {
		return CheckUtils.isTrue( this.getGeneralProperties().getProperty( PROP_DELETE_FIRST ) );
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

	/**
	 * @return the tableBackup
	 */
	public TableBackup getTableBackup() {
		return tableBackup;
	}

	/**
	 * @return the tableList
	 */
	public List<TableConfig> getTableList() {
		return tableList;
	}

	/**
	 * @return the generalProperties
	 */
	public Properties getGeneralProperties() {
		return generalProperties;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo sequenceList.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of sequenceList.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo sequenceList.</jdl:text>
	 *         		<jdl:text lang='en'>the value of sequenceList.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public List<SequenceConfig> getSequenceList() {
		return sequenceList;
	}

	public List<String> getPreSqlToList() {
		return preSqlToList;
	}

	public List<String> getPostSqlToList() {
		return postSqlToList;
	}

	public List<String> getPreSqlFromList() {
		return preSqlFromList;
	}

	public List<String> getPostSqlFromList() {
		return postSqlFromList;
	}	
	
	
	
	
	
}
