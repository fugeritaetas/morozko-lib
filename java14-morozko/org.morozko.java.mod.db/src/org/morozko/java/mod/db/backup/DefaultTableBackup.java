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
 * @(#)DefaultTableBackup.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.backup
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.backup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.db.metadata.query.QueryColumnMap;
import org.morozko.java.mod.db.metadata.query.QueryColumnModel;
import org.morozko.java.mod.db.metadata.query.QueryMetadataFacade;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class DefaultTableBackup extends BasicLogObject implements TableBackup {

	public DefaultTableBackup() {
		this.getLog().debug( this.getClass().getName()+" VERSION 0.74" );
		this.commitOn = 10;
	}
	
	private int commitOn;
	
	private String insertMode;
	
	private String adaptorFrom;
	
	private String adaptorTo;
	
	private String columnCheckMode;
	
	private String statementMode;
	
    /* (non-Javadoc)
	 * @see org.morozko.java.mod.db.backup.TableBackup#setProperty(java.lang.String, java.lang.String)
	 */
	public boolean setProperty(String name, String value) {
		boolean result = false;
		this.getLog().debug( "setProperty "+name+"="+value );
		if ( PROP_COMMIT_ON.equalsIgnoreCase( name ) ) {
			this.commitOn = Integer.parseInt( value );
			result = true;
		} else if ( PROP_STATEMENT_MODE.equalsIgnoreCase( name ) ) {
			this.statementMode = value;
		} else if ( PROP_INSERT_MODE.equalsIgnoreCase( name ) ) {
			this.insertMode = value;
		} else if ( PROP_ADAPTOR_FROM.equalsIgnoreCase( name ) ) {
			this.adaptorFrom = value;
		} else if ( PROP_ADAPTOR_TO.equalsIgnoreCase( name ) ) {
			this.adaptorTo = value;
		} else if ( PROP_COLUMN_CHECK_MODE.equalsIgnoreCase( name ) ) {
			this.columnCheckMode = value;
		}
		return result;
	}

	private String[] getColumnNames(Connection from, Connection to, String table, String select ) throws SQLException {
		String result[] = null;
		try {
			List list = new ArrayList();
			QueryColumnMap mapFrom = null;
			try {
				mapFrom = QueryMetadataFacade.columnMap( from, select );
			} catch (Exception eM ){
				this.getLog().info( "error : "+eM+" try noModify query - "+select );
				mapFrom = QueryMetadataFacade.columnMap( from, select, true );
			}
			QueryColumnMap mapTo = QueryMetadataFacade.columnMap( to, " SELECT * FROM "+table );
			this.getLog().debug( "columns from : "+ mapFrom.size() );
			this.getLog().debug( "columns to   : "+ mapTo.size() );
			Iterator it = null;
			if ( PROP_INSERT_MODE_VALUE_RIGHT.equals( this.insertMode ) ) {
				it = mapTo.iterator();
			} else {
				it = mapFrom.iterator();
			}
			while ( it.hasNext() ) {
				QueryColumnModel columnFrom = (QueryColumnModel)it.next();
				QueryColumnModel columnTo = mapTo.get( columnFrom.getName() );
				if ( columnTo != null || PROP_COLUMN_CHECK_MODE_VALUE_NOCHECK.equalsIgnoreCase( this.columnCheckMode ) ) {
					list.add( columnFrom.getName() );
				} else {
					this.getLog().warn( "column : "+columnFrom.getName()+" doesn't exist in destination, skipping column!" );
				}
			}
			result = new String[ list.size() ];
			for ( int k=0; k<list.size(); k++ ) {
				result[k] = (String)list.get( k );
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ( new SQLException( e.toString() ) );
		}
        return result;
    }
    
    private String[] getTableColumnNames(Connection conn, Connection to, String table, String select) throws SQLException {
        return this.getColumnNames(conn,  to, table, select );
    }
    
    
    /* (non-Javadoc)
     * @see it.engine.backup.TableBackup#backupTable(java.lang.String, java.sql.Connection, java.sql.Connection)
     */
    public int backupTable(String table, Connection from, Connection to , TableConfig tableConfig  )throws SQLException {
        return this.backupTable(table, from, to, "SELECT * FROM "+table, tableConfig );
    }

    /* (non-Javadoc)
     * @see it.engine.backup.TableBackup#backupTable(java.lang.String, java.sql.Connection, java.sql.Connection, java.lang.String)
     */
    public int backupTable(String table, Connection from, Connection to, String select, TableConfig tableConfig ) throws SQLException {
    	this.getLog().info( "table  : "+table );
    	this.getLog().info( "select : "+select );
    	
        int result = 0;
        
        long starTime = System.currentTimeMillis();

        int count = 0;

        if ( PROP_STATEMENT_MODE_SINGLE.equalsIgnoreCase( this.statementMode ) ) {
        	to.setAutoCommit( true );
        } else {
        	to.setAutoCommit( false );	
        }

        this.getLog().debug("Statement mode           : "+this.statementMode);
        this.getLog().debug("Starting backup of table : "+table);
        this.getLog().debug("Select statement : "+select);
        // costruisco la insert
        String[] toTableColumns = this.getTableColumnNames(from, to, table, select);
        StringBuffer queryBuffer = new StringBuffer();
        queryBuffer.append( "INSERT INTO "+table+" (" );
        for (int k=0; k<toTableColumns.length-1; k++) {
            queryBuffer.append( toTableColumns[k]+"," );
        }
        queryBuffer.append( toTableColumns[toTableColumns.length-1]+")" );
        queryBuffer.append( " VALUES (" );
        for (int k=0; k<toTableColumns.length-1; k++) {
            queryBuffer.append( "?, " );
        }
        queryBuffer.append( "?)" );
        // eseguo la query sulla sorgente dei dati
        PreparedStatement fromPS = from.prepareStatement(select);
        ResultSet fromRS = fromPS.executeQuery();
        // inizio l'inserimento dei dati
        String insert = queryBuffer.toString();
        this.getLog().info("Insert statement : "+insert);
        
        PreparedStatement toPS = to.prepareStatement(insert);
        // inserimento dati
        int rowCount = 0;
        int copyCount = 0;
        
        // test
        ResultSetMetaData rsmd = fromRS.getMetaData();
        // test
        
    	BackupAdaptor backupAdaptorFrom = null;;
    	BackupAdaptor backupAdaptorTo = null;;
    	
    	if ( tableConfig.getAdaptorFrom() != null ) {
    		backupAdaptorFrom = tableConfig.getAdaptorFrom();
    		this.getLog().info("Override adaptor from : "+backupAdaptorFrom );
    	} else {
    		try {
    			backupAdaptorFrom = (BackupAdaptor)ClassHelper.newInstance( this.adaptorFrom );
    		} catch (Exception e) {
    			throw new SQLException( e.toString() );
    		}
    	}
    	if ( tableConfig.getAdaptorTo() != null ) {
    		backupAdaptorTo = tableConfig.getAdaptorTo();
    		this.getLog().info("Override adaptor to : "+backupAdaptorTo );
    	} else {
    		try {
				backupAdaptorTo = (BackupAdaptor)ClassHelper.newInstance( this.adaptorTo );
			} catch (Exception e) {
				throw new SQLException( e.toString() );
			}
    	}
        
        while (fromRS.next()) {
            rowCount++;
            String comment = null;
            StringBuffer fullComment = new StringBuffer();
            long res = 0;
            try {

                for (int k=0; k<=toTableColumns.length-1; k++) {
                	String colName = toTableColumns[k];
                	int colIndex = (k+1);
                    comment = "setting column "+colName+" : "+colIndex;
                    Object obj = backupAdaptorFrom.get( fromRS , rsmd, colIndex );
                    fullComment.append( " "+comment+" '"+obj+"'" );
                    backupAdaptorTo.set( toPS , rsmd, obj, colIndex );
                }
                
                comment = "executing insert ";
                try {
                	if ( PROP_STATEMENT_MODE_SINGLE.equalsIgnoreCase( this.statementMode ) ) {
                		res+= toPS.executeUpdate();
                	} else if ( PROP_STATEMENT_MODE_EXECUTE.equalsIgnoreCase( this.statementMode ) ) {
                		res+= toPS.executeUpdate();	
                    } else {
                    	toPS.addBatch();
                    }
                	this.getLog().debug( "RES : "+res );
                } catch (SQLException e) {
                	this.getLog().error("Error backing up table "+table+" on row "+rowCount+", "+fullComment, e);
                	throw e;
                }
                
                
                copyCount++;
                count++;
                
                // System.out.println( "----" );
                
                if ( count>= commitOn ) {
                	this.getLog().debug( "commit count : "+count+" time : "+(System.currentTimeMillis()-starTime) );
                	if ( PROP_STATEMENT_MODE_SINGLE.equalsIgnoreCase( this.statementMode ) ) {
                		// skip commit
                	} else if ( PROP_STATEMENT_MODE_EXECUTE.equalsIgnoreCase( this.statementMode ) ) {
                		to.commit();	
                	} else {
                		toPS.executeBatch();
                		to.commit();
                		toPS.clearBatch();
                	}
                	count = 0;
                }
                
            } catch (Exception e) {
                this.getLog().error("Error backing up table "+table+" on row "+rowCount+", "+comment, e);
                result++;
            }
            
        }
        
        // execute all remaining
    	if ( PROP_STATEMENT_MODE_EXECUTE.equalsIgnoreCase( this.statementMode ) ) {
    		to.commit();	
    	} else {
    		toPS.executeBatch();
    		to.commit();
    		toPS.clearBatch();
    	}
        
        to.setAutoCommit( true );
        fromRS.close();
        fromPS.close();

        this.getLog().info("Total source rows    : "+rowCount);
        this.getLog().info("Total copied rows    : "+copyCount);
        this.getLog().info("Total time (seconds) : "+((System.currentTimeMillis()-starTime)/1000) );
        this.getLog().info("Return code [error count] : '"+result+"'");
        return result;
    }
   
}