package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.log.helpers.LogObjectServlet;

import util.handler.EditHandler;
import util.handler.ExecHandler;
import util.handler.FileHandler;
import util.handler.SecurityHandler;
import util.handler.Util;

/**
 * Servlet implementation class UtilityServlet
 */
public class UtilityServlet extends LogObjectServlet {
	
	private static final long serialVersionUID = 133432432432L;
       
	private String usePassword;
	
	private int maxPostSize = 1024*1024;
	
	private String filterHost;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilityServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String remoteHost = request.getRemoteAddr();
		this.getLog().info( "UtilityServlet remoteHost : '"+remoteHost+"' filterHost : '"+this.filterHost+"'" );
		if ( remoteHost.indexOf( this.filterHost ) != -1 ) {
	        try {
	        	String currentUser = (String)request.getSession().getAttribute( "currentUser" );
	        	ServletContext context = this.getServletContext();
	        	if ( this.usePassword == null || currentUser != null ) {
	        		String action = request.getParameter( "action" );
	                if ( action == null ) {
	                	FileHandler.handleFile(request, response, context, this.maxPostSize );
	                } else if ( "edit".equalsIgnoreCase( action ) ) {
	                	EditHandler.handleEdit(request, response, context);
	                } else if ( "exec".equalsIgnoreCase( action ) ) {
	                	ExecHandler.handleEdit(request, response, context);
	                }
	        	} else {
	        		SecurityHandler.handleUser(request, response, context, this.usePassword);
	        	}
	        } catch(Exception e) {
	            PrintWriter pw = Util.startHtml(response);
	            pw.println("<pre>");
	            e.printStackTrace(pw);
	            pw.println("</pre>");
	        }
		} else {
			response.sendError( HttpServletResponse.SC_NOT_FOUND );
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.usePassword = config.getInitParameter( "usePassword" );
		try {
			this.maxPostSize = Integer.parseInt( config.getInitParameter( "maxPostSize" ) );
		} catch (Exception e) {}
		try {
			this.filterHost = config.getInitParameter( "filterHost" );
			if ( this.filterHost == null ) {
				this.filterHost = "";
			}
		} catch (Exception e) {}
		System.out.println( "UtilityServlet startup OK" );
	}
	
}
