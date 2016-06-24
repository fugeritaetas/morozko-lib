package org.morozko.java.mod.codegen.handler;

public class HandlerHelper {

	public static String substituteIncludeDel( String text, String sub, String del1, String del2 ) {
		return substitute(text, del1+"\n"+sub+del2, del1, del2);
	}
	
	public static String substitute( String text, String sub, String del1, String del2 ) {
		String result = null;
		int index1 = text.indexOf( del1 );
		int index2 = text.indexOf( del2 );
		if ( index1 != -1 && index2 != -1 ) {
			result = text.substring( 0, index1 )+sub+text.substring( index2+del2.length() );
		}
		return result;
	}
	
}
