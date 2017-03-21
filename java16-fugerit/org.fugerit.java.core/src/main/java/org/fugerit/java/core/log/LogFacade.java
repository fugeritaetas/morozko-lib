/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @package  : org.fugerit.java.core.log
 * @creation : 21-mag-2005 15.05.25
 */
package org.fugerit.java.core.log;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>.</p>
 *
 * @author Matteo Franci a.k.a. TUX2
 *
 */
public class LogFacade {

    private static Logger logger = LoggerFactory.getLogger( LogFacade.class ); 
    
    public static Logger getLog() {
    	return logger;
    }

    public static Logger newLogger( Object c ) {
    	return LoggerFactory.getLogger( c.getClass() );
    }
    
    public static void handleWarn( Throwable t ) {
    	handleWarn( getLog(), t );
    }
    
    public static void handleWarn( Logger logger, Throwable t ) {
    	logger.warn( t.getMessage(), t );
    }
    
    public static void handleError( Throwable t ) {
    	handleError( getLog(), t );
    }
    
    public static void handleError( Logger logger, Throwable t ) {
    	logger.error( t.getMessage(), t );
    }
    
}

