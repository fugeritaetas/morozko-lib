package org.morozko.java.mod.codegen;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.codegen.handler.DaogenHandler;
import org.morozko.java.mod.codegen.handler.NavMapConfig;
import org.morozko.java.mod.codegen.handler.StrutsConfig;
import org.morozko.java.mod.daogen.gen.coder.Coder;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;
import org.w3c.dom.Element;

public class CodeGen extends Coder {

	private static TemplateResolver templateResolver = new TemplateResolver( null );
	
	public static void main( String[] args ) {
		try {
			System.out.println( "CodeGen V 0.1.1 ( 2011-08-11 ) by Matteo Franci" );
			ArgList list = ArgUtils.parseArgs( args );
			String c = list.findArgValue( "c" );
			NavMap navMap = ConfigParse.parse( DOMIO.loadDOMDoc( new File( c ) ).getDocumentElement() );
			generate( navMap );
			System.out.println( "CodeGen END" );
		} catch (Exception e) {
			System.out.println( "Usage: CodeGen -c [config-file]" );
			e.printStackTrace( System.out );
		}
	}
	
	private static void handleNode( Iterator<NavNode> nodes, GenHelper helper, Element parent, NavMap navMap ) throws Exception {	
		while ( nodes.hasNext() ) {
			NavNode node = nodes.next();
			
			try {
				// genera actions
				String baseName = upFirst( convertName( node.getName() ) );
				String actionName = "Action"+baseName;
				String formName = "Form"+baseName;
				String formParamName = "form"+baseName;
				String extendForm = "org.apache.struts.validator.ValidatorForm";
				
				String dtoName = "DTO"+baseName;
				String facadeName = "Facade"+baseName;
				String facadeProp = node.getFacade();
				if ( facadeProp != null ) {
					facadeName = "Facade"+facadeProp;
				}
				String pageName = node.getName().toLowerCase();
				String factoryName = upFirst( helper.moduleName );
				
				StringBuffer dtoBuffer = new StringBuffer();
				StringBuffer formToDtoBuffer = new StringBuffer();
				
				StringBuffer jspFormBody = new StringBuffer();
				
				StringBuffer linkBuff = new StringBuffer();
				
				Iterator<NavNode> itNodes = node.getKids().iterator();
				linkBuff.append( "<ul>\n" );
				while ( itNodes.hasNext() ) {
					NavNode currNode = itNodes.next();
					linkBuff.append( "<li><navmap:link navEntry=\""+currNode.getName()+"\" navModule=\""+helper.moduleName+"\"/></li>\n" );
				}
				linkBuff.append( "</ul>\n" );
	
	
				
				NavForm form =  node.getForm();
				if ( form != null ) {
					formName = "Form"+form.getName();
					formParamName = "form"+form.getName();
					dtoName = "DTO"+form.getName();
					if ( form.getDaogen() != null ) {
						DGConfig daogen = navMap.getDaogen( form.getDaogen() );
						TableConfig tc = daogen.getTable( form.getDaogen() );
						extendForm = daogen.getGeneralProps().getProperty( "package.bean" )+"."+form.getDaogen()+"Bean";
						String modelType = daogen.getGeneralProps().getProperty( "package.model" )+"."+form.getDaogen()+"Model";
						dtoBuffer.append( "private "+modelType+" model;\n" );
						dtoBuffer.append( makeGetter( "model" , modelType ) );
						dtoBuffer.append( makeSetter( "model" , modelType ) );
						formToDtoBuffer.append( "dto.setModel( this.getModel() );" );
					}
				}
		
				// operation handling
				StringBuffer facadeBody = new StringBuffer();
				StringBuffer jspBody = new StringBuffer();
				String operation = node.getOperation();
				boolean handle = false;
				if ( operation != null ) {
					if ( "ok-op".equalsIgnoreCase( operation ) ) {
						handle = true;
						facadeBody.append( "ResultBean result = ResultBean.newResult( \"OK\" );" );
					} else {
						handle = DaogenHandler.handleFacadeBody(node, facadeBody, jspBody, jspFormBody, operation, navMap, helper );	
					}
				}
				if ( !handle ) {	
					facadeBody.append( "ResultBean result = null;" );
				}
	
				if ( node.getJspInclude() != null ) {
					jspBody.append( "<jsp:include page=\"/struts/"+helper.moduleName+"/content/"+node.getJspInclude()+".jsp\" flush=\"true\"/>" );
				}
				
				String actionForm = "";
				if ( node.isFormValidate() ) {
					actionForm = " validate='"+node.isFormValidate()+"' ";
					if ( node.getFormInput() != null ) {
						actionForm+= "input='tiles."+helper.moduleName+"."+node.getFormInput()+"'";
					}
				} else {
					actionForm = " validate='false' ";
				}
				
				Object[] params = { 
									// parametri actions
									actionName ,						 // 0 -	
									helper.actionPackage,						 // 1 -
									// parametri jsp			
									"tiles."+helper.moduleName+"."+pageName,	// 2 - tiles definition name
									node.getJspTemplate(),     // 3 -
									helper.moduleName,							// 4 -
									pageName,							 // 5 -
									// navmap properties
									node.getDescription(),			     // 6 -
									node.getDescription(),				 // 7 -
									node.getDescription(),				 // 8 -
									formName,							 // 9 -
									String.valueOf( System.currentTimeMillis() )+"L",	// 10 -
									formParamName, 						 // 11 -
									helper.formPackage,					// 12 - 
									helper.dtoPackage,					// 13 -
									dtoName,							// 14 -
									helper.facadePackage,				// 15 -
									facadeName,							// 16 -			
									factoryName, 						//17 -
									linkBuff.toString(),				// 18 -
									baseName,							// 19 -
									facadeBody.toString(), 				// 20 -
									jspBody.toString(), 				// 21 -
									extendForm,							// 22 -
									dtoBuffer.toString(),				// 23 -
									formToDtoBuffer.toString(),			// 24 -
									actionForm,	// 25 - form input
									jspFormBody.toString(), 			// 26
									node.getAuth(),						// 27
									helper.appName,						// 28
								};
				
				BufferGen currentFacadeBuffer = helper.facadeList.get( facadeName );
				if ( currentFacadeBuffer == null ) {
					currentFacadeBuffer = new BufferGen();
					helper.facadeList.put( facadeName, currentFacadeBuffer );
					helper.facadeBuffer.append( CodeGenUtils.genText( templateResolver.getFacadeFactoryFun(), params ) );
				} else {
					
				}
				
				boolean overwrite = "true".equalsIgnoreCase( node.getOverwrite() );
				if ( overwrite ) {
					currentFacadeBuffer.generate = true;
				}
				
				// action
				if ( node.getForm() != null ) {
					CodeGenUtils.genFile( templateResolver.getAction(), params, new File( helper.dirOutputSrc , helper.actionsDir+"/"+actionName+".java" ), overwrite );
				} else {
					CodeGenUtils.genFile( templateResolver.getActionNoForm(), params, new File( helper.dirOutputSrc , helper.actionsDir+"/"+actionName+".java" ), overwrite );
				}
				// jsp
				CodeGenUtils.genFile( templateResolver.getPage(), params, new File( helper.dirOutputJsp , helper.jspDir+"/"+pageName+".jsp" ), overwrite );
				if ( jspFormBody.length() > 0 ) {
					CodeGenUtils.genFile( templateResolver.getJspForm(), params, new File( helper.dirOutputJsp , helper.jspFormDir+"/"+node.getForm().getName().toLowerCase()+"_form.jsp" ), overwrite );
				}
				// form
				if ( node.getForm() != null ) {
					CodeGenUtils.genFile( templateResolver.getForm(), params, new File( helper.dirOutputSrc , helper.formDir+"/"+formName+".java" ), overwrite );	
				}
				// dto
				CodeGenUtils.genFile( templateResolver.getDTO(), params, new File( helper.dirOutputSrc , helper.dtoDir+"/"+dtoName+".java" ), overwrite );
				// buffer
				
				helper.tilesBuffer.append( CodeGenUtils.genText( templateResolver.getTilesDef(), params ) );
				helper.navMap1Buffer.append( CodeGenUtils.genText( templateResolver.getNavmapProp(), params ) );
				helper.navMap2Buffer.append( CodeGenUtils.genText( templateResolver.getNavmapConf(), params ) );
				if ( node.getForm() != null ) {
					helper.struts1Buffer.append( CodeGenUtils.genText( templateResolver.getStrutsForm(), params ) );
				}
				if ( node.getForm() != null ) {
					helper.struts2Buffer.append( CodeGenUtils.genText( templateResolver.getStrutsActionForm(), params ) );	
				} else {
					helper.struts2Buffer.append( CodeGenUtils.genText( templateResolver.getStrutsAction(), params ) );
				}
				currentFacadeBuffer.buffer.append( CodeGenUtils.genText( templateResolver.getFacadeFun(), params ) );
				
				Element currentElement = helper.navTree.createElement( "nav-node" );
				currentElement.setAttribute( "nav-entry" , node.getName() );
				currentElement.setAttribute( "nav-module" , helper.moduleName );
				String menu1 = nvl( node.getMenu1(), helper.navMap.getGeneralProps().get( "menu-1-def" ) );
				String menu2 = nvl( node.getMenu1(), helper.navMap.getGeneralProps().get( "menu-2-def" ) );
				String menu3 = nvl( node.getMenu1(), helper.navMap.getGeneralProps().get( "menu-3-def" ) );
				currentElement.setAttribute( "menu-1" , menu1 );
				currentElement.setAttribute( "menu-2" , menu2 );
				currentElement.setAttribute( "menu-3" , menu3 );
				parent.appendChild( currentElement );
				// add facade name to facade list
				
				handleNode( node.getKids().iterator(), helper, currentElement, navMap );
		
			} catch (Exception e) {
				throw ( new Exception( "Error on node : "+node.getName(), e ) );
			}
			
		}
	}
	
	private static String nvl( String value1, String value2 ) {
		String res = value1;
		if ( value1 == null ) {
			res = value2;
		}
		return res;
	}
	
	private static void generate( NavMap navMap ) throws Exception {
		// main config

		templateResolver = new TemplateResolver( new File( navMap.getGeneralProps().get( "template-dir" ) ) );
		
		GenHelper helper = new GenHelper();
		
		helper.navMap = navMap;

		helper.appName = navMap.getGeneralProps().get( "app-name" );
		
		helper.dirOutput = new File( navMap.getGeneralProps().get( "output-dir" ) );
		helper.dirOutputSrc = new File( helper.dirOutput, navMap.getGeneralProps().get( "output-dir-src" ) );
		helper.dirOutputJsp = new File( helper.dirOutput, navMap.getGeneralProps().get( "output-dir-jsp" ) );
		helper.basePackage = navMap.getGeneralProps().get( "base-package" );
		helper.moduleName = navMap.getGeneralProps().get( "module-name" );

		helper.actionPackage = helper.basePackage+"."+helper.moduleName+".actions";
		helper.actionsDir = helper.actionPackage.replace( '.' , '/' );
		helper.formPackage = helper.basePackage+"."+helper.moduleName+".forms";
		helper.formDir = helper.formPackage.replace( '.' , '/' );
		helper.jspDir = "/struts/"+helper.moduleName+"/content";
		helper.jspFormDir = "/struts/"+helper.moduleName+"/include";
		helper.dtoPackage = helper.basePackage+"."+helper.moduleName+".dto";
		helper.dtoDir = helper.dtoPackage.replace( '.' , '/' );
		helper.facadePackage = helper.basePackage+"."+helper.moduleName+".facade";
		helper.facadeDir = helper.facadePackage.replace( '.' , '/' );	

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		helper.navTree = db.newDocument();
		Element navMapElement = helper.navTree.createElement( "nav-map" ); 
		
		
		// pre load
		String messagePropsFile = navMap.getGeneralProps().get( "output-file-stuts-message" );
		if (  messagePropsFile != null ) {
			File propsFile = new File( helper.dirOutput, messagePropsFile );
			System.out.println( "path : "+propsFile.getCanonicalPath() );
			FileIO.createFullFile( propsFile );
			FileInputStream fis = new FileInputStream( propsFile );
			helper.strutsMessageProps.load( fis );
			fis.close();
			BufferedReader br = new BufferedReader( new FileReader( propsFile ) );
			while ( br.ready() ) {
				helper.strutsMessageLines.add( br.readLine() );
			}
		}
		
		System.out.println( "actionPackage : "+helper.actionPackage );
		System.out.println( "actionsDir    : "+helper.actionsDir );
		System.out.println( "jspDir        : "+helper.jspDir );
		System.out.println( "formPackage   : "+helper.formPackage );
		System.out.println( "formDir       : "+helper.formDir );

		handleNode( navMap.getKids().iterator() , helper, navMapElement, navMap );
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DOMIO.writeDOMIndent( navMapElement , baos );

		// navmap properties
		String navMapProp = navMap.getGeneralProps().get( "output-file-navmap-props" );
		if ( navMapProp != null ) {
			File file = new File( helper.dirOutput, navMapProp );
			NavMapConfig.handleProps( file , helper.navMap1Buffer.toString() );
		} else {
			CodeGenUtils.writeFile( new File( helper.dirOutput, "navmap.properties" ), helper.navMap1Buffer.toString() );
		}
		
		// navmap config
		String navmapConfigFile = navMap.getGeneralProps().get( "output-file-navmap-conf" );
		if ( navmapConfigFile != null ) {
			File file = new File( helper.dirOutput, navmapConfigFile );
			String navmapProperties = navMap.getGeneralProps().get( "output-file-navmap-props" );
			Object[] paramNavMap = { navmapProperties.substring( 4 , navmapProperties.length()-(".properties".length()) ).replaceAll( "/" , "." ) , helper.moduleName };
			CodeGenUtils.createFile(file, "/org/morozko/java/mod/codegen/res/nav-map.xml", paramNavMap );
			NavMapConfig.handleNavMap( file , helper.navMap2Buffer.toString() , baos.toString() );
		} else {
			CodeGenUtils.writeFile( new File( helper.dirOutput, "nav-map.xml" ), helper.navMap2Buffer.toString()+baos.toString() );
		}
		
		// struts config
		String strutsConfigFile = navMap.getGeneralProps().get( "output-file-struts-config" );
		if ( strutsConfigFile != null ) {
			File file = new File( helper.dirOutput, strutsConfigFile );
			CodeGenUtils.createFile(file, "/org/morozko/java/mod/codegen/res/struts-config.xml" );
			StrutsConfig.handleStutsConfig( file , helper.struts1Buffer.toString() , helper.struts2Buffer.toString() );
		} else {
			CodeGenUtils.writeFile( new File( helper.dirOutput, "struts-form.xml" ), helper.struts1Buffer.toString() );
			CodeGenUtils.writeFile( new File( helper.dirOutput, "struts-action.xml" ), helper.struts2Buffer.toString() );
		}
		
		// tiles defs
		String tilesConfigFile = navMap.getGeneralProps().get( "output-file-tiles-defs" );
		if ( tilesConfigFile != null ) {
			File file = new File( helper.dirOutput, tilesConfigFile );
			CodeGenUtils.createFile(file, "/org/morozko/java/mod/codegen/res/tiles-defs.xml" );
			StrutsConfig.handleTileDefs( file , helper.tilesBuffer.toString() );
		} else {
			CodeGenUtils.writeFile( new File( helper.dirOutput, "tiles-"+helper.moduleName+"-def.xml" ), helper.tilesBuffer.toString() );
		}
		
		// facade
		Object[] paramFacade = { helper.facadeBuffer.toString(), upFirst( helper.moduleName ), helper.facadePackage };
		CodeGenUtils.genFile( templateResolver.getFacadeFactory(), paramFacade, new File( helper.dirOutputSrc , helper.facadeDir+"/"+upFirst( helper.moduleName )+"FacadeFactory.java" ), true );
		Iterator<String> itFacade = helper.facadeList.keySet().iterator();
		while ( itFacade.hasNext() ) {
			String facadeName = itFacade.next();
			BufferGen currentFacadeBuffer = helper.facadeList.get( facadeName );
			System.out.println( "facadeName : "+facadeName+" : generate : "+currentFacadeBuffer.generate );
			File currenteFacadeFile = new File( helper.dirOutputSrc , helper.facadeDir+"/"+facadeName+".java" );
			if ( currentFacadeBuffer.generate || !currenteFacadeFile.exists() ) {
				Object[] pcf = { helper.facadePackage, facadeName, currentFacadeBuffer.toString() };
				CodeGenUtils.genFile( templateResolver.getFacade(), pcf, currenteFacadeFile, true );				
			}
		}
		
		// ending setup
		// tlds
		SetupHandler.setupTlds( new File( helper.dirOutputJsp , "WEB-INF/" ) );
		
		
		// MESSAGE PROPS
		if ( messagePropsFile != null ) {
			StrutsConfig.handleMessageProps( new File( helper.dirOutput, messagePropsFile ) , helper.strutsMessageLines );
		}
		
	}
	
	
} 