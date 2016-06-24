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
 * @(#)NavMapFactory.java
 *
 * @project	   : org.morozko.java.mod.navmap
 * @package	   : org.morozko.java.mod.navmap
 * @creation   : 13-lug-2005 13.00.07
 */

package org.morozko.java.mod.navmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.lang.Result;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.XMLException;
import org.morozko.java.core.xml.XMLValidator;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.core.xml.sax.XMLValidatorSAX;
import org.morozko.java.core.xml.sax.er.ByteArrayEntityResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * <p>Classe che gestisce la factory per la creazione dell'albero di NavNode.</p>
 * 
 * @author asacca
 *
 */
public class NavMapFactory {
	
	public static void main( String[] args ) {
		try {
			String confFile = args[0];
			
			System.out.println( "CONF FILE : "+confFile );
			
			NavMap map = configure( new InputSource( new FileInputStream( new File( confFile ) ) ) );
			
			System.out.println( "NAV NODE : "+map.getNavNode( "privateMessageList" ) );
			
			System.out.println( "NAV NODE : "+map.getNavNode( "privateMessageShow" ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String DTD = "/org/morozko/java/mod/navmap/res/nav-map-1-0.dtd";
	
	public final static String SYSTEM_ID = "http://www.morozko.org/data/java/mod/nav-map/dtd/nav-map-1-0.dtd";

	private static void createMap( NavNode parent, Element parentNode, Map navNodeMap, Map navEntryMap, Map navMenuMap, int depth ) {
		NodeList kids = parentNode.getChildNodes();
		for (int k=0; k<kids.getLength(); k++) {
			Node currentNode = kids.item( k );
			if ( currentNode.getNodeType() == Node.ELEMENT_NODE ) {
				Element currentTag = (Element)currentNode;
				Properties attributes = DOMUtils.attributesToProperties( currentTag );
				String entryUrl = NavEntry.renderUrl( attributes.getProperty( "nav-module" ) , attributes.getProperty( "nav-entry" ) );
				NavEntry navEntry = (NavEntry)navEntryMap.get( entryUrl );
				Map currentNavMenuMap = new HashMap();
				int i = 1;
				String nome = "menu-"+i;
				Object value = navMenuMap.get( attributes.getProperty( nome ) );
				LogFacade.getLog().debug( "new nav-node : "+entryUrl );
				LogFacade.getLog().debug( "new nav-node atts : "+attributes );
				while ( value != null) {
					LogFacade.getLog().debug( "adding menu : "+value );
					currentNavMenuMap.put(nome, value);
					i++;
					nome = "menu-"+i;
					value = navMenuMap.get( attributes.getProperty( nome ) );
				}
				NavNode currentNavNode = new NavNode( navEntry, parent, currentNavMenuMap );
				int newdepth = depth+1;
				currentNavNode.setDepth( new Integer( newdepth ) );
				currentNavNode.setBackPath( !"false".equalsIgnoreCase( attributes.getProperty( "path" ) ) );
				navNodeMap.put( entryUrl, currentNavNode );
				createMap( currentNavNode, currentTag, navNodeMap, navEntryMap, navMenuMap, newdepth );
			}
		}
	}
	
	public static NavMap configure( InputSource source ) throws XMLException {
		return configure( source, true );
	}
	
	private static void addNavMenuEntry( Map navEntryMap, List currentNavMenuItemList, NavMenuItemContainer container ) throws XMLException {
		Iterator currentNavMenuItemListIt = currentNavMenuItemList.iterator();
	 	   while (currentNavMenuItemListIt.hasNext()) {
	 		   Element currentNavMenuItem = (Element)currentNavMenuItemListIt.next();
	 		   Properties currentMenuItemAttributes = DOMUtils.attributesToProperties( currentNavMenuItem );
	 		   String entryUrl = NavEntry.renderUrl( currentMenuItemAttributes.getProperty( "nav-module" ),
	 				   									currentMenuItemAttributes.getProperty( "nav-entry" ));
	 		   Object navEntryLink = navEntryMap.get( entryUrl );
	 		   if ( navEntryLink == null) {
	 			   throw ( new XMLException( "Entry del menu non definita : '"+entryUrl+"'" ) );
	 		   } else {
	 			   // verifico se c'è qualche overriding
	 			   String auth = currentMenuItemAttributes.getProperty( "auth" );
	 			   String display = currentMenuItemAttributes.getProperty( "display" );
	 			   String title = currentMenuItemAttributes.getProperty( "title" );
	 			   String renderLink = currentMenuItemAttributes.getProperty( "renderLink" );
	 			   if ( auth != null || title != null || display != null || renderLink != null ) {
	 				   NavEntry navEntry = (NavEntry)navEntryLink;
	 				   navEntry = navEntry.copy();
	 				   if ( auth != null ) {
	 					   LogFacade.getLog().debug( "overriding menu auth : "+auth );
	 					   navEntry.setAuth( auth );
	 				   }
	 				   if ( display != null ) {
	 					   LogFacade.getLog().debug( "overriding menu display : "+display );
	 					   navEntry.setDisplay( display );
	 				   }
	 				   if ( title != null ) {
	 					   LogFacade.getLog().debug( "overriding menu title : "+title );
	 					   navEntry.setTitle( title );
	 				   }
	 				  if ( renderLink != null ) {
	 					   LogFacade.getLog().debug( "overriding menu renderLink : "+renderLink );
	 					   navEntry.setRenderLink( Boolean.valueOf( renderLink ) );
	 				   }			 				   
	 				   navEntryLink = navEntry;
	 			   }
	 		   }
	 		   
	 		  container.add( (NavEntry)navEntryLink );
	 		   
	 	   }

	}
	
	/**
	 * 
	 * <p>Metodo pricipale che configura il NavMap leggendo la configurazione 
	 * dal file XML. Vengono usate le API DOM per la lettura del file di configurazione XML.</p>
	 * 
	 * @param source
	 * @return
	 * @throws XMLException
	 */
	public static NavMap configure( InputSource source, boolean validateDTD ) throws XMLException {
		LogFacade.getLog().info( "NAV-MAP configuration start" );
		NavMap map = null;
		try {
			
			boolean valid = !validateDTD;
			
			System.out.println( ">>>>>>>>>>>>> TEST NAV MAP FACTORY 1 "+source );
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			StreamIO.pipeStream( source.getByteStream(), buffer );
			System.out.println( ">>>>>>>>>>>>> TEST NAV MAP FACTORY 2 "+source );
			
			StringReader reader1 = new StringReader( buffer.toString() );
			StringReader reader2 = new StringReader( buffer.toString() );
			
			InputStream dtdStream = NavMap.class.getResourceAsStream( DTD );
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamIO.pipeStream( dtdStream, baos, StreamIO.MODE_CLOSE_BOTH );
			ByteArrayEntityResolver er = new ByteArrayEntityResolver( baos.toByteArray(), null, SYSTEM_ID );
			
			if ( !valid ) {
				LogFacade.getLog().info( "NAV-MAP dtd validation OK" );
				XMLValidator validator = XMLValidatorSAX.newInstance( er );
				Result result = validator.validateXML(reader1);
				valid = result.isTotalSuccess();
				if ( !valid ) {
					for  (int k=0; k<result.errorCount(); k++) {
						Exception e = result.getError( k );
						String message = e.toString();
						if (e instanceof SAXParseException) {
							SAXParseException spe = (SAXParseException)e;
							message+= "[row:"+spe.getLineNumber()+", col:"+spe.getColumnNumber()+"]";
						}
						System.out.println( "[ERRORE-XML] "+message );
					}
					throw ( new XMLException( "File non valido per il DTD" ) );					
				}
			} else {
				LogFacade.getLog().info( "NAV-MAP dtd validation OFF" );
			}
			
			if ( valid ) {
				Document document = DOMIO.loadDOMDoc( new InputSource( reader2 ) , er );
				
				SearchDOM searchDOM = SearchDOM.newInstance( true, true );

				Element rootTag = document.getDocumentElement();
			
				Element navMapConfig = searchDOM.findTag( rootTag, "nav-map-config" );
				Properties navMapProps = DOMUtils.attributesToProperties( navMapConfig );
				
				
				// definizione nav handler
				Map navHandlerMap = new HashMap();
				Element navHandlerListTag = searchDOM.findTag( rootTag, "nav-handler-list" );
				if ( navHandlerListTag != null ) {
					List navHandlerList = searchDOM.findAllTags( navHandlerListTag , "nav-handler" );
					Iterator navHandlerListIt = navHandlerList.iterator();
					while ( navHandlerListIt.hasNext() ) {
						Element navHandlerTag = (Element)navHandlerListIt.next();
						Properties currentNavHandlerAttributes = DOMUtils.attributesToProperties( navHandlerTag );
						String name= currentNavHandlerAttributes.getProperty( "name" );
						String type= currentNavHandlerAttributes.getProperty( "type" );
						
						NavHandler navHandler = null;
						try {
							navHandler = (NavHandler)ClassHelper.newInstance( type );
						} catch (Exception e) {
							throw ( new XMLException( e ) );
						}
						LogFacade.getLog().debug( "nav handler : "+name+" ("+navHandler+")" );
						navHandlerMap.put( name, navHandler );
						
					}					
				}

				
				// per la modellazione delle entry
				Map navEntryMap = new HashMap();
				Element navEntryListTag = searchDOM.findTag( rootTag, "nav-entry-list" );
				List navEntryList = searchDOM.findAllTags( navEntryListTag , "nav-entry" );
				Iterator navEntryListIt = navEntryList.iterator();
				while (navEntryListIt.hasNext()) {
					Element currentNavEntryTag = (Element)navEntryListIt.next();
					Properties currentNavEntryAttributes = DOMUtils.attributesToProperties( currentNavEntryTag );
					NavEntry currentNavEntry = new NavEntry();
					currentNavEntry.setModule( currentNavEntryAttributes.getProperty( "module" ) );
					currentNavEntry.setName( currentNavEntryAttributes.getProperty( "name" ) );
					currentNavEntry.setAuth( currentNavEntryAttributes.getProperty( "auth" ) );
					currentNavEntry.setContext( currentNavEntryAttributes.getProperty( "context" ) );
					currentNavEntry.setDisplay( currentNavEntryAttributes.getProperty( "display" ) );
					currentNavEntry.setTitle( currentNavEntryAttributes.getProperty( "title" ) );
					currentNavEntry.setLabel( currentNavEntryAttributes.getProperty( "label" ) );
					if ( currentNavEntry.getTitle() == null ) {
						currentNavEntry.setTitle( currentNavEntry.getDisplay() );
					}
					if ( currentNavEntry.getLabel() == null ) {
						currentNavEntry.setLabel( currentNavEntry.getDisplay() );
					}					
					currentNavEntry.setLink( currentNavEntryAttributes.getProperty( "link" ) );
					currentNavEntry.setTarget( currentNavEntryAttributes.getProperty( "target" ) );
					currentNavEntry.setRenderLink( new Boolean( !"false".equalsIgnoreCase( currentNavEntryAttributes.getProperty( "renderLink" ) ) ) );	// default true
					currentNavEntry.setAbsolute( Boolean.valueOf( currentNavEntryAttributes.getProperty( "absolute" ) ) );
					currentNavEntry.setNavHandler( currentNavEntryAttributes.getProperty( "handler" ) );
					currentNavEntry.setDirectDisplay( Boolean.FALSE );
					currentNavEntry.setDirectTitle( Boolean.FALSE );
					currentNavEntry.setDirectLabel( Boolean.FALSE );
					String url = currentNavEntry.getUrl();
					LogFacade.getLog().debug( "new nav entry url : '"+url+"' - entry : '"+currentNavEntry+"'" );
					navEntryMap.put( url , currentNavEntry );
				}

				// modelling nav switches
				Map navSwitchMap = new HashMap();
				List navSwitchList = searchDOM.findAllTags( navEntryListTag , "nav-switch" );
				Iterator navSwitchListIt = navSwitchList.iterator();
				while ( navSwitchListIt.hasNext() ) {
					Element currentNavSwitchTag = (Element) navSwitchListIt.next();
					// search for keys
					Map currentNavSwitchKeyMap = new HashMap();
					List navSwitchKeyList = searchDOM.findAllTags( currentNavSwitchTag , "nav-switch-key" );
					Iterator navSwitchKeyListIt = navSwitchKeyList.iterator();
					while ( navSwitchKeyListIt.hasNext() ) {
						Element currentNavSwitchKeyTag = (Element)navSwitchKeyListIt.next();
						Properties currentNavSwitchKeyAttributes = DOMUtils.attributesToProperties( currentNavSwitchKeyTag );
						String key = currentNavSwitchKeyAttributes.getProperty( "key" );
						String name = currentNavSwitchKeyAttributes.getProperty( "nav-entry" );
						String module = currentNavSwitchKeyAttributes.getProperty( "nav-module" );
						String url = NavId.renderUrl( module , name );
						NavEntry navEntry = (NavEntry) navEntryMap.get( url );
						if ( navEntry != null ) {
							LogFacade.getLog().debug( "new nav switch key : '"+key+"' - entry : '"+navEntry+"'" );
							currentNavSwitchKeyMap.put( key , navEntry );
						} else {
							throw ( new ConfigException( "nav entry link undefined for key : "+key+" url : "+url ) );
						}
					}
					// define nav-switch
					Properties currentNavSwitchAttributes = DOMUtils.attributesToProperties( currentNavSwitchTag );
					NavSwitch currentNavSwitch = new NavSwitch( currentNavSwitchKeyMap );
					String name = currentNavSwitchAttributes.getProperty( "name" );
					String module = currentNavSwitchAttributes.getProperty( "module" );
					String navEntry = currentNavSwitchAttributes.getProperty( "nav-entry" );
					String navModule = currentNavSwitchAttributes.getProperty( "nav-module" );
					String url = NavId.renderUrl( navModule , navEntry );
					NavEntry defaultNavEntry = (NavEntry) navEntryMap.get( url );
					if ( defaultNavEntry != null ) {
						currentNavSwitch.setName( name );
						currentNavSwitch.setModule( module );
						currentNavSwitch.setDefaultNavEntry( defaultNavEntry );
					} else {
						throw ( new ConfigException( "nav swirch definition error" ) );
					}
					navSwitchMap.put( NavId.renderUrl( module, name) , currentNavSwitch );
				}
				
				Map navMenuMap = new HashMap();
				
				// per la modellazione dei menu
				Element navMenuListTag = searchDOM.findTag( rootTag, "nav-menu-list" );
				List navMenuList = searchDOM.findAllTags( navMenuListTag, "nav-menu" );
				Iterator navMenuListIt = navMenuList.iterator();
				while(navMenuListIt.hasNext()){
				   Element currentNavMenuTag = (Element)navMenuListIt.next();
				   Properties currentMenuEntryAttributes = DOMUtils.attributesToProperties( currentNavMenuTag );
			 	   NavMenu currentNavMenu = new NavMenu( currentMenuEntryAttributes.getProperty("name") );
			 	   // aggiungo le menu entry
			 	   List currentNavMenuItemList = searchDOM.findAllTags( currentNavMenuTag, "nav-menu-item" );
			 	   addNavMenuEntry( navEntryMap, currentNavMenuItemList, currentNavMenu );
			 	   
			 	   // aggiungo le sezionie
			 	  List currentNavSectionList = searchDOM.findAllTags( currentNavMenuTag, "nav-menu-section" );
			 	  Iterator currentNavSectionIt = currentNavSectionList.iterator();
			 	  while ( currentNavSectionIt.hasNext() ) {
			 		  Element currentNavSectionTag = (Element)currentNavSectionIt.next();
			 		  Properties currentNavSectionAttributes = DOMUtils.attributesToProperties( currentNavSectionTag );
			 		  String name = currentNavSectionAttributes.getProperty( "name" );
			 		  String navEntry = currentNavSectionAttributes.getProperty( "nav-entry" );
			 		  String navModule = currentNavSectionAttributes.getProperty( "nav-module" );
			 		  NavEntry sectionEntry = (NavEntry)navEntryMap.get( NavEntry.renderUrl( navModule , navEntry ) );
			 		  NavMenuSection currentNavMenuSection = new NavMenuSection( name, sectionEntry );
			 		  List currentNaveSectionEntryList = searchDOM.findAllTags( currentNavSectionTag, "nav-menu-item" );
			 		  addNavMenuEntry( navEntryMap, currentNaveSectionEntryList, currentNavMenuSection );
			 		  currentNavMenu.getSectionList().add( currentNavMenuSection );
			 	  }
			 	   
			 	   LogFacade.getLog().debug( "new nav menu : "+currentNavMenu.getName()+" : "+currentNavMenu );
			 	   navMenuMap.put( currentNavMenu.getName(), currentNavMenu );
				}
			 	   
			 	   

				// per la modellazione dell' albero delle action
				Element navTreeTag = searchDOM.findTag( rootTag, "nav-tree" );
				Map navNodeMap = new HashMap();
				NavMap rootNode = new NavMap( navMapProps, navNodeMap, navEntryMap, navMenuMap, navHandlerMap, navSwitchMap );
				int baseDepth = -1;
				rootNode.setDepth( new Integer( baseDepth ) );
				createMap( rootNode, navTreeTag, navNodeMap, navEntryMap, navMenuMap, baseDepth );
			
				map = rootNode;
				
				// per la modellazione delle entry
				List navAliasList = searchDOM.findAllTags( navEntryListTag , "nav-alias" );
				Iterator navAliasListIt = navAliasList.iterator();
				while (navAliasListIt.hasNext()) {
					Element currentNavAliasTag = (Element)navAliasListIt.next();
					Properties currentNavAliasAttributes = DOMUtils.attributesToProperties( currentNavAliasTag );
					String name = currentNavAliasAttributes.getProperty( "name" );
					String module = currentNavAliasAttributes.getProperty( "module" );
					String navEntry = currentNavAliasAttributes.getProperty( "nav-entry" );
					String navModule = currentNavAliasAttributes.getProperty( "nav-module" );
					String url = NavEntry.renderUrl( module, name );
					NavNode navNodeAlias = rootNode.getNavNode( navModule, navEntry );
					navNodeMap.put( url, navNodeAlias );
				}		
				
				Element defaultNavNodeTag = searchDOM.findTag( rootTag , "nav-node-default" );
				if ( defaultNavNodeTag != null ) {
					Properties defaultNavNodeAtts = DOMUtils.attributesToProperties( defaultNavNodeTag );
					NavNode defaultNavNode = map.getNavNode( defaultNavNodeAtts.getProperty( "nav-module" ), defaultNavNodeAtts.getProperty( "nav-entry" ) );
					LogFacade.getLog().debug( "default-nav-node : "+defaultNavNode );
					if ( defaultNavNode == null ) {
						throw ( new IOException( "Invalid default nav node" ) );
					} else {
						map.setDefaultNavNode( defaultNavNode );
					}
				}
				
				Element navModuleListTag = searchDOM.findTag( rootTag , "nav-module-list" );
				if ( navModuleListTag != null ) {
					List navModuleTagList = searchDOM.findAllTags( navModuleListTag, "nav-module" );
					Iterator itNavModule = navModuleTagList.iterator();
					while ( itNavModule.hasNext() ) {
						Element navModuleTag = (Element)itNavModule.next();
						Properties navModuleAtts = DOMUtils.attributesToProperties( navModuleTag );
						String name = navModuleAtts.getProperty( "name" );
						String type = navModuleAtts.getProperty( "type" );
						String config = navModuleAtts.getProperty( "config" );
						String home = navModuleAtts.getProperty( "home" );
						NavModule navModule = (NavModule) ClassHelper.newInstance( type );
						navModule.configure( new InputSource( navModule.getClass().getResourceAsStream( config ) ), map, name, home );
						map.getNavModuleMap().put( name, navModule );
					}
				}
				
			}
			
		} catch (Exception ioe ) {
			throw ( new XMLException( ioe ) );
		}
		LogFacade.getLog().info( "NAV-MAP configuration end" );	
		return map;
	}
	
}


