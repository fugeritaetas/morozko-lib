package org.morozko.java.mod.web.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserAccountDAO;
import org.morozko.java.mod.web.dg.user.model.UserAccountModel;
import org.morozko.java.mod.web.message.MessageFacade;
import org.morozko.java.mod.web.user.forms.UserLoginForm;

public class UserLoginAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserAccountDAO userAccountDAO = webDAOFactory.getUserDAOFactory().getUserAccountDAO();
		UserLoginForm userLoginForm = (UserLoginForm) form;
		UserAccountModel userAccountModel = userAccountDAO.loadOneUserAccountSk1( userLoginForm.getUserName() );
		if ( userAccountModel == null ) {
			MessageFacade.queueUserMessage(request, "morozko.web.user.login.noUser" );
		} else {
			if ( userAccountModel.getUserPass().equals( userLoginForm.getUserPass() ) ) {
				request.getSession().setAttribute( "loggedUser" , userAccountModel );
			} else {
				MessageFacade.queueUserMessage(request, "morozko.web.user.login.wrongPass" );
			}	
		}
		
		return mapping.findForward( forward );
	}

}
