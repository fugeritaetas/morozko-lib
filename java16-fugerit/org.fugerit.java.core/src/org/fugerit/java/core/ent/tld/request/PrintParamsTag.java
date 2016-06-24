package org.fugerit.java.core.ent.tld.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.fugerit.java.core.ent.encoder.SecurityEncoder;
import org.fugerit.java.core.ent.servlet.RequestHelper;
import org.fugerit.java.core.ent.tld.helpers.TagSupportHelper;
import org.fugerit.java.core.ent.tld.helpers.TagUtilsHelper;

public class PrintParamsTag extends TagSupportHelper {

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
	
	private String skip;
	
	private String scope;

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

	public String getSkip() {
		return skip;
	}

	public void setSkip(String skip) {
		this.skip = skip;
	}

	
	@Override
	public int doStartTag() throws JspException {
		int res = EVAL_PAGE;
		try {
			SecurityEncoder encoder = getDefaultSecurityEncoder( this.pageContext.getServletContext() );
			List<String> skipParams = new ArrayList<String>();
			List<String> dsp = getDefaultSkipParamsList( this.pageContext.getServletContext() );
			if ( dsp != null ) {
				skipParams.addAll( dsp );
			}
			if ( this.getSkip() != null ) {
				String[] skipArray = this.getSkip().split( ";" );
				skipParams.addAll( Arrays.asList( skipArray ) );
			}
			String paramString = RequestHelper.getParamString( (HttpServletRequest) this.pageContext.getRequest(), skipParams, encoder );
			if ( this.getId() == null ) {
				this.print( paramString );
			} else {
				TagUtilsHelper.setAttibute( this.pageContext , this.getScope(), this.getId(), paramString );
			}
		} catch (Exception e) {
			throw new JspException( e );
		}
		return res;
	}
	
	
	
}
