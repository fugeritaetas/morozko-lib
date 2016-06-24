package org.morozko.java.mod.web.user.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.dg.user.bean.UserGroupBean;

public class UserGroupForm extends UserGroupBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6792644525671505921L;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	public UserGroupForm() {
		this.setIdUserGroup( "0" );
	}
	
}
