package org.morozko.java.mod.web.user.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.dg.user.bean.UserGroupuserBean;

public class UserGroupuserForm extends UserGroupuserBean {

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5952254582973030193L;

	public UserGroupuserForm() {
		this.setIdUserGroupuser( "0" );
	}
	
}
