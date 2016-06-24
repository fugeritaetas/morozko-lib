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
 * @(#)FileFunSecure.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.file
 * @creation	: 16-feb-2005 12.11.37
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.file;

import java.io.File;
import java.io.IOException;

import org.morozko.java.core.io.FileFun;
import org.morozko.java.core.lang.ExHandler;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Classe che incapsula una <code>FileFun</code> e la rende 'sicura', nel
 * 		senso che sopprime ogni eventuale eccezione lanciata.
 * 		</jdl:text>
 * 		Wrapper class for a <code>FileFun</code> instance and makes it 'secure',
 * 		any exception thrown by the wrapped class is suppressed.
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class FileFunSecure extends FileFunWrapper {

	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di FileFunSecure.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of FileFunSecure.</p></jdl:text>
	 * </jdl:section>
	 *
	 * @param wrapped		<jdl:section>
     * 							<jdl:text lang='it'><p>Lo stream su cui scrivere l'output.</p></jdl:text>
     * 							<jdl:text lang='en'><p>The stream where output is written to.</p></jdl:text>
     * 						</jdl:section>
	 * @param handler		<jdl:section>
     * 							<jdl:text lang='it'><p>Lo stream su cui scrivere l'output.</p></jdl:text>
     * 							<jdl:text lang='en'><p>The stream where output is written to.</p></jdl:text>
     * 						</jdl:section>
	 */
    public FileFunSecure(FileFun wrapped, ExHandler handler) {
        super(wrapped);
        this.handler = handler;
    }
    
    private ExHandler handler;	// handler for exception which
    							// can be thrown during execution

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.io.File, java.lang.String)
     */
    public void handleFile(File parent, String name) throws IOException {
        try {
            super.handleFile(parent, name);    
        } catch (IOException ioe) {
            this.handler.error(ioe);
        }
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.io.File)
     */
    public void handleFile(File file) throws IOException {
        try {
            super.handleFile(file);    
        } catch (IOException ioe) {
            this.handler.error(ioe);
        }
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.lang.String, java.lang.String)
     */
    public void handleFile(String parent, String name) throws IOException {
        try {
            super.handleFile(parent, name);    
        } catch (IOException ioe) {
            this.handler.error(ioe);
        }
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.FileFun#handleFile(java.lang.String)
     */
    public void handleFile(String path) throws IOException {
        try {
            super.handleFile(path);    
        } catch (IOException ioe) {
            this.handler.error(ioe);
        }
    }
    
}
