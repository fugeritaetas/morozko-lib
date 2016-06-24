package org.morozko.java.mod.web.upload.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.log.helpers.LogObjectServlet;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.mod.web.upload.dg.model.UploadModel;
import org.morozko.java.mod.web.upload.facade.UploadFacade;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends LogObjectServlet {
	
	private static final long serialVersionUID = 124324324L;
       
    /**
     * @see LogObjectServlet#LogObjectServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		int index = url.lastIndexOf( "/" );
		String path = url.substring( index+1 );
		UploadFacade facade = UploadFacade.getFromContext( this.getServletContext() );
		try {
			UploadModel model = facade.load( path );
			StreamIO.pipeStream( new ByteArrayInputStream( model.getFileData() ) , response.getOutputStream() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
