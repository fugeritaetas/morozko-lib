/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.cmd 

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
 * @(#)WordCMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 21-dic-2004 16.10.31
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class WordCMDOutput extends TableCMDOutput {

    private static String[][] toMatrix(String word) {
        String[][] table = { { word } };
        return table;
    }
    
    private static String[] toLine(String word) {
        String[] line = { word };
        return line;
    }    
    
    /**
     * <p>Crea un nuovo WordCMDOutput</p>
     * 
     * @param command
     * @param line
     */
    public WordCMDOutput(String command, String word) {
        super(command, null, toMatrix(word));
    }

    /**
     * <p>Crea un nuovo WordCMDOutput</p>
     * 
     * @param command
     * @param head
     * @param line
     */
    public WordCMDOutput(String command, String head, String word) {
        super(command, toLine(head), toMatrix(word));
    }

}
