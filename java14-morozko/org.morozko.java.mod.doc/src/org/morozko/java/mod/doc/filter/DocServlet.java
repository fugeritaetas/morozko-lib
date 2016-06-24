package org.morozko.java.mod.doc.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.log.helpers.LogObjectServlet;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.doc.config.DocConfig;
import org.morozko.java.mod.doc.config.DocServletConfig;
import org.morozko.java.mod.web.servlet.config.ConfigContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocServlet extends LogObjectServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3048440498921813465L;

	private DocRequestFacade configFacade;
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		this.configFacade = null;
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getLog().info( "TESTDOC" );
		if ( this.configFacade == null ) {
			this.configFacade = (DocRequestFacade)this.getServletContext().getAttribute( DocServletConfig.ATT_NAME_DOCFACADE );
		}
		this.configFacade.handleDoc(request, response);
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init( config );
		this.logInit( "start : "+DocConfig.VERSION );
		String skipInit = config.getInitParameter( "skip-init" );
		if ( "true".equalsIgnoreCase(skipInit) ) {
			this.logInit( "docservlet init skip "+skipInit );
		} else {
			try {
				String configPath = config.getInitParameter( "config" );
				File configFile = new File( config.getServletContext().getRealPath( "/" ), configPath );
				Document doc = DOMIO.loadDOMDoc( configFile );
				Element root = doc.getDocumentElement();
				this.configFacade = new DocRequestFacade();
				this.configFacade.configure( root , new ConfigContext( config.getServletContext() ) );
			} catch (Throwable t) {
				this.getLog().error( t );
			}			
		}		
		this.logInit( "end" );
	}	
	
}
