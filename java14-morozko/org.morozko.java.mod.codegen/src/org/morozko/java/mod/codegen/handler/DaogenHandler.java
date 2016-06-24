package org.morozko.java.mod.codegen.handler;

import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.codegen.GenHelper;
import org.morozko.java.mod.codegen.NavMap;
import org.morozko.java.mod.codegen.NavNode;
import org.morozko.java.mod.daogen.gen.coder.Coder;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
import org.morozko.java.mod.daogen.gen.config.LoadConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

public class DaogenHandler extends Coder {

	private static String tranformFieldName( String field ) {
		return upFirst( field.replaceAll( "_" , " " ) );
	}
	
	private static void handleProperties( DGConfig daogen, String table, GenHelper helper ) {
		TableConfig tc = daogen.getTable( table );
		List<FieldConfig> fc = tc.getFields();
		String commetTable = "# message properties for table : "+table;
		if ( !helper.strutsMessageLines.contains( commetTable ) ) {
			helper.strutsMessageLines.add( commetTable );	
		}
		for ( int k=0; k<fc.size(); k++ ) {
			FieldConfig field = fc.get( k );
			String propName = "message."+helper.moduleName+"."+table+"."+field.getJavaFieldName();
			if ( !helper.strutsMessageProps.containsKey( propName ) ) {
				helper.strutsMessageLines.add( propName+"="+tranformFieldName( field.getFieldName() ) );
				helper.strutsMessageProps.setProperty( propName , "" );
			}
		}
	}
	
	public static boolean handleFacadeBody( NavNode node, StringBuffer facadeBody, StringBuffer jspBody, StringBuffer jspFormBuffer, String operation, NavMap navMap, GenHelper helper ) {
		boolean handle = false;
		// load one
		if ( operation.indexOf( "daogen-delete-one" ) == 0 ) {
			String table = operation.split( ":" )[1];
			DGConfig daogen = navMap.getDaogen( table );
			// facade body
			String daoPack = daogen.getGeneralProps().getProperty( "package.dao" );
			String daoFactoryType = daoPack+"."+daogen.getGeneralProps().getProperty( "factory.dao.module" );
			TableConfig tc = daogen.getTable( table );
			facadeBody.append( daoFactoryType+" daoFactory = "+daoFactoryType+".getInstance();\n");
			facadeBody.append( "\t\t"+daoPack+"."+table+"DAO dao = daoFactory.get"+table+"DAO();\n" );
			facadeBody.append( "\t\t"+"int updateResult = dao.deleteByPk( org.morozko.java.mod.db.dao.DAOID.valueOf( paramMap.getParam( \""+fieldName( tc.getUpdateKey()[0] )+"\" ) ) );\n" );
			facadeBody.append( "\t\t"+"ResultBean result = ResultBean.newResult( Boolean.valueOf( updateResult>0 ) );" );
			handle = true;
		}					
		// load one
		if ( operation.indexOf( "daogen-load-one" ) == 0 ) {
			String table = operation.split( ":" )[1];
			DGConfig daogen = navMap.getDaogen( table );
			TableConfig tc = daogen.getTable( table );
			String loadName = operation.split( ":" )[2];
			LogFacade.getLog().info( "loadName : "+loadName );
			LoadConfig lc = tc.getLoad( loadName );
			// facade body
			String daoPack = daogen.getGeneralProps().getProperty( "package.dao" );
			String daoFactoryType = daoPack+"."+daogen.getGeneralProps().getProperty( "factory.dao.module" );
			String modelName = daogen.getGeneralProps().getProperty( "package.model" )+"."+table+"Model";
			facadeBody.append( daoFactoryType+" daoFactory = "+daoFactoryType+".getInstance();\n");
			facadeBody.append( "\t\t"+daoPack+"."+table+"DAO dao = daoFactory.get"+table+"DAO();\n" );
			List<String> lfc = lc.getFieldList();
			facadeBody.append( "\t\t"+modelName+" model = dao.load"+upFirst( lc.getType() )+lc.getName()+"( org.morozko.java.mod.db.dao.DAOID.valueOf( paramMap.getParam( \""+fieldName( lfc.get( 0 ) )+"\" ) ) );\n" );
//			for ( int k=0; k<lfc.size(); k++ ) {
//				String f = lfc.get( k );
//				if ( k != 0 ) {
//					facadeBody.append( ", "  );
//				}
//				facadeBody.append( "model.get"+upFirst( methodName( f ) )+"()" );	
//			}
//			facadeBody.append( ");\n"  );
			facadeBody.append( "\t\t"+"ResultBean result = ResultBean.newResult( model );" );
			handle = true;
		}			
		// update one
		if ( operation.indexOf( "daogen-update-one" ) == 0 ) {
			String table = operation.split( ":" )[1];
			DGConfig daogen = navMap.getDaogen( table );
			// facade body
			String daoPack = daogen.getGeneralProps().getProperty( "package.dao" );
			String daoFactoryType = daoPack+"."+daogen.getGeneralProps().getProperty( "factory.dao.module" );
			facadeBody.append( daoFactoryType+" daoFactory = "+daoFactoryType+".getInstance();\n");
			facadeBody.append( "\t\t"+daoPack+"."+table+"DAO dao = daoFactory.get"+table+"DAO();\n" );
			facadeBody.append( "\t\t"+"int updateResult = dao.update( dto.getModel() );\n" );
			facadeBody.append( "\t\t"+"ResultBean result = null;\n" );
			facadeBody.append( "\t\t"+"if ( updateResult > 0 ) {\n" );
			facadeBody.append( "\t\t\t"+"result = ResultBean.newResult( dto.getModel() );\n" );
			facadeBody.append( "\t\t"+"} else {\n" );
			facadeBody.append( "\t\t\t"+"throw new Exception( \"Operation failed\" );\n" );
			facadeBody.append( "\t\t"+"}" );
			// properties
			handleProperties(daogen, table, helper);
			// jsp body
			TableConfig tc = daogen.getTable( table );
			List<FieldConfig> fc = tc.getFields();
			jspBody.append( "<logic:present name=\"result\">\n" );
			jspBody.append( "<c:set var=\"form"+node.getForm().getName()+"\" scope=\"session\" value=\"${result.bean}\"/>\n" );
			jspBody.append( "</logic:present>\n" );
			jspBody.append( "<div class=\"formData\">\n" );
			jspBody.append( "\t<html:form method=\"post\" action=\"/"+node.getName()+"\">\n" );
			for ( int k=0; k<fc.size(); k++ ) {
				FieldConfig field = fc.get( k );
				jspFormBuffer.append( "\t\t<p class=\"formField\">\n" +
								"\t\t\t<bean:message bundle=\""+helper.moduleName+"\" key=\""+"message."+helper.moduleName+"."+table+"."+field.getJavaFieldName()+"\"/>\n" +
								"\t\t\t<html:text property=\""+field.getJavaFieldName()+"\" size=\"64\" maxlength=\"64\"/>\n"+
								"\t\t</p>\n" );
			}
			jspBody.append( "\t\t<jsp:include page=\"/struts/"+helper.moduleName+"/include/"+node.getForm().getName().toLowerCase()+"_form.jsp\"/>\n" );
			jspBody.append( "\t\t<html:submit property=\"save\" value=\"Modifica\"/>\n" );
			jspBody.append( "\t</html:form>\n" );
			jspBody.append( "</div>\n" );
			handle = true;
		}
		// insert one
		if ( operation.indexOf( "daogen-insert-one" ) == 0 ) {
			String table = operation.split( ":" )[1];
			DGConfig daogen = navMap.getDaogen( table );
			// facade body
			String daoPack = daogen.getGeneralProps().getProperty( "package.dao" );
			String daoFactoryType = daoPack+"."+daogen.getGeneralProps().getProperty( "factory.dao.module" );
			facadeBody.append( daoFactoryType+" daoFactory = "+daoFactoryType+".getInstance();\n");
			facadeBody.append( "\t\t"+daoPack+"."+table+"DAO dao = daoFactory.get"+table+"DAO();\n" );
			facadeBody.append( "\t\t"+"int updateResult = dao.insert( dto.getModel() );\n" );
			facadeBody.append( "\t\t"+"ResultBean result = null;\n" );
			facadeBody.append( "\t\t"+"if ( updateResult > 0 ) {\n" );
			facadeBody.append( "\t\t\t"+"result = ResultBean.newResult( dto.getModel() );\n" );
			facadeBody.append( "\t\t"+"} else {\n" );
			facadeBody.append( "\t\t\t"+"throw new Exception( \"Operation failed\" );\n" );
			facadeBody.append( "\t\t"+"}" );
			// properties
			handleProperties(daogen, table, helper);
			// jsp body
			TableConfig tc = daogen.getTable( table );
			List<FieldConfig> fc = tc.getFields();
			jspBody.append( "<div class=\"formData\">\n" );
			jspBody.append( "\t<html:form method=\"post\" action=\"/"+node.getName()+"\">\n" );
			for ( int k=0; k<fc.size(); k++ ) {
				FieldConfig field = fc.get( k );
				jspFormBuffer.append( "\t\t<p class=\"formField\">\n" +
								"\t\t\t<bean:message bundle=\""+helper.moduleName+"\" key=\""+"message."+helper.moduleName+"."+table+"."+field.getJavaFieldName()+"\"/>\n" +
								"\t\t\t<html:text property=\""+field.getJavaFieldName()+"\" size=\"64\" maxlength=\"64\"/>\n"+
								"\t\t</p>\n" );
			}
			jspBody.append( "\t\t<jsp:include page=\"/struts/"+helper.moduleName+"/include/"+node.getForm().getName().toLowerCase()+"_form.jsp\"/>\n" );
			jspBody.append( "\t\t<html:submit property=\"save\" value=\"Crea\"/>\n" );
			jspBody.append( "\t</html:form>\n" );
			jspBody.append( "</div>\n" );
			handle = true;
		}				
		// load all
		if ( operation.indexOf( "daogen-load-all" ) == 0 ) {
			String table = operation.split( ":" )[1];
			DGConfig daogen = navMap.getDaogen( table );
			// facade body
			System.out.println( "PROPS : "+daogen );
			String daoPack = daogen.getGeneralProps().getProperty( "package.dao" );
			String daoFactoryType = daoPack+"."+daogen.getGeneralProps().getProperty( "factory.dao.module" );
			facadeBody.append( daoFactoryType+" daoFactory = "+daoFactoryType+".getInstance();\n");
			facadeBody.append( "\t\t"+daoPack+"."+table+"DAO dao = daoFactory.get"+table+"DAO();\n" );
			facadeBody.append( "\t\t"+"org.morozko.java.core.util.result.PagedResult pagedResult = dao.loadAllPaged( Integer.parseInt( paramMap.getParam( \"perPage\", \"10\" ) ), Integer.parseInt( paramMap.getParam( \"loadPage\", \"1\" ) ) );\n" );
			facadeBody.append( "\t\t"+"ResultBean result = ResultBean.newResult( pagedResult );" );
			// properties
			handleProperties(daogen, table, helper);
			// jsp body
			jspBody.append( "<div class=\"formSubmit\"><navmap:link navEntry=\""+node.getName()+"_ins\" navModule=\""+helper.moduleName+"\" styleClass=\"formSubmit\"/></div>\n\n" );
			jspBody.append( "<table class=\"tableData\">\n" );
			jspBody.append( "\t<tr>\n" );
			TableConfig tc = daogen.getTable( table );
			List<FieldConfig> fc = tc.getFields();
			jspBody.append( "\t\t<th></th>\n" );
			for ( int k=0; k<fc.size(); k++ ) {
				FieldConfig field = fc.get( k );
				jspBody.append( "\t\t<th><bean:message bundle=\""+helper.moduleName+"\" key=\""+"message."+helper.moduleName+"."+table+"."+field.getJavaFieldName()+"\"/></th>\n" );
			}
			jspBody.append( "\t\t<th></th>\n" );
			jspBody.append( "\t</tr>\n" );
			jspBody.append( "\t<c:forEach var=\"current"+table+"Model\" items=\"${result.pageElements}\">\n" );
			jspBody.append( "\t\t<c:set var=\"current"+table+"Bean\" value=\"${current"+table+"Model.bean}\"/>\n" );
			jspBody.append( "\t<tr>\n" );
			jspBody.append( "\t\t<td><html:link action=\""+node.getName()+"_mod\" paramId=\""+fc.get( 0 ).getJavaFieldName()+"\" paramName=\"current"+table+"Bean\" paramProperty=\""+fc.get( 0 ).getJavaFieldName()+"\"><mhtml:img border=\"0\" src=\"/html/img/update.gif\" webapp=\"true\"/></html:link></td>\n" );
			for ( int k=0; k<fc.size(); k++ ) {
				FieldConfig field = fc.get( k );
				jspBody.append( "\t\t<td>${current"+table+"Bean."+field.getJavaFieldName()+"}</td>\n" );
			}
			jspBody.append( "\t\t<td><html:link action=\""+node.getName()+"_del\" paramId=\""+fc.get( 0 ).getJavaFieldName()+"\" paramName=\"current"+table+"Bean\" paramProperty=\""+fc.get( 0 ).getJavaFieldName()+"\"><mhtml:img border=\"0\" src=\"/html/img/delete.gif\" webapp=\"true\"/></html:link></td>\n" );
			jspBody.append( "\t</tr>\n" );
			jspBody.append( "\t</c:forEach>\n" );
			jspBody.append( "</table>\n" );
			jspBody.append( "<jsp:include page='/struts/tiles/include/paging.jsp'/>\n" );
			handle = true;
		}
		return handle;
	}
	
}
