package org.morozko.java.mod.web.servlet.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletRequestUrlChanger extends HttpServletRequestWrapper {

	private String text;
	
	private String replace;
	
	public HttpServletRequestUrlChanger( HttpServletRequest request, String text, String replace ) {
		super(request);
		this.text = text;
		this.replace = replace;
	}

	public String getRequestURI() {
		return super.getRequestURI().replaceAll( this.text , this.replace );
	}

	public StringBuffer getRequestURL() {
		String s = super.getRequestURL().toString().replaceAll( this.text , this.replace );
		StringBuffer b = new StringBuffer();
		b.append( s );
		return b;
	}
	
}
