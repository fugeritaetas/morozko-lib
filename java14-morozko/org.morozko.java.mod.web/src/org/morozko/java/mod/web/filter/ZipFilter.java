package org.morozko.java.mod.web.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.filter.HttpFilter;
import org.morozko.java.mod.web.servlet.request.HttpServletRequestUrlChanger;
import org.morozko.java.mod.web.servlet.response.HttpServletResponseByteData;

public class ZipFilter extends HttpFilter {
	
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	public static final int COMPRESS_MODE_NONE = 0;
	
	public static final int COMPRESS_MODE_ZIP = 1;
	
	public static final int COMPRESS_MODE_GZIP = 2;
	
	private static final String EXT_ZIP = ".zip";
	
	private static final String EXT_GZIP = ".gz";
	
	
	private static final CompressMode MODE_ZIP = new CompressMode( COMPRESS_MODE_ZIP , EXT_ZIP );
	private static final CompressMode MODE_GZIP = new CompressMode( COMPRESS_MODE_GZIP , EXT_GZIP );
	
	private static final CompressMode MODE_LIST[] = { MODE_ZIP, MODE_GZIP};
	
	public static void zip( byte[] data, String fileName, HttpServletResponse response ) throws IOException {
		response.setContentType( "application/zip" );
		String contentDisposition = "attachment; filename="+fileName+EXT_ZIP;
		response.addHeader("Content-Disposition", contentDisposition );
		ZipOutputStream zos = new ZipOutputStream( response.getOutputStream() );
		ZipEntry ze = new ZipEntry( fileName );
		zos.putNextEntry( ze );
		zos.write( data );
		zos.closeEntry();
		zos.close();
	}

	public static void gzip( byte[] data, String fileName, HttpServletResponse response ) throws IOException {
		response.setContentType( "application/gzip" );
		String contentDisposition = "attachment; filename="+fileName+EXT_GZIP;
		response.addHeader("Content-Disposition", contentDisposition );
		GZIPOutputStream gos = new GZIPOutputStream( response.getOutputStream() );
		gos.write( data );
		gos.close();
	}

	public static boolean checkExt( String fileName, String ext ) {
		return fileName.lastIndexOf( ext ) == fileName.length()-ext.length();
	}
	
	public static int checkMode( String url ) {
		int mode = COMPRESS_MODE_NONE;
		for ( int k=0; k<MODE_LIST.length; k++ ) {
			CompressMode current = MODE_LIST[k];
			if ( checkExt( url , current.getExt() ) ) {
				mode = current.getMode();
			}
		}
		return mode;
	}

	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = request.getRequestURI();
		int mode = checkMode( url );
		if ( mode != COMPRESS_MODE_NONE ) {
			int index1 = url.lastIndexOf( '/' );
			String text = url.substring( index1+1 );
			int index2 = text.lastIndexOf( '.' );
			String replace = text.substring( 0, index2 );
			HttpServletRequest req = new HttpServletRequestUrlChanger( request, text, replace );
			HttpServletResponseByteData res = new HttpServletResponseByteData( response );
			chain.doFilter( req, res );
			if ( mode == COMPRESS_MODE_ZIP ) {
				zip( res.getBaos().toByteArray() , replace, response );
			} else if ( mode == COMPRESS_MODE_GZIP ) {
				gzip( res.getBaos().toByteArray() , replace, response );
			}
		} else {
			chain.doFilter( request, response );	
		}
	}

	public void destroy() {
		
	}

}

class CompressMode {
	
	private int mode;

	private String ext;

	public CompressMode(int mode, String ext) {
		super();
		this.mode = mode;
		this.ext = ext;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
