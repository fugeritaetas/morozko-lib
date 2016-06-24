package org.morozko.java.core.ent.tld.html;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.core.text.TextFilter;

public class WriteTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233710119777283351L;

	public int doStartTag() throws JspException {
		Object obj = this.findObject( this.getName(), this.getProperty(), this.getValue() );
		String w = String.valueOf( obj );
		if ( this.getFilter() != null ) {
			TextFilter f = HtmlTagUtils.getFilter( this.pageContext.getServletContext(), this.getFilter() );
			w = f.filterFrom( w );
		}
		this.print( w );
		return EVAL_PAGE; 
	}

	private String value;
	
	private String filter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	
}
