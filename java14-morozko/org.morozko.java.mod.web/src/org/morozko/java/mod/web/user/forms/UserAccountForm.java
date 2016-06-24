package org.morozko.java.mod.web.user.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.dg.user.bean.UserAccountBean;

public class UserAccountForm extends UserAccountBean {

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7724104419000228348L;

	public UserAccountForm() {
		this.setIdUserAccount( "0" );
	}
	
}
