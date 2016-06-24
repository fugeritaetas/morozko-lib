package org.morozko.java.mod.web.servlet;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigurableObject;
import org.morozko.java.core.ent.log.helpers.LogObjectServlet;
import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.web.servlet.config.BasicConfig;
import org.morozko.java.mod.web.servlet.config.CommandConfig;
import org.morozko.java.mod.web.servlet.config.ConfigContext;
import org.morozko.java.mod.web.servlet.config.ModuleConfig;
import org.morozko.java.mod.web.servlet.config.StatusConfig;
import org.morozko.java.mod.web.servlet.config.VersionConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigServlet extends LogObjectServlet {
	
	public static final String OPERATION_INITLOG = "initlog";
	
	public static final String LOAD_TIME = String.valueOf( new java.sql.Timestamp( System.currentTimeMillis() ) );
	
	private VersionConfig versionConfig = null;
	
	private CommandConfig commandConfig = null;
	
	private ModuleConfig moduleConfig = null;
	
	private StatusConfig statusConfig = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if ( req.getRequestURI().indexOf( "/"+ModuleConfig.OPERATION_RELOAD ) != -1 && this.moduleConfig != null ) {
				this.moduleConfig.renderModule( req, resp );
			} else if ( req.getRequestURI().indexOf( "/"+VersionConfig.OPERATION_VERSION ) != -1 && this.versionConfig != null ) {
				this.versionConfig.renderVersion( req, resp );
			} else if ( req.getRequestURI().indexOf( "/"+StatusConfig.OPERATION ) != -1 && this.statusConfig != null ) {
				this.statusConfig.render( req, resp );
			} else if ( req.getRequestURI().indexOf( "/"+CommandConfig.OPERATION_COMMAND ) != -1 && this.commandConfig != null ) {
				int index = req.getRequestURI().indexOf( "/"+CommandConfig.OPERATION_COMMAND );
				String command = req.getRequestURI().substring( index+2+CommandConfig.OPERATION_COMMAND.length() );
				String[] params = new String[0];
				this.getLog().info( "command : '"+command+"'" );
				this.commandConfig.execute( command, this.getServletContext(), req, resp, params );	
			} else {
				resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			}
		} catch (Exception e) {
			this.getLog().error( "Config servler error"+e , e );
			resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
		}
	}

	private static SearchDOM searchDOM = SearchDOM.newInstance( true, true );
	
	private ConfigContext configContext;
	
	public static File resolvePath( String path, ServletContext context ) throws FileNotFoundException {
		File file = null;
		file = new File( path );
		if ( !file.exists() ) {
			file = new File( context.getRealPath( "/" ), path );
			if ( !file.exists() ) {
				throw ( new FileNotFoundException( path ) );
			}
		}
		return file;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567054666335688813L;

	public static boolean checkSecret( ServletContext context, String secret ) {
		boolean ok = false;
		if ( secret != null && secret.equalsIgnoreCase( (String)context.getAttribute( SECRET_ATT ) ) ) {
			ok = true;
		}
		return ok;
	}
	
	private static final String SECRET_ATT = ConfigServlet.class.getName()+"_ATT_NAME";
	
	public void init(ServletConfig config) throws ServletException {
		super.init( config );
		String configFilePath = config.getInitParameter( "config-file-path" );
		this.configContext = new ConfigContext( config.getServletContext() );
		this.log( "ConfigServlet v 0.2.0 2011-10-01" );
		logProp( "reading configuration", configFilePath ); 
		this.moduleConfig = new ModuleConfig();
		this.moduleConfig.setConfigContext( this.configContext );
		this.statusConfig = new StatusConfig();
		this.statusConfig.setConfigContext( this.configContext );
		try {
			File configFile = resolvePath( configFilePath , config.getServletContext() );
			logProp( "configuration read", configFile.getCanonicalPath() );
			Document doc = DOMIO.loadDOMDoc( configFile );
			Element root = doc.getDocumentElement();
			try {
				String secretFile = root.getAttribute( "secret-file" );
				String secret = FileIO.readString( secretFile );
				if ( secret != null && secret.trim().length() > 0 ) {
					config.getServletContext().setAttribute( SECRET_ATT , secret );
				}
				
			} catch (Exception e) {
				this.getLog().error( "Error setting secret file", e );
			}
			
			// module configuration
			Element moduleConfigListTag = searchDOM.findTag( root , "module-config-list" );
			List moduleConfigList = searchDOM.findAllTags( moduleConfigListTag , "module-config" );
			Iterator moduleConfigIt = moduleConfigList.iterator();
			List initLog = new ArrayList();
			while ( moduleConfigIt.hasNext() ) {
				String name = null;
				String type = null;
				String logMessage = null;
				try {
					Element moduleTag = (Element)moduleConfigIt.next();
					Properties moduleAtts = DOMUtils.attributesToProperties( moduleTag );
					name = moduleAtts.getProperty( "name" );
					type = moduleAtts.getProperty( "type" );
					logProp( "configuring module", name );
					logProp( "module class", type );
					ConfigurableObject co = (ConfigurableObject)ClassHelper.newInstance( type );
					if ( co instanceof BasicConfig ) {
						BasicConfig bc = (BasicConfig)co;
						bc.setConfigContext( configContext );
						if ( co instanceof VersionConfig ) {
							this.versionConfig = (VersionConfig) co;
							this.versionConfig.setInitLog( initLog );
						} else if ( co instanceof CommandConfig ) {
							this.commandConfig = (CommandConfig) co;
						}
						this.moduleConfig.addModule( name , bc , moduleTag );
					}
					co.configure( moduleTag );
					logProp( "module configured [OK]", name );
					logMessage = "[OK] configured";
					
				} catch ( Throwable t ) {
					log( "module configuration failed [KO] "+t );
					logMessage = "[KO] "+t;
				}
				initLog.add( "module:"+name+" type:"+type+" "+logMessage );
			}
		} catch (Exception e) {
			throw ( new ServletException( e ) );
		}
	}

	public void logProp( String prop, String value ) {
		log( prop+" : "+value );
	}
	
	public void log( String message ) {
		System.out.println( "morozko.ConfigServlet[INFO] "+message );
	}
	
}
