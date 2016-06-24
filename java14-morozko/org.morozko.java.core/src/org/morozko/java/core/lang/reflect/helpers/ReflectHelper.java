package org.morozko.java.core.lang.reflect.helpers;

import java.lang.reflect.Method;

public class ReflectHelper {

	public static Class[] EMPTY_PARAM_TYPES = new Class[0];
	public static Object[] EMPTY_PARAM_OBJECTS = new Object[0];

	public static Object getProperty( Object o, String propertyName ) throws Exception {
		Object r = null;
		Method m = o.getClass().getMethod( propertyName , EMPTY_PARAM_TYPES );
		r = m.invoke( o , EMPTY_PARAM_OBJECTS );
		return r;
	}
	
}
