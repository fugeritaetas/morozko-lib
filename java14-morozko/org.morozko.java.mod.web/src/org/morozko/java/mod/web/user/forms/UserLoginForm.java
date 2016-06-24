package org.morozko.java.mod.web.user.forms;

import org.apache.struts.action.ActionForm;

public class UserLoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5104887477819494188L;

	private String userName;
	
	private String userPass;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
