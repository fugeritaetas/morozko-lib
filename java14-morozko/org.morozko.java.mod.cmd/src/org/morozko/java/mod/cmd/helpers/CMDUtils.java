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
 * @(#)CMDUtils.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 22-dic-2004 15.27.18
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDUtils {
    
    public static CMDOutput propsToOutput(Properties props, String command) {
        String[][] table = new String[props.size()][2];
        Enumeration e = props.keys();
        int count = 0;
        while (e.hasMoreElements()) {
            String k = (String)e.nextElement();
            table[count][0] = k;
            table[count][1] = props.getProperty(k);
            count++;
        }
        String[] head = { "Property", "Value" };
        CMDOutput output = new TableCMDOutput(command, head, table);
        return output;
    }
    
    public static String parseArgs(String command, String input) {
        String result = null;
        if (input.trim().length()>command.length()) {
            result = input.substring(command.length()).trim();
        }
        return result;
    }
    
    public static CMDOutput noHeadOutput(CMDOutput output) throws CMDException {
        return new NoHeadCMDOutput(output);
    }
    
    public static CMDOutput paddedOutput(CMDOutput output) throws CMDException {
        return new PaddedCMDOutput(output);
    }
    
    public static CMDOutput bufferedOutput(CMDOutput output) throws CMDException {
        CMDOutput table = null;
        if (output.hasHead()) {
            table = new TableCMDOutput(output.getCommand(), output.getHead(), getContentAsTable(output));
        } else {
            table = new TableCMDOutput(output.getCommand(), getContentAsTable(output));
        }
        output.release();
        return table;
    }
    
    public static String[][] getContentAsTable(CMDOutput output) throws CMDException {
        Vector rows = new Vector();
        while (output.nextRow()) {
            rows.add(output.getRow());
        }
        String[][] content = new String[rows.size()][output.columns()];
        for (int k=0; k<rows.size(); k++) {
            content[k] = (String[])rows.get(k);
                
        }
        return content;
    }
    
    public static boolean isCommandIgnoreCase(String command, String test) {
        return command.toUpperCase().trim().indexOf(test.toUpperCase())==0;
    }
    
    public static boolean isCommandIgnoreCase(String command, String[] cmdList) {
        boolean result = false;
        for (int k=0; k<cmdList.length && !result; k++) {
            result = isCommandIgnoreCase(command, cmdList[k]);
        }
        return result;
    }
    
    private CMDUtils() {
        super();
    }

}
