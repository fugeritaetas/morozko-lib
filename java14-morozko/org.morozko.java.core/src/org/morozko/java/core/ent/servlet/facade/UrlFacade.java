package org.morozko.java.core.ent.servlet.facade;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.ent.servlet.request.RequestFacade;
import org.morozko.java.core.io.StreamFacade;

public class UrlFacade {

	public static byte[] readRelativeUrl( HttpServletRequest request, String relUrl ) throws IOException {
		return StreamFacade.readUrl( RequestFacade.getRelativeUrl(request, relUrl ) );
	}
	
	public static byte[] readUrl( String url ) throws IOException {
		return StreamFacade.readUrl( url );
	}
	
	public static byte[] readRelativeUrl( HttpServletRequest request, String relUrl, ParamMap params ) throws IOException {
		return StreamFacade.readUrl( RequestFacade.getRelativeUrl(request, relUrl, params ) );
	}
	
	public static byte[] readUrl( String url, ParamMap params ) throws IOException {
		return StreamFacade.readUrl( url+params.getQueryString() );
	}
	

	
}
