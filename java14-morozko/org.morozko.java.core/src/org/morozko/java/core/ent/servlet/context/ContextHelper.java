/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
 * @(#)ContextHelper.java
 *
 * @project  : org.morozko.java.core.ent
 * @package  : org.morozko.java.core.ent.servlet.context
 * @creation : 13-feb-2006
 */
package org.morozko.java.core.ent.servlet.context;

import java.io.File;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.morozko.java.core.log.LogFacade;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ContextHelper {

	public static Object resolveJNDI( String jndiName, Object defValue ) throws NamingException {
		LogFacade.getLog().debug( "ContextHelper.resulveJNDI - jndiName : "+jndiName );
		Object value = null;
		InitialContext ic = new InitialContext();
		value = ic.lookup( jndiName );
		if ( value == null ) {
			LogFacade.getLog().debug( "ContextHelper.resulveJNDI - null value, using default : "+defValue );
			value = defValue;
		}
		LogFacade.getLog().debug( "ContextHelper.resulveJNDI - value : "+value );
		return value;
	}
	
	public static File resolvePath( ServletContext context, String path ) throws Exception {
		LogFacade.getLog().debug( "ContextHelper.resolvePath - path : "+path );
		if ( path.indexOf( "jndi:" ) == 0 ) {
			String jndiName = path.substring( 5 );
			path = (String)resolveJNDI( jndiName, null );
		}
		File result = new File( path );
		if ( !result.exists() ) {
			result = new File( context.getRealPath( "/" ), path );
		}
		LogFacade.getLog().debug( "ContextHelper.resolvePath - result : "+result );
		return result;
	}
	
}
