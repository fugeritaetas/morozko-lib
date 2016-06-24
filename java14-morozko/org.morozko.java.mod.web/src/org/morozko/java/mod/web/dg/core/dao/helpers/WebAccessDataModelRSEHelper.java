/*
 * @(#)WebAccessDataModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.core.dao.helpers
 * @creation   : 03/10/2007 08/35/38
 */
package org.morozko.java.mod.web.dg.core.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti WebAccessDataModel da un ResultSet.</p>
 *
 * @author Morozko
 */
import org.morozko.java.mod.web.dg.core.model.WebAccessDataModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class WebAccessDataModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 119139333873466L;

    public Object extractNext(ResultSet rs) throws SQLException {
        WebAccessDataModel model = new WebAccessDataModel();
        model.setIdWebAccessData( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_web_access_data") ) );
        model.setSessionId( rs.getString("session_id") );
        model.setRequestUri( rs.getString("request_uri") );
        model.setRequestUrl( rs.getString("request_url") );
        model.setRequestMethod( rs.getString("request_method") );
        model.setRequestQueryString( rs.getString("request_query_string") );
        model.setRequestContextPath( rs.getString("request_context_path") );
        model.setRequestRemoteAddr( rs.getString("request_remote_addr") );
        model.setRequestRemoteHost( rs.getString("request_remote_host") );
        model.setRequestRemoteUser( rs.getString("request_remote_user") );
        model.setRequestRemotePort( new Integer( rs.getInt("request_remote_port") ) );
        model.setRequestServerName( rs.getString("request_server_name") );
        model.setRequestServerPort( new Integer( rs.getInt("request_server_port") ) );
        model.setRequestScheme( rs.getString("request_scheme") );
        model.setResponseStatusCode( new Integer( rs.getInt("response_status_code") ) );
        model.setApplicationHost( rs.getString("application_host") );
        model.setRequestStart( rs.getTimestamp("request_start") );
        model.setRequestEnd( rs.getTimestamp("request_end") );
        return model;
    }

}
