package org.morozko.java.mod.web.user.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserAccountDAO;
import org.morozko.java.mod.web.dg.user.dao.UserGroupDAO;
import org.morozko.java.mod.web.dg.user.model.UserAccountModel;

public class UserAccountViewAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserAccountDAO userAccountDAO = webDAOFactory.getUserDAOFactory().getUserAccountDAO();
		String idUserAccount = request.getParameter( "idUserAccount" );
		this.getLog().info( "idUserAccount : "+idUserAccount );
		if ( idUserAccount != null ) {
			UserAccountModel userAccountModel = userAccountDAO.loadOnePrimary( DAOID.valueOf( idUserAccount ) );
			request.getSession().setAttribute( "userAccountModel" , userAccountModel );
			UserGroupDAO userGroupDAO = webDAOFactory.getUserDAOFactory().getUserGroupDAO();
			List groupList = userGroupDAO.loadAll();
			request.getSession().setAttribute( "groupList" , groupList );
		} else {
			request.getSession().removeAttribute( "userAccountModel" );
			request.getSession().removeAttribute( "groupList" );
		}
		return mapping.findForward( forward );
	}

}
