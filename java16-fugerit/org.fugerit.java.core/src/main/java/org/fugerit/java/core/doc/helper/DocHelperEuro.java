package org.fugerit.java.core.doc.helper;

import org.fugerit.java.core.doc.DocHelper;

public class DocHelperEuro extends DocHelper {

	public String filterText(String temp) {
		StringBuffer text = new StringBuffer();
		for ( int k=0; k<temp.length(); k++ ) {
			char c = temp.charAt( k );
			int i = (int)c;
			if ( i == 164 ) {
				text.append( "�" );
			} else {
				text.append( c );
			}
		}
		return text.toString();
	}

	
	
}