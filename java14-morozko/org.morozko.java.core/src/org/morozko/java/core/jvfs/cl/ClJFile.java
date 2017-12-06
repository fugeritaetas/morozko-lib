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
 * @(#)ClJFile.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.jvfs.cl
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.jvfs.cl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.helpers.AbstractJFile;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Un JFile basato su un file caricato da un <code>ClassLoader</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		A Jfile based upon a file loaded by a <code>ClassLoader</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class ClJFile extends AbstractJFile {

	private Package pack;		// null if is not a package (directory)
	
	private URL url;			// the url of the file
	
    /**
     * 
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di ClJFile.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of ClJFile.</p></jdl:text>
     * </jdl:section>
     *
     * @param path				<jdl:section>
	 * 								<jdl:text lang='it'><p>Il persorso del file relativamente al suo JMount.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>The path of the file relative to his JMount.</p></jdl:text>
	 * 							</jdl:section>	
     * @param jvfs				<jdl:section>
	 * 								<jdl:text lang='it'><p>Il file system virtuale.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>The virtual file system.</p></jdl:text>
	 * 							</jdl:section>
     * @param pack				<jdl:section>
	 * 								<jdl:text lang='it'><p>Il package del file.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>The file's package.</p></jdl:text>
	 * 							</jdl:section>		 * 	
     * @param url				<jdl:section>
	 * 								<jdl:text lang='it'><p>L'url del file.</p></jdl:text>
	 * 								<jdl:text lang='en'><p>The url of the file.</p></jdl:text>
	 * 							</jdl:section>	
     */
	public ClJFile(String path, JVFS jvfs, Package pack, URL url) {
		super(path, jvfs);
		this.pack = pack;
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#canRead()
	 */
	public boolean canRead() throws IOException {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#canWrite()
	 */
	public boolean canWrite() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#create()
	 */
	public boolean create() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#delete()
	 */
	public boolean delete() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#exists()
	 */
	public boolean exists() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#getInputStream()
	 */
	public InputStream getInputStream() throws IOException {
		return this.url.openStream();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#getLastModified()
	 */
	public long getLastModified() throws IOException {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#getName()
	 */
	public String getName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#getOutputStream()
	 */
	public OutputStream getOutputStream() throws IOException {
		OutputStream os = null;
		if ( Boolean.TRUE.booleanValue() ) {
			throw ( new IOException( "Unsupported Operation" ) );	
		}
		return os;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#isFile()
	 */
	public boolean isFile() throws IOException {
		return (this.pack == null);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#list()
	 */
	public String[] list() throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#mkdir()
	 */
	public boolean mkdir() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#mkdirs()
	 */
	public boolean mkdirs() throws IOException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#setLastModified(long)
	 */
	public void setLastModified(long time) throws IOException {

	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.jvfs.helpers.AbstractJFile#setReadOnly()
	 */
	public void setReadOnly() throws IOException {

	}

}
