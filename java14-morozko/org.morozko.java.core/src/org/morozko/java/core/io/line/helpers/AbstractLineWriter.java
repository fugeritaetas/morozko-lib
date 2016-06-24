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
 * @(#)AbstractLineWriter.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 23-dic-2004 13.32.49
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import java.io.IOException;

import org.morozko.java.core.io.line.LineWriter;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione astratta di <code>LineWriter</code>.
 * 		E' sufficiente implementare il metodo <code>print(String)</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Abstract implementation of <code>LineWriter</code>.
 * 		You must only implements <code>print(String)</code>. 
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public abstract class AbstractLineWriter implements LineWriter {

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#close()
     */
    public void close() throws IOException {

    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#print(java.lang.String)
     */
    public abstract void print(String line);
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#println()
     */
    public void println() {
        this.println("");
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#println(java.lang.String)
     */
    public void println(String line) {
        this.print(line+'\n');
    }
    
}
