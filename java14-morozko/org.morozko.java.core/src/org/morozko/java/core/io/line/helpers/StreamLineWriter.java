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
 * @(#)StreamLineWriter.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 3-gen-2005 17.35.04
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.morozko.java.core.io.line.LineWriter;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione di <code>LineWriter</code> che incapsula
 * 		un <code>java.io.Writer</code> o un <code>java.io.OutputStram</code>. 
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		<code>LineWriter</code> implementation wrapping a 
 * 		<code>java.io.Writer</code> or a <code>java.io.OutputStram</code>. 
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class StreamLineWriter implements LineWriter {

    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Line writer basato sul <code>System.out</code>.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Line writer based upon <code>System.out</code>.</p></jdl:text>
     * </jdl:section>
     *
     */
	public static final LineWriter out = new StreamLineWriter( System.out );
	
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Line writer basato sul <code>System.err</code>.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Line writer based upon <code>System.err</code>.</p></jdl:text>
     * </jdl:section>
     *
     */	
	public static final LineWriter err = new StreamLineWriter( System.err );
	
    private PrintWriter writer;		// il PrintWriter utilizzato per gestire effettivamente la scrittura
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineWriter.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineWriter.</p></jdl:text>
     * </jdl:section>
     *
     */
    public StreamLineWriter() {
        this(System.out);
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineWriter.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineWriter.</p></jdl:text>
     * </jdl:section>
     *
     * @param stream	<jdl:section>
     * 						<jdl:text lang='it'>Il <code>java.io.OutputStream</code> da incapsulare.</jdl:text>
     * 						<jdl:text lang='en'>The <code>java.io.OutputStream</code> to wrap.</jdl:text>  
     *					</jdl:section>
     */
    public StreamLineWriter(OutputStream stream) {
        this(new OutputStreamWriter(stream));
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di StreamLineWriter.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of StreamLineWriter.</p></jdl:text>
     * </jdl:section>
     *
     * @param writer	<jdl:section>
     * 						<jdl:text lang='it'>Il <code>java.io.Writer</code> da incapsulare.</jdl:text>
     * 						<jdl:text lang='en'>The <code>java.io.Writer</code> to wrap.</jdl:text>  
     *					</jdl:section>
     */
    public StreamLineWriter(Writer writer) {
        if (writer instanceof PrintWriter) {
            this.writer = (PrintWriter)writer;
        } else {
            this.writer = new PrintWriter(writer, true);
        }
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#println()
     */
    public void println() {
        this.writer.println();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#print(java.lang.String)
     */
    public void print(String line) {
        this.writer.print(line);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#println(java.lang.String)
     */
    public void println(String line) {
        this.writer.println(line);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#close()
     */
    public void close() throws IOException {
        this.writer.close();
    }

} 
