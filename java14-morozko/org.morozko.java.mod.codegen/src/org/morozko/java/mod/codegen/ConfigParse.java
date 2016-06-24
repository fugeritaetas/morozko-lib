package org.morozko.java.mod.codegen;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.config.PropertyXML;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.daogen.gen.config.ConfigParser;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigParse {
	
	private static void createMap( Element node, NavNode parent, NavMap navMap ) throws ConfigException {
		NodeList nl = node.getChildNodes();
		for ( int k=0; k<nl.getLength(); k++ ) {
			Node current = nl.item( k );
			if ( current.getNodeType() == Element.ELEMENT_NODE ) {
				Element currentKid = ((Element)current);
				if ( currentKid.getNodeName().equals( "nav-node" ) ) {
					Properties props = DOMUtils.attributesToProperties( currentKid );
					NavNode currentNode = new NavNode();
					currentNode.setName( props.getProperty( "name" ) );
					currentNode.setModule( props.getProperty( "module" ) );
					currentNode.setDescription( props.getProperty( "description" ) );
					currentNode.setFacade( props.getProperty( "facade" ) );
					currentNode.setMenu1( props.getProperty( "menu-1" ) );
					currentNode.setMenu2( props.getProperty( "menu-2" ) );
					currentNode.setMenu3( props.getProperty( "menu-3" ) );
					currentNode.setOverwrite( props.getProperty( "overwrite" ) );
					currentNode.setOperation( props.getProperty( "operation" ) );
					currentNode.setJspInclude( props.getProperty( "jsp-include" ) );
					currentNode.setJspTemplate( "tiles."+props.getProperty( "module" )+"."+props.getProperty( "jsp-template", "template" ) );
					currentNode.setAuth( props.getProperty( "auth", "NOCHECK" ) );
					String formName = props.getProperty( "form" );
					if ( formName != null ) {
						NavForm navForm = navMap.getFormMap().get( formName );
						if ( navForm == null ) {
							throw new ConfigException( "Form "+formName+" doesn't exist" );
						}
						currentNode.setForm( navForm );
						currentNode.setFormInput( props.getProperty( "form-input" ) );
						currentNode.setFormValidate( "true".equalsIgnoreCase( props.getProperty( "form-validate" ) ) );
						System.out.println( "current form : "+currentNode.getName()+" : "+currentNode.isFormValidate()+" : "+currentNode.getFormInput() );
					}
					parent.getKids().add( currentNode );
					createMap( currentKid , currentNode, navMap );
				}
			}
		}
	}
	
	public static NavMap parse( Element root ) throws Exception {
		NavMap navMap = new NavMap();
		SearchDOM searchDOM = SearchDOM.newInstance( true , true );
		if ( root.getNodeName().equals( "codegen-config" ) ) {
			// general props
			Element generalProps = searchDOM.findTag( root , "general-props" );
			navMap.setGeneralProps( PropertyXML.parse( generalProps ) );
		
			// plugin doagen
			String daogenPlugin = navMap.getGeneralProps().get( "daogen-plugin" );
			if ( daogenPlugin != null ) {
				ConfigParser cp = new ConfigParser();
				DGConfig dgConfig = cp.parseConfig( daogenPlugin );
				navMap.getDaogenList().add( dgConfig );
			}
			String daogenPlugin1 = navMap.getGeneralProps().get( "daogen-plugin-1" );
			if ( daogenPlugin1 != null ) {
				ConfigParser cp = new ConfigParser();
				DGConfig dgConfig = cp.parseConfig( daogenPlugin1 );
				navMap.getDaogenList().add( dgConfig );
			}
			
			
			// nav forms
			Element navFormsTag = searchDOM.findTag( root , "nav-forms" );
			if ( navFormsTag != null ) {
				List<Element> navFormList = searchDOM.findAllTags( navFormsTag, "nav-form" );
				Iterator<Element> navFormIt = navFormList.iterator();
				while ( navFormIt.hasNext() ) {
					Element navFormTag = navFormIt.next();
					String name = navFormTag.getAttribute( "name" );
					NavForm navForm = NavForm.newNavForm( name );
					navMap.getFormMap().put( name , navForm );
					String daogen = navFormTag.getAttribute( "daogen" );
					LogFacade.getLog().info( "name : "+name+" daogen : '"+daogen+"'" );
					if ( daogen != null && daogen.trim().length() > 0 ) {
						navForm.setDaogen( daogen );
						TableConfig formTable = navMap.getDaogenTable( daogen );
						Iterator<FieldConfig> fields = formTable.getFields().iterator();
						while ( fields.hasNext() ) {
							FieldConfig fieldConfig = fields.next();
							navForm.getFieldList().add( NavFormField.newField( fieldConfig.getJavaFieldName() , fieldConfig.getJavaFieldType() ) );
						}
					}
				}
			}
			// nav map
			Element navMapTag = searchDOM.findTag( root , "nav-map" );
			createMap( navMapTag , navMap, navMap );
		} else {
			throw ( new Exception( "Root element must be codegen-config : "+root.getNodeName() ) );
		}
		return navMap;
	}
	
	
}
