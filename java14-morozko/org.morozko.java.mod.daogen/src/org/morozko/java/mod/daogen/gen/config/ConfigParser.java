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
 * @(#)ConfigParser.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.mod.daogen.gen.config.types.SQLType;
import org.morozko.java.mod.daogen.gen.config.types.TypeHandler;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.connect.pool.ConnectionFactoryPool;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.lang.Result;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.core.xml.XMLValidator;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.core.xml.sax.XMLValidatorSAX;
import org.morozko.java.core.xml.sax.er.ByteArrayEntityResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * <p>Estrae la configurazione del sistema dagli opportuni file di configurazione.</p>
 *
 * @author mfranci
 *
 */
public class ConfigParser extends BasicLogObject {

	private static final String DTD = "/org/morozko/java/mod/daogen/gen/config/res/dao-gen-1-0.dtd";
	
	private static final String DEF_PROPS = "/org/morozko/java/mod/daogen/gen/config/daogen.properties";
	
	public final static String SYSTEM_ID = "http://www.morozko.org/data/java/mod/dao-gen/dtd/dao-gen-1-0.dtd";
	
	private static final SearchDOM DOMSEARCH = SearchDOM.newInstance( true, true );
	
	private static String queryFormat( String sql ) {
		MessageFormat f = new MessageFormat( sql );
		Object[] args = { "" };
		return f.format( args );
	}
	
	private Document validate( File source ) throws Exception {
		
		Document doc = null;
		
		FileInputStream fis = new FileInputStream( source );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		
		StreamIO.pipeStream( fis, buffer, StreamIO.MODE_CLOSE_BOTH );
		
		StringReader reader1 = new StringReader( buffer.toString() );
		StringReader reader2 = new StringReader( buffer.toString() );

		InputStream dtdStream = ConfigParser.class.getResourceAsStream( DTD );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamIO.pipeStream( dtdStream, baos, StreamIO.MODE_CLOSE_BOTH );
		ByteArrayEntityResolver er = new ByteArrayEntityResolver( baos.toByteArray(), null, SYSTEM_ID );
		
		XMLValidator validator = XMLValidatorSAX.newInstance( er );
		Result result = validator.validateXML(reader1);
		
		if ( result.isTotalSuccess() ) {
			doc = DOMIO.loadDOMDoc( new InputSource( reader2 ) , er ); 
		} else {
			result.printErrorReport( System.out );
		}
		
		return doc;
		
	}	
	
    private static String oneLine( String line ) throws IOException {
    	
        String result = "";
        if ( line!= null ) {
        	for (int k=0; k<line.length(); k++) {
                if (line.charAt(k)!='\n') {
                    result+= line.charAt( k );
                } else {
                    result+= " ";
                }
            }	
        } else {
        	result = null;
        }
        return result;
    }   	

    
    public static void populateFields( DGConfig dgConfig, String tableName, String tableSQL, List fields ) throws Exception {
    	if ( tableSQL != null ) {
    		LogFacade.getLog().info( "ConfigParser.populateFields() 1 : "+tableSQL );
    		tableSQL = queryFormat( tableSQL );
    		LogFacade.getLog().info( "ConfigParser.populateFields() 2 : "+tableSQL );
        	Connection conn = dgConfig.getConnectionFactory().getConnection();
        	String sql = " SELECT v.* FROM ( "+tableSQL+" ) v WHERE 1=0";
        	LogFacade.getLog().debug( "populateFields QUERY : "+sql );
        	ResultSet rs = conn.createStatement().executeQuery( sql );
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int cols = rsmd.getColumnCount();
        	for ( int k=0; k<cols; k++ ) {
        		int current = k+1;
        		String fieldName = rsmd.getColumnName( current );
        		int type = rsmd.getColumnType( current );
        		if ( type == Types.BINARY || type == Types.LONGVARBINARY ) {
        			type = Types.BLOB;
        		}
        		SQLType sqlType = (SQLType)SQLType.KNOWN_TYPES_SQL.get( new Integer( type ) );
        		String sqlTypeName = rsmd.getColumnTypeName( current );
        		if ( sqlType == null ) {
        			String error = "Tipo dati sconoscito per la tabella ("+tableName+"), campo "+fieldName+" : "+sqlTypeName;
        			LogFacade.getLog().info( "ERRORE -> "+error );
        			dgConfig.getErrorList().add( error );
        		} else {
        			LogFacade.getLog().info( "Campo : "+fieldName+" Tipo : "+sqlTypeName );
        			fields.add( new FieldConfig( fieldName, sqlType, (TypeHandler)TypeHandler.HANDLERS.get( sqlType ) ) );
        		}
        	}
        	rs.close();
        	conn.close();    		
    	}
    }
	
    private static String prepareTableName( String tn) throws IOException {
    	String result = tn;
    	return oneLine( result );
    }    
    
    private static Object newInstance( String type ) throws Exception {
    	Object obj = null;
    	Class c = Class.forName( type );
    	obj = c.newInstance();
    	return obj;
    }
    
	public DGConfig parseConfig( String path ) throws Exception {
		this.getLog().info( "DAOGEN parseConfig START : "+path );
		DGConfig dgConfig = new DGConfig();
		
		// leggo le proprietà generali
        File configDir = new File( path );
        FileInputStream fis = new FileInputStream( (new File( configDir, "daogen.properties" ) ) );
        dgConfig.getGeneralProps().load( fis );
        fis.close();
        
        Properties defProps = new Properties();
        InputStream is = ConfigParser.class.getResourceAsStream( DEF_PROPS );
        defProps.load( is );
        is.close();
        
        File excludeFile = new File( configDir, "exclude.properties" );
        if ( excludeFile.exists() ) {
        	dgConfig.setExcludeProps( PropsIO.loadFromFile( excludeFile ) );
        } else {
        	dgConfig.setExcludeProps( new Properties() );
        }

        
        Enumeration e = defProps.keys();
        while ( e.hasMoreElements() ) {
        	String k = (String)e.nextElement();
        	String rv = dgConfig.getGeneralProps().getProperty( k );
        	if ( rv==null ) {
        		dgConfig.getGeneralProps().setProperty( k, defProps.getProperty( k ) );
        		this.getLog().info( "general property default   : "+k+"='"+defProps.getProperty( k )+"'" );
        	} else {
        		this.getLog().info( "general property ovverride : "+k+"='"+rv+"'" );
        	}
        }
        
        
        // test di connessione
        this.getLog().info( "Crea la factory di connessione" );
        ConnectionFactory connectionFactory = ConnectionFactoryImpl.newInstance( dgConfig.getGeneralProps().getProperty( "jdbc.drv" ),
        																		dgConfig.getGeneralProps().getProperty( "jdbc.url" ),
        																		dgConfig.getGeneralProps().getProperty( "jdbc.usr" ),
        																		dgConfig.getGeneralProps().getProperty( "jdbc.pwd" ) );
        
        if ( "true".equalsIgnoreCase( dgConfig.getGeneralProps().getProperty( "pooled" ) ) ) {
        	connectionFactory = ConnectionFactoryPool.newFactory( connectionFactory, 1, 10, 10 );
        	this.getLog().info( "Pooled factory [OK]" );
        }
        
        
        this.getLog().info( "Factory di connessione [OK]" );
        this.getLog().info( "Test di connessione" );
        connectionFactory.getConnection().close();
        this.getLog().info( "Test di connessione [OK]" );
        
        dgConfig.setConnectionFactory( connectionFactory );
        
        // parsing tables.xml
        File file = new File( configDir, "dao-gen.xml" );
        this.getLog().info( "Parsing xml : "+file.getCanonicalPath() );
        Document doc = validate( file );
        if ( doc != null ) {

            Element root = doc.getDocumentElement();

            // id generator
            Element listIdGeneratorTag = DOMSEARCH.findTag( root, "id-generator-list" );
            if ( listIdGeneratorTag != null ) {
            	List listIdGenerator =  DOMSEARCH.findAllTags( listIdGeneratorTag, "id-generator" );
            	for ( int k=0; k<listIdGenerator.size(); k++) {
            		Element idGeneratorTag = (Element)listIdGenerator.get( k );
            		Properties idGeneratorAtts = DOMUtils.attributesToProperties( idGeneratorTag );
            		IdGeneratorConfig idGeneratorConfig = new IdGeneratorConfig();
            		idGeneratorConfig.setName( idGeneratorAtts.getProperty( "name" ) );
            		idGeneratorConfig.setType( idGeneratorAtts.getProperty( "type" ) );
            		String config = idGeneratorAtts.getProperty( "config" );
            		Properties props = new Properties();
            		String[] configProps = config.split( ";" );
            		for ( int i=0; i<configProps.length; i++ ) {
            			String[] split = configProps[k].split( "=" );
            			props.setProperty( split[0], split[1] );
            		}
            		idGeneratorConfig.setConfig( props );
            		this.getLog().info( "idGenerator : "+idGeneratorConfig );
            		dgConfig.getIdGeneratorMap().put( idGeneratorConfig.getName(),  idGeneratorConfig );
            	}
            }
            
            // type handlers
            Element listTypeHandlerTag = DOMSEARCH.findTag( root, "type-handler-list" );
            if ( listTypeHandlerTag != null ) {
            	List listTypeHandler =  DOMSEARCH.findAllTags( listTypeHandlerTag, "type-handler" );
            	for ( int k=0; k<listTypeHandler.size(); k++ ) {
            		Element typeHandlerTag = (Element)listTypeHandler.get( k );
            		Properties typeHandlerAtts = DOMUtils.attributesToProperties( typeHandlerTag );
            		String name = typeHandlerAtts.getProperty( "name" );
            		String type = typeHandlerAtts.getProperty( "type" );
            		String defaultFor = typeHandlerAtts.getProperty( "default-for" );
            		TypeHandler th = (TypeHandler) newInstance( typeHandlerAtts.getProperty( "type" ) );
            		if ( defaultFor != null ) {
            			this.getLog().info( "Default Handler Override : "+name+" - "+type+", default-for : "+defaultFor );
            			SQLType typeSQL = (SQLType)SQLType.KNOWN_TYPES_NAME.get( defaultFor );
            			TypeHandler.HANDLERS.put( typeSQL, th );
            		} else {
            			this.getLog().info( "Custom Type Handler : "+name+" - "+type );
            			dgConfig.getCustomTypeHandlers().put( name, th );
            		}
            	}
            }

            // tabelle
            List list = DOMSEARCH.findAllTags( root, "table" );
            List relationsCache = new ArrayList();
            for (int k=0; k<list.size(); k++) {
                Element table = (Element)list.get( k );
                // xml version
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
            	DOMIO.writeDOMIndent( table , baos );
            	String xmlTable = baos.toString();
            	
                // start
                Properties props = DOMUtils.attributesToProperties( table );
                TableConfig tableConfig = new TableConfig();
                tableConfig.setXmlTable( xmlTable );
                
                // il nome del nuovo oggetto
                String name = props.getProperty( "name" ) ;
                this.getLog().info( "table name '"+name+"'" );
                // fake
                String fake = props.getProperty( "fake" ) ;
                this.getLog().info( "table isFake '"+fake+"'" );
                if ( "true".equalsIgnoreCase( fake ) ) {
                	tableConfig.setFake( true );
                } else {
                	tableConfig.setFake( false );
                }
                // la tabella di modifica
                String update = props.getProperty("update");
                this.getLog().info( "update sql '"+update+"'" );
                String key = props.getProperty("key");
                tableConfig.setTableName( name );
                tableConfig.setTableUpdateSQL( prepareTableName( update ) );
                // aggiungo la chiave di modifica/cancellazione della tabella
                String[] keys = null;
                if ( key != null ) {
                	keys = key.split( ";" );
                }
                tableConfig.setUpdateKey( keys );
                // eventuale ordinamento
                String orderBy = props.getProperty("order-by");
                this.getLog().info( "order-by '"+orderBy+"'" );
                tableConfig.setOrderBy( orderBy );
                
                // eventuale generator di ID
                String idGenerator = props.getProperty( "id-generator" );
                this.getLog().info( "id-generator '"+idGenerator+"'" );
                tableConfig.setIdGenerator( idGenerator );
                
                // la vista virtuale sull' oggetto
                String text = DOMSEARCH.findText( DOMSEARCH.findTag( table, "view" ) );
                tableConfig.setTableViewSQL( prepareTableName( text ) );
                this.getLog().info( "table view : '"+tableConfig.getTableViewSQL()+"'" );
                // aggiunto eventuali campi personalizzati
                if ( !tableConfig.isFake() ) {
                    populateFields( dgConfig, tableConfig.getTableName(), tableConfig.getTableViewSQL(), tableConfig.getFields() );
                    if ( tableConfig.getTableUpdateSQL() != null ) {
                    	populateFields( dgConfig, tableConfig.getTableName(), "SELECT * FROM "+tableConfig.getTableUpdateSQL(), tableConfig.getFieldsUpdate() );	
                    }                	
                }                
                
                // campi START
                Element fieldsTag = DOMSEARCH.findTag( table, "fields" );
                if (fieldsTag!=null) {
                	List fieldList = DOMSEARCH.findAllTags( fieldsTag, "field" );
                	for (int i=0; i<fieldList.size(); i++) {
                		Properties fieldAtts = DOMUtils.attributesToProperties( (Element)fieldList.get( i ) );
                		String fieldName = fieldAtts.getProperty( "name" ).toLowerCase();
                		String fieldType = fieldAtts.getProperty( "type" ).toLowerCase();
                		String fieldTypeHandler = fieldAtts.getProperty( "type-handler" );
                		boolean fieldFake = Boolean.valueOf( fieldAtts.getProperty( "fake" ) ).booleanValue();
                		boolean fieldUnsafe = Boolean.valueOf( fieldAtts.getProperty( "unsafe" ) ).booleanValue();
                		boolean fieldExcludeRse = Boolean.valueOf( fieldAtts.getProperty( "exclude-rse" ) ).booleanValue();
                		FieldConfig fieldConfig = tableConfig.getFieldConfig( fieldName );
                		SQLType newSQLtype = (SQLType)SQLType.KNOWN_TYPES_NAME.get( fieldType );
                		if ( newSQLtype == null ) {
                			dgConfig.getErrorList().add( "Tipo dati sconoscito per la tabella "+name+", campo "+fieldName+" : "+fieldType );
                		} else if ( fieldConfig == null && !fieldFake && !tableConfig.isFake() ) {
                			dgConfig.getErrorList().add( "Il campo "+fieldName+", non esiste nella tabella "+name );
                		} else if ( fieldConfig != null && fieldFake ) {
                			dgConfig.getErrorList().add( "Il campo "+fieldName+", non può essere definito di tipo fake in quanto è definito nella tabella "+name );
                		} else {
                			if ( fieldFake ) {
                				fieldConfig = new FieldConfig( fieldName, newSQLtype, (TypeHandler)TypeHandler.HANDLERS.get( newSQLtype ) );
                				fieldConfig.setFake( fieldFake );
                				fieldConfig.setFakeDefault( fieldAtts.getProperty( "fake-default" ) );
                				tableConfig.getFields().add( fieldConfig );
                				this.getLog().info( "field fake     : '"+fieldName+"' from '"+fieldConfig.getFieldType().getTypeName()+"' to '"+fieldType+"' (type-handler:"+fieldTypeHandler+") - default : "+fieldConfig.getFakeDefault() );
                			} else {
                				fieldConfig.setFieldType( newSQLtype );
                				fieldConfig.setUnsafe( fieldUnsafe );
                				fieldConfig.setExcludeRse( fieldExcludeRse );
                				this.getLog().info( "field override : '"+fieldName+"' from '"+fieldConfig.getFieldType().getTypeName()+"' to '"+fieldType+"' (type-handler:"+fieldTypeHandler+") unsafe:"+fieldUnsafe+" exclude-rse:"+fieldExcludeRse );	
                			}
                			if ( fieldTypeHandler != null ) {
                				TypeHandler th = (TypeHandler)dgConfig.getCustomTypeHandlers().get( fieldTypeHandler );
                				if ( th != null ) {
                					this.getLog().info( "setting type handler 1 : "+th );	
                					fieldConfig.setTypeHandler( th );	
                				} else {
                					dgConfig.getErrorList().add( "Il campo "+fieldName+", ha un type handler personalizzato non definito" );		
                				}
                			} else {
                				TypeHandler th = (TypeHandler)TypeHandler.HANDLERS.get( newSQLtype );
                				if ( th != null ) {
                					this.getLog().info( "setting type handler 2 : "+th );	
                					fieldConfig.setTypeHandler( th );	
                				} else {
                					dgConfig.getErrorList().add( "Il campo "+fieldName+", ha un type handler non definito : "+newSQLtype.getSqlType() );		
                				}
                				//dgConfig.getErrorList().add( "test : "+(newSQLtype.equals( SQLType.VARCHAR ) ) );
                			}
                			
                		}
                		// aggiungere verifiche sui tipi di campi
                		
                	}
                } 
                // campi END
                
                // alias START
                Element aliasesTag = DOMSEARCH.findTag( table, "aliases" );
                if ( aliasesTag != null ) {
                	List aliasTagList = DOMSEARCH.findAllTags( aliasesTag, "alias" );
                	Iterator it = aliasTagList.iterator();
                	while ( it.hasNext() ) {
                		Element aliasTag = (Element)it.next();
                		Properties aliasAtts = DOMUtils.attributesToProperties( aliasTag );
                		AliasConfig aliasConfig = new AliasConfig();
                		aliasConfig.setName( aliasAtts.getProperty( "name" ) );
                		aliasConfig.setField( aliasAtts.getProperty( "field" ) );
                		this.getLog().debug( "alias name  : "+aliasConfig.getName() );
                		this.getLog().debug( "alias field : "+aliasConfig.getField() );
                		if ( tableConfig.getFieldConfig( aliasConfig.getField() ) == null ) {
                			dgConfig.getErrorList().add( "L' alias "+aliasConfig.getName()+" della tabella "+tableConfig.getTableName()+" fa riferimento ad un campo non definito : "+aliasConfig.getField() );
                		} else {
                			tableConfig.getAliasList().add( aliasConfig );
                		}
                	}
                }
                // alias END
                
                // aggiunto eventuali relazioni
                Element relationsTag = DOMSEARCH.findTag( table, "relations" );
                if ( relationsTag != null ) {
                	List relationList = DOMSEARCH.findAllTags( relationsTag, "relation" );
                	for ( int i=0; i<relationList.size(); i++ ) {
                		Element relation = (Element)relationList.get( i );
                		Properties relationAtts = DOMUtils.attributesToProperties( relation );
                		RelationConfig relationConfig = new RelationConfig();
                		relationConfig.setName( relationAtts.getProperty( "name" ) );
                		relationConfig.setType( relationAtts.getProperty( "type" ) );
                		relationConfig.setTable( relationAtts.getProperty( "table" ) );
                		relationConfig.setFieldList( Arrays.asList( relationAtts.getProperty( "field" ).split(";") ) );
                		relationConfig.setTableOut( tableConfig.getTableName() );
                		relationConfig.setCascade( "true".equalsIgnoreCase( relationAtts.getProperty( "cascade"  )  ) );
                		String orderByRel = relationAtts.getProperty( "order-by" );
                		relationConfig.setOrderBy( orderByRel );
                		String fieldOut = relationAtts.getProperty( "field-out" );
                		if ( fieldOut != null ) {
                			relationConfig.setFieldOutList( Arrays.asList( fieldOut.split( ";" ) ) );
                		} else {
                			relationConfig.setFieldOutList( relationConfig.getFieldList() );
                		}
                		Element relationSql = DOMSEARCH.findTag( relation , "relation-sql");
                		if ( relationSql!= null ) {
                			relationConfig.setSql( oneLine( DOMSEARCH.findText( relationSql ) ) );
                		}
                		this.getLog().info( "relation name       : "+relationConfig.getName() );
                		this.getLog().info( "relation type       : "+relationConfig.getType() );
                		this.getLog().info( "relation table      : "+relationConfig.getTable() );
                		this.getLog().info( "relation table out  : "+relationConfig.getTableOut() );
                		this.getLog().info( "relation fields     : "+relationConfig.getFieldList() );
                		this.getLog().info( "relation fields-out : "+relationConfig.getFieldOutList() );
                		this.getLog().info( "relation cascade    : "+relationConfig.isCascade() );
                		this.getLog().info( "relation sql        : "+relationConfig.getSql() );
                		this.getLog().info( "order by            : "+relationConfig.getOrderBy() );
                		tableConfig.getRelations().add( relationConfig );
                		relationsCache.add( relationConfig );
                		if ( relationConfig.getFieldList().size() != relationConfig.getFieldOutList().size() ) {
                			dgConfig.getErrorList().add( "La relazione "+relationConfig.getName()+" non ha lo stesso numero di campi di join." );
                		}
                	}
                }
                
                // configurzazione tag loads start
                Element operationsTag = DOMSEARCH.findTag( table, "operations" );
                if ( operationsTag != null ) {
                	List operationTagList = DOMSEARCH.findAllTags( operationsTag, "operation" );
                	Iterator it = operationTagList.iterator();
                	while ( it.hasNext() ) {
                		Element operationTag = (Element)it.next();
                		Properties operationAtts = DOMUtils.attributesToProperties( operationTag );
                		OperationConfig operationConfig = new OperationConfig();
                		operationConfig.setName( operationAtts.getProperty( "name" ) );
                		operationConfig.setType( operationAtts.getProperty( "type" ) );
                		operationConfig.setFieldList( Arrays.asList( operationAtts.getProperty( "field" ).split( ";" ) ) );
                		this.getLog().debug( "operation name      : "+operationConfig.getName() );
                		this.getLog().debug( "operation type      : "+operationConfig.getType() );
                		this.getLog().debug( "operation field     : "+operationConfig.getFieldList() );
                		tableConfig.getOperationsList().add( operationConfig );
                	}
                }
                // configurzazione tag loads end
                
                // configurzazione tag loads start
                Element loadsTag = DOMSEARCH.findTag( table, "loads" );
                if ( loadsTag != null ) {
                	List loadTagList = DOMSEARCH.findAllTags( loadsTag, "load" );
                	Iterator it = loadTagList.iterator();
                	while ( it.hasNext() ) {
                		Element loadTag = (Element)it.next();
                		Properties loadAtts = DOMUtils.attributesToProperties( loadTag );
                		LoadConfig loadConfig = new LoadConfig();
                		loadConfig.setName( loadAtts.getProperty( "name" ) );
                		loadConfig.setRelations( Boolean.valueOf( loadAtts.getProperty( "relations" ) ).booleanValue() );
                		loadConfig.setType( loadAtts.getProperty( "type" ) );
                		loadConfig.setFieldList( Arrays.asList( loadAtts.getProperty( "field" ).split( ";" ) ) );
                		this.getLog().debug( "load name      : "+loadConfig.getName() );
                		this.getLog().debug( "load type      : "+loadConfig.getType() );
                		this.getLog().debug( "load relations : "+loadConfig.isRelations() );
                		this.getLog().debug( "load field     : "+loadConfig.getFieldList() );
                		tableConfig.getLoadList().add( loadConfig );
                	}
                }
                // configurzazione tag loads end                
                
                
                // aggiungo la tabella configurata
                dgConfig.getTableConfigList().add( tableConfig );
            }        	
            for ( int k = 0; k< relationsCache.size(); k++  ) {
            	RelationConfig relationConfig = (RelationConfig)relationsCache.get( k );
            	TableConfig tableConfig = dgConfig.getTable( relationConfig.getTable() );
            	tableConfig.getRelationsOut().add( relationConfig );
            }
        } else {
        	this.getLog().info( "dao-gen.xml non valido" );
        }
               
        this.getLog().info( "DAOGEN parseConfig END : "+path );
		return dgConfig;
	}
	
}
