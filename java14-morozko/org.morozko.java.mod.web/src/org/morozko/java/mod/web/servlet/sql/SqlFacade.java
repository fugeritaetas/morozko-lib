package org.morozko.java.mod.web.servlet.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.web.servlet.sql.query.Result;

public class SqlFacade {

	public static void handle(HttpServletRequest request, HttpServletResponse response, SqlConfig sqlConfig ) throws ServletException, IOException {
		String query = request.getParameter( "query" );
		String update = request.getParameter( "update" );
		List messageList = new ArrayList();
		if ( query != null ) {
			SqlQuery sqlQuery = sqlConfig.getQuery( query );
			try {
				ConnectionFactory cf = sqlConfig.getConnectionFactory();
				if ( update != null ) {
					String updateName = request.getParameter( "name" );
					String updateKey = request.getParameter( "key" );
					String updateValue = request.getParameter( "value" );
					SqlUpdate sqlUpdate = (SqlUpdate)sqlQuery.getUpdateMap().get( updateName );
					StringBuffer buffer = new StringBuffer();
					buffer.append( "update " );
					buffer.append( sqlUpdate.getTable() );
					buffer.append( " set " );
					buffer.append( sqlUpdate.getColumn() );
					buffer.append( " = " );
					buffer.append( updateValue );
					buffer.append( " WHERE " );
					buffer.append( sqlQuery.getKey() );
					buffer.append( " = " );	
					buffer.append( updateKey );
					UpdateDAO dao = new UpdateDAO( cf );
					if ( dao.update( buffer.toString() ) > 0 ) {
						messageList.add( "OK" );
					}
				}
				Result result = Result.query(cf, sqlQuery);
				request.setAttribute( "output" , result );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		request.setAttribute( "messageList" , messageList );
	}
	
}
