/*
 * @(#)WebAccessDataModelHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.core.model.helpers
 * @creation   : 03/10/2007 08/35/38
 */
package org.morozko.java.mod.web.dg.core.model.helpers;

import org.morozko.java.mod.web.dg.core.bean.WebAccessDataBean;

/**
 * <p>Oggetto di modello per WebAccessData.</p>
 *
 * @author Morozko
 */
public class WebAccessDataModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 11913933386358L;


	public static final String ATT_NAME = "webAccessDataModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idWebAccessData;

    /** 
     * <p>Restituisce il valore di idWebAccessData</p> 
     * 
     * @return      restituisce il valore di idWebAccessData
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdWebAccessData() {
        return this.idWebAccessData;
    }
    /** 
     * <p>Imposta il valore di idWebAccessData</p> 
     * 
     * @param      idWebAccessData il valore di idWebAccessData da impostare
     */ 
    public void setIdWebAccessData( org.morozko.java.mod.db.dao.DAOID idWebAccessData ) {
        this.idWebAccessData = idWebAccessData;
    }

	private String sessionId;

    /** 
     * <p>Restituisce il valore di sessionId</p> 
     * 
     * @return      restituisce il valore di sessionId
     */ 
    public String getSessionId() {
        return this.sessionId;
    }
    /** 
     * <p>Imposta il valore di sessionId</p> 
     * 
     * @param      sessionId il valore di sessionId da impostare
     */ 
    public void setSessionId( String sessionId ) {
        this.sessionId = sessionId;
    }

	private String requestUri;

    /** 
     * <p>Restituisce il valore di requestUri</p> 
     * 
     * @return      restituisce il valore di requestUri
     */ 
    public String getRequestUri() {
        return this.requestUri;
    }
    /** 
     * <p>Imposta il valore di requestUri</p> 
     * 
     * @param      requestUri il valore di requestUri da impostare
     */ 
    public void setRequestUri( String requestUri ) {
        this.requestUri = requestUri;
    }

	private String requestUrl;

    /** 
     * <p>Restituisce il valore di requestUrl</p> 
     * 
     * @return      restituisce il valore di requestUrl
     */ 
    public String getRequestUrl() {
        return this.requestUrl;
    }
    /** 
     * <p>Imposta il valore di requestUrl</p> 
     * 
     * @param      requestUrl il valore di requestUrl da impostare
     */ 
    public void setRequestUrl( String requestUrl ) {
        this.requestUrl = requestUrl;
    }

	private String requestMethod;

    /** 
     * <p>Restituisce il valore di requestMethod</p> 
     * 
     * @return      restituisce il valore di requestMethod
     */ 
    public String getRequestMethod() {
        return this.requestMethod;
    }
    /** 
     * <p>Imposta il valore di requestMethod</p> 
     * 
     * @param      requestMethod il valore di requestMethod da impostare
     */ 
    public void setRequestMethod( String requestMethod ) {
        this.requestMethod = requestMethod;
    }

	private String requestQueryString;

    /** 
     * <p>Restituisce il valore di requestQueryString</p> 
     * 
     * @return      restituisce il valore di requestQueryString
     */ 
    public String getRequestQueryString() {
        return this.requestQueryString;
    }
    /** 
     * <p>Imposta il valore di requestQueryString</p> 
     * 
     * @param      requestQueryString il valore di requestQueryString da impostare
     */ 
    public void setRequestQueryString( String requestQueryString ) {
        this.requestQueryString = requestQueryString;
    }

	private String requestContextPath;

    /** 
     * <p>Restituisce il valore di requestContextPath</p> 
     * 
     * @return      restituisce il valore di requestContextPath
     */ 
    public String getRequestContextPath() {
        return this.requestContextPath;
    }
    /** 
     * <p>Imposta il valore di requestContextPath</p> 
     * 
     * @param      requestContextPath il valore di requestContextPath da impostare
     */ 
    public void setRequestContextPath( String requestContextPath ) {
        this.requestContextPath = requestContextPath;
    }

	private String requestRemoteAddr;

    /** 
     * <p>Restituisce il valore di requestRemoteAddr</p> 
     * 
     * @return      restituisce il valore di requestRemoteAddr
     */ 
    public String getRequestRemoteAddr() {
        return this.requestRemoteAddr;
    }
    /** 
     * <p>Imposta il valore di requestRemoteAddr</p> 
     * 
     * @param      requestRemoteAddr il valore di requestRemoteAddr da impostare
     */ 
    public void setRequestRemoteAddr( String requestRemoteAddr ) {
        this.requestRemoteAddr = requestRemoteAddr;
    }

	private String requestRemoteHost;

    /** 
     * <p>Restituisce il valore di requestRemoteHost</p> 
     * 
     * @return      restituisce il valore di requestRemoteHost
     */ 
    public String getRequestRemoteHost() {
        return this.requestRemoteHost;
    }
    /** 
     * <p>Imposta il valore di requestRemoteHost</p> 
     * 
     * @param      requestRemoteHost il valore di requestRemoteHost da impostare
     */ 
    public void setRequestRemoteHost( String requestRemoteHost ) {
        this.requestRemoteHost = requestRemoteHost;
    }

	private String requestRemoteUser;

    /** 
     * <p>Restituisce il valore di requestRemoteUser</p> 
     * 
     * @return      restituisce il valore di requestRemoteUser
     */ 
    public String getRequestRemoteUser() {
        return this.requestRemoteUser;
    }
    /** 
     * <p>Imposta il valore di requestRemoteUser</p> 
     * 
     * @param      requestRemoteUser il valore di requestRemoteUser da impostare
     */ 
    public void setRequestRemoteUser( String requestRemoteUser ) {
        this.requestRemoteUser = requestRemoteUser;
    }

	private Integer requestRemotePort;

    /** 
     * <p>Restituisce il valore di requestRemotePort</p> 
     * 
     * @return      restituisce il valore di requestRemotePort
     */ 
    public Integer getRequestRemotePort() {
        return this.requestRemotePort;
    }
    /** 
     * <p>Imposta il valore di requestRemotePort</p> 
     * 
     * @param      requestRemotePort il valore di requestRemotePort da impostare
     */ 
    public void setRequestRemotePort( Integer requestRemotePort ) {
        this.requestRemotePort = requestRemotePort;
    }

	private String requestServerName;

    /** 
     * <p>Restituisce il valore di requestServerName</p> 
     * 
     * @return      restituisce il valore di requestServerName
     */ 
    public String getRequestServerName() {
        return this.requestServerName;
    }
    /** 
     * <p>Imposta il valore di requestServerName</p> 
     * 
     * @param      requestServerName il valore di requestServerName da impostare
     */ 
    public void setRequestServerName( String requestServerName ) {
        this.requestServerName = requestServerName;
    }

	private Integer requestServerPort;

    /** 
     * <p>Restituisce il valore di requestServerPort</p> 
     * 
     * @return      restituisce il valore di requestServerPort
     */ 
    public Integer getRequestServerPort() {
        return this.requestServerPort;
    }
    /** 
     * <p>Imposta il valore di requestServerPort</p> 
     * 
     * @param      requestServerPort il valore di requestServerPort da impostare
     */ 
    public void setRequestServerPort( Integer requestServerPort ) {
        this.requestServerPort = requestServerPort;
    }

	private String requestScheme;

    /** 
     * <p>Restituisce il valore di requestScheme</p> 
     * 
     * @return      restituisce il valore di requestScheme
     */ 
    public String getRequestScheme() {
        return this.requestScheme;
    }
    /** 
     * <p>Imposta il valore di requestScheme</p> 
     * 
     * @param      requestScheme il valore di requestScheme da impostare
     */ 
    public void setRequestScheme( String requestScheme ) {
        this.requestScheme = requestScheme;
    }

	private Integer responseStatusCode;

    /** 
     * <p>Restituisce il valore di responseStatusCode</p> 
     * 
     * @return      restituisce il valore di responseStatusCode
     */ 
    public Integer getResponseStatusCode() {
        return this.responseStatusCode;
    }
    /** 
     * <p>Imposta il valore di responseStatusCode</p> 
     * 
     * @param      responseStatusCode il valore di responseStatusCode da impostare
     */ 
    public void setResponseStatusCode( Integer responseStatusCode ) {
        this.responseStatusCode = responseStatusCode;
    }

	private String applicationHost;

    /** 
     * <p>Restituisce il valore di applicationHost</p> 
     * 
     * @return      restituisce il valore di applicationHost
     */ 
    public String getApplicationHost() {
        return this.applicationHost;
    }
    /** 
     * <p>Imposta il valore di applicationHost</p> 
     * 
     * @param      applicationHost il valore di applicationHost da impostare
     */ 
    public void setApplicationHost( String applicationHost ) {
        this.applicationHost = applicationHost;
    }

	private java.sql.Timestamp requestStart;

    /** 
     * <p>Restituisce il valore di requestStart</p> 
     * 
     * @return      restituisce il valore di requestStart
     */ 
    public java.sql.Timestamp getRequestStart() {
        return this.requestStart;
    }
    /** 
     * <p>Imposta il valore di requestStart</p> 
     * 
     * @param      requestStart il valore di requestStart da impostare
     */ 
    public void setRequestStart( java.sql.Timestamp requestStart ) {
        this.requestStart = requestStart;
    }

	private java.sql.Timestamp requestEnd;

    /** 
     * <p>Restituisce il valore di requestEnd</p> 
     * 
     * @return      restituisce il valore di requestEnd
     */ 
    public java.sql.Timestamp getRequestEnd() {
        return this.requestEnd;
    }
    /** 
     * <p>Imposta il valore di requestEnd</p> 
     * 
     * @param      requestEnd il valore di requestEnd da impostare
     */ 
    public void setRequestEnd( java.sql.Timestamp requestEnd ) {
        this.requestEnd = requestEnd;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public WebAccessDataBean getBean() {
        WebAccessDataBean bean = new WebAccessDataBean();
        bean.setIdWebAccessData( formatObject(idWebAccessData) );
        bean.setSessionId( formatObject(sessionId) );
        bean.setRequestUri( formatObject(requestUri) );
        bean.setRequestUrl( formatObject(requestUrl) );
        bean.setRequestMethod( formatObject(requestMethod) );
        bean.setRequestQueryString( formatObject(requestQueryString) );
        bean.setRequestContextPath( formatObject(requestContextPath) );
        bean.setRequestRemoteAddr( formatObject(requestRemoteAddr) );
        bean.setRequestRemoteHost( formatObject(requestRemoteHost) );
        bean.setRequestRemoteUser( formatObject(requestRemoteUser) );
        bean.setRequestRemotePort( formatObject(requestRemotePort) );
        bean.setRequestServerName( formatObject(requestServerName) );
        bean.setRequestServerPort( formatObject(requestServerPort) );
        bean.setRequestScheme( formatObject(requestScheme) );
        bean.setResponseStatusCode( formatObject(responseStatusCode) );
        bean.setApplicationHost( formatObject(applicationHost) );
        bean.setRequestStart( formatObject(requestStart) );
        bean.setRequestEnd( formatObject(requestEnd) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idWebAccessData=" );
        buffer.append( this.idWebAccessData );
        buffer.append( "; " );
        buffer.append( "sessionId=" );
        buffer.append( this.sessionId );
        buffer.append( "; " );
        buffer.append( "requestUri=" );
        buffer.append( this.requestUri );
        buffer.append( "; " );
        buffer.append( "requestUrl=" );
        buffer.append( this.requestUrl );
        buffer.append( "; " );
        buffer.append( "requestMethod=" );
        buffer.append( this.requestMethod );
        buffer.append( "; " );
        buffer.append( "requestQueryString=" );
        buffer.append( this.requestQueryString );
        buffer.append( "; " );
        buffer.append( "requestContextPath=" );
        buffer.append( this.requestContextPath );
        buffer.append( "; " );
        buffer.append( "requestRemoteAddr=" );
        buffer.append( this.requestRemoteAddr );
        buffer.append( "; " );
        buffer.append( "requestRemoteHost=" );
        buffer.append( this.requestRemoteHost );
        buffer.append( "; " );
        buffer.append( "requestRemoteUser=" );
        buffer.append( this.requestRemoteUser );
        buffer.append( "; " );
        buffer.append( "requestRemotePort=" );
        buffer.append( this.requestRemotePort );
        buffer.append( "; " );
        buffer.append( "requestServerName=" );
        buffer.append( this.requestServerName );
        buffer.append( "; " );
        buffer.append( "requestServerPort=" );
        buffer.append( this.requestServerPort );
        buffer.append( "; " );
        buffer.append( "requestScheme=" );
        buffer.append( this.requestScheme );
        buffer.append( "; " );
        buffer.append( "responseStatusCode=" );
        buffer.append( this.responseStatusCode );
        buffer.append( "; " );
        buffer.append( "applicationHost=" );
        buffer.append( this.applicationHost );
        buffer.append( "; " );
        buffer.append( "requestStart=" );
        buffer.append( this.requestStart );
        buffer.append( "; " );
        buffer.append( "requestEnd=" );
        buffer.append( this.requestEnd );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
