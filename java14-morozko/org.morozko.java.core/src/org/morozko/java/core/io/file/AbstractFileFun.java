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
 * @(#)AbstractFileFun.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.file
 * @creation	: 16-feb-2005 8.01.06
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.file;

import java.io.File;
import java.io.IOException;

import org.morozko.java.core.io.FileFun;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione astratta di <code>FileFun</code>,
 * 		è sufficiente implementare il metodo <code>handleFile(File)</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Abstract implementation of <code>FileFun</code>,
 * 		you must only implement the <code>handleFile(File)</code> method.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public abstract class AbstractFileFun implements FileFun {

	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di AbstractFileFun.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of AbstractFileFun.</p></jdl:text>
	 * </jdl:section>
	 *
	 */
    public AbstractFileFun() {
        super();
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.io.File, java.lang.String)
     */
    public void handleFile(File parent, String name) throws IOException {
        this.handleFile(new File(parent, name));
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.io.File)
     */
    public abstract void handleFile(File file) throws IOException;
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.lang.String, java.lang.String)
     */
    public void handleFile(String parent, String name) throws IOException {
        this.handleFile(new File(parent), name);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.lang.String)
     */
    public void handleFile(String path) throws IOException {
        this.handleFile(new File(path));
    }
    
}
