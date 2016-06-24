package org.morozko.java.mod.option.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.option.dg.dao.OptionItemDAO;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.option.dg.model.OptionItemModel;
import org.morozko.java.mod.web.actions.AbstractAction;

public class OptionItemLoadAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionItemDAO optionItemDAO = OptionDaoFactory.getInstance().getOptionItemDAO();
        String idOptionItem = request.getParameter( "idOptionItem" );
        this.getLog().info( "idOptionItem  "+idOptionItem );
        if ( idOptionItem != null ) {
        	OptionItemModel optionItemModel = optionItemDAO.loadOneOptionItemPk( DAOID.valueOf( idOptionItem ) );
            request.getSession().setAttribute( "optionItemModel", optionItemModel );	
        } else {
        	request.getSession().removeAttribute( "optionItemModel" );
        }
        request.getSession().removeAttribute( "optionItemForm" );
        return mapping.findForward( forward );
    }

}
