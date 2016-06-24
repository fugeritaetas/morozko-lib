package org.morozko.java.core.ent.tld.format;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;

public class EncodeUrlTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4917312122770430621L;

	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int doEndTag() throws JspException {
		String u = this.getUrl();
		try {
			u = URLEncoder.encode( u , "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.print( u );
		//TagUtilsHelper.setAttibute( this.pageContext , this.getScope(), this.getId(), u );
		return EVAL_PAGE;
	}
	
	
}
