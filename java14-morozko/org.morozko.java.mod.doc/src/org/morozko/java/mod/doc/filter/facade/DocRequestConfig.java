package org.morozko.java.mod.doc.filter.facade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.ent.tld.html.HtmlTagUtils;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.text.DefaultTextFilter;
import org.morozko.java.core.text.TextFilter;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.doc.DocHelper;
import org.morozko.java.mod.doc.filter.DefaultDocHandler;
import org.morozko.java.mod.doc.filter.DocHandler;
import org.morozko.java.mod.doc.filter.DocRequestFacade;
import org.morozko.java.mod.doc.filter.DocTypeHandler;
import org.morozko.java.mod.doc.itext.ITextDocHandler;
import org.morozko.java.mod.web.servlet.config.ConfigContext;
import org.w3c.dom.Element;

public class DocRequestConfig extends BasicLogObject {

private ConfigContext context;

	public HashMap getTypeHandlerMap() {
		return typeHandlerMap;
	}

	public boolean isSkipFilter() {
		return skipFilter;
	}

	public String getProcessingPage() {
		return processingPage;
	}
	
	public String getErrorManager() {
		return errorManager;
	}

	private void addTypeHandlers( SearchDOM searchDOM, Element rootTag ) {
		List typeHandlerTagList = searchDOM.findAllTags( rootTag , "type-handler" );
		Iterator typeHandlerTagIt = typeHandlerTagList.iterator();
		while ( typeHandlerTagIt.hasNext() ) {
			Element docHandlerTag = (Element) typeHandlerTagIt.next();
			Properties atts = DOMUtils.attributesToProperties( docHandlerTag );
			String name = atts.getProperty( "name" );
			String type = atts.getProperty( "type" );
			this.getLog().info( "TypeHandler : "+name+" -> "+type );
			try {
				DocTypeHandler typeHandler = (DocTypeHandler)ClassHelper.newInstance( type );
				typeHandler.init( docHandlerTag );
				this.typeHandlerMap.put( name , typeHandler );
			} catch (Throwable t1) {
				this.getLog().warn( "Error on type handler init : "+name+" : "+type, t1 );
			}
		}
	}

	public void configure( Element root, ConfigContext context ) {
		this.docHandlerMap = new HashMap();
		Properties defMime = new Properties();
		try {
			this.context = context;
			this.typeHandlerMap = new HashMap();
			SearchDOM searchDOM = SearchDOM.newInstance( true , true );
			Element generalConfigTag = searchDOM.findTag( root , "general-config" );
			Properties generalConfigAtts = DOMUtils.attributesToProperties( generalConfigTag );
			this.jspPath = generalConfigAtts.getProperty( "jsp-path" );
			this.outMode = generalConfigAtts.getProperty( "out-mode" );
			this.debug = Boolean.valueOf( generalConfigAtts.getProperty( "debug" ) ).booleanValue();
			this.debug = Boolean.valueOf( generalConfigAtts.getProperty( "skip-filter" ) ).booleanValue();
			this.processingPage = generalConfigAtts.getProperty( "processing-page" );
			this.errorManager = generalConfigAtts.getProperty( "error-manager" );
			this.getLog().info( "jsp-path : "+this.jspPath );
			this.getLog().info( "debug    : "+this.debug );
			this.getLog().info( "processing-page    : "+this.processingPage );
			// doc helper
			String docHelperType = generalConfigAtts.getProperty( "helper-type" );
			this.getLog().info( "docHelperType    : "+docHelperType );
			if ( docHelperType != null ) {
				try {
					this.docHelper = (DocHelper)ClassHelper.newInstance( docHelperType );
				} catch (Exception e2) {
					this.getLog().info( "failed to create : "+e2 );
					this.docHelper = DocHelper.DEFAULT;
				}
			} else {
				this.docHelper = DocHelper.DEFAULT;
			}
			this.getLog().info( "docHelper    : "+this.docHelper );
			
			// filtro 
			String skipFilterAtt = generalConfigAtts.getProperty( "skip-filter" );
			this.skipFilter = "true".equalsIgnoreCase( skipFilterAtt );
			String filterName = generalConfigAtts.getProperty( "filter" );
			this.getLog().info( "filterName    : "+filterName );
			if ( filterName != null ) {
				this.filter = HtmlTagUtils.getFilter( context.getContext() , filterName );
			} else {
				this.filter = new DefaultTextFilter();
			}
			this.getLog().info( "filter      : "+this.filter );
			this.getLog().info( "skip-filter : "+this.skipFilter );
			
			// itext font
			List itextFontTagList = searchDOM.findAllTags( root , "itext-font" );
			Iterator itextFontTagIt = itextFontTagList.iterator();
			while ( itextFontTagIt.hasNext() ) {
				Element itextFontTag = (Element) itextFontTagIt.next();
				Properties itextFontAtts = DOMUtils.attributesToProperties( itextFontTag );
				String name = itextFontAtts.getProperty( "name" );
				String path = itextFontAtts.getProperty( "path" );
				this.getLog().info( "ITextFont : "+name+" -> "+path );
				ITextDocHandler.registerFont(name, path);
			}
			// type handlers
			try {
				this.addTypeHandlers(searchDOM, DOMIO.loadDOMDoc( DocRequestFacade.class.getResourceAsStream("/org/morozko/java/mod/doc/res/type-handler-default.xml") ).getDocumentElement() );
			} catch (Exception e1) {
				this.getLog().warn( e1 );
			}
			this.addTypeHandlers(searchDOM, root);
			// doc handlers
			List docHandlerTagList = searchDOM.findAllTags( root , "doc-handler" );
			Iterator docHandlerTagIt = docHandlerTagList.iterator();
			while ( docHandlerTagIt.hasNext() ) {
				Element docHandlerTag = (Element) docHandlerTagIt.next();
				Properties atts = DOMUtils.attributesToProperties( docHandlerTag );
				String name = atts.getProperty( "name" );
				String type = atts.getProperty( "type" );
				String mode = atts.getProperty( "mode" );
				if ( mode == null || mode.equalsIgnoreCase( "" ) ) {
					mode = DocHandler.MODE_JSP;
				}
				String useJsp = atts.getProperty( "use-jsp", "true" );
				String jsp = atts.getProperty( "jsp", name );
				this.getLog().info( "DocHandler : "+name+" -> "+type );
				DocHandler docHandler = null;
				if ( type != null ) {
					docHandler = (DocHandler)ClassHelper.newInstance( type );
				} else {
					docHandler = new DefaultDocHandler();
				}
				docHandler.init( docHandlerTag );
				docHandler.setJsp( jsp );
				docHandler.setUseJsp( "true".equals( useJsp ) );
				docHandler.setMode( mode );
				this.docHandlerMap.put( name , docHandler );
			}
			try {
				Element mimeTag = searchDOM.findTag( root , "mime-map" );
				if ( mimeTag != null ) {
					this.mime = new Properties( defMime );
					this.mime.putAll( DOMUtils.attributesToProperties( mimeTag ) );
					this.getLog().info( "override mime : "+this.mime );
				} else {
					this.mime = defMime;
					this.getLog().info( "default mime : "+this.mime );
				}
			} catch (Exception e1) {
				this.getLog().warn( "error setting mime types", e1 );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ConfigContext getContext() {
		return context;
	}

	public void setContext(ConfigContext context) {
		this.context = context;
	}

	private HashMap docHandlerMap;
	
	public HashMap getDocHandlerMap() {
		return docHandlerMap;
	}

	public void setDocHandlerMap(HashMap docHandlerMap) {
		this.docHandlerMap = docHandlerMap;
	}
	
	private String jspPath;
	
	private boolean skipFilter;
	
	private boolean debug;
	
	private TextFilter filter;
	
	private Properties mime;
	
	private DocHelper docHelper;
	
	private String outMode;
	
	private String processingPage;

	private HashMap typeHandlerMap;
	
	private String errorManager;
	
	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public TextFilter getFilter() {
		return filter;
	}

	public void setFilter(TextFilter filter) {
		this.filter = filter;
	}

	public Properties getMime() {
		return mime;
	}

	public void setMime(Properties mime) {
		this.mime = mime;
	}

	public DocHelper getDocHelper() {
		return docHelper;
	}

	public void setDocHelper(DocHelper docHelper) {
		this.docHelper = docHelper;
	}


	public String getOutMode() {
		return outMode;
	}

	public void setOutMode(String outMode) {
		this.outMode = outMode;
	}
	
}
