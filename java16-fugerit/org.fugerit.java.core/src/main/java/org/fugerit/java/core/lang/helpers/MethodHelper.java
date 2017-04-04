package org.fugerit.java.core.lang.helpers;

import java.lang.reflect.Method;


/**
 * <p>Helper class for invoiking methods by reflection</p> 
 * 
 * @author Matteo Franci a.k.a. Fugerit
 * @version 0.0.1 (2013-03-19)
 *
 */
public class MethodHelper {

	/**
	 * Constant for empty argument values on invoking
	 */
	public static final Object[] NO_PARAM_VALUES = {};
	
	/**
	 * Constant for empty argument types on invoking
	 */
	public static final Class<?>[] NO_PARAM_TYPES = {};
	
	/**
	 * <p>Change the first character in a string <code>toUppercase()</code>.</p>
	 * 
	 * @param propertyName	the string to modify
	 * @return				the modified string
	 */
	public static String getUpFirstForProperty( String propertyName ) {
		return propertyName.substring( 0, 1 ).toUpperCase()+propertyName.substring( 1 );
	}
	
	/**
	 * <p>Transform a property string in a setter method string</p>
	 *
	 * @param propertyName		the property name (for example 'name')
	 * @return					the setter method name (for example 'getName' )
	 */
	public static String getSetterNameForProperty( String propertyName ) {
		return "set"+getUpFirstForProperty( propertyName );
	}
	
	/**
	 * <p>Transform a property string in a getter method string</p>
	 *
	 * @param propertyName		the property name (for example 'name')
	 * @return					the getter method name (for example 'setName' )
	 */	
	public static String getGetterNameForProperty( String propertyName ) {
		return "get"+getUpFirstForProperty( propertyName );
	}
	
	/**
	 * <p>Invoke a setter method on an object's property</p>
	 * 
	 * <p>In case the property is 'name', then method 'setName' will be invoked</p>
	 * 
	 * @param obj				the object on which the method will be invoked
	 * @param propertyName		property name
	 * @param paramType			property type
	 * @param paramValue		property value
	 * @throws Exception		in case something goes wrong
	 */
	public static void invokeSetter( Object obj, String propertyName, Class<?> paramType, Object paramValue ) throws Exception {
		invoke(obj, getSetterNameForProperty(propertyName), paramType, paramValue);
	}

	/**
	 * <p>Invoke a getter method on an object's property</p>
	 * 
	 * <p>In case the property is 'name', then method 'getName' will be invoked</p>
	 * 
	 * @param obj				the object on which the method will be invoked
	 * @param propertyName		property name
	 * @return					the object returned by the method
	 * @throws Exception		in case something goes wrong
	 */
	public static Object invokeGetter( Object obj, String propertyName ) throws Exception {
		return invoke(obj, getGetterNameForProperty( propertyName ), NO_PARAM_TYPES, NO_PARAM_VALUES );
	}
	
	/**
	 * <p>Invoke a method with one argument only on an object</p>
	 * 
	 * @param obj			the object on which the method will be invoked
	 * @param methodName	the name of the method
	 * @param paramType		the type of the argument
	 * @param paramValue	the value of the argument
	 * @return				the result of invokation
	 * @throws Exception	in case something goes wrong
	 */
	public static Object invoke( Object obj, String methodName, Class<?> paramType, Object paramValue ) throws Exception {
		Class<?>[] paramTypes = { paramType };
		Object[] paramValues = { paramValue };
		return invoke(obj, methodName, paramTypes, paramValues);
	}
	
	/**
	 * <p>Invoke a method with multiple arguments on an object</p>
	 * 
	 * @param obj			the object on which the method will be invoked
	 * @param methodNames	the name of the method
	 * @param paramTypes	the types of the arguments
	 * @param paramValue	the values of the arguments
	 * @return				the result of invokation
	 * @throws Exception	in case something goes wrong
	 */	
	public static Object invoke( Object obj, String methodName, Class<?>[] paramTypes, Object[] paramValues ) throws Exception {
		//LogFacade.getLog().info( "MethodHelper.invoice() methodName = "+methodName+" obj : "+obj );
		Method method = obj.getClass().getMethod( methodName, paramTypes );
		if ( method == null ) {
			throw new Exception( "Method not found "+methodName+" on class "+obj.getClass().getName() );
		}
		Object result = method.invoke( obj, paramValues );
		return result;
	}
	
}
