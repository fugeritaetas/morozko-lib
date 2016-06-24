package org.morozko.java.mod.web.poll.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.poll.dg.dao.PollDAOFactory;
import org.morozko.java.mod.web.poll.facade.PollFacade;
import org.morozko.java.mod.web.poll.facade.PollModel;

public class PollStatAction extends AbstractAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		String questionaryName = request.getParameter( "questionaryName" );
		String day = request.getParameter( "day" );
		this.getLog().info( "questionaryName : "+questionaryName );
		this.getLog().info( "day             : "+day );
		PollModel pollModel = PollFacade.loadQuestionary( questionaryName );
		String sql = " SELECT DISTINCT TO_CHAR( data_time, ''YYYY-MM-DD'' ) AS data_time, id_poll_index FROM "+PollDAOFactory.getInstance().getTablePrefix()+"poll_data WHERE id_poll_index = "+pollModel.getPollIndexModel().getIdPollIndex();
		List<String> listDay = PollDAOFactory.getInstance().getDaoUtils().extractStringList( sql );
		if ( day == null || day.length() == 0 ) {
			PollFacade.loadStat( pollModel );	
		} else {
			PollFacade.loadStatDay( pollModel, day );
		}
		request.setAttribute( "pollModel" , pollModel );
		request.setAttribute( "listDay" , listDay );
		return mapping.findForward( forward );
	}

}
