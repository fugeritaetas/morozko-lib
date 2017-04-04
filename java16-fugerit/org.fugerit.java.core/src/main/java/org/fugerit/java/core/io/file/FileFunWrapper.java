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
 * @(#)FileFunWrapper.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.io.file
 * @creation	: 16-feb-2005 8.19.03
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.io.file;

import java.io.File;
import java.io.IOException;

import org.fugerit.java.core.io.FileFun;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Classe che incapsula una <code>FileFun</code>.
 * 		può essere usata per aggiungere funzionalità ad
 * 		un'altra <code>FileFun</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Wrapper class for a <code>FileFun</code>.
 * 		Can be used to add functionalities to
 * 		another <code>FileFun</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class FileFunWrapper implements FileFun {

    private FileFun wrappedFileFun;		// the FileFun to be wrapped
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di FileFunWrapper.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of FileFunWrapper.</p></jdl:text>
     * </jdl:section>
     *
     * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>wrappedFileFun il valore di wrappedFileFun da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>wrappedFileFun the wrappedFileFun to set.</jdl:text>
	 * 			</jdl:section>
     */
    public FileFunWrapper(FileFun wrappedFileFun) {
        super();
        this.wrappedFileFun = wrappedFileFun;
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.io.FileFun#handleFile(java.lang.String, java.lang.String)
     */
    public void handleFile(String parent, String name) throws IOException {
        this.getWrappedFileFun().handleFile(parent, name);
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.io.FileFun#handleFile(java.io.File, java.lang.String)
     */
    public void handleFile(File parent, String name) throws IOException {
        this.getWrappedFileFun().handleFile(parent, name);
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.io.FileFun#handleFile(java.io.File)
     */
    public void handleFile(File file) throws IOException {
        this.getWrappedFileFun().handleFile(file);
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.io.FileFun#handleFile(java.lang.String)
     */
    public void handleFile(String path) throws IOException {
        this.getWrappedFileFun().handleFile(path);
    }

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo wrappedFileFun.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of wrappedFileFun.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo wrappedFileFun.</jdl:text>
	 *         		<jdl:text lang='en'>the value of wrappedFileFun.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public FileFun getWrappedFileFun() {
		return wrappedFileFun;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo wrappedFileFun.</jdl:text>
	 * 		<jdl:text lang='en'>Sets wrappedFileFun.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>wrappedFileFun il valore di wrappedFileFun da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>wrappedFileFun the wrappedFileFun to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setWrappedFileFun(FileFun wrappedFileFun) {
		this.wrappedFileFun = wrappedFileFun;
	}

}
