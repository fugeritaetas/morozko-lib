package org.morozko.java.core.ent.tld.format;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

public class PadTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -59076912668005112L;

	private String padData;
	
	private String padLength;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPadData() {
		return padData;
	}

	public void setPadData(String padData) {
		this.padData = padData;
	}



	public String getPadLength() {
		return padLength;
	}

	public void setPadLength(String padLength) {
		this.padLength = padLength;
	}

	public int doStartTag() throws JspException {
		Object o = this.findDefaultObject( this.getValue() );
		String v = String.valueOf( o );
		int l = Integer.parseInt( this.getPadLength() );
		while ( v.length() < l ) {
			v = this.getPadData() + v;
		}
		this.renderValue( v );
		return EVAL_PAGE;
	}
	
}
