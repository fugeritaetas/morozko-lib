package org.fugerit.java.core.web.servlet.filter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class HtmlTagFilter extends HttpFilter {
	
	private ServletContext context;
	
	public void destroy() {
		this.context = null;
	}

	public void doFilter( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws IOException, ServletException {
		chain.doFilter( request , response );
	}

	public void init(FilterConfig config) throws ServletException {
		this.getLogger().info( "init start" );
		this.context = config.getServletContext();
		try {
			String configPath = config.getInitParameter( "config" );
			File configFile = new File( this.context.getRealPath( "/" ), configPath );
			Document doc = DOMIO.loadDOMDoc( configFile );
			Element root = doc.getDocumentElement();
			SearchDOM searchDOM = SearchDOM.newInstance( true , true );
			// bundle list
			Element bundleListTag = searchDOM.findTag( root , "bundle-list" );
			if ( bundleListTag != null ) {
				List bundleList = searchDOM.findAllTags( bundleListTag , "bundle" );
				Iterator bundleIt = bundleList.iterator();
				while ( bundleIt.hasNext() ) {
					Element currentBundleTag = (Element)bundleIt.next();
					Properties atts = DOMUtils.attributesToProperties( currentBundleTag );
					String name = atts.getProperty( "name", "DEFAULT" );
					String path = atts.getProperty( "path" );
					this.getLogger().info( "bundle name : "+name );
					this.getLogger().info( "bundle path : "+path );
					try {
						ResourceBundle bundle = ResourceBundle.getBundle( path, Locale.getDefault(), ClassHelper.getDefaultClassLoader() );
						this.context.setAttribute( "HtmlTagFilter.bundle."+name, bundle );	
					} catch ( Exception e ) {
						this.getLogger().error( "Failed bundle setup", e );
					}
				}
			}
			// filter list
			Element filterListTag = searchDOM.findTag( root , "filter-list" );
			if ( filterListTag != null ) {
				List filterList = searchDOM.findAllTags( filterListTag , "filter" );
				Iterator filterIt = filterList.iterator();
				while ( filterIt.hasNext() ) {
					Element currentFilterTag = (Element)filterIt.next();
					Properties atts = DOMUtils.attributesToProperties( currentFilterTag );
					String name = atts.getProperty( "name" );
					String type = atts.getProperty( "type" );
					this.getLogger().info( "filter name : "+name );
					this.getLogger().info( "filter type : "+type );
					try {
						//TODO: review
						//TextFilter filter = (TextFilter)ClassHelper.newInstance( type );
						//this.context.setAttribute( "HtmlTagFilter.filter."+name, filter );	
					} catch ( Exception e ) {
						this.getLogger().error( "Failed bundle setup", e );
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getLogger().info( "init end" );
	}

}
