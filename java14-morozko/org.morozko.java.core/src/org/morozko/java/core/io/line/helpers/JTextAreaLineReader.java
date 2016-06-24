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
 * @(#)JTextAreaLineReader.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 3-gen-2005 17.30.32
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Implementazione di <code>LineReader</code> che legge da una <code>javax.swing.JTextArea</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Implementation of <code>LineReader</code> reading from a <code>javax.swing.JTextArea</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class JTextAreaLineReader extends AbstractLineReader {
    
    private JTextArea area;		// the text area to read from
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di JTextAreaLineReader.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of JTextAreaLineReader.</p></jdl:text>
     * </jdl:section>
     *
     * @param area		<jdl:section>
     * 						<jdl:text lang='it'><p>La text area da cui leggere l' input.</p></jdl:text>
     * 						<jdl:text lang='en'><p>The text area to read from.</p></jdl:text>
     * 					</jdl:section>		
     */
    public JTextAreaLineReader(JTextArea area) {
        this.area = area;
    }
    
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineReader#readLine()
     */
    public String readLine() throws IOException {
        return this.area.getText();
    }
    
}
