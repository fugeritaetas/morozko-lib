package org.morozko.java.mod.web.servlet.extmap;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.log.helpers.LogObjectServlet;

public class ExtMapServlet extends LogObjectServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		int indexPath = url.indexOf( contextPath );
		url = url.substring( indexPath+contextPath.length() );
		this.getLog().info( "base   url : "+url );
		ExtMapProvider provider = (ExtMapProvider)this.getServletContext().getAttribute( ExtMapProvider.ATT_NAME );
		url = provider.map( url );
		this.getLog().info( "forward to : "+url );
		RequestDispatcher rd = req.getRequestDispatcher( url );
		rd.forward( req, resp );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986871639744359124L;

}
