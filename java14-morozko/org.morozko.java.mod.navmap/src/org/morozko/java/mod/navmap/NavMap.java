/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
 * @(#)NavMap.java
 *
 * @project	   : simoss
 * @package	   : org.morozko.java.mod.navmap
 * @creation   : 15-giu-2005 7.36.32
 */


package org.morozko.java.mod.navmap;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.navmap.servlet.NavController;
/**
 * <p>Classe che costruisce la mappa dei nodi dai singoli NavNode</p>
 * 
 * @author asacca
 *
 */
public class NavMap extends NavNode {

	private static final NavHandler DEFAULT_NAVHANDLER = new NavHandlerDefault();
	
	private static Properties loadProps( String path ) {
		Properties props = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if ( classLoader == null ) {
				classLoader = NavMap.class.getClassLoader();
			}
			String newPath = "/"+path.replace( '.' , '/' )+".properties";
			LogFacade.getLog().info( "path "+path+" to new path "+newPath );
			props.load( classLoader.getResourceAsStream( newPath ) );
			
		} catch (Exception e) {
			LogFacade.getLog().warn("loadProps "+path);
			//LogFacade.getLog().debug("loadProps "+path, e);
		}
		return props;
	}

	private static final String DEF_BUNDLE = "defaultBundle";
	
	/**
	 * 
	 * <p>Crea una nuova istanza di NavMap.</p>
	 * 
	 * @param navMapConfig
	 * @param navNodeMap
	 * @param navEntryMap
	 * @param navMenuMap
	 */
	public NavMap( Properties navMapConfig, Map navNodeMap, Map navEntryMap, Map navMenuMap, Map navHandlerMap, Map navSwitchMap ) {
		super( null, null, null );
		this.navMapConfig = navMapConfig;
		this.navEntryMap = navEntryMap;
		this.navNodeMap = navNodeMap;
		this.bundleMap = new HashMap();
		this.navSwitchMap = navSwitchMap;
		this.navHandlerMap = navHandlerMap;
		this.bundleMap.put( DEF_BUNDLE, loadProps( this.navMapConfig.getProperty( "bundle" ) ) );
		this.keepPath = Boolean.valueOf( navMapConfig.getProperty( "keep-path" ) );
		this.navModuleMap = new HashMap();
	}

	private Properties navMapConfig;
	
	private Map navNodeMap;
	
	private Map navEntryMap;

	
	private Map bundleMap;
	
	private Boolean keepPath;
	
	private Map navHandlerMap;
	
	private Map navModuleMap;
	
	private Map navSwitchMap;
	
	/**
	 * <p>Restituisce una entry dalla URL.</p>
	 * 
	 * @param navUrl la richesta per determinare l'Url
	 * @return       la entry della nav mao
	 */
	public NavEntry getNavEntryByNavUrl( String navUrl ) {
		NavEntry navEntry = (NavEntry)this.navEntryMap.get( navUrl );
		if ( navEntry == null ) {
			int index = navUrl.lastIndexOf( "/" );
			String module = navUrl.substring( 0, index );
			String entry = navUrl.substring( index+1 );
			NavModule navModule = (NavModule)this.getNavModuleMap().get( module );
			if ( navModule != null ) {
				navEntry = navModule.getNavNode( module, entry ).getNavEntry();
			}
		}
		return navEntry;
	}	
	
	public NavSwitch getNavSwitchByUrl( String navUrl ) {
		return (NavSwitch)this.navSwitchMap.get( navUrl );
	}
	
	private static int counter = 0;
	
	/**
	 * <p>Restituisce il nodo dalla URL.</p>
	 * 
	 * @param navUrl la richesta per determinare l'Url
	 * @return       il nodo del navNode
	 */
	public NavNode getNavNodeByNavUrl( String navUrl ) {
		counter++;
		int count = counter;
		LogFacade.getLog().debug( "getNavNodeByNavUrl ("+count+"), navUrl : "+navUrl );
		//LogFacade.getLog().debug( "getNavNodeByNavUrl ("+count+"), map : "+this.getNavEntryMap() );
		NavNode navNode = (NavNode)this.navNodeMap.get( navUrl );
		if ( navNode == null ) {
			int index = navUrl.lastIndexOf( "/" );
			String module = navUrl.substring( 0, index );
			String entry = navUrl.substring( index+1 );
			NavModule navModule = (NavModule)this.getNavModuleMap().get( module );
			LogFacade.getLog().debug( "getNavNodeByNavUrl ("+count+"), null nav node try kids map, module : "+navModule );
			if ( navModule != null ) {
				navNode = navModule.getNavNode( module, entry );
			}
		}
		LogFacade.getLog().debug( "getNavNodeByNavUrl ("+count+"), nav node : "+navNode );
		return navNode;
	}
	
	/**
	 * <p>Restituisce il nodo dal nome del nodo.</p>
	 * 
	 * @param name il nome del nodo
	 * @return     il nodo del navNode
	 */
	public NavNode getNavNode( String name ) {
		return this.getNavNode( null, name );
	}
	/**
	 *  <p>Restituisce il nodo dati il modulo e il nome del nodo.</p>
	 * 
	 * @param module  il modulo del nodo
	 * @param name    il nome del nodo
	 * @return        il nodo del navNode
	 */
	public NavNode getNavNode( String module, String name ) {
		String url = NavEntry.renderUrl( module, name );
		LogFacade.getLog().debug( "navNode : '"+url+"'" );
		NavNode navNode = this.getNavNodeByNavUrl( url );
		LogFacade.getLog().debug( "navNode : '"+navNode+"'" );
		return navNode;
	}

	/**
	 * <p>Restituisce una property della NavMap localizzata.</p>
	 * 
	 * @param request	la richiesta per determinare il Locale corretto	
	 * @param key		la chiave della property richiesta
	 * @return			la property
	 */
	public String getNavMapMessage( ServletRequest request, String key, NavEntry navEntry ) {
		LogFacade.getLog().debug( "getNavMapMessage : START key : "+key+" entry : "+navEntry );
		String value = null;
		if ( navEntry.getModule() != null ) {
			NavModule navModule = (NavModule)this.navModuleMap.get( navEntry.getModule() );
			if ( navModule != null ) {
				LogFacade.getLog().debug( "getNavMapMessage : navModule : "+navModule.getModuleName() );
				value = navModule.getNavMap().getNavMapMessage( request, key, navEntry );	
			}
		}
		if ( value == null ) {
			String bundleName = this.navMapConfig.getProperty( "bundle" );
			LogFacade.getLog().debug( "getNavMapMessage : bundleName : "+bundleName );
			Locale locale = (Locale)((HttpServletRequest)request).getSession().getAttribute( "org.apache.struts.action.LOCALE" );
			if (locale!=null) {
				bundleName+= "_"+locale;
			}
			Properties props = (Properties)this.bundleMap.get( bundleName );
			if (props==null) {
				props = loadProps( bundleName );
				this.bundleMap.put( bundleName, props );
			}
			value = props.getProperty( key );
			if (value==null) {
				value = ((Properties)this.bundleMap.get( DEF_BUNDLE )).getProperty( key );
			}			
		}
		LogFacade.getLog().debug( "getNavMapMessage : END value : "+value );
		return value;
	}
	
	private NavNode defaultNavNode;

	/**
	 * @return Restituisce il valore di defaultNavNode.
	 */
	public NavNode getDefaultNavNode() {
		return defaultNavNode;
	}

	/**
	 * @param defaultNavNode il valore di defaultNavNode da impostare.
	 */
	public void setDefaultNavNode(NavNode defaultNavNode) {
		this.defaultNavNode = defaultNavNode;
	}

	/**
	 * @return Restituisce il valore di keepPath.
	 */
	public Boolean getKeepPath() {
		return keepPath;
	}

	/**
	 * @param keepPath il valore di keepPath da impostare.
	 */
	public void setKeepPath(Boolean keepPath) {
		this.keepPath = keepPath;
	}
	
	public NavNode handleNavNode( NavNode navNode, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		NavEntry navEntry = navNode.getNavEntry();
		String handlerName = navEntry.getNavHandler();
		if ( handlerName != null ) {
			NavHandler navHandler = (NavHandler)this.navHandlerMap.get( handlerName );
			navNode = navHandler.handleNavNode( navNode, request, response);
		} else {
			DEFAULT_NAVHANDLER.handleNavNode( navNode, request, response);
		}
		return navNode;
	}
	
	public boolean isCurrent( NavEntry navEntry, HttpServletRequest request ) throws Exception {
		boolean ok = false;
		String handlerName = navEntry.getNavHandler();
		if ( handlerName != null ) {
			NavHandler navHandler = (NavHandler)this.navHandlerMap.get( handlerName );
			ok = navHandler.isCurrent( navEntry, request );
		} else {
			ok = DEFAULT_NAVHANDLER.isCurrent( navEntry, request );
		}
		return ok;		
	}
	
	public static NavMap getFromContext( ServletContext context ) {
		return (NavMap)context.getAttribute( NavController.ATT_NAME_NAVMAP );
	}

	/**
	 * @return Restituisce il valore di navModuleMap.
	 */
	public Map getNavModuleMap() {
		return navModuleMap;
	}

	/**
	 * @param navModuleMap il valore di navModuleMap da impostare.
	 */
	public void setNavModuleMap(Map navModuleMap) {
		this.navModuleMap = navModuleMap;
	}
	
}
