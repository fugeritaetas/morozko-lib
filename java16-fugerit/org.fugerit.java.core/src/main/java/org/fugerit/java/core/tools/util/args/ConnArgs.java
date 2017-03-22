package org.fugerit.java.core.tools.util.args;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
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
        
        Properties props = new Properties();
        props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_DRV, drv );
        props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL, url );
        props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_USR, usr );
        props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_PWD, pwd );
        
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
				c.newInstance();
			} catch (Exception e) {
				throw ( new DAOException( e ) );
			}
        }
        
        if ( pooled != null ) {
        	try {
        		String[] values = pooled.split( ";" );
        		props.setProperty( ConnectionFactoryImpl.PROP_CF_EXT_POOLED_IC , values[0] );
        		props.setProperty( ConnectionFactoryImpl.PROP_CF_EXT_POOLED_SC , values[1] );
        		props.setProperty( ConnectionFactoryImpl.PROP_CF_EXT_POOLED_MC , values[2] );
        		LogFacade.getLog().info( "pooled : "+pooled );        		
        	} catch (Exception e) {
        		LogFacade.getLog().warn( "Failed to created pooled connection : "+e );
        	}
        }
       
        connectionFactory = ConnectionFactoryImpl.newInstance( props );
        
		return connectionFactory;
	}
	
}
