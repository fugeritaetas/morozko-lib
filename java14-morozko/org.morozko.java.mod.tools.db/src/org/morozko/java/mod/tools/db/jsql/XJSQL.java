/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

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
 * @(#)XJSQL.java
 *
 * @project  : org.morozko.java.mod.tools.db
 * @package  : org.morozko.java.mod.tools.db.jsql
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.tools.db.jsql;

import java.awt.Color;

import org.morozko.java.core.lang.helpers.AbstractOp;
import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.gui.CMDFrame;
import org.morozko.java.mod.cmd.helpers.WordCMDOutput;
import org.morozko.java.mod.db.cmd.sql.SQLCMD;
import org.morozko.java.mod.db.cmd.sql.SQLCMDUtils;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class XJSQL {

    public static final String VERSION = "0.2.1";
    
    private static final String INFO_TEXT = 
        " XJSQL v "+VERSION+" by Matteo Franci a.k.a. TUX2, \n based on " +
        		"\n   CMDFrame " +CMDFrame.VERSION+
        		"\n and " +
        		"\n   SQLCMD "+SQLCMD.VERSION;    
    
    private static final String HELP_TEXT = 
        " XJSQL v "+VERSION+" HELP\n"+
        " ----------------------------------------\n"+
        " press \\? for command help\n";
        
    
    private static Color parseColor(Arg colorArg, Color def) {
        Color res = def;
        try {
            String color = colorArg.getValue();
            int r = Integer.parseInt(color.substring(0, 3));
            int g = Integer.parseInt(color.substring(3, 6));
            int b = Integer.parseInt(color.substring(6, 9));
            res = new Color(r, g, b);
            System.out.println("Red    comp : "+r);
            System.out.println("Green  comp : "+g);
            System.out.println("Blue   comp : "+b);
        } catch (Exception e) {
            System.err.println("Color conversion error : "+e);
        }
        return res;
    }
    
    //private static final String lef  = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    //private static final String lef    = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";    
    
    public static void main(String[] arg) {
        try {
        	
        	//UIManager.setLookAndFeel( lef );
        	
            ArgList list = ArgUtils.parseArgsProps(arg);
            
            String url = list.findArg("c").getValue();
            String usr = list.findArg("u").getValue();
            
            Color bc = parseColor(list.findArg("bc"), null);
            Color fc = parseColor(list.findArg("fc"), null);
            
            ConnectionFactory cp = ConnArgs.createConnectionFactory( list );
            
            CMDFrame frame = CMDFrame.getInstance("XJSQL "+url+" ("+usr+")", 
                        new ConnCMD( SQLCMDUtils.newSQLCMDComplete(cp) ), 
                        new CloseOnExit(), bc, fc);
            
            
            
            frame.setHelpText(HELP_TEXT);
            frame.setInfoText(INFO_TEXT);
            
        } catch (Throwable e) {
            System.err.println(" options : ");
            System.err.println("      -d  [jdbc-driver-class] ");
            System.err.println("      -c  [jdbc-url] ");
            System.err.println("      -u  [username] ");
            System.err.println("      -p  [password] ");
            System.err.println("      -bc  [rrrgggbbb] (optional, range is 000-255, default '255255255')");
            System.err.println("      -fc  [rrrgggbbb] (optional, range is 000-255, default '000000000')");
            System.err.println();
            e.printStackTrace();
        }
    }
    
}

class CloseOnExit extends AbstractOp {
        
    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.Op#doOp()
     */
    public boolean doOp() throws Exception {
        System.exit(0);
        return true;
    }
    
}

class ConnCMD implements CMD {

	public ConnCMD( CMD wrapped ) {
		this.wrapped = wrapped;
	}
	
	private CMD wrapped;
	
	public final static String SAVE_CONN = "jsql_sc";
	
	public final static String OPEN_CONN = "jsql_oc";
	
	public boolean canHandle(String cmd) {
		return cmd.indexOf( SAVE_CONN ) == 0 || cmd.indexOf( OPEN_CONN ) == 0 || this.wrapped.canHandle( cmd );
	}

	public CMDOutput handleCommand(String cmd) throws CMDException {
		CMDOutput output = null;
		if ( cmd.indexOf( OPEN_CONN ) == 0 ) {
			String[] args = cmd.substring( OPEN_CONN.length() ).trim().split( " " );
			ArgList list = ArgUtils.parseArgs( args );
			try {
				ConnectionFactory cf = ConnArgs.createConnectionFactory( list );
				this.wrapped = SQLCMDUtils.newSQLCMDComplete(cf);
			} catch (DAOException e) {
				throw ( new CMDException( e ) );
			}
			output = new WordCMDOutput( cmd, "params set" );
		} else {
			output = this.wrapped.handleCommand( cmd );
		}
		return output;
	}
	
	
	

}