package org.morozko.java.mod.web.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;

public class UserLogoutAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		request.getSession().removeAttribute( "loggedUser" );
		return mapping.findForward( forward );
	}

}
