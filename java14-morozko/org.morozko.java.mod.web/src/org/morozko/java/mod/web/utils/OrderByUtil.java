package org.morozko.java.mod.web.utils;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.web.servlet.config.SessionContext;

/**
 * <h3>Helps to handle ordering.</h3>
 * 
 * <p>Default is no ordering, some behaviour may be set :
 * 	<ul>
 * 		<li>PARAM_ORDER_BY must be set to the field to order (if 'null', it will default to NONE)</li>
 * 		<li>PARAM_ORDER_BY_CHANGE may be set to '1' to rotate amont 3 modes : ASC, DESC, NONE</li>
 * 		<li>PARAM_ORDER_BY_MODE may be set to one of the tree ordering mode : ASC, DESC, NONE</li>
 * 		<li>Ordering mode is preserved through following request as it's stored in session</li>
 * 	</ul>
 * </p>
 * 
 * @author Matteo Franci a.k.a. Fugerit
 *
 */
public class OrderByUtil {

	/**
	 * Parameter containing the order by 'field'
	 */
	public static final String PARAM_ORDER_BY = "orderBy";
	
	/**
	 * Value '1' to this parameter to change ordering mode ( rotate among 3 modes : ASC, DESC, NONE )
	 */	
	public static final String PARAM_ORDER_BY_CHANGE = "orderByChange";
	
	/**
	 * Parameter to set directly ordering mode ( ASC, DESC, NONE )
	 */	
	public static final String PARAM_ORDER_BY_MODE = "orderByMode";

	private static final String ATT_ORDER_BY_MODE = OrderByUtil.class.getName()+".ATT_MODE";
	private static final String ATT_ORDER_BY_TEMP = OrderByUtil.class.getName()+".ATT_TEMP";
	
	/**
	 * Constant for ascending ordering mode (ASC)
	 */
	public static final String ATT_ORDER_BY_MODE_ASC = "ASC";
	
	/**
	 * Constant for descending ordering mode (DESC)
	 */
	public static final String ATT_ORDER_BY_MODE_DESC = "DESC";
	
	/**
	 * Constant for removing ordering mode (NONE)
	 */
	public static final String ATT_ORDER_BY_MODE_NONE = "NONE";
	
	/**
	 * 
	 * Reset the ordering mode
	 * 
	 * @param sessionContext	session
	 */
	public static void resetOrderBy( SessionContext sessionContext ) {
		sessionContext.removeAttribute( ATT_ORDER_BY_MODE );
	}
	
	/**
	 * Return the ordering string and handles the ordering mode settings
	 * 
	 * @param sessionContext	session
	 * @param paramMap			parameter
	 * @return					ordering string
	 */
	public static String handleOrderBy( SessionContext sessionContext, ParamMap paramMap ) {
		String orderBy = paramMap.getParam( PARAM_ORDER_BY );
		String mode = paramMap.getParam( PARAM_ORDER_BY_MODE );
		if ( orderBy != null && mode == null ) {
			if ( "1".equalsIgnoreCase( paramMap.getParam( PARAM_ORDER_BY_CHANGE ) ) ) {
				String att = (String) sessionContext.getAttribute( ATT_ORDER_BY_MODE );
				if ( ATT_ORDER_BY_MODE_ASC.equals( att ) ) {
					mode = ATT_ORDER_BY_MODE_DESC;
				} else if ( ATT_ORDER_BY_MODE_DESC.equals( att ) ) {
					mode = ATT_ORDER_BY_MODE_NONE;
					orderBy = null;
				} else {
					mode = ATT_ORDER_BY_MODE_ASC;
				}
			} else {
				mode = (String) sessionContext.getAttribute( ATT_ORDER_BY_MODE, ATT_ORDER_BY_MODE_ASC );
			}			
		}
		sessionContext.setAttribute( ATT_ORDER_BY_MODE , mode );
		String result = null;
		if ( orderBy != null && ( ATT_ORDER_BY_MODE_ASC.equals( mode ) || ATT_ORDER_BY_MODE_DESC.equals( mode ) ) ) {
			result = orderBy+" "+mode;
		}
		return result;
	}
	
}
