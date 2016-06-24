package org.morozko.java.mod.option.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.option.dg.dao.OptionCatalogDAO;
import org.morozko.java.mod.option.dg.dao.OptionDaoFactory;
import org.morozko.java.mod.option.dg.model.OptionCatalogModel;
import org.morozko.java.mod.web.actions.AbstractAction;

public class OptionCatalogLoadAction extends AbstractAction {

    /* (non Javadoc)
     * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = STANDARD_FORWARD_SUCCESS;      
        OptionCatalogDAO optionCatalogDAO = OptionDaoFactory.getInstance().getOptionCatalogDAO();
        String idOptionCatalog = request.getParameter( "idOptionCatalog" );
        this.getLog().info( "idOptionCatalog  "+idOptionCatalog );
        if ( idOptionCatalog != null ) {
        	OptionCatalogModel optionCatalogModel = optionCatalogDAO.loadOneOptionCatalogPk( DAOID.valueOf( idOptionCatalog ) );
            request.getSession().setAttribute( "optionCatalogModel", optionCatalogModel );
        } else {
        	request.getSession().removeAttribute( "optionCatalogModel" );
        }
        request.getSession().removeAttribute( "optionCatalogForm" );
        return mapping.findForward( forward );
    }

}
