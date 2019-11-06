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
 * @(#)SQLCMD.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.mod.db.cmd.sql
 * @creation	: 21-dic-2004 15.36.01
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.db.cmd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.helpers.CMDUtils;
import org.morozko.java.mod.cmd.helpers.WordCMDOutput;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class SQLCMD implements CMD {
    
	public final static String VERSION = "0.1";
	
    private final static String[] UPDATE_CMD = { "INSERT", "UPDATE", "DELETE" };
    
    private final static String[] QUERY_CMD = { "SELECT" };
    
    private ConnectionFactory connector;
    
    /**
     * <p>Crea un nuovo SQLCMD</p>
     * 
     * 
     */
    public SQLCMD(ConnectionFactory connector) {
        super();
        this.connector = connector;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMD#canHandle(java.lang.String)
     */
    public boolean canHandle(String command) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMD#handleCommand(java.lang.String)
     */
    public CMDOutput handleCommand(String command) throws CMDException {
        CMDOutput output = null;
        Connection conn = null;
        try {
            conn = this.connector.getConnection();
            if (isQuery(command)) {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(command);
                output = ResultSetCMDOutput.getInstance(conn, rs, command);
            } else if (isUpdate(command)) {
                Statement stm = conn.createStatement();
                int result = stm.executeUpdate(command);
                output = new WordCMDOutput(command, String.valueOf(result));
                stm.close();
                conn.close();
            } else {
                Statement stm = conn.createStatement();
                boolean result = stm.execute(command);
                output = new WordCMDOutput(command, String.valueOf(result));
                stm.close();
                conn.close();
            }
        } catch (SQLException sqle) {
        	String message = "SQLException : "+sqle;
            if (conn!=null)
                try {
                    conn.close();
                } catch (SQLException sqle1) {
                	message+= "[closeconnectionerror:"+sqle1+"]";
                }
            throw new CMDException( message, sqle);
        } catch (DAOException de) {
            throw new CMDException( "DAPException : "+de, de);
        }
        return output;
    }
    
    private static boolean isUpdate(String command) {
        return CMDUtils.isCommandIgnoreCase(command, UPDATE_CMD);
    }
    
    private static boolean isQuery(String command) {
        return CMDUtils.isCommandIgnoreCase(command, QUERY_CMD);
    }

//    private static void printRow(String[] row) throws CMDException {
//        System.out.print("| ");
//        for (int k=0; k<row.length; k++) {
//            String cell = row[k];
//            System.out.print(cell+" |");
//        }
//        System.out.println();
//    }
    
}
