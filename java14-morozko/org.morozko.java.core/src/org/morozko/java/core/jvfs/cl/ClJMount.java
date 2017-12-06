/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)ClJMount.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.jvfs.cl
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.jvfs.cl;

import java.net.URL;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.DefaultConfigurableObject;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JMount;
import org.morozko.java.core.jvfs.JVFS;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione di JMount che risolve i file all' interno di un <code>ClassLoader</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		JMount implementation which resolves file in a  <code>ClassLoader</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class ClJMount extends DefaultConfigurableObject implements JMount {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365971783948169969L;
	
	/**
	 *	<jdl:section>
	 * 		<jdl:text lang='it'>Propriet� di configurazione che indica il package name.</jdl:text>
	 * 		<jdl:text lang='en'>Property to configure the package to use.</jdl:text>  
	 *	</jdl:section>
	 */	
	public static final String PROP_PACK_NAME = "cl-package-name";
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.helpers.DefaultConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		this.packageName = props.getProperty( PROP_PACK_NAME );
	}

	private String packageName;		// the base package name
	
	public ClJMount() {}
	
	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di ClJMount.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of ClJMount.</p></jdl:text>
	 * </jdl:section>
	 *
	 * @param packageName		<jdl:section>
	 * 								<jdl:text lang='it'><p>Il nome del package da usare come base.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>The base package name.</p></jdl:text>
	 * 							</jdl:section>	
	 * @throws ConfigException	<jdl:section>
	 * 								<jdl:text lang='it'><p>Se vi sono dei problemi di configurazione.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>If something goes wrong during configuration.</p></jdl:text>
	 * 							</jdl:section>	
	 */
	public ClJMount( String packageName ) throws ConfigException {
		Properties props = new Properties();
		props.put( PROP_PACK_NAME, packageName );
		configure( props );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.JMount#getJFile(org.morozko.java.core.jvfs.JVFS, java.lang.String, java.lang.String)
	 */
	public JFile getJFile( JVFS jvfs, String point, String relPath ) {
		this.getLog().debug( "REL PATH : "+relPath );
		String path = this.packageName+relPath.replaceAll( jvfs.getPathResolver().getSepartor(), "." );
		String path2 = "/"+path.replaceAll( "\\.", "/" );
		this.getLog().debug( "PATH : "+relPath+" -> "+path );
		Package pack = Package.getPackage( path );
		URL url = null;
		if ( pack == null ) {
			int index = path2.lastIndexOf( "/" );
			path2 = path2.substring( 0, index )+"."+path2.substring( index+1 );
			this.getLog().debug( "PATH2 : "+path2+" -> "+path2 );
			url = ClJMount.class.getResource( path2  );
		}
		ClJMount.class.getResource( path );
		this.getLog().debug( "PACK  : "+pack );
		this.getLog().debug( "URL   : "+url );
		return new ClJFile( relPath, jvfs, pack, url );
	}

}
