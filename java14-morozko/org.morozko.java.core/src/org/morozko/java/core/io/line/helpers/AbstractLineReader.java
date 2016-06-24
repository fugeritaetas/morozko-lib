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
 * @(#)AbstractLineReader.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 23-dic-2004 13.34.47
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import java.io.IOException;

import org.morozko.java.core.io.line.LineReader;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione astratta di <code>LineReader</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Abstract implementation of <code>LineReader</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public abstract class AbstractLineReader implements LineReader {

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineReader#readLine()
     */
    public abstract String readLine() throws IOException;

    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineReader#close()
     */
    public void close() throws IOException {
    }

}
