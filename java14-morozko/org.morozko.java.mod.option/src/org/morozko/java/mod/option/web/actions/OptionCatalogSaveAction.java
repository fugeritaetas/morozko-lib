package org.morozko.java.mod.option.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.option.dg.dao.OptionCatalogDAO;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.option.dg.model.OptionCatalogModel;
import org.morozko.java.mod.option.web.form.OptionCatalogForm;
import org.morozko.java.mod.web.actions.AbstractAction;
import org.morozko.java.mod.web.message.MessageFacade;

public class OptionCatalogSaveAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionCatalogForm optionCatalogForm = (OptionCatalogForm) form;
        OptionCatalogModel optionCatalogModel = optionCatalogForm.getModel();
        OptionCatalogDAO optionCatalogDAO = OptionDaoFactory.getInstance().getOptionCatalogDAO();
        if ( optionCatalogModel.getIdOptionCatalog().longValue() == 0 ) {
        	// insert
        	optionCatalogModel.setIdOptionCatalog( optionCatalogDAO.generateId() );
        	int result = optionCatalogDAO.insert( optionCatalogModel );
        	if ( result > 0 ) {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.insert.ok" );
        	} else {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.insert.ko" );
        	}
        } else {
        	// update
        	int result = optionCatalogDAO.update( optionCatalogModel );
        	if ( result > 0 ) {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.update.ok" );
        	} else {
        		MessageFacade.queueUserMessage( request , "morozko.option.web.message.update.ko" );
        	}        	
        }
        return mapping.findForward( forward );
    }

}
