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
 * @(#)SQLCMDMeta.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.mod.db.cmd.sql
 * @creation	: 22-dic-2004 15.19.53
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.db.cmd.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.helpers.CMDUtils;
import org.morozko.java.mod.cmd.helpers.TableCMDOutput;
import org.morozko.java.mod.cmd.helpers.WordCMDOutput;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class SQLCMDMeta implements CMD {

    public static Properties getDBInfo(Connection conn) throws SQLException {
        Properties props = new Properties();
        DatabaseMetaData dbmd = conn.getMetaData();
        props.setProperty("Data Base name", dbmd.getDatabaseProductName());
        props.setProperty("Data Base version", dbmd.getDatabaseProductVersion());
        props.setProperty("Driver version", dbmd.getDriverVersion());
        props.setProperty("Driver name", dbmd.getDriverName());
        props.setProperty( "Driver major version", String.valueOf( dbmd.getDriverMajorVersion() ) );
        props.setProperty( "Driver minor version", String.valueOf( dbmd.getDriverMinorVersion() ) );
        props.setProperty("Max connections", String.valueOf(dbmd.getMaxConnections()));
        return props;
    }
    
    public final static String CMD_HELP = "\\?";
    
    public final static String CMD_INFO = "\\info";
    
    public final static String CMD_LIST_SCHEMA = "\\dn";
    
    public final static String CMD_LIST_TABLE = "\\dt";
    
    public final static String CMD_LIST_VIEW = "\\dv";
    
    public final static String CMD_LIST_ALL = "\\dall";
    
    public final static String CMD_DETAIL_JDBC = "\\dj";
    
    public final static String CMD_DETAIL = "\\d"; 

    public final static String CMD_DETAIL_ALL = "\\da";
    
    public final static String[] CMD_LIST = {   
                                                CMD_LIST_TABLE, 
                                                CMD_LIST_VIEW, 
                                                CMD_LIST_SCHEMA, 
                                                CMD_LIST_ALL, 
                                                CMD_INFO,
                                                CMD_HELP,
                                                CMD_DETAIL_JDBC,
                                                CMD_DETAIL,
                                                CMD_DETAIL_ALL
                                            };

    public static final String[][] HELP = { 
            { CMD_HELP, "This help" },
            { CMD_INFO, "Info on database" },
            { CMD_LIST_SCHEMA, "List all schemas" },
            { CMD_LIST_TABLE, "List all tables (accept optional 'schema_name' as argument to narrow search)" },
            { CMD_LIST_VIEW, "List all views (accept optional 'schema_name' as argument to narrow search)" },
            { CMD_LIST_ALL, "List all entity (accept optional 'schema_name' as argument to narrow search)" },
            { CMD_DETAIL_JDBC, "List detail on entity (required 'entity_name'), use JDBC MetaData" },
            { CMD_DETAIL, "List detail on entity (required 'entity_name'), custom description" },
            { CMD_DETAIL_ALL, "List detail on entity (required 'schema_name-entity_name'), custom description" },
        };
    
    private ConnectionFactory connector;
    
    /**
     * <p>Crea un nuovo SQLCMD</p>
     * 
     * 
     */
    public SQLCMDMeta(ConnectionFactory connector) {
        super();
        this.connector = connector;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMD#canHandle(java.lang.String)
     */
    public boolean canHandle(String command) {
        return CMDUtils.isCommandIgnoreCase(command, CMD_LIST);
    }

    
    private CMDOutput getTables(String schema, String type, String command) throws SQLException, DAOException, CMDException {
        String[] types = { type };
        return this.getTables(schema, types, command);
    }
    
    private CMDOutput getTables(String schema, String[] types, String command) throws SQLException, DAOException, CMDException {
        CMDOutput output = null;
        Connection conn = this.connector.getConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, schema, null, types);
        output = ResultSetCMDOutput.getInstance(conn, rs, command);
        return output;
    }
    
    private CMDOutput getTableColumnsJDBC(String tableName, String command) throws SQLException, DAOException, CMDException {
        CMDOutput output = null;
        Connection conn = this.connector.getConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getColumns(null, null, tableName, "");
        output = ResultSetCMDOutput.getInstance(conn, rs, command);
        return output;        
    }
    
    private CMDOutput getTableColumns(String tableName, String command) throws SQLException, DAOException, CMDException {
        Connection conn = this.connector.getConnection();
        String query = "SELECT * FROM "+CMDUtils.parseArgs(CMD_DETAIL, command);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] head = { "Column", "Label", "Type", "TypeID", "Class", "Null", "Notes" };
        String[][] table = new String[rsmd.getColumnCount()][7];
        for (int k=0; k<rsmd.getColumnCount(); k++) {
            int col = k+1;
            table[k][0] = rsmd.getColumnName(col);
            table[k][1] = rsmd.getColumnLabel(col);
            table[k][2] = rsmd.getColumnTypeName(col);
            table[k][3] = String.valueOf(rsmd.getColumnType(col));
            table[k][4] = rsmd.getColumnClassName(col);
            table[k][5] = String.valueOf(rsmd.isNullable(col));
            table[k][6] = "";
        }
        CMDOutput output = new TableCMDOutput(command, head, table);
        rs.close();
        stm.close();
        conn.close();       
        return output;
    }    
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.cmd.CMD#handleCommand(java.lang.String)
     */
    public CMDOutput handleCommand(String command) throws CMDException {
        CMDOutput output = null;
        try {
            if (CMDUtils.isCommandIgnoreCase(command, CMD_LIST_SCHEMA)) {
                Connection conn = this.connector.getConnection();
                output = ResultSetCMDOutput.getInstance(conn, conn.getMetaData().getSchemas(), command);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_LIST_TABLE)) {
                output = this.getTables(CMDUtils.parseArgs(CMD_LIST_TABLE, command), "TABLE", command);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_DETAIL_ALL)) {
                Connection conn = this.connector.getConnection();
                DatabaseMetaData dbmd = conn.getMetaData();
                System.err.println( "TEST" );
                String[] split = CMDUtils.parseArgs(CMD_DETAIL_ALL, command).split( "-" );
                System.err.println( "RA : "+split.length );
                ResultSet rs = dbmd.getColumns( null, split[0], split[1], null);
                output = ResultSetCMDOutput.getInstance(conn, rs, command);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_LIST_VIEW)) {
                output = this.getTables(CMDUtils.parseArgs(CMD_LIST_VIEW, command), "VIEW", command);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_LIST_ALL)) {
                String[] types = null;
                output = this.getTables(CMDUtils.parseArgs(CMD_LIST_ALL, command), types, command);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_INFO)) {
                Connection conn = this.connector.getConnection();
                output = CMDUtils.propsToOutput(getDBInfo(conn), command);
                conn.close();
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_HELP)) {
                String[] head = { "Command", "Description" };
                output = new TableCMDOutput(command, head, HELP);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_DETAIL_JDBC)) {
                output = this.getTableColumnsJDBC(CMDUtils.parseArgs(CMD_DETAIL_JDBC, command), command);                
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_DETAIL)) {
                output = this.getTableColumns(CMDUtils.parseArgs(CMD_DETAIL, command), command);
            } else {
                output = new WordCMDOutput(command, "Command not supported : '"+command+"'");
            } 
        } catch (SQLException sqle) {
            output = new WordCMDOutput(command, sqle.toString());
        } catch (DAOException de) {
            output = new WordCMDOutput(command, de.toString());
        } catch (Exception e) {
            output = new WordCMDOutput(command, e.toString());
            e.printStackTrace();
        }
        return output;
    }

}
