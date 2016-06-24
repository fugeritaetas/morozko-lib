package org.morozko.java.mod.option.web.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.option.dg.dao.OptionCatalogDAO;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.web.actions.AbstractAction;

public class OptionCatalogListAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionCatalogDAO optionCatalogDAO = OptionDaoFactory.getInstance().getOptionCatalogDAO();
        List list = optionCatalogDAO.loadAll();
        request.setAttribute( "listOptionCatalog" , list );
        return mapping.findForward( forward );
    }


}
