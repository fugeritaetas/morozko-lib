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
package org.morozko.java.mod.navmap.tld.helpers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.servlet.http.ParameterUtils;
import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.mod.navmap.NavEntry;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavMenuSection;
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.auth.AuthException;
import org.morozko.java.mod.navmap.auth.AuthHandler;
import org.morozko.java.mod.navmap.facade.NavArgs;
import org.morozko.java.mod.navmap.servlet.NavController;
import org.morozko.java.mod.navmap.servlet.NavFilter;
import org.morozko.java.mod.web.servlet.config.SessionContext;

public class NavEntryTagSupportHelper extends TagSupportHelper {

	protected String createRealLink( NavNode navNode, boolean params ) throws JspException {
		String href = this.createRealLink( navNode.getNavEntry() );
		TagLogger.log.debug( "createRealLink - navNode params : "+params );
		if ( params ) {
			SessionContext sessionContext = SessionContext.getHttpSessionContext( (HttpServletRequest)this.pageContext.getRequest() );
			Map paramNodeMap = (Map)sessionContext.getAttribute( NavController.ATT_NAV_PARAM_HANDLER_MAP );
			ParamMap paramMap = (ParamMap)paramNodeMap.get( navNode.getNavEntry().getUrl() );
			if ( paramMap != null ) {
				TagLogger.log.debug( "createRealLink - navNode exclude params : "+this.getExcludeParams() );
				if ( this.getExcludeParams() == null ) {
					href+= paramMap.getQueryString(  );	
				} else {
					String ep[] = this.getExcludeParams().split( ";" );
					href+= paramMap.getQueryString( ep );	
				}
			}
//			List paramHandler = (List)sessionContext.getAttribute( NavController.ATT_NAV_PARAM_HANDLER , new ArrayList() );
//			TagLogger.log.debug( "createRealLink - navNode params : "+paramHandler.size()+" : "+navNode.getDepth()+" : "+paramHandler );
//			if ( navNode.getDepth().intValue() < paramHandler.size() ) {
//				ParamMap paramMap = (ParamMap)paramHandler.get( navNode.getDepth().intValue() );
//				href+= paramMap.getQueryString();				
//			}
		}
		return href;
	}
	
	protected String createRealLink( NavEntry navEntry ) throws JspException {
		String href = null;
		TagLogger.log.debug( "createRealLink - navEntry absolute : "+navEntry.getAbsolute() );
		if ( navEntry.getAbsolute().booleanValue() ) {
			href = navEntry.getLink();
		} else {
			href = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath()+"/"+navEntry.getUrl()+".do";
			try {
				href+= ParameterUtils.encodeParameterString( navEntry.getParameters() );
			} catch (Exception e) {
				throw ( new JspException( e ) );
			}
		}
		TagLogger.log.debug( "createRealLink - href result       : "+href );
		return href;
	}
	
	protected NavMap getNavMap() {
		return (NavMap)this.pageContext.getServletContext().getAttribute( NavFilter.ATT_NAME_NAVMAP );
	}
	
	protected NavNode findNavNode() throws JspException {
		NavEntry ne = this.findNavEntry();
		return this.getNavMap().getNavNode( ne.getModule(), ne.getName() );
	}
	
	protected void println( String line ) {
		try {
			this.pageContext.getOut().println( line );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Classe per la creazione del tag personalizzato navmap
	 */
	private static final long serialVersionUID = 4882788637189435059L;

	protected NavEntry findNavEntry() throws JspException {
		NavEntry navEntryFound = null;
		if (this.getName()!=null) {
			Object attribute = this.pageContext.findAttribute( this.name );
			if ( attribute instanceof NavEntry ) {
				navEntryFound = (NavEntry)attribute;	
			} else if ( attribute instanceof NavNode ) {
				navEntryFound = ((NavNode)attribute).getNavEntry();
			} else if ( attribute instanceof NavMenuSection ) {
				navEntryFound = ((NavMenuSection)attribute).getNavEntry();				
			} else {
				String type = "";
				if ( attribute!=null ) {
					type = attribute.getClass().getName();
				}
				throw ( new JspException( "Tipo oggetto sbagliato : "+attribute+" ("+type+")" ) );
			}
		} else if ( this.getNavEntry()!=null ) {
			NavMap navMap = (NavMap)this.pageContext.getServletContext().getAttribute( NavFilter.ATT_NAME_NAVMAP );
			navEntryFound = navMap.getNavEntryByNavUrl( NavEntry.renderUrl( this.getNavModule(), this.getNavEntry() ) );
		} else {
			throw new JspException( "Gli attributi name e nav-entry non possono essere entrambi non specificati" );
		}
		return navEntryFound;
	}
	
	protected int checkAuth() throws JspException {
		return this.checkAuth( this.findNavEntry() );
	}
	
	protected int checkAuth( NavEntry navEntry ) throws JspException {
		int result = AuthHandler.STATE_UNAUTHORIZED;
		AuthHandler authHandler = (AuthHandler)this.pageContext.getServletContext().getAttribute( AuthHandler.ATT_NAME );
		try {
			result = authHandler.checkAuth( (HttpServletRequest)this.pageContext.getRequest(), navEntry.getAuth() );
		} catch (AuthException e) {
			throw ( new JspException( e ) );
		}
		return result;		
	}
	
	private String navEntry;
	
	private String navModule;
	
	private String name;
	
	private String excludeParams;

	public String getExcludeParams() {
		return excludeParams;
	}

	public void setExcludeParams(String excludeParams) {
		this.excludeParams = excludeParams;
	}

	public String getName() {
		return name;
	}

	private String getNavMapMessage( String key, NavEntry navEntry ) {
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		String prop = this.getNavMap().getNavMapMessage( request , key, navEntry  );
		MessageFormat format = new MessageFormat( prop ); 
		return format.format( NavArgs.getNavArg(request, navEntry.getUrl() ) );
	}

	public String getTitle() throws JspException {
		return this.getTitle( this.findNavEntry() );
	}	
	
	public String getTitle( NavEntry navEntry ) throws JspException {
		String title = null;
		if ( navEntry.getDirectTitle().booleanValue() ) {
			title = navEntry.getTitle();
		} else {
			String titleKey = navEntry.getTitle();
			TagLogger.log.debug( "titleKey : "+titleKey );
			title = this.getNavMapMessage( titleKey, navEntry );
		}
		TagLogger.log.debug( "title : "+title );
		return title;
	}		
	
	public String getLabel() throws JspException {
		return this.getLabel( this.findNavEntry() );
	}		
	
	public String getLabel( NavEntry navEntry ) throws JspException {
		String label = null;
		if ( navEntry.getDirectLabel().booleanValue() ) {
			label = navEntry.getLabel();
		} else {
			String labelKey = navEntry.getLabel();
			TagLogger.log.debug( "labelKey : "+labelKey );
			label = this.getNavMapMessage( labelKey, navEntry );
		}
		TagLogger.log.debug( "label : "+label );
		return label;
	}	
	
	public String getDisplay() throws JspException {
		return this.getDisplay( this.findNavEntry() );
	}
	
	public String getDisplay( NavEntry navEntry ) throws JspException {
		TagLogger.log.debug( "getDisplay navEntry : "+navEntry );
		String display = null;
		if ( navEntry.getDirectTitle().booleanValue() ) {
			display = navEntry.getDisplay();
		} else {
			String displayKey = navEntry.getDisplay();
			TagLogger.log.debug( "displayKey : "+displayKey );
			display = this.getNavMapMessage( navEntry.getDisplay(), navEntry );
		}
		TagLogger.log.debug( "display : "+display );
		return display;
	}
	
	/**
	 * <p>Imposta il nome di un attributo, in una delle mappe applicative, di tipo
	 * NavNode o NavEntry.</p>
	 * 
	 * <p>NOTA: nel caso il bean sia di tipo NavNode, le elaborazioni vengono
	 * fatte sulla NavEntry in esso contenuta.</p>
	 * 
	 * @param name Il nome del Bean
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNavModule() {
		return navModule;
	}

	public void setNavModule(String navModule) {
		this.navModule = navModule;
	}

	public String getNavEntry() {
		return navEntry;
	}

	public void setNavEntry(String navEntry) {
		this.navEntry = navEntry;
	}
	
}
