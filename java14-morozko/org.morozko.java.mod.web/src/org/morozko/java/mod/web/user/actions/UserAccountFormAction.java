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
import org.morozko.java.mod.web.user.forms.UserAccountForm;

public class UserAccountFormAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserAccountDAO userAccountDAO = webDAOFactory.getUserDAOFactory().getUserAccountDAO();
		UserAccountForm userAccountForm = (UserAccountForm) form;
		UserAccountModel userAccountModel = userAccountForm.getModel();
		if ( userAccountModel.getIdUserAccount().intValue() == 0 ) {
			int result = userAccountDAO.insert( userAccountModel );
			if ( result > 0 ) {
				MessageFacade.queueUserMessage(request, "morozko.web.user.insertUser.ok" );
			} else {
				MessageFacade.queueUserMessage(request, "morozko.web.user.insertUser.ko" );
			}
		} else {
			int result = userAccountDAO.update( userAccountModel );
			if ( result > 0 ) {
				MessageFacade.queueUserMessage(request, "morozko.web.user.updateUser.ok" );
			} else {
				MessageFacade.queueUserMessage(request, "morozko.web.user.updateUser.ko" );
			}			
		}
		return mapping.findForward( forward );
	}

}
