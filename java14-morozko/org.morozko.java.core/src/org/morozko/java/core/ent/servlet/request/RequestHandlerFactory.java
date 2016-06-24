/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
 * @(#)RequestHandlerFactory.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.request
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class RequestHandlerFactory extends BasicLogObject {

	private Map handlerMap;
	
	public RequestHandlerFactory() {
		this.handlerMap = new HashMap();
	}
	
	public Iterator listhandlers() {
		return this.handlerMap.values().iterator();
	}

	public void addAll( Element config ) throws Exception {
		SearchDOM searchDOM = SearchDOM.newInstance(true, true);
		List list = searchDOM.findAllTags( config , "request-handler-config" );
		Iterator it = list.iterator();
		while ( it.hasNext() ) {
			this.addHandler( (Element) it.next() );
		}
	}
	
	public void addHandler( Element config ) throws Exception {
		this.getLog().debug( "addHandler START" );
		SearchDOM searchDOM = SearchDOM.newInstance(true, true);
		Properties configAtts = DOMUtils.attributesToProperties( config );
		String name = configAtts.getProperty( "name" );
		String type = configAtts.getProperty( "type" );
		this.getLog().debug( "addHandler name:"+name );
		this.getLog().debug( "addHandler type:"+type );
		RequestHandler handler = (RequestHandler)ClassHelper.newInstance( type );
		Element customConfig = searchDOM.findTag( config , "handler-config" );
		if ( customConfig != null ) {
			handler.configure( customConfig );
		}
		this.handlerMap.put( name, handler );
		this.getLog().debug( "addHandler END name:"+name+", type:"+type+" (custom config:"+(customConfig!=null)+")" );
	}	
	
}
