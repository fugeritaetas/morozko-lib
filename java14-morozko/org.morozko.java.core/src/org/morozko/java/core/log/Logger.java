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
 * @(#)SimossLogger.java
 *
 * @project  : simossWeb
 * @package  : org.morozko.java.core.log
 * @creation : 21-mag-2005 15.03.51
 */
package org.morozko.java.core.log;

/**
 * <p>Interfaccia per la gestione del logging Applicativo.</p>
 *
 * @author Matteo Franci a.k.a. TUX2
 *
 */
public interface Logger {

	/**
	 * <p>Registra un messaggio di log a livello di DEBUG</p>
	 * 
	 * @param obj		il messaggio
	 */
    public void debug(Object obj);

	/**
	 * <p>Registra un messaggio di log a livello di INFO</p>
	 * 
	 * @param obj		il messaggio
	 */    
    public void info(Object obj);
    
	/**
	 * <p>Registra un messaggio di log a livello di WARN</p>
	 * 
	 * @param obj		il messaggio
	 */    
    public void warn(Object obj);
    
	/**
	 * <p>Registra un messaggio di log a livello di ERROR</p>
	 * 
	 * @param obj		il messaggio
	 */    
    public void error(Object obj);
    
	/**
	 * <p>Registra un messaggio di log a livello di FATAL ERROR</p>
	 * 
	 * @param obj		il messaggio
	 */    
    public void fatal(Object obj);
    
	/**
	 * <p>Registra un messaggio di log a livello di DEBUG</p>
	 * 
	 * @param obj		il messaggio
	 * @param t			la causa del messaggio
	 */
    public void debug(Object obj, Throwable t);

	/**
	 * <p>Registra un messaggio di log a livello di INFO</p>
	 * 
	 * @param obj		il messaggio
	 * @param t			la causa del messaggio
	 */    
    public void info(Object obj, Throwable t);
    
	/**
	 * <p>Registra un messaggio di log a livello di WARN</p>
	 * 
	 * @param obj		il messaggio
	 * @param t			la causa del messaggio
	 */    
    public void warn(Object obj, Throwable t);

	/**
	 * <p>Registra un messaggio di log a livello di ERROR</p>
	 * 
	 * @param obj		il messaggio
	 * @param t			la causa del messaggio
	 */    
    public void error(Object obj, Throwable t);

	/**
	 * <p>Registra un messaggio di log a livello di FATAL ERROR</p>
	 * 
	 * @param obj		il messaggio
	 * @param t			la causa del messaggio
	 */    
    public void fatal(Object obj, Throwable t);    
    
}
