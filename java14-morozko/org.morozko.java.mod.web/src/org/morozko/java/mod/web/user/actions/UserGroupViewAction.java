package org.morozko.java.mod.web.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserGroupDAO;
import org.morozko.java.mod.web.dg.user.model.UserGroupModel;

public class UserGroupViewAction extends AbstractAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		WebDAOFactory webDAOFactory = WebDAOFactory.getInstance();
		UserGroupDAO userGroupDAO = webDAOFactory.getUserDAOFactory().getUserGroupDAO();
		String idUserGroup = request.getParameter( "idUserGroup" );
		this.getLog().info( "idUserGroup : "+idUserGroup );
		if ( idUserGroup != null ) {
			UserGroupModel userGroupModel = userGroupDAO.loadOnePrimary( DAOID.valueOf( idUserGroup ) );
			request.getSession().setAttribute( "userGroupModel" , userGroupModel );	
		} else {
			request.getSession().removeAttribute( "userGroupModel" );
		}
		return mapping.findForward( forward );
	}
	
}
