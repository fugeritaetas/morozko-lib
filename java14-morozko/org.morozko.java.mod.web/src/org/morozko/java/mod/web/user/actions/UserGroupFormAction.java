package org.morozko.java.mod.web.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserGroupDAO;
import org.morozko.java.mod.web.dg.user.model.UserGroupModel;
import org.morozko.java.mod.web.message.MessageFacade;
import org.morozko.java.mod.web.user.forms.UserGroupForm;

public class UserGroupFormAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserGroupDAO userGroupDAO = webDAOFactory.getUserDAOFactory().getUserGroupDAO();
		UserGroupForm userGroupForm = (UserGroupForm) form;
		UserGroupModel userGroupModel = userGroupForm.getModel();
		if ( userGroupModel.getIdUserGroup().intValue() == 0 ) {
			int result = userGroupDAO.insert( userGroupModel );
			if ( result > 0 ) {
				MessageFacade.queueUserMessage(request, "morozko.web.user.insertGroup.ok" );
			} else {
				MessageFacade.queueUserMessage(request, "morozko.web.user.insertGroup.ko" );
			}
		} else {
			int result = userGroupDAO.update( userGroupModel );
			if ( result > 0 ) {
				MessageFacade.queueUserMessage(request, "morozko.web.user.updateGroup.ok" );
			} else {
				MessageFacade.queueUserMessage(request, "morozko.web.user.updateGroup.ko" );
			}			
		}
		return mapping.findForward( forward );
	}

}
