/*
 * @(#)JspDataFilter.java
 *
 * @project    : serviceapp
 * @package    : net.jsomnium.jlib.mod.web.filter
 * @creation   : 12/lug/07
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.doc.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.filter.HttpFilter;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.doc.config.DocConfig;
import org.morozko.java.mod.web.servlet.config.ConfigContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 * @deprecated this api will be deprecated soon, as Filter infrastructure doesn't fit all the needs, use DocServlet intestad.
 *
 */
public class DocFilter extends HttpFilter {
	
	private DocRequestFacade configFacade;
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		this.configFacade = null;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		this.configFacade.handleDoc(request, response);
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.getLog().info( "init start : "+DocConfig.VERSION );
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
		this.getLog().info( "init end" );
	}

}

