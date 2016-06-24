/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)BasicImageServlet.java
 *
 * @project  : org.morozko.java.core.ent
 * @package  : org.morozko.java.core.ent.servlet.http
 * @creation : 13-feb-2006
 */
package org.morozko.java.core.ent.servlet.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.utils.ImageLibs;
import org.morozko.java.core.io.StreamIO;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class BasicImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4638584083387324933L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String img = uri.substring( uri.lastIndexOf( "/" )+1 ); 
		OutputStream out = response.getOutputStream();
		InputStream is = null;
		if ( img.equalsIgnoreCase( "back.gif" ) ) {
			is = new ByteArrayInputStream( ImageLibs.IMAGE_DATA_HTML_RENDER_BACK );
		} else {
			is = new ByteArrayInputStream( ImageLibs.IMAGE_DATA_HTML_RENDER_COMPRESSED );
		}
		StreamIO.pipeStream( is, out, StreamIO.MODE_CLOSE_IN_ONLY );
	}

}
