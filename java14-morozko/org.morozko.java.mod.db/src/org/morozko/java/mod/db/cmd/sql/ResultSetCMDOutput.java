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
 * @(#)ResultSetCMDOutput.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.mod.db.cmd.sql
 * @creation	: 21-dic-2004 15.36.40
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.db.cmd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.helpers.AbstractCMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class ResultSetCMDOutput extends AbstractCMDOutput {

    public static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
	
    public static CMDOutput getInstance(Connection conn, ResultSet rs, String command) throws CMDException {
        CMDOutput out = null;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            out = new ResultSetCMDOutput(conn, rs, rsmd, command);
        } catch (SQLException sqle) {
            throw (new CMDException(sqle));
        }
        return out;
    }
    
    private Connection connection;
    private ResultSet queryResult;
    private ResultSetMetaData meta;
    
    /**
     * <p>Crea un nuovo ResultSetCMDOutput</p>
     * 
     * 
     */
    private ResultSetCMDOutput(Connection conn, ResultSet rs, ResultSetMetaData rsmd, String command) {
        super(command);
        this.queryResult = rs;
        this.meta = rsmd;
        this.connection = conn;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#columns()
     */
    public int columns() throws CMDException {
        int result = -1;
        try {
            result = this.meta.getColumnCount();
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#getHead()
     */
    public String[] getHead() throws CMDException {
        String[] result = new String[this.columns()];
        try {
            
            for (int k=0; k<result.length; k++) {
                result[k] = this.meta.getColumnLabel(k+1);
            }
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#getRow()
     */
    public String[] getRow() throws CMDException {
        String[] result = new String[this.columns()];
        try {
            for (int k=0; k<result.length; k++) {
                Object obj = this.queryResult.getObject(k+1);
                if (obj == null)
                    result[k] = "";
                else {
                	// se è un numero formato in modo localizzato
                	if ( obj instanceof java.util.Date ) {
                		java.util.Date d = (java.util.Date)obj;
                		result[k] = TIMESTAMP_FORMAT.format( new java.sql.Timestamp( d.getTime() ) );
                	} else if ( obj instanceof java.lang.Number ) {
                		Object[] args = { obj };
                		MessageFormat mf = new MessageFormat( "{0}" );
                		result[k] = mf.format( args );
                	} else {
                		// altrimenti formatto come stringa
                		result[k] = obj.toString();
                	}
                }
            }
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#hasHead()
     */
    public boolean hasHead() throws CMDException {
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#nextRow()
     */
    public boolean nextRow() throws CMDException {
        boolean result = false;
        try {
            result = this.queryResult.next();
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#release()
     */
    public void release() throws CMDException {
        try {
            Statement stm = this.queryResult.getStatement();
            this.queryResult.close();
            if (stm!=null) {
                stm.close();
            }
            this.connection.close();
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
    }
    
    
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMDOutput#displaySize(int)
     */
    public int displaySize(int col) throws CMDException {
        int result = -1;
        try {
            result = Math.max(this.meta.getColumnDisplaySize(col+1), 
                            this.meta.getColumnLabel((col+1)).length());
        } catch (SQLException e) {
            throw (new CMDException(e));
        }
        return result;
    }
}
