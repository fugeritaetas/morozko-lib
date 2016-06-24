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
 * @(#)StreamLineReader.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 3-gen-2005 17.39.57
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.morozko.java.core.io.line.LineReader;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione di <code>LineReader</code> che incapsula
 * 		un <code>java.io.Reader</code> o un <code>java.io.InputStram</code>. 
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		<code>LineReader</code> implementation wrapping a 
 * 		<code>java.io.Reader</code> or a <code>java.io.InputStram</code>. 
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class StreamLineReader implements LineReader {

    private BufferedReader reader;
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineReader.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineReader.</p></jdl:text>
     * </jdl:section>
     *
     */
    public StreamLineReader() {
        this(System.in);
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineReader.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineReader.</p></jdl:text>
     * </jdl:section>
     *
     * @param stream	<jdl:section>
     * 						<jdl:text lang='it'>Il <code>java.io.InputStream</code> da incapsulare.</jdl:text>
     * 						<jdl:text lang='en'>The <code>java.io.InputStream</code> to wrap.</jdl:text>  
     *					</jdl:section>
     */
    public StreamLineReader(InputStream stream) {
        this(new InputStreamReader(stream));
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineReader.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineReader.</p></jdl:text>
     * </jdl:section>
     *
     * @param reader	<jdl:section>
     * 						<jdl:text lang='it'>Il <code>java.io.Reader</code> da incapsulare.</jdl:text>
     * 						<jdl:text lang='en'>The <code>java.io.Reader</code> to wrap.</jdl:text>  
     *					</jdl:section>
     */
    public StreamLineReader(Reader reader) {
        if (reader instanceof BufferedReader) {
            this.reader = (BufferedReader)reader;
        } else {
            this.reader = new BufferedReader(reader);
        }
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineReader#readLine()
     */
    public String readLine() throws IOException {
        return this.reader.readLine();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineReader#close()
     */
    public void close() throws IOException {
        this.reader.close();
    }

}
