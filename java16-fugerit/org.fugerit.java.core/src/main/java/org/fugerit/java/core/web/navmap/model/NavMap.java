package org.fugerit.java.core.web.navmap.model;

import java.io.Serializable;

import org.fugerit.java.core.util.collection.ListMapStringKey;
import org.fugerit.java.core.web.auth.handler.AllowAuthHandler;
import org.fugerit.java.core.web.auth.handler.AuthHandler;

/**
 * Main modeling object for NavMap library.
 * 
 * Version 1.0 (2016-12-02)
 * 
 * @author Fugerit <m@fugerit.org>
 *
 * @see org.fugerit.java.core.web.navmap.model.NavMap
 *
 */
public class NavMap implements Serializable {

	public static final String CONTEXT_ATT_NAME = "org.fugerit.java.mod.web.navmap.model.NavMap#AttName";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3883392083434225523L;

	private ListMapStringKey<NavEntry> entryList;
	private ListMapStringKey<NavMenu> menuList;
	private AuthHandler authHandler;
	
	public NavMap(ListMapStringKey<NavEntry> entryList,
			ListMapStringKey<NavMenu> menuList) {
		super();
		this.entryList = entryList;
		this.menuList = menuList;
		this.authHandler = new AllowAuthHandler();
	}
	
	public NavEntry getEntryByUrl( String url ) {
		return this.entryList.get( url );
	}
	
	public NavMenu getMenuById( String id ) {
		return this.menuList.get( id );
	}

	public AuthHandler getAuthHandler() {
		return authHandler;
	}

	public void setAuthHandler(AuthHandler authHandler) {
		this.authHandler = authHandler;
	}
	
	
	
}
