package org.fugerit.java.core.lang.helpers;

public class StringHelper {

	public static String value( String value, String def ) {
		if ( value == null ) {
			value = def;
		}
		return value;
	}
	
}
