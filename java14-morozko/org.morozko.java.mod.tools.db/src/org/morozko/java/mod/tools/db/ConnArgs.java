/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

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
 * @(#)Args.java
 *
 * @project  : org.morozko.java.mod.tools.db
 * @package  : org.morozko.java.mod.tools.db
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.tools.db;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.connect.pool.ConnectionFactoryPool;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ConnArgs {

	public static ConnectionFactory createConnectionFactory( ArgList list ) throws DAOException {
		ConnectionFactory connectionFactory = null;
        
        String drv = list.findArg("d").getValue();
        String url = list.findArg("c").getValue();
        String usr = list.findArg("u").getValue();
        String pwd = list.findArg("p").getValue();
        
        Arg pooledArg = list.findArg("pooled");
        
        String pooled = null;
        
        if ( pooledArg != null ) {
        	pooled = pooledArg.getValue();
        }

        Arg jar = list.findArg("j");
        
        if ( jar!=null ) {
        	try {
        		File jarFile = new File( jar.getValue() );
				URL[] jarUrl = { jarFile.toURL() };
				URLClassLoader cl = new URLClassLoader( jarUrl );
				Class c = cl.loadClass( drv );
				Driver driver = (Driver)c.newInstance();
				connectionFactory = ConnectionFactoryImpl.newInstance( driver, url, usr, pwd );
			} catch (Exception e) {
				throw ( new DAOException( e ) );
			}
        } else {
        	connectionFactory = ConnectionFactoryImpl.newInstance( drv, url, usr, pwd );
        }
        
        if ( pooled != null ) {
        	try {
        		String[] values = pooled.split( ";" );
        		connectionFactory = ConnectionFactoryPool.newFactory( connectionFactory, 
        				Integer.parseInt( values[0] ), 
        				Integer.parseInt( values[1] ), 
        				Integer.parseInt( values[2] ) 
        		);
        		LogFacade.getLog().info( "pooled : "+pooled );
        		
        	} catch (Exception e) {
        		LogFacade.getLog().warn( "Failed to created pooled connection : "+e );
        	}
        } 
        
		return connectionFactory;
	}
	
}
