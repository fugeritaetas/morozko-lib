package org.morozko.java.core.ent.tld.html;

import javax.servlet.ServletContext;

import org.morozko.java.core.text.TextFilter;

public class HtmlTagUtils {

	public static TextFilter getFilter( ServletContext context, String name ) {
		return (TextFilter) context.getAttribute( "HtmlTagFilter.filter."+name );
	}
	
}
