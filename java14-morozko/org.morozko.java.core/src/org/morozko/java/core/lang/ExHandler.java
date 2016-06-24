/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.std 

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
 * @(#)ExHandler.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.opinf.jlib.std.lang
 * @creation	: 6-dic-2004 12.39.54
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang;

/**
 * <p>Interfaccia per l'handling di eccezioni.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface ExHandler {

    /**
     * <p>Gestisce un errore fatale.</p>
     * 
     * @param e l'eccezione
     */
    public void fatal(Exception e);

    /**
     * <p>Gestisce un errore.</p>
     * 
     * @param e l'eccezione
     */
    public void error(Exception e);
    
    /**
     * <p>Gestisce un avvertimento.</p>
     * 
     * @param e l'eccezione
     */
    public void warning(Exception e);
    
}
