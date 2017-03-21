package org.fugerit.java.core.ent.web.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.fugerit.java.core.web.encoder.SecurityEncoder;
import org.fugerit.java.core.web.servlet.request.RequestHelper;
import org.fugerit.java.core.web.tld.helpers.TagSupportHelper;
import org.fugerit.java.core.web.tld.helpers.TagUtilsHelper;

public class EncodeTag extends TagSupportHelper {

	public static final String ATT_NAME_DEFAULT_SKIP_PARAMS = "DefaultSkipParams";
	
	public static final String ATT_NAME_DEFAULT_SECURITY_ENCODER = "DefaultSecurityEncoder";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7446887068084987958L;

	public static List<String> getDefaultSkipParamsList( ServletContext context ) {
		List<String> dsp = (List<String>)context.getAttribute( ATT_NAME_DEFAULT_SKIP_PARAMS );
		if ( dsp == null ) {
			dsp = new ArrayList<String>();
			context.setAttribute( ATT_NAME_DEFAULT_SKIP_PARAMS , dsp );
		}
		return dsp;
	}
	
	public static SecurityEncoder getDefaultSecurityEncoder( ServletContext context ) {
		SecurityEncoder dsp = (SecurityEncoder)context.getAttribute( ATT_NAME_DEFAULT_SECURITY_ENCODER );
		if ( dsp == null ) {
			dsp = RequestHelper.ENCODER;
			context.setAttribute( ATT_NAME_DEFAULT_SECURITY_ENCODER , dsp );
		}
		return dsp;
	}
	
	private String id;
	
	private String scope;
	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}


	
	@Override
	public int doStartTag() throws JspException {
		int res = EVAL_PAGE;
		try {
			SecurityEncoder encoder = getDefaultSecurityEncoder( this.pageContext.getServletContext() );
			String out = this.getValue();
			if ( "html".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForHTML( out );
			} else if ( "htmlAttribute".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForHTMLAttribute( out );
			} else if ( "css".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForCSS( out );
			} else if ( "url".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForURL( out );
			} else if ( "js".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForJavaScript( out );
			} else 	if ( "xml".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForXML( out );
			} else if ( "xmlAttribute".equalsIgnoreCase( this.getType() ) ) {
				out = encoder.encodeForXMLAttribute( out );
			} else {
				throw new JspException( "type must be one of those values: html|htmlAttribute|css|url|js|xml|xmlAttribute" );
			}
			if ( this.getId() == null ) {
				this.print( out );
			} else {
				TagUtilsHelper.setAttibute( this.pageContext , this.getScope(), this.getId(), out );
			}
		} catch (Exception e) {
			throw new JspException( e );
		}
		return res;
	}
	
	
	
}
