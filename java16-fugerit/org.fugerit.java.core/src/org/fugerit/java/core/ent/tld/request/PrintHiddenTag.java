package org.fugerit.java.core.ent.tld.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.fugerit.java.core.ent.encoder.SecurityEncoder;
import org.fugerit.java.core.ent.servlet.RequestHelper;
import org.fugerit.java.core.ent.tld.helpers.TagSupportHelper;

public class PrintHiddenTag extends TagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7446887068083444458L;

	
	private String skip;
	

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
			SecurityEncoder encoder = PrintParamsTag.getDefaultSecurityEncoder( this.pageContext.getServletContext() );
			List<String> skipParams = new ArrayList<String>();
			List<String> dsp = PrintParamsTag.getDefaultSkipParamsList( this.pageContext.getServletContext() );
			if ( dsp != null ) {
				skipParams.addAll( dsp );
			}
			if ( this.getSkip() != null ) {
				String[] skipArray = this.getSkip().split( ";" );
				skipParams.addAll( Arrays.asList( skipArray ) );
			}
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			Enumeration<String> paramNames = request.getParameterNames();
			while ( paramNames.hasMoreElements() ) {
				String paramName = paramNames.nextElement();
				if ( !skipParams.contains( paramName ) && !paramName.equalsIgnoreCase( RequestHelper.PARAM_GENERATED ) ) {
					String[] paramValues = request.getParameterValues( paramName );
					if ( paramValues != null ) {
						for ( int k=0; k<paramValues.length; k++ ) {
							String text = "<input type='hidden' name='"+paramName+"' value='"+encoder.encodeForHTMLAttribute( paramValues[k] )+"'/>";
							this.pageContext.getOut().println( text );
						}
					}
				}
			}
		} catch (Exception e) {
			throw new JspException( e );
		}
		return res;
	}
	
	
	
}
