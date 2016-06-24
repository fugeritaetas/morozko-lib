package org.morozko.java.mod.doc.type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.doc.filter.DocContext;
import org.morozko.java.mod.doc.filter.DocTypeHandler;
import org.w3c.dom.Element;

public class BasicTypeHandler extends BasicLogObject implements DocTypeHandler {

	public void setContentDisposition( HttpServletRequest request, HttpServletResponse response, DocContext docContext ) {
		setContentDisposition(request, response, docContext.getFileName() );
	}
	
	public void setContentDisposition( HttpServletRequest request, HttpServletResponse response, String fileName ) {
		response.addHeader("Content-Disposition", "attachment; filename="+fileName );
	}
	
	private String type;
	
	private String extension;

	
	public String getExtension() {
		return this.extension;
	}

	public String getType() {
		return this.type;
	}

	public BasicTypeHandler(String type, String extension) {
		super();
		this.type = type;
		this.extension = extension;
	}

	public void handleDocTypeInit(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {
		this.setContentDisposition( request, response, docContext );
	}
	
	public void handleDocType(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {
	}

	public void handleDocTypePost(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {
	}

	public void init(Element config) throws ConfigException {
	}

}
