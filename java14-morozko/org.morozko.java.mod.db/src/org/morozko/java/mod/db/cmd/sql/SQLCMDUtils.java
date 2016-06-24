/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)SQLCMDUtils.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.mod.db.cmd.sql
 * @creation	: 23-dic-2004 10.54.01
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.db.cmd.sql;

import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.helpers.BufferedCMD;
import org.morozko.java.mod.cmd.helpers.MultiCMD;
import org.morozko.java.mod.db.connect.ConnectionFactory;


/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class SQLCMDUtils {

    public static CMD newSQLCMDComplete(ConnectionFactory cp) {
        MultiCMD cmd = new MultiCMD();
        cmd.add(newSQLCMDMeta(cp));
        cmd.add(newSQLCMD(cp));
        return new BufferedCMD(cmd);
    }
    
    public static CMD newSQLCMDMeta(ConnectionFactory cp) {
        return new SQLCMDMeta(cp);
    }
    
    public static CMD newSQLCMD(ConnectionFactory cp) {
        return new SQLCMD(cp);
    }
    
    private SQLCMDUtils() {
        super();
    }

}
