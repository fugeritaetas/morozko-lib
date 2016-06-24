/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)NavPathBackUrl.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap.tld
 * @creation   : 8-mag-2006
 */
package org.morozko.java.mod.navmap.tld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NavPathBackUrl extends NavEntryTagSupportHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7465199002997193502L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			NavNode navNode = NavNode.getFromSession( (HttpServletRequest)this.pageContext.getRequest()  );
			NavNode parent = navNode.getParent();
			if ( parent != null && parent.getNavEntry() != null && navNode.isBackPath() ) {
				this.pageContext.setAttribute( this.getId() , this.createRealLink( parent, true ) );
			}
		} catch (Exception e) {
			e.printStackTrace(System.out); 
		}

		//NavPath navPath = (NavPath)this.pageContext.getRequest().getAttribute( NavFilter.ATT_NAME_NAVPATH );
//		if ( navPath != null ) {
//			if ( navPath.getParent() != null ) {
//				NavNode navNode = navPath.getParent().getNavNode();
//				String url = this.createRealLink( navNode.getNavEntry() );
//				Properties params = navPath.getParent().getParams();
//				Enumeration e  = params.keys();
//				boolean first = true;
//				while ( e.hasMoreElements() ) {
//					String k = (String)e.nextElement();
//					String v = params.getProperty( k );
//					if ( first ) {
//						url+="?";
//						first = false;
//					} else {
//						url+="&";
//					}
//					url+= k+"="+v;
//				}
//				this.println( url );
//			}
//		}
		return EVAL_PAGE;
	}

}
