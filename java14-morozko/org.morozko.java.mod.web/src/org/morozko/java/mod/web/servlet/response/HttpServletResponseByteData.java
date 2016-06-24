package org.morozko.java.mod.web.servlet.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.doc.filter.DataServletOutputStream;

public class HttpServletResponseByteData extends HttpServletResponseWrapper  {

//	/* (non-Javadoc)
//	 * @see javax.servlet.ServletResponseWrapper#setContentType(java.lang.String)
//	 */
//	public void setContentType(String type) {
//		throw ( new RuntimeException( "methodod setContentType unsupported on HttpServletResponseData" ) );
//	}

	private PrintWriter writer = null;
	
	private ServletOutputStream stream = null;
	
	private ByteArrayOutputStream baos;

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getOutputStream()
	 */
	public ServletOutputStream getOriginalOutputStream() throws IOException {
		return super.getOutputStream();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getWriter()
	 */
	public PrintWriter getOriginalWriter() throws IOException {
		return super.getWriter();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getOutputStream()
	 */
	public ServletOutputStream getOutputStream() throws IOException {
		this.stream = new DataServletOutputStream( baos );
		return this.stream;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getWriter()
	 */
	public PrintWriter getWriter() throws IOException {
		this.writer = new PrintWriter( new OutputStreamWriter( this.getOutputStream() ), true );
		return this.writer;
	}

	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di HttpServletResponseData.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of HttpServletResponseData.</p></jdl:text>
	 * </jdl:section>
	 *
	 * @param arg0
	 */
	public HttpServletResponseByteData(HttpServletResponse response) {
		super(response);
		this.baos = new ByteArrayOutputStream();
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo baos.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of baos.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo baos.</jdl:text>
	 *         		<jdl:text lang='en'>the value of baos.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public ByteArrayOutputStream getBaos() {
		return baos;
	}
	
	public void flush() throws IOException {
		if ( this.writer != null ) {
			this.writer.flush();
		}
		if ( this.stream != null ) {
			this.stream.flush();
		}
	}

//	public void setCharacterEncoding(String charset) {
//		LogFacade.getLog().info( "HttpServletResponseByteData.setCharacterEncoding() do nothing : operation not allowed here" );
//	}

	public void setContentType(String type) {
		LogFacade.getLog().info( "HttpServletResponseByteData.setContentType() do nothing : operation not allowed here" );
	}
		
}
