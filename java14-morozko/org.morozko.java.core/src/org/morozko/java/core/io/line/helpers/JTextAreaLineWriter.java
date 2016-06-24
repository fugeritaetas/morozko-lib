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
 * @(#)JTextAreaLineWriter.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.io.line.helpers
 * @creation	: 3-gen-2005 17.31.02
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.io.line.helpers;

import javax.swing.JTextArea;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		<code>LineWriter</code> che scrive il testo su una <code>javax.swing.JTextArea</code>.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		<code>LineWriter</code> which appends text on a <code>javax.swing.JTextArea</code>.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class JTextAreaLineWriter extends AbstractLineWriter {
    
    private JTextArea area;		// la JTextArea su cui viene scritto l'output
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di JTextAreaLineWriter.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of JTextAreaLineWriter.</p></jdl:text>
     * </jdl:section>
     *
     * @param area		<jdl:section>
     * 						<jdl:text lang='it'>Il <code>javax.swing.JTextArea</code> da incapsulare.</jdl:text>
     * 						<jdl:text lang='en'>The <code>javax.swing.JTextArea</code> to wrap.</jdl:text>  
     *					</jdl:section>
     */
    public JTextAreaLineWriter(JTextArea area) {
        this.area = area;
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.io.line.LineWriter#print(java.lang.String)
     */
    public void print(String line) {
        this.area.append(line);
    }
    
}