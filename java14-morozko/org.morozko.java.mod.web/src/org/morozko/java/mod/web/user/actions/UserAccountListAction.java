package org.morozko.java.mod.web.user.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserAccountDAO;

public class UserAccountListAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserAccountDAO userAccountDAO = webDAOFactory.getUserDAOFactory().getUserAccountDAO();
		List userList = userAccountDAO.loadAll();
		request.setAttribute( "userList" , userList );
		return mapping.findForward( forward );
	}

}
