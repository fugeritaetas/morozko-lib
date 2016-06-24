package org.morozko.java.mod.navmap.auth;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.mod.web.dg.user.model.UserAccountModel;

public class UserAuthHandler extends BasicAuthHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2523839660983165797L;

	public int checkAuth(HttpServletRequest request, String resource) throws AuthException {
		int result = STATE_AUTHORIZED;
		if ( !"NOCHECK".equalsIgnoreCase( resource ) ) {
			UserAccountModel userAccountModel = (UserAccountModel)request.getSession().getAttribute( UserAccountModel.LOGGED_ATT_NAME );
			if ( userAccountModel != null ) {
				if ( resource.indexOf( "group:" ) == 0 ) {
					String[] group = resource.substring( "group:".length() ).split( ";" );
					result = STATE_UNAUTHORIZED; 
					for ( int k=0; k<group.length; k++ ) {
						if ( userAccountModel.hasGroup( group[k] ) ) {
							result = STATE_AUTHORIZED; 
						}	
					}
				}
			} else {
				result = STATE_UNAUTHORIZED;
			}
		}
		return result;
	}

}
