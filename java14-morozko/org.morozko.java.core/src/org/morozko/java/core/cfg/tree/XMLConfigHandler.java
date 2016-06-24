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
package org.morozko.java.core.cfg.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.util.path.DefaultPathResolver;
import org.morozko.java.core.util.path.PathResolver;
import org.morozko.java.core.xml.XMLException;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

public class XMLConfigHandler extends AbstractConfigHandler {

	public static final int CODE_ELEMENT_NOT_PRESENT = 404;
	
	private Element root;
	
	public static ConfigHandler getInstance( File fileConfig ) throws ConfigException {
		ConfigHandler handler = null;
		try {
			handler = new XMLConfigHandler( DOMIO.loadDOMDoc( fileConfig ).getDocumentElement() );
		} catch (XMLException e) {
			throw ( new ConfigException( e ) );
		}
		return handler;
	}
	
	public static ConfigHandler getInstance( Element root ) throws ConfigException {
		ConfigHandler handler = null;
		handler = new XMLConfigHandler( root );
		return handler;
	}
	
	protected XMLConfigHandler( Element root ) {
		super( "", DefaultPathResolver.DEFAULT );
		this.root = root;
	}

	private String[] parse( String path ) {
		List parts = new ArrayList();
		PathResolver resolver = this.getModuleResolver();
		String currentTagName = resolver.getName( path );
		String currentParent = resolver.getParent( path );
		while (!currentTagName.equals( "" ) ) {
			parts.add( currentTagName );
			currentTagName = resolver.getName( currentParent );
			currentParent = resolver.getParent( currentParent );
		}
		String[] result = new String[ parts.size() ];
		for (int k=0; k<result.length; k++) {
			result[k] = (String)parts.get( result.length-1-k );
		}
		return result;
	}
	
	public String getParameter(String module, String name) throws ConfigException {
		String value = null;
		SearchDOM search = SearchDOM.newInstance( true, true );
		String[] path = this.parse( module+"/"+name ); 
		String attribute = null;
		String last = path[ path.length-1 ];

		int index = last.indexOf( '#' );
		if ( index!=-1 ) {
			attribute = last.substring( index+1 );
			path[ path.length-1 ] = last.substring( 0, index );
		}
		
		int position = 0;
		int index1 = last.indexOf( '@' );
		if (index1!=-1) {
			position = Integer.parseInt( last.substring( index1+1, index ) );
			path[ path.length-1 ] = last.substring( 0, index1 );
		}

		int count = 0;
		Element tag = root;
		while (count<path.length-1) {
			tag = search.findTag( tag, path[count] );
			if (tag==null) {
				throw ( new ConfigException( "Elemento '"+path[count]+"' non presente", CODE_ELEMENT_NOT_PRESENT ) );
			}
			count++;
		}
		
		List l = search.findAllTags( tag, path[count] );
		if (l.isEmpty()) {
			throw ( new ConfigException( "Elemento '"+path[count]+"' non presente", CODE_ELEMENT_NOT_PRESENT ) );
		}
		tag = (Element)l.get( position );
		
		if ( attribute==null ) {
			value = search.findText( tag );
		} else {
			Properties atts = DOMUtils.attributesToProperties( tag );
			value = atts.getProperty( attribute );
		}
		return value;
	}

	public void setParameter(String module, String name, String value) throws ConfigException {
		throw ( new ConfigException( "Not implemented yet" ) );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConfigHandler ch = XMLConfigHandler.getInstance( new File( "test/cfg/test.xml" ) );
			System.out.println( "TEST : "+ch.getParameter( "/root/ele/ele1/ele@2#prova" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}