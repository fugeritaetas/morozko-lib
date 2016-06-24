/*
 * @(#)UserAccountModel.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.model
 * @creation   : 21/08/2009 15/33/53
 */
package org.morozko.java.mod.web.dg.user.model;

import java.util.Iterator;

import org.morozko.java.mod.web.dg.user.model.helpers.UserAccountModelHelper;

/**
 * <p>Classe UserAccountModel.</p>
 *
 * @author Morozko
 */
public class UserAccountModel extends UserAccountModelHelper {

	public static final String LOGGED_ATT_NAME = "loggedUser";
	
	private final static long serialVersionUID = 125086163356650L;

	public boolean hasGroup( String group ) {
		boolean ok = false;
		Iterator it = this.getListGroup().iterator();
		while ( it.hasNext() && !ok ) {
			UserGroupuserModel userGroupuserModel = (UserGroupuserModel) it.next();
			ok = group.equals( userGroupuserModel.getGroupName() );
		}
		return ok;
	}
	
}
