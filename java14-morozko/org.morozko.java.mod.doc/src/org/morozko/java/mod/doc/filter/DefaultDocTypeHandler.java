package org.morozko.java.mod.doc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.w3c.dom.Element;

public abstract class DefaultDocTypeHandler extends BasicLogObject implements DocTypeHandler {

	public void handleDocType(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {

	}

	public void handleDocTypeInit(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {

	}

	public void handleDocTypePost(HttpServletRequest request, HttpServletResponse response, DocContext docContext) throws Exception {

	}

	public void init(Element config) throws ConfigException {

	}

}
