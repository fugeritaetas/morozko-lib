package org.morozko.java.mod.option.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.option.dg.dao.OptionItemDAO;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.message.MessageFacade;

public class OptionItemDeleteAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionItemDAO optionItemDAO = OptionDaoFactory.getInstance().getOptionItemDAO();
        String idOptionItem = request.getParameter( "idOptionItem" );
        this.getLog().info( "idOptionItem  "+idOptionItem );
        if ( idOptionItem != null ) {
        	int result = optionItemDAO.deleteByPk( DAOID.valueOf( idOptionItem ) );
        	if ( result > 0 ) {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.delete.ok" );
        	} else {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.delete.ko" );
        	}
        }
        return mapping.findForward( forward );
    }


}
