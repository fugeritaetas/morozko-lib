package org.morozko.java.mod.web.poll.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.poll.dg.model.PollDataModel;
import org.morozko.java.mod.web.poll.facade.PollFacade;
import org.morozko.java.mod.web.poll.facade.PollModel;

public class PollSaveAction extends AbstractAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		PollModel pollModel = (PollModel)request.getSession().getAttribute( "pollModel" );
		List<String> pollMessage = new ArrayList<String>();
		boolean ok = PollFacade.populate(pollModel, ParamMap.getParamMap(request) );
		if ( ok ) {
			boolean save = PollFacade.saveData( pollModel.getPollDataModel() );
		} else {
			pollMessage.add( "Devi rispondere a tutte le domande" );
			request.setAttribute( "pollMessage" , pollMessage );
			forward = STANDARD_FORWARD_FAILURE;
		}
		return mapping.findForward( forward );
	}

}
