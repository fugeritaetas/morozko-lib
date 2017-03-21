package org.fugerit.java.core.tools.util.args;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.connect.ConnectionFactoryPool;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.log.LogFacade;

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
