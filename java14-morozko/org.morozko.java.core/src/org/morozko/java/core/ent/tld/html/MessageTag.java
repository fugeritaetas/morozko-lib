package org.morozko.java.core.ent.tld.html;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.core.text.TextFilter;

public class MessageTag extends TagSupportHelper {

	private String key;
	
	private String bundle;
	
	private String filter;
	
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -931182773137993139L;

	public int doStartTag() throws JspException {
		String bundleName = this.getBundle();
		if ( bundleName == null ) {
			bundleName = "DEFAULT";
		}
		ResourceBundle bundle = (ResourceBundle)this.pageContext.getServletContext().getAttribute( "HtmlTagFilter.bundle."+bundleName );
		if ( bundle != null ) {
			String w = bundle.getString( this.getKey() );
			Object arg0 = findObject( this.getArg0Name(), this.getArg0Property(), this.getArg0Value() );
			if ( arg0 != null ) {
				MessageFormat mf = new MessageFormat( w );
				Object[] args = { arg0 };
				w = mf.format( args );
			}
			if ( this.getFilter() != null ) {
				TextFilter f = HtmlTagUtils.getFilter( this.pageContext.getServletContext(), this.getFilter() );
				w = f.filterFrom( w );
			}
			this.print( w );
		} else {
			throw new JspException( "Failed to load bundle : "+this.getBundle() );
		}
		return EVAL_PAGE;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	
	private String arg0Name;
	private String arg0Property;
	private String arg0Value;

	public String getArg0Name() {
		return arg0Name;
	}

	public void setArg0Name(String arg0Name) {
		this.arg0Name = arg0Name;
	}

	public String getArg0Property() {
		return arg0Property;
	}

	public void setArg0Property(String arg0Property) {
		this.arg0Property = arg0Property;
	}

	public String getArg0Value() {
		return arg0Value;
	}

	public void setArg0Value(String arg0Value) {
		this.arg0Value = arg0Value;
	}	

}
