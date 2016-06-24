package org.morozko.java.mod.option.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.option.dg.dao.OptionItemDAO;
import org.morozko.java.mod.option.dg.model.OptionItemModel;
import org.morozko.java.mod.option.web.form.OptionItemForm;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.message.MessageFacade;

public class OptionItemSaveAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionItemForm optionItemForm = (OptionItemForm) form;
        OptionItemModel optionItemModel = optionItemForm.getModel();
        OptionItemDAO optionItemDAO = OptionDaoFactory.getInstance().getOptionItemDAO();
        if ( optionItemModel.getIdOptionItem().longValue() == 0 ) {
        	// insert
        	optionItemModel.setIdOptionItem( optionItemDAO.generateId() );
        	optionItemModel.setOrdering( optionItemModel.getIdOptionItem() );
        	int result = optionItemDAO.insert( optionItemModel );
        	if ( result > 0 ) {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.insert.ok" );
        	} else {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.insert.ko" );
        	}
        } else {
        	// update
        	int result = optionItemDAO.update( optionItemModel );
        	if ( result > 0 ) {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.update.ok" );
        	} else {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.update.ko" );
        	}        	
        }
        return mapping.findForward( forward );
    }

}
