package util.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Util {

	public static PrintWriter startHtml( HttpServletResponse response ) throws IOException {
		response.setContentType( "text/html" );
		return new PrintWriter( response.getWriter() );
	}
	
}
