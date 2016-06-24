package org.morozko.java.mod.web.servlet.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.filter.HttpFilter;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.mod.web.servlet.response.HttpServletResponseByteData;

public class RequestLogFilter extends HttpFilter {

	public void destroy() {

	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequestBuffer req = new HttpServletRequestBuffer( request );
		HttpServletResponseByteData resp = new HttpServletResponseByteData( response );
		chain.doFilter( req, resp );
		ByteArrayOutputStream buffer = resp.getBaos();
		this.getLog().info( ">>>>> request start" );
		this.getLog().info( new String( req.getData() ) );
		this.getLog().info( ">>>>> request end" );
		this.getLog().info( ">>>>> response start" );
		this.getLog().info( new String( buffer.toByteArray() ) );
		this.getLog().info( ">>>>> response end" );
		buffer.writeTo( resp.getOriginalOutputStream() );
	}

	public void init(FilterConfig config) throws ServletException {

	}

}

class HttpServletRequestBuffer extends HttpServletRequestWrapper {

	private byte[] data;  
	
	public HttpServletRequestBuffer( HttpServletRequest req ) throws IOException {
		super(req);
		ByteArrayOutputStream input = new ByteArrayOutputStream();
		StreamIO.pipeStream( req.getInputStream() , input );
		this.data = input.toByteArray();
	}

	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStreamWrapper( new ByteArrayInputStream( this.data ) );
	}

	public BufferedReader getReader() throws IOException {
		return new BufferedReader( new InputStreamReader( this.getInputStream() ) );
	}

	public byte[] getData() {
		return data;
	}
	
}

class ServletInputStreamWrapper extends ServletInputStream {

	private InputStream wrapped;
	
	public int read() throws IOException {
		return this.wrapped.read();
	}

	public ServletInputStreamWrapper(InputStream wrapped) {
		super();
		this.wrapped = wrapped;
	}
	
}
