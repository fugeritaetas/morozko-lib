package org.morozko.java.mod.option.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.option.dg.bean.OptionCatalogBean;

public class OptionCatalogForm extends OptionCatalogBean {

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094507336863564842L;
	
	public OptionCatalogForm() {
		this.setIdOptionCatalog( "0" );
	}

}
