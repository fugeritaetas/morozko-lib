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
 * @(#)ZipFileFun.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.io.file
 * @creation	: 16-feb-2005 12.19.09
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.fugerit.java.core.io.StreamIO;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione di <code>FileFun</code> che comprime dei file in formato ZIP.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Class implementing <code>FileFun</code> for compressing file in ZIP format.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class ZipFileFun extends AbstractFileFun {

    private int base;
    
    private ZipOutputStream stream;
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di ZipFileFun.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of ZipFileFun.</p></jdl:text>
     * </jdl:section>
     *
     * @param stream	    <jdl:section>
     * 							<jdl:text lang='it'><p>Lo stream su cui scrivere l'output.</p></jdl:text>
     * 							<jdl:text lang='en'><p>The stream where output is written to.</p></jdl:text>
     * 						</jdl:section>
     * @param baseDir		<jdl:section>
     * 							<jdl:text lang='it'><p>La cartella base da cui parte la compressione.</p></jdl:text>
     * 							<jdl:text lang='en'><p>The base directory for the compression.</p></jdl:text>
     * 						</jdl:section>
     */
    public ZipFileFun(ZipOutputStream stream, File baseDir) {
        super();
        this.stream = stream;
        this.base = baseDir.getAbsolutePath().length()+1;
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.io.FileFun#handleFile(java.io.File)
     */
    public void handleFile(File file) throws IOException {
        String path = file.getAbsolutePath();
        String entryPath = path.substring(this.base);
        if (file.isDirectory()) {
            entryPath+= File.separator;
        }
        ZipEntry entry = new ZipEntry(entryPath);
        this.stream.putNextEntry(entry);
        if (!file.isDirectory()) {
            StreamIO.pipeStream(new FileInputStream(file), this.stream, StreamIO.MODE_CLOSE_IN_ONLY);            
        }        
        this.stream.closeEntry();
    }

}
