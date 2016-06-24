package org.morozko.java.mod.codegen;

import java.io.File;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.StreamIO;

public class TemplateResolver {

	public TemplateResolver( File templateDir ) {
		this.templateDir = templateDir;
	}
	
	private File templateDir;

	public File getTemplateDir() {
		return templateDir;
	}

	private String resolve( String template ) throws Exception {
		String result = null;
		File f = new File( this.getTemplateDir(), template );
		if ( f.exists() ) {
			result = FileIO.readString( f );
		} else {
			result = StreamIO.readString( TemplateResolver.class.getResourceAsStream( "/org/morozko/java/mod/codegen/res/template/"+template ) );
		}
		return result;
	}
	
	public String getFacadeFactoryFun() throws Exception {
		return resolve( "FacadeFactoryFun.def" );
	}
	
	public String getAction() throws Exception {
		return resolve( "Action.def" );
	}
	
	public String getActionNoForm() throws Exception {
		return resolve( "ActionNoForm.def" );
	}
	
	public String getForm() throws Exception {
		return resolve( "Form.def" );
	}
	
	public String getPage() throws Exception {
		return resolve( "page.def" );
	}

	public String getJspForm() throws Exception {
		return resolve( "jspform.def" );
	}

	
	public String getDTO() throws Exception {
		return resolve( "DTO.def" );
	}
	
	public String getNavmapProp() throws Exception {
		return resolve( "navmap-prop.def" );
	}
	
	public String getNavmapConf() throws Exception {
		return resolve( "navmap-conf.def" );
	}	
	
	public String getStrutsForm() throws Exception {
		return resolve( "struts-form.def" );
	}	
	
	public String getStrutsAction() throws Exception {
		return resolve( "struts-action.def" );
	}	
	
	public String getStrutsActionForm() throws Exception {
		return resolve( "struts-action-form.def" );
	}		
	
	public String getTilesDef() throws Exception {
		return resolve( "tiles.def" );
	}		
	
	public String getFacadeFun() throws Exception {
		return resolve( "FacadeFun.def" );
	}
	
	public String getFacade() throws Exception {
		return resolve( "Facade.def" );
	}	

	public String getFacadeFactory() throws Exception {
		return resolve( "FacadeFactory.def" );
	}		
	
}
