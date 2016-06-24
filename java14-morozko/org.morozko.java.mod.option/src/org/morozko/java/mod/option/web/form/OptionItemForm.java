package org.morozko.java.mod.option.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.option.dg.bean.OptionItemBean;

public class OptionItemForm extends OptionItemBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5901242398060705671L;

	public OptionItemForm() {
		this.setIdOptionItem( "0" );
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}	
	
}
