package org.morozko.java.mod.web.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserGroupuserDAO;
import org.morozko.java.mod.web.dg.user.model.UserGroupuserModel;
import org.morozko.java.mod.web.user.forms.UserGroupuserForm;

public class UserAccountGroupAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserGroupuserDAO userGroupuserDAO = webDAOFactory.getUserDAOFactory().getUserGroupuserDAO();
		UserGroupuserForm userGroupuserForm = (UserGroupuserForm) form;
		UserGroupuserModel userGroupuserModel = userGroupuserForm.getModel();
		if ( request.getParameter( "delete" ) != null ) {
			userGroupuserDAO.deleteByPk( userGroupuserModel.getIdUserGroupuser() );
		} else {
			userGroupuserDAO.insert( userGroupuserModel );
		}
		return mapping.findForward( forward );
	}

}
