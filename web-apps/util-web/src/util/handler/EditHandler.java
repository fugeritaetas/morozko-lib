package util.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditHandler {

	public static void handleEdit( HttpServletRequest request, HttpServletResponse response, ServletContext context ) throws Exception {
		
		String path = request.getParameter( "path" );
		File currentFile = new File( path );
		String fileContent = null;
		
		if ( request.getParameter( "save" ) != null ) {
			fileContent = request.getParameter( "fileContent" );
			FileOutputStream fos = new FileOutputStream( currentFile );
			fos.write( fileContent.getBytes() );
			fos.close();
		} else {
			
			byte buffer[] = new byte[1024];
			FileInputStream fis = new FileInputStream(currentFile);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			for ( int read = fis.read(buffer); read > 0; read = fis.read(buffer) ) {
				os.write( buffer, 0, read );
			}
			fis.close();
			fileContent = os.toString();			
		}
		
		PrintWriter pw = Util.startHtml(response);
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<form method='post' action='fs'>");
		pw.println("<input type='hidden' name='action' value='edit'>");
		pw.println("<input type='hidden' name='path' value='"+path+"'>");
		pw.println("<textarea cols='120' rows='40' name='fileContent'>"+fileContent+"</textarea>");
		pw.println("<input type='submit' name='save'>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
	
}
