package org.fugerit.java.core.lang.helpers;

import java.io.InputStream;

/**
 * <p>Helper for handling class loading and instantiation.</p>
 * 
 * @author fugerit
 *
 */
public class ClassHelper {

	/**
	 * <p>Get a resource as stream from default class loader or from a <code>Class</code>
	 * 
	 * @param path		path of the resource to load as stream
	 * @param c			class to use as alternate class loader
	 * @return			the resource in stream format
	 * @throws Exception	if something goes wrong during loading
	 */
	public static <T> InputStream getResourceStream( String path, Class<T> c ) throws Exception {
		InputStream is = getDefaultClassLoader().getResourceAsStream( path );
		if ( is == null && c != null ) {
			is = c.getResourceAsStream( path );
		}
		return is;
	}
	
	/**
	 * <p>Return top level class loader.</p>
	 * 
	 * <p>The code of this method is taked from source of apache struts 1 class 
	 * <a href="http://svn.apache.org/repos/asf/struts/struts1/trunk/core/src/main/java/org/apache/struts/util/RequestUtils.java">RequestUtil</a></p>
	 * 
	 * @return				the top level class loader
	 * @throws Exception
	 */
	public static ClassLoader getDefaultClassLoader() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if ( classLoader == null ) {
			classLoader = ClassHelper.class.getClassLoader();
		}
		return classLoader;
	}
	 
	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance( String type ) throws Exception {
		Object result = null;
		ClassLoader classLoader = getDefaultClassLoader();
		Class<?> c = classLoader.loadClass( type );
		result = c.newInstance();
		return result;
	}

	
}
