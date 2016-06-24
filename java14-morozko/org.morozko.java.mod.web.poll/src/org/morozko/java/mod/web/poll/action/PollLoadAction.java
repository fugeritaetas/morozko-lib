package org.morozko.java.mod.web.poll.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.poll.facade.PollFacade;
import org.morozko.java.mod.web.poll.facade.PollModel;

public class PollLoadAction extends AbstractAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		String questionaryName = request.getParameter( "questionaryName" );
		this.getLog().info( "questionaryName : "+questionaryName );
		PollModel pollModel = PollFacade.loadQuestionary( questionaryName );
		request.getSession().setAttribute( "pollModel" , pollModel );
		return mapping.findForward( forward );
	}

}
