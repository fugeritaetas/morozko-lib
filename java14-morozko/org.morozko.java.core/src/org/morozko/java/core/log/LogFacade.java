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
 * @(#)LogFacade.java
 *
 * @project  : simossWeb
 * @package  : org.morozko.java.core.log
 * @creation : 21-mag-2005 15.05.25
 */
package org.morozko.java.core.log;

import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;


/**
 * <p>.</p>
 *
 * @author Matteo Franci a.k.a. TUX2
 *
 */
public class LogFacade {

	static {
		configure( new Properties() );
	}
	
	public static final String PROP_ = "props.main.mail";
	
    public static final int LL_ON = 6;
    public static final int LL_DEBUG = 5;
    public static final int LL_INFO = 4;
    public static final int LL_WARN = 3;
    public static final int LL_ERROR = 2;
    public static final int LL_FATAL = 1;
    public static final int LL_OFF = 0;	
	
    private static LoggerFactory factory;
	
    public static final String PROP_LOG_FACTORY = "log.factory";
    
    public static Logger getLog() {
    	return factory.getDefaultLogger();
    }

	public static void configure( Properties props ) {
		String factoryType = props.getProperty( "log.factory", "org.morozko.java.core.log.DefaultLoggerFactory" );
		try {
			factory = (LoggerFactory)ClassHelper.newInstance( factoryType );
		} catch (Exception e) {
			e.printStackTrace();
			factory = new DefaultLoggerFactory();
		}
		factory.configure( props );
	}	
    
    public static Logger createLogger(Object obj) {
    	return factory.createLogger( obj );
    }
    
	public static void updateCurrentConfig( String prop, String value ) {
		if ( factory instanceof DefaultLoggerFactory ) {
			DefaultLoggerFactory loggerFactory = (DefaultLoggerFactory) factory;
			loggerFactory.updateCurrentConfig(prop, value);
		}
	}	
    
}

