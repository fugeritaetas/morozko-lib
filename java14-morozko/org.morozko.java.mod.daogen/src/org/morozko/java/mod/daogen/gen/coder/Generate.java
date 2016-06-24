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
 * @(#)Generate.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class Generate {

	private static void log( Object message ) {
		System.out.println( "[GENERATE]:"+message );
	}
    
    public static void gen( DGConfig dgConfig  ) {
        try {
   
        	log( "CODE GENERATION START" );
        	
        	int gen = 0;
        	
        	Properties generalProps = dgConfig.getGeneralProps();
        	
            Iterator it = dgConfig.getTableConfigList().iterator();
            while ( it.hasNext() ) {
            	TableConfig tableConfig = ( TableConfig ) it.next();
            	if ( !"true".equalsIgnoreCase( dgConfig.getExcludeProps().getProperty( tableConfig.getTableName() ) ) ) {
            		log( "GENERATING CODE FOR TABLE : "+tableConfig.getTableName() );
                	gen+= handleTable( dgConfig, tableConfig );	
            	} else {
            		log( "SKIPPING   CODE FOR TABLE : "+tableConfig.getTableName() );
            	}
            }
            
            File baseDir = new File( generalProps.getProperty( "base.dir" ) );
            
            if ( gen > 0 && !"true".equalsIgnoreCase( dgConfig.getExcludeProps().getProperty( "factory" ) ) && "true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.factory" ) )) {
            	log( "GENERATING FACTORY" );
                File daoFile = new File( baseDir, createPath( generalProps.getProperty( "package.dao" )+".helpers", generalProps.getProperty( "factory.dao.module" )+"Helper.java" ) );
                daoFile.getParentFile().mkdirs();
                PrintStream daoStream = new PrintStream( new FileOutputStream( daoFile ), true );       
                FactoryCoder.generate( daoStream, dgConfig );
                daoStream.flush();
                daoStream.close();
            } else {
            	log( "SKIPPING   FACTORY" );
            }
            
            log( "CODE GENERATION END" );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }    
    
    private static String createPath( String pack, String file ) {
        String result = "";
        StringTokenizer st = new StringTokenizer( pack, "." );
        while (st.hasMoreTokens()) {
            result+= st.nextToken()+"/";
        }
        return result+file;
    }
    
    public static boolean REGENERATE = false;
    
    public static int handleTable( DGConfig dgConfig, TableConfig tableConfig ) throws Exception {
        
    	String name = tableConfig.getTableName();
    	
    	int gen = 0;
    	
    	if ( name != null ) {
        	Properties generalProps = dgConfig.getGeneralProps();
        	
            File baseDir = new File( generalProps.getProperty( "base.dir" ) );

            boolean generateHelpers = true;
            
            File xmlTableConfig = new File( baseDir, createPath( generalProps.getProperty( "package.dao" ), tableConfig.getTableName()+".xml" ) );
            String xmlTableContent = "";
            if ( xmlTableConfig.exists() ) {
            	xmlTableContent = FileIO.readString( xmlTableConfig );
            }
            if ( tableConfig.getXmlTable().equals( xmlTableContent ) ) {
            	generateHelpers = false;
            } else {
            	xmlTableConfig.getParentFile().mkdirs();
            	FileIO.writeBytes( tableConfig.getXmlTable().getBytes() , xmlTableConfig );
            	gen++;
            }
            
            
            if ( generateHelpers && "true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.model" ) )) {
            	log( "GENERATING MODEL ("+tableConfig.getTableName()+")" );
            	File baseDirModel = baseDir;
            	if ( generalProps.getProperty( "base.dir.model" ) != null ) {
            		baseDirModel = new File( generalProps.getProperty( "base.dir.model" ) );
            	}
                File daoFile = new File( baseDirModel, createPath( generalProps.getProperty( "package.model" )+".helpers", tableConfig.getTableName()+"ModelHelper.java" ) );
                daoFile.getParentFile().mkdirs();
                PrintStream daoStream = new PrintStream( new FileOutputStream( daoFile ), true );       
                ModelCoder.generate( daoStream, dgConfig, tableConfig );
                daoStream.flush();
                daoStream.close();
            }

            if ( generateHelpers && "true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.bean" ) )) {
            	log( "GENERATING BEAN ("+tableConfig.getTableName()+")" );
            	File baseDirBean = baseDir;
            	if ( generalProps.getProperty( "base.dir.bean" ) != null ) {
            		baseDirBean = new File( generalProps.getProperty( "base.dir.bean" ) );
            	}
                File daoFile = new File( baseDirBean, createPath( generalProps.getProperty( "package.bean" )+".helpers", tableConfig.getTableName()+"BeanHelper.java" ) );
                daoFile.getParentFile().mkdirs();
                PrintStream daoStream = new PrintStream( new FileOutputStream( daoFile ), true );       
                BeanCoder.generate( daoStream, dgConfig, tableConfig );
                daoStream.flush();
                daoStream.close();    
            }

            if ( generateHelpers && "true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.rse" ) )) {
            	log( "GENERATING RSE ("+tableConfig.getTableName()+")" );
            	File baseDirDao = baseDir;
            	if ( generalProps.getProperty( "base.dir.dao" ) != null ) {
            		baseDirDao = new File( generalProps.getProperty( "base.dir.dao" ) );
            	}
                File daoFile = new File( baseDirDao, createPath( generalProps.getProperty( "package.dao" )+".helpers", tableConfig.getTableName()+"ModelRSEHelper.java" ) );
                daoFile.getParentFile().mkdirs();
                PrintStream daoStream = new PrintStream( new FileOutputStream( daoFile ), true );       
                RSECoder.generate( daoStream, dgConfig, tableConfig );
                daoStream.flush();
                daoStream.close();                    
            }
            
            if ( generateHelpers && "true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.dao" ) )) {
            	log( "GENERATING DAO ("+tableConfig.getTableName()+")" );
            	File baseDirDao = baseDir;
            	if ( generalProps.getProperty( "base.dir.dao" ) != null ) {
            		baseDirDao = new File( generalProps.getProperty( "base.dir.dao" ) );
            	}
                File daoFile = new File( baseDirDao, createPath( generalProps.getProperty( "package.dao" )+".helpers", tableConfig.getTableName()+"DAOHelper.java" ) );
                daoFile.getParentFile().mkdirs();
                PrintStream daoStream = new PrintStream( new FileOutputStream( daoFile ), true );       
                DAOCoder.generate( daoStream, dgConfig, tableConfig );
                daoStream.flush();
                daoStream.close();
            }
                   
            File baseDirTrue = new File( generalProps.getProperty( "base.dir.true" ) );
            
            String moduleDAOFactory = generalProps.getProperty( "factory.dao.module" );
            log( "factory.dao.module="+moduleDAOFactory );
        	File baseDirDaoTrue = baseDirTrue;
        	if ( generalProps.getProperty( "base.dir.dao.true" ) != null ) {
        		baseDirDaoTrue = new File( generalProps.getProperty( "base.dir.dao.true" ) );
        	}
            if ( moduleDAOFactory != null ) {
            	log( "factory.dao.module=GENERATING" );
            	File daoFactoryModuleFileTrue = new File( baseDirDaoTrue, createPath( generalProps.getProperty( "package.dao" ), moduleDAOFactory+".java" ) );
                TrueCoder.createTrueFile( dgConfig, daoFactoryModuleFileTrue, moduleDAOFactory, generalProps.getProperty( "package.dao" ), TrueCoder.TYPE_DAO_FACTORY );
            }            
            if ("true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.dao" ) )) {            	

            	File daoFileTrue = new File( baseDirDaoTrue, createPath( generalProps.getProperty( "package.dao" ), name+"DAO.java" ) );
                TrueCoder.createTrueFile( dgConfig, daoFileTrue, name+"DAO", generalProps.getProperty( "package.dao" ), TrueCoder.TYPE_DAO );
                System.out.println( "TEST "+daoFileTrue+" : "+daoFileTrue.exists() );
            }
            if ("true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.bean" ) )) {
            	File baseDirBeanTrue = baseDirTrue;
            	if ( generalProps.getProperty( "base.dir.bean.true" ) != null ) {
            		baseDirBeanTrue = new File( generalProps.getProperty( "base.dir.bean.true" ) );
            	}
                File beanFileTrue = new File( baseDirBeanTrue, createPath( generalProps.getProperty( "package.bean" ), name+"Bean.java" ) );
                TrueCoder.createTrueFile( dgConfig, beanFileTrue, name+"Bean", generalProps.getProperty( "package.bean" ), TrueCoder.TYPE_BEAN );
            }
            if ("true".equalsIgnoreCase( generalProps.getProperty( "dogenerate.model" ) )) {
            	File baseDirModelTrue = baseDirTrue;
            	if ( generalProps.getProperty( "base.dir.model.true" ) != null ) {
            		baseDirModelTrue = new File( generalProps.getProperty( "base.dir.model.true" ) );
            	}
                File modelFileTrue = new File( baseDirModelTrue, createPath( generalProps.getProperty( "package.model" ), name+"Model.java" ) );
                TrueCoder.createTrueFile( dgConfig, modelFileTrue, name+"Model", generalProps.getProperty( "package.model" ), TrueCoder.TYPE_MODEL );
            }

    	} else {
    		log( "NULL TABLE : "+name );
    	}
    	
    	return gen;
    	
    }
		
}
