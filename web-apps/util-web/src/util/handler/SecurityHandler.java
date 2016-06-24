package util.handler;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityHandler {

	public static void handleUser( HttpServletRequest request, HttpServletResponse response, ServletContext context, String checkPassword ) throws Exception {
		String user = request.getParameter( "user" );
		String pass = request.getParameter( "pass" );
		boolean logged = false;
		if ( user != null && pass != null ) {
			if ( pass.equals( checkPassword ) ) {
				request.getSession().setAttribute( "currentUser" , user );
				RequestDispatcher rd = request.getRequestDispatcher( "/fs" );
				rd.forward( request , response );
				logged = true;
			}
		} 
		if ( !logged ) {
			PrintWriter pw = Util.startHtml(response);
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<form method='post' action='fs'>");
			pw.println("Username <input type='text' name='user'>");
			pw.println("Password <input type='password' name='pass'>");
			pw.println("<input type='submit' name='LogIn'>");
			pw.println("</form>");
			pw.println("</body>");
			pw.println("</html>");
		}
	}
	
}
