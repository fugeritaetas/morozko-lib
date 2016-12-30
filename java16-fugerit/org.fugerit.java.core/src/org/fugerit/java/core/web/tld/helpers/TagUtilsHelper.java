package org.fugerit.java.core.web.tld.helpers;

import java.lang.reflect.Method;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.fugerit.java.core.lang.helpers.StringHelper;


/**
 * <p>Libreria di funzioni di sostegno nello sviluppo di Tag Personalizzati.</p>
 * 
 * <p>In particolare vengono fornite delle funzioni per accedere ad attributi impostato nelle mappa applicative
 * di un ServeletContainer, o per impostarli nelle stesse.</p>
 *
 * @author Morozko
 * @since JLIB-CORE 1.0
 */
public class TagUtilsHelper {

	/**
	 * Costante rappresentante la mappa di contesto (context).
	 */
	public final static String SCOPE_CONTEXT = "context";

	/**
	 * Costante rappresentante la mappa di sessione (session).
	 */	
	public final static String SCOPE_SESSION = "session";

	/**
	 * Costante rappresentante la mappa della richiesta (request).
	 */	
	public final static String SCOPE_REQUEST = "request";

	/**
	 * Costante rappresentante la mappa della pagina (page).
	 */	
	public final static String SCOPE_PAGE = "page";

	
	/**
	 * <p>Imposta un attributo nella mappa specificata, se lo scop è lasciato a null, viene utilizzato 'page'.</p>
	 * 
	 * @param pageContext	il page context del tag
	 * @param scope			la mappa dove impostare l'attributo (<code>null</code> viene preso come 'page')
	 * @param name			il nome da attribuire all'attributo
	 * @param value			il valore da attribuire all'attributo
	 */
	public static void setAttibute( PageContext pageContext, String scope, String name, Object value ) throws JspException {
		// imposto lo scope, se non è definito imposto allo scope di default ( SCOPE_PAGE ).
		scope = StringHelper.value( scope, SCOPE_PAGE );
		// verifico dove va impostato lo scope
		if ( scope.equalsIgnoreCase( SCOPE_PAGE ) ) {
			pageContext.setAttribute( name, value );
		} else if ( scope.equalsIgnoreCase( SCOPE_REQUEST ) ) {
			pageContext.getRequest().setAttribute( name, value );
		} else if ( scope.equalsIgnoreCase( SCOPE_SESSION ) ) {
			((HttpServletRequest)pageContext.getRequest()).setAttribute( name, value );
		} else if ( scope.equalsIgnoreCase( SCOPE_CONTEXT ) ) {
			pageContext.getServletContext().setAttribute( name, value );
		}		
	}	

	/**
	 * <p>Trova un bean impostato in una delle mappa dell' applicazione.</p>
	 * 
	 * @param pageContext	il page context del tag
	 * @param scope			la mappa dove cercare l'attributo (<code>null</code> per cercarlo in tutti).
	 * @param name			il nome dell' attributo da cercare
	 * @param property		la proprietà da cercare sull' oggetto trovato dal parametro name (se <code>null</code> viene riportato l'oggetto stesso).
	 * @return				l'oggetto richiesto
	 * @throws JspException
	 */
	public static Object findAttibute( PageContext pageContext, String scope, String name, String property ) throws JspException {		
		Object result = null;
		// cerco l'oggetto nel percorso specificato
		if ( scope == null ) {
			result = pageContext.findAttribute( name );	
		} else if ( scope.equalsIgnoreCase( SCOPE_PAGE ) ) {
			result = pageContext.getAttribute( name );
		} else if ( scope.equalsIgnoreCase( SCOPE_REQUEST ) ) {
			result = pageContext.getRequest().getAttribute( name );
		} else if ( scope.equalsIgnoreCase( SCOPE_SESSION ) ) {
			result = ((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute( name );
		} else if ( scope.equalsIgnoreCase( SCOPE_CONTEXT ) ) {
			result = pageContext.getServletContext().getAttribute( name );
		}
		// se la proprietà è stata impostata la invoco sull' oggetto trovato
		if ( property != null ) {
			try {
				Class[] c = new Class[0];
				Object[] o = new Object[0];
				Method method = result.getClass().getMethod( "get"+property.substring( 0, 1 ).toUpperCase()+property.substring( 1 ), c );
				result = method.invoke( result, o );
			} catch (Exception e) {
				throw ( new JspException( e ) );
			}
		}
		return result;
	}

}
