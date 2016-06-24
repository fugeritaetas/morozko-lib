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
 * @(#)DAOCoder.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
import org.morozko.java.mod.daogen.gen.config.IdGeneratorConfig;
import org.morozko.java.mod.daogen.gen.config.LoadConfig;
import org.morozko.java.mod.daogen.gen.config.OperationConfig;
import org.morozko.java.mod.daogen.gen.config.RelationConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DAOCoder extends Coder {
	
    private static String prepareTableName( String tn) throws IOException {
    	String result = "";
    	if ( tn != null ) {
        	int index = tn.indexOf( "\"" );
        	while ( index != -1 ) {
        		result+= tn.substring( 0, index )+"\\\"";
        		tn = tn.substring( index+1 );
        		index = tn.indexOf( "\"" );
        	}
        	result+= tn;    		
    	}
    	return result;
    }    	
	
    private static String whereUtil( List fieldName, TableConfig tableConfig ) {
    	String sql = " ";
    	Iterator it = fieldName.iterator();
    	boolean first = true;
    	while ( it.hasNext() ) {
    		FieldConfig fieldConfig = tableConfig.getFieldConfig( ( String )it.next() );
    		if ( first ) {
    			first = false;
    		} else {
    			sql+= " AND ";
    		}
    		sql+= "v."+fieldConfig.getFieldName()+"=? ";
    	}
    	return sql;
    }
    
    public static void generate( PrintStream stream, DGConfig dgConfig, TableConfig tableConfig ) throws Exception {
    	
    	Properties generalProps = dgConfig.getGeneralProps();
    	
    	boolean jvm5 = "5".equalsIgnoreCase( dgConfig.getGeneralProps().getProperty( "daogen.target.vm" ) );
    	
    	String name = tableConfig.getTableName();
        String pName = generalProps.getProperty( "package.dao" )+".helpers";
        String cName = name+"DAOHelper";
        String modelName = name+"Model";
        String rseName = modelName+"RSEHelper";
        
        String moduleDaoFactory = null; 
        if ( generalProps.getProperty( "factory.dao.module" ) != null ) {
        	moduleDaoFactory = generalProps.getProperty( "factory.dao.module" )+"Helper";
        }

		String resultList = "List";
		String newList = "this.newList()";
        String loadAppend = "";
		if ( jvm5 ) {
			resultList+= "<"+modelName+">";
			newList = "new java.util.ArrayList<"+modelName+">()";
			//loadAppend = "J5";
		}        
        
        addClassComment( stream, cName, pName, dgConfig );
        stream.println( "package "+pName+";" );
        stream.println();
        
        // import START
        stream.println( "import java.util.List;" );
        stream.println( "import org.morozko.java.core.util.result.PagedResult;" );
        stream.println( "import org.morozko.java.mod.db.dao.PageInfoDB;" );
        stream.println( "import "+generalProps.getProperty( "package.model" )+"."+modelName+";" );
        stream.println( "import "+generalProps.getProperty( "field.type" )+";" );
        stream.println( "import "+generalProps.getProperty( "class.opdao" )+";" );
        stream.println( "import "+generalProps.getProperty( "field.list.type" )+";" );
        stream.println( "import "+generalProps.getProperty( "load.result.type" )+";" );
        stream.println( "import "+generalProps.getProperty( "dao.exception.type" )+";" );
        stream.println( "import "+generalProps.getProperty( "interface.idg" )+";" );
        stream.println();
        // import END
        
        // inizio classe START
        addTypeComment( stream, "Classe per la gestione dell' accesso al DB per oggetti di "+name+"Model", dgConfig );
        stream.println( "public class "+cName+" extends "+generalProps.getProperty( "superclass.dao" )+" {"  );
        stream.println();
        // inizio classe END        
        
        // serial ver UID START
        stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
        stream.println();      
        // serial ver UID END
        
        // variabili e costanti START
        stream.println( "    protected static final "+rseName+" RSE_MAIN = new "+rseName+"();" );
        stream.println();
        stream.println( "    protected static final String QUERY_VIEW = \""+prepareTableName( tableConfig.getTableViewSQL() )+"\";" );
        stream.println();
        stream.println( "    protected String queryView;" );
        String update = tableConfig.getTableUpdateSQL();       
        stream.println();
        stream.println( "    protected static final String SQL_UPDATE = \""+prepareTableName( update )+"\";" );
        stream.println();
        stream.println( "    protected String sqlUpdate;" );
        stream.println();
        stream.println();
        stream.println( "	 public String getQueryViewString() { return QUERY_VIEW; }");
        stream.println( "	 public String getSqlUpdateString() { return SQL_UPDATE; }");
        stream.println();
        // variabili e costanti END
        
        // module dao factory start
        if ( moduleDaoFactory != null ) {
            stream.println( "    private "+moduleDaoFactory+" moduleDaoFactory;" );
            stream.println();
            stream.println( makeSetterWorker( "moduleDaoFactory", moduleDaoFactory ) );
            stream.println();
            stream.println( makeGetterWorker( "moduleDaoFactory", moduleDaoFactory ) );
            stream.println();
            stream.println();   	
        }
        // module dao factory end

        // id generator START
        if ( tableConfig.getIdGenerator() != null ) {
        	IdGeneratorConfig idGeneratorConfig = (IdGeneratorConfig)dgConfig.getIdGeneratorMap().get( tableConfig.getIdGenerator() );
        	stream.println();   
        	stream.println( "    // id generator START" );
        	stream.println();   
        	stream.println( "    private IdGenerator idGenerator;" );
         	
        	stream.println();   
        	stream.println( "    public "+dgConfig.getGeneralProps().getProperty( "class.daoid" )+" generateId() throws DAOException {" );
        	stream.println( "    	if ( this.idGenerator == null ) { " );
        	stream.println( "    		try {" );
        	stream.println( "    			java.util.Properties props = new java.util.Properties();" );
        	Enumeration e = idGeneratorConfig.getConfig().keys();
        	while ( e.hasMoreElements() ) {
        		String key = (String)e.nextElement();
        		String value = idGeneratorConfig.getConfig().getProperty( key );
        		stream.println( "    			props.setProperty( "+stringValue( key )+","+stringValue( value )+" );" );
        		stream.println( "    			this.idGenerator = (IdGenerator)org.morozko.java.core.lang.helpers.ClassHelper.newInstance( "+stringValue( idGeneratorConfig.getType() )+" );" );
        		stream.println( "    			this.idGenerator.setConnectionFactory( this.getMainDAOFactory().getConnectionFactory() ); " );
        		stream.println( "    			this.idGenerator.configure( props ); " );
        	}
        	stream.println( "    		} catch (Exception e) {" );
        	stream.println( "    			e.printStackTrace();" );
        	stream.println( "    		}" );        	
        	stream.println( "    	} " );
        	stream.println( "    	return this.idGenerator.generateId();" );
        	stream.println( "    }" );
        	stream.println();   
        	stream.println( "    // id generator END" );
        	stream.println();   
        }
        // id generator END
        
        // load by pk START
        stream.println();
        String[] keyList = tableConfig.getUpdateKey();
        if (keyList != null && keyList.length > 0) {
            stream.print( "    protected "+modelName+" loadByPkWorker( Object "+tableConfig.getFieldConfig( keyList[0] ).getJavaFieldName()+" " );
            for (int k=1; k<keyList.length; k++) {
            	//System.out.println( "FieldName : "+keyList[k]+" : "+tableConfig.getFieldConfig( keyList[k] )+" "+tableConfig.getTableName() );
                stream.print( " , Object "+tableConfig.getFieldConfig( keyList[k] ).getJavaFieldName() );    
            }
            stream.println( " ) throws DAOException { " );
            stream.print( "        String query = \"SELECT * FROM ( \"+this.queryView+\") v WHERE "+keyList[0]+" = ?" );
            for (int k=1; k<keyList.length; k++) {
                stream.print( " AND "+keyList[k]+" = ? " );    
            }
            stream.println( " \";" );
            stream.println( "        FieldList fl = this.newFieldList(); " );
            for (int k=0; k<keyList.length; k++) {
                stream.println( "        fl.addField( this.getFieldFactory().newField( "+tableConfig.getFieldConfig( keyList[0] ).getJavaFieldName()+" ) ); " );    
            }
            stream.println( "        return ( "+modelName+" ) this.loadOne( query, fl );" );
            stream.println( "    } " );
        }
        // load by pk END
        
        // delete by pk START
        if (keyList != null && keyList.length > 0) {
            stream.print( "    public int deleteByPk( "+tableConfig.getFieldConfig( keyList[0] ).getJavaFieldType()+" "+tableConfig.getFieldConfig( keyList[0] ).getJavaFieldName()+" " );
            for (int k=1; k<keyList.length; k++) {
                stream.print( " , "+tableConfig.getFieldConfig( keyList[k] ).getJavaFieldType()+" "+tableConfig.getFieldConfig( keyList[k] ).getJavaFieldName() );    
            }
            stream.println( " ) throws DAOException { " );
            stream.print( "        String query = \"DELETE FROM \"+this.sqlUpdate+\" WHERE "+keyList[0]+" = ?" );
            for (int k=1; k<keyList.length; k++) {
                stream.print( " AND "+keyList[k]+" = ? " );    
            }
            stream.println( " \";" );
            stream.println( "        FieldList fl = this.newFieldList(); " );
            for (int k=0; k<keyList.length; k++) {
            	System.out.println( "KEY : "+keyList[k] );
                stream.println( "        fl.addField( this.getFieldFactory().newField( "+tableConfig.getFieldConfig( keyList[k] ).getJavaFieldName()+" ) ); " );    
            }
            stream.println( "        return this.update( query, fl );" );
            stream.println( "    } " );
        }                
        // delete by pk END
        
        // operation list start
        Iterator itOperations = tableConfig.getOperationsList().iterator();
        while ( itOperations.hasNext() ) {
        	OperationConfig operationConfig = (OperationConfig)itOperations.next();
        	if ( operationConfig.isTypeDelete() ) {
        		FieldConfig field0 = tableConfig.getFieldConfig( (String)operationConfig.getFieldList().get( 0 ) );
                stream.print( "    public int delete"+operationConfig.getName()+"( "+field0.getJavaFieldType()+" "+field0.getJavaFieldName()+" " );
                String queryDelete = "        String query = \"DELETE FROM \"+this.sqlUpdate+\" WHERE "+field0.getJavaFieldName()+" = ?";
                for (int k=1; k<operationConfig.getFieldList().size(); k++) {
                	FieldConfig fieldCurrent = tableConfig.getFieldConfig( (String)operationConfig.getFieldList().get( k ) );
                    stream.print( " , "+fieldCurrent.getJavaFieldType()+" "+fieldCurrent.getJavaFieldName() );
                    queryDelete+= " AND "+fieldCurrent.getJavaFieldName()+" = ? "; 
                }
                stream.println( " ) throws DAOException { " );
                stream.println( queryDelete+" \";" );
                stream.println( "        FieldList fl = this.newFieldList(); " );
                for (int k=0; k<operationConfig.getFieldList().size(); k++) {
                	FieldConfig fieldCurrent = tableConfig.getFieldConfig( (String)operationConfig.getFieldList().get( k ) );
                    stream.println( "        fl.addField( this.getFieldFactory().newField( "+fieldCurrent.getJavaFieldName()+" ) ); " );    
                }
                stream.println( "        return this.update( query, fl );" );
                stream.println( "    } " );
        	}
        }
        // operation list end
       
        // update / insert inizio
        if ( update != null ) {
        	
        	
        	// inserimento per muysql (salto il primo campo)
        	if ( tableConfig.getFieldsUpdate().size() > 2 ) {
        		FieldConfig field1 = (FieldConfig)tableConfig.getFieldsUpdate().get( 1 );
                stream.println( "    public OpDAO newInsertOpDAOMysql( "+modelName+" model ) throws DAOException { "  );
                String insQueryPart1 = field1.getFieldName();
                String insQueryPart2 = " ? ";
                String fieldSet = "       fl.addField( this.getFieldFactory().newField( model.get"+methodName( field1.getFieldName() )+"(), "+field1.getFieldType().getSqlType().intValue()+" ) );\n";
                for (int k=2; k<tableConfig.getFieldsUpdate().size(); k++) {
                    FieldConfig current = (FieldConfig)tableConfig.getFieldsUpdate().get( k );
                    insQueryPart1+= ", "+current.getFieldName();
                    insQueryPart2+= ", ?";
                    fieldSet+= "       fl.addField( model.get"+methodName( current.getFieldName() )+"(), "+current.getFieldType().getSqlType().intValue()+" );\n";
                }
                stream.println( "       String query = \"INSERT INTO \"+this.sqlUpdate+\" ( "+insQueryPart1+" ) VALUES ( "+insQueryPart2+" )\";" );
                stream.println( "       FieldList fl = this.newFieldList();" );
                stream.print( fieldSet );            
                stream.println( "       return OpDAO.newUpdateOp( query, fl );" );        
                stream.println( "    }" );  
                
                stream.println( "    public int newInsertMysql( "+modelName+" model ) throws DAOException { "  );
                stream.println( "       int result = this.update( this.newInsertOpDAO( model ) );" );
                stream.println( "       return result;" );        
                stream.println( "    }" );                
                
        	}        	
        	
        	FieldConfig field1 = (FieldConfig)tableConfig.getFieldsUpdate().get( 0 );
        	
            stream.println( "    public OpDAO newInsertOpDAO( "+modelName+" model ) throws DAOException { "  );
            String insQueryPart1 = field1.getFieldName();
            String insQueryPart2 = " ? ";
            String fieldSet = "       fl.addField( this.getFieldFactory().newField( model.get"+methodName( field1.getFieldName() )+"(), "+field1.getFieldType().getSqlType().intValue()+" ) );\n";
            for (int k=1; k<tableConfig.getFieldsUpdate().size(); k++) {
                FieldConfig current = (FieldConfig)tableConfig.getFieldsUpdate().get( k );
                insQueryPart1+= ", "+current.getFieldName();
                insQueryPart2+= ", ?";
                fieldSet+= "       fl.addField( model.get"+methodName( current.getFieldName() )+"(), "+current.getFieldType().getSqlType().intValue()+" );\n";
            }
            stream.println( "       String query = \"INSERT INTO \"+this.sqlUpdate+\" ( "+insQueryPart1+" ) VALUES ( "+insQueryPart2+" )\";" );
            stream.println( "       FieldList fl = this.newFieldList();" );
            stream.print( fieldSet );            
            stream.println( "       return OpDAO.newUpdateOp( query, fl );" );        
            stream.println( "    }" );  
            
            
            stream.println( "    public int insert( "+modelName+" model ) throws DAOException { "  );
            stream.println( "       return this.update( this.newInsertOpDAO( model ) );" );        
            stream.println( "    }" );
            
            if (keyList != null && keyList.length > 0 ) {
                
                // codice update
                              
                stream.println( "    public OpDAO newUpdateOpDAO( "+modelName+" model ) throws DAOException { "  );
                String upQueryPart1 = "SET "+ field1.getFieldName() +"=? ";
                for (int k=1; k<tableConfig.getFieldsUpdate().size(); k++) {
                	FieldConfig current = (FieldConfig)tableConfig.getFieldsUpdate().get( k );
                    String colName = current.getFieldName();
                    upQueryPart1+= ", "+colName+" = ?";
                }           
                for (int k=0; k<keyList.length; k++) {
                    fieldSet+= "       fl.addField( model.get"+methodName( keyList[k] )+"(), "+tableConfig.getFieldConfig( keyList[k] ).getFieldType().getSqlType().intValue()+" );\n";    
                }
                
                stream.print( "       String query = \"UPDATE \"+this.sqlUpdate+\" "+upQueryPart1+" WHERE "+keyList[0]+"=? " );
                for (int k=1; k<keyList.length; k++) {
                    stream.print( " AND "+keyList[k]+"=? " );    
                }
                stream.println( "    \";" );                
                stream.println( "       FieldList fl = this.newFieldList();" );
                stream.print( fieldSet );
                stream.println( "       return OpDAO.newUpdateOp( query, fl );" );        
                stream.println( "    }" );                  	
                
                stream.println( );                  	
                stream.println( "    public int update( "+modelName+" model ) throws DAOException { "  );
                stream.println( "       return this.update( newUpdateOpDAO( model ) );" );
                stream.println( "    }" );                  	
                
            }
            
        }
        // update / insert fine
        
        
       stream.println();
        
        stream.println( "    public "+modelName+" loadOne( String sql, FieldList fl ) throws DAOException {" );
        stream.println( "        return ("+modelName+")loadOne( sql, fl, RSE_MAIN );" );
        stream.println( "    }" );      

        stream.println( "    public "+modelName+" loadOne( String sql, Field f ) throws DAOException {" );
        stream.println( "        return ("+modelName+")loadOne( sql, f, RSE_MAIN );" );
        stream.println( "    }" );        
        
        stream.println( "    protected void loadAll( "+resultList+" list, String sql, FieldList fl ) throws DAOException {" );
        stream.println( "        this.loadAll"+loadAppend+"( list, sql, fl, RSE_MAIN );" );
        stream.println( "    }" );       
        
        stream.println( "    protected void loadAll( "+resultList+" list, String sql, Field f ) throws DAOException {" );
        stream.println( "        this.loadAll"+loadAppend+"( list, sql, f, RSE_MAIN );" );
        stream.println( "    }" );         

        stream.println( "    protected void loadAll( "+resultList+" list, String sql ) throws DAOException {" );
        stream.println( "        this.loadAll"+loadAppend+"( list, sql, this.newFieldList(), RSE_MAIN );" );
        stream.println( "    }" );                   
        
        stream.println( "    public void loadAll( "+resultList+" list ) throws DAOException {" );
        stream.println( "        String sql = this.queryView; " );
		if ( tableConfig.getOrderBy() != null ) {
			stream.println( "		sql+=\"  ORDER BY "+tableConfig.getOrderBy()+" \";" );        			
		} 
        stream.println( "        this.loadAll"+loadAppend+"( list, sql, this.newFieldList(), RSE_MAIN );" );
        stream.println( "    }" );        
        
        stream.println( "    public List loadAll() throws DAOException {" );
        stream.println( "        "+resultList+" list = "+newList+";" );
        stream.println( "        this.loadAll( list );" );
        stream.println( "        return list;" );
        stream.println( "    }" );  
        stream.println( "    public LoadResult loadAllResult() throws DAOException {" );
        stream.println( "        String sql = this.queryView; " );
		if ( tableConfig.getOrderBy() != null ) {
			stream.println( "		sql+=\"  ORDER BY "+tableConfig.getOrderBy()+" \";" );        			
		}         
        stream.println( "        return LoadResult.initResult( this, sql, this.newFieldList(), RSE_MAIN ) ;" );
        stream.println( "    }" );
        stream.println( "    public PagedResult loadAllPaged( int perPage, int loadPage ) throws DAOException {" );
        stream.println( "        return this.loadAllPaged( this.queryView, this.newFieldList(), RSE_MAIN, new PageInfoDB( loadPage, perPage ) );" );
        stream.println( "    }" );

        stream.println( "    public PagedResult loadAllPaged( int perPage, int loadPage, String orderBy ) throws DAOException {" );
        stream.println( "        return this.loadAllPaged( this.queryView, this.newFieldList(), RSE_MAIN, new PageInfoDB( loadPage, perPage, orderBy ) );" );
        stream.println( "    }" );  
        
        stream.println();
        
        stream.println( "    public "+generalProps.getProperty( "factory.dao" )+" getMainDAOFactory() {" );
        stream.println( "        return ("+generalProps.getProperty( "factory.dao" )+")this.getDaoFactory();" );
        stream.println( "    }" );        
        
        // costruttore inizio
        stream.println( "    public "+cName+"("+generalProps.getProperty( "factory.dao" )+" daoFactory, String queryView, String sqlUpdate ) {" );
        stream.println( "        super(daoFactory);" );
        stream.println( "        this.init(daoFactory);" );
        stream.println( "        this.queryView = queryView;" );
        if ( update!=null ) {
        	stream.println( "        this.sqlUpdate = sqlUpdate;" );
        }
        stream.println( "    }" );        
        stream.println( "    public "+cName+"("+generalProps.getProperty( "factory.dao" )+" daoFactory ) {" );
        stream.println( "        this( daoFactory, QUERY_VIEW, SQL_UPDATE );" );
        stream.println( "    }" );
        // costruttore fine        
        
        
        // inizio relazioni IN
        // carica tutte le relazioni su una lista di oggetti
        stream.println( "    protected void loadAllRelations( List list ) throws DAOException { " ); 
        stream.println( "        for ( int k=0; k<list.size(); k++) { " );
        stream.println( "        	this.loadAllRelations( ("+modelName+")list.get(k) ); " );
        stream.println( "        } " );
        stream.println( "    }" );
        stream.println( );
        stream.println( "	public void loadAllRelations("+modelName+" model) throws DAOException {" );
        for ( int k=0; k< tableConfig.getRelations().size(); k++ ) {
        	RelationConfig relationConfig = (RelationConfig)tableConfig.getRelations().get( k );  
        	stream.println( "    	this.loadRelation"+upFirst( relationConfig.getName() )+"( model );" );	
        }
        stream.println( );
        stream.println( "    }" );
        for ( int k=0; k< tableConfig.getRelations().size(); k++ ) {
        	RelationConfig relationConfig = (RelationConfig)tableConfig.getRelations().get( k );
        	stream.println( "    public void loadRelation"+upFirst( relationConfig.getName() )+"( "+modelName+" model ) throws DAOException {" );
        	String checkField = "";
        	Iterator fieldIt = relationConfig.getFieldList().iterator();
        	while ( fieldIt.hasNext() ) {
        		String field = (String)fieldIt.next();
        		checkField+= " && model.get"+methodName( field )+"() != null ";
        	}
        	stream.println( "		if ( model != null "+checkField+" ) {" );
        	String relDAOType = generalProps.getProperty( "package.dao" )+"."+relationConfig.getTable()+"DAO";
        	String relDAOVar = lowFirst( relationConfig.getName()+"DAO" );
        	String relModelType = generalProps.getProperty( "package.model" )+"."+relationConfig.getTable()+"Model";
        	String relModelVar = lowFirst( relationConfig.getName() );
        	if ( relationConfig.isTypeOneToMany() ) {
        		relModelType = "java.util.List";
        	}
        	stream.println( "    		"+relDAOType+" "+relDAOVar+" = this.getModuleDaoFactory().get"+relationConfig.getTable()+"DAO();" );
        	stream.println( "    		"+relModelType+" "+relModelVar+" = "+relDAOVar+".outRelation"+upFirst( relModelVar )+"( model );" );
        	stream.println( "    		model.set"+upFirst( relModelVar )+"("+relModelVar+");" );
        	stream.println( "		}" );
        	stream.println( "	}" );
        }   
        stream.println(  );
        // fine relazioni IN
        
        // inizio relazioni OUT
        for ( int k=0; k< tableConfig.getRelationsOut().size(); k++ ) {
        	RelationConfig relationConfig = (RelationConfig)tableConfig.getRelationsOut().get( k );
        	String modelArgType = generalProps.getProperty( "package.model" )+"."+upFirst( relationConfig.getTableOut() )+"Model";
        	String retType = modelName;
        	String retNew = "new "+modelName+"()";
        	if ( relationConfig.isTypeOneToMany() ) {
        		retType = "java.util.List";
        		retNew = "this.newList()";
        	}
        	stream.println( "	public "+retType+" outRelation"+upFirst( relationConfig.getName() )+"( "+modelArgType+" model ) throws DAOException {" );
        	stream.println( "		"+retType+" result = "+retNew+";" );
        	stream.println( "		FieldList fl = this.newFieldList();" );
        	String sql = " \"SELECT v.* FROM ( ";
        	if ( relationConfig.getSql() == null ) {
        		sql+= "\"+this.queryView+\"";
        	} else {
        		sql+= relationConfig.getSql();
        	}
        	sql+= " ) v WHERE 1=1 ";
        	for ( int i=0; i<relationConfig.getFieldList().size(); i++ ) {
        		String currentField = (String)relationConfig.getFieldList().get( i );
        		String currentFieldOut = (String)relationConfig.getFieldOutList().get( i );
        		stream.println( "		fl.addField( model.get"+methodName( currentField )+"() );" );
        		sql+= " AND v."+currentFieldOut+" = ? ";
        	}
        	if ( relationConfig.getOrderBy() != null ) {
        		sql+= " ORDER BY v."+relationConfig.getOrderBy()+" ";
        	}
        	sql+= "\"";
        	stream.println( "		String sql = "+sql+";" );
        	if ( relationConfig.isTypeOneToOne() ) {
        		stream.println( "		result = this.loadOne( sql, fl );" );	
        	} else if ( relationConfig.isTypeOneToMany() ) {
        		stream.println( "		this.loadAll( result, sql, fl );" );
        	}
        	if ( relationConfig.isCascade() ) {
        		stream.println( "		this.loadAllRelations( result );" );
        	}
        	stream.println( "		return result;" );						
        	stream.println( "	}" );
        	stream.println(  );
        }        	
        // fine relazioni OUT        
        
        // loads
        

        List loadList = tableConfig.getLoadList();
        Iterator loadIt = loadList.iterator();
        while ( loadIt.hasNext() ) {
        	LoadConfig loadConfig = (LoadConfig)loadIt.next();
        	LogFacade.getLog().debug( "DAOCoder.generate() : LoadConfig name      : "+loadConfig.getName() );
        	LogFacade.getLog().debug( "DAOCoder.generate() : LoadConfig type      : "+loadConfig.getType() );
        	LogFacade.getLog().debug( "DAOCoder.generate() : LoadConfig class     : "+loadConfig.getClass() );
        	LogFacade.getLog().debug( "DAOCoder.generate() : LoadConfig fieldList : "+loadConfig.getFieldList() );
        	if ( loadConfig.isTypeAll() ) {        		        		
        		stream.print( "	public "+resultList+" load"+upFirst( loadConfig.getType() )+loadConfig.getName()+"( " );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			FieldConfig current = (FieldConfig)tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) );
        			if ( k!=0 ) {
        				stream.print( " , " );
        			}
        			stream.print( current.getJavaFieldType()+" "+current.getFieldName() );
        		}
        		stream.println( " ) throws DAOException { " );
        		stream.println( "		"+resultList+" result = "+newList+";" );
        		stream.println( "		String sql = \"SELECT v.* FROM (\"+this.queryView+\") v WHERE "+whereUtil( loadConfig.getFieldList(), tableConfig )+"\";" );
        		if ( tableConfig.getOrderBy() != null ) {
        			stream.println( "		sql+=\" ORDER BY "+tableConfig.getOrderBy()+" \";" );        			
        		} 
        		stream.println( "		FieldList fl = this.newFieldList();" );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			stream.println( "		fl.addField("+tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) ).getFieldName()+");" );
        		}
        		stream.println( "		this.loadAll"+loadAppend+"( result, sql, fl );" );
        		if ( loadConfig.isRelations() ) {
        			stream.println( "		this.loadAllRelations( result );" );
        		}        		
        		stream.println( "		return result;" );
            	stream.println( "	}" );	    
            	
            	// load all result
        		stream.print( "	public LoadResult loadResult"+upFirst( loadConfig.getType() )+loadConfig.getName()+"( " );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			FieldConfig current = (FieldConfig)tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) );
        			if ( k!=0 ) {
        				stream.print( " , " );
        			}
        			stream.print( current.getJavaFieldType()+" "+current.getFieldName() );
        		}
        		stream.println( " ) throws DAOException { " );
        		stream.println( "		String sql = \"SELECT v.* FROM (\"+this.queryView+\") v WHERE "+whereUtil( loadConfig.getFieldList(), tableConfig )+"\";" );
        		if ( tableConfig.getOrderBy() != null ) {
        			stream.println( "		sql+=\" ORDER BY "+tableConfig.getOrderBy()+" \";" );        			
        		} 
        		stream.println( "		FieldList fl = this.newFieldList();" );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			stream.println( "		fl.addField("+tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) ).getFieldName()+");" );
        		}
        		stream.println( "		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );" );
        		stream.println( "		return result;" );
            	stream.println( "	}" );
            	
            	// load all paged result 
//        		stream.print( "	public PagedResult loadPaged"+upFirst( loadConfig.getType() )+loadConfig.getName()+"( int perPage, int loadPage, " );
//        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
//        			FieldConfig current = (FieldConfig)tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) );
//        			if ( k!=0 ) {
//        				stream.print( " , " );
//        			}
//        			stream.print( current.getJavaFieldType()+" "+current.getFieldName() );
//        		}
//        		stream.println( " ) throws DAOException { " );
//        		stream.println( "		FieldList fl = this.newFieldList();" );
//        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
//        		 	FieldConfig current = (FieldConfig)tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) );
//        		stream.println( "		fl.addField( "+current.getFieldName()+");" );
//        		}
//        		stream.println( "		return this.loadAllPaged( sql, fl, RSE_MAIN, new PageInfoDB( loadPage, perPage ) );" );
//            	stream.println( "	}" );            	
            	
        	} else if ( loadConfig.isTypeOne() ) {
        		stream.print( "	public "+modelName+" load"+upFirst( loadConfig.getType() )+loadConfig.getName()+"( " );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			FieldConfig current = (FieldConfig)tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) );
        			if ( k!=0 ) {
        				stream.print( " , " );
        			}
        			LogFacade.getLog().debug( "DAOCoder.generate() : FieldConfig current : "+current );
        			stream.print( current.getJavaFieldType()+" "+current.getFieldName() );
        		}
        		stream.println( " ) throws DAOException { " );
        		stream.println( "		"+modelName+" result = null;" );
        		stream.println( "		String sql = \"SELECT v.* FROM (\"+this.queryView+\") v WHERE "+whereUtil( loadConfig.getFieldList(), tableConfig )+"\";" );
        		stream.println( "		FieldList fl = this.newFieldList();" );
        		for ( int k=0; k<loadConfig.getFieldList().size(); k++ ) {
        			stream.println( "		fl.addField("+tableConfig.getFieldConfig( (String)loadConfig.getFieldList().get( k ) ).getFieldName()+");" );
        		}
        		stream.println( "		result = this.loadOne( sql, fl );" );
        		if ( loadConfig.isRelations() ) {
        			stream.println( "		this.loadAllRelations( result );" );
        		}
        		stream.println( "		return result;" );
            	stream.println( "	}" );	
        	}
        	
        }
        
        // loads
 
        stream.println( "}" );
    }        	
	
}
