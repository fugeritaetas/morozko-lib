/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)TrueCoder.java
 *
 * @project	   : org.morozko.java.mod.daogen
 * @package	   : org.morozko.java.mod.daogen.generator
 * @creation   : 19-ott-2005 11.50.52
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.morozko.java.mod.daogen.DAOGenStarter;
import org.morozko.java.mod.daogen.gen.config.DGConfig;

public class TrueCoder extends Coder {

	public static final int TYPE_DAO = 1;
	public static final int TYPE_BEAN = 2;
	public static final int TYPE_MODEL= 3;
	public static final int TYPE_DAO_FACTORY = 4;
	
    public static void createTrueFile( DGConfig dgConfig, File file, String cName, String pName, int type ) throws IOException {
    	Properties generalProps = dgConfig.getGeneralProps();
    	file.getParentFile().mkdirs();
    	if ( !file.exists() || DAOGenStarter.REGENERATE ) {
    		String custom = null;
        	PrintStream stream = new PrintStream( new FileOutputStream( file ) );
        	addClassComment( stream, cName, pName, dgConfig );
        	stream.println( "package "+pName+";" );
        	stream.println( );
            stream.println( "import "+pName+".helpers."+cName+"Helper;" );
        	stream.println( );
        	addTypeComment( stream, "Classe "+cName, dgConfig );
        	stream.println( "public class "+cName+" extends "+cName+"Helper {" );
            stream.println();
            stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
            stream.println();
        	if ( type == TYPE_DAO ) {
                stream.println( "    public "+cName+"("+generalProps.getProperty( "factory.dao" )+" daoFactory ) {" );
                stream.println( "        super(daoFactory);" );
                stream.println( "        this.init(daoFactory);" );
                stream.println( "    }" );
                stream.println( );    		
        	}
        	if ( type == TYPE_DAO_FACTORY ) {
        		stream.println( "    private static "+cName+" instance;" );
        		stream.println( "    public static "+cName+" getInstance() {" );
                stream.println( "        return instance;" );
                stream.println( "    }" );
                stream.println( "    public static void init( org.morozko.java.mod.db.dao.BasicDAOFactory bdf ) {" );
                stream.println( "        instance = new "+cName+"( bdf );" );
                stream.println( "    }" );            
                stream.println( "    public static void init( org.morozko.java.mod.db.connect.ConnectionFactory cf ) {" );
                stream.println( "        instance = new "+cName+"( new org.morozko.java.mod.db.dao.BasicDAOFactory( cf ) );" );
                stream.println( "    }" );                  
                stream.println( "    public "+cName+"("+generalProps.getProperty( "factory.dao" )+" daoFactory ) {" );
                stream.println( "        super(daoFactory);" );
                stream.println( "    }" );
                stream.println( );    		
        	}
        	if (custom!=null) {
        		stream.println( custom );
        	}
        	stream.println( "}" );    		
    	}
    }

}
