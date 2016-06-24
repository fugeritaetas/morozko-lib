/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.app.cms 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)CmsServlet.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.servlet
 * @creation   : 10/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.log.helpers.LogObjectServlet;
import org.morozko.java.core.ent.servlet.context.ContextHelper;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.JVFSFacade;
import org.morozko.java.core.jvfs.JVFSImpl;
import org.morozko.java.core.jvfs.cl.ClJMount;
import org.morozko.java.core.jvfs.file.RealJMount;
import org.morozko.java.core.lang.OpExec;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.core.util.ver.ProgramVersion;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsFileDescriptionDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsFileDescriptionModel;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.cms.web.facade.CmsPageFacade;
import org.morozko.java.mod.db.update.UpdateDbOp;
import org.morozko.java.mod.doc.DocBase;
import org.morozko.java.mod.doc.DocFacade;
import org.morozko.java.mod.sync.SyncFacade;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsServlet extends LogObjectServlet {


	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.getLog().info( "INIT : START" );
		super.init( config );
			
		try {
			String configFilePath = config.getInitParameter( "config-file" );
			File file = ContextHelper.resolvePath( this.getServletContext() , configFilePath );
			CmsConfig cmsConfig = CmsConfig.parse( DOMIO.loadDOMDoc( file ) );
			baseUrl = cmsConfig.getGeneralProps().getProperty( CmsConfig.PROP_BASE_MAP_URL );	
			this.getLog().info( "INIT : base_url '"+baseUrl+"'" );
			
			
			// esecuzione dell' update dell' applicazione
			if ( "1".equalsIgnoreCase( cmsConfig.getGeneralProps().getProperty( "update" ) ) ) {
				
				this.getLog().info( "INIT : starting update" );

				String jvfsUpdate = "cms-update-jvfs";
				
				RealJMount root = new RealJMount( new File( this.getServletContext().getRealPath( "/" ) ) );
				ClJMount update = new ClJMount( "org.morozko.java.mod.cms.web" );
				JVFSImpl jvfs = JVFSImpl.getInstance( root );
				jvfs.mount( update, "/mod-app-cms-jar" );
				JVFSFacade.registerJVFS( jvfsUpdate , jvfs );
				
				this.getLog().info( "INIT : loading versions " );
				JFile fileFrom = jvfs.getJFile( "/WEB-INF/cms-version.properties" );
				JFile fileTo = jvfs.getJFile( "/mod-app-cms-jar/update/webinf/cms-version.properties" );
				JFile fileSql = jvfs.getJFile( "/mod-app-cms-jar/update/sql/sqlupdate.properties" );
				if ( !fileFrom.exists() ) {
					fileFrom = jvfs.getJFile( "/mod-app-cms-jar/update/webinf/cms-no-version.properties" );
				}
				this.getLog().info( "INIT : from version file : "+fileFrom );
				this.getLog().info( "INIT : to   version file : "+fileTo );
				this.getLog().info( "INIT : sqlupdate    file : "+fileSql );
				ProgramVersion fromVersion = ProgramVersion.newInstance( PropsIO.loadFromStream( fileFrom.getInputStream() ) );
				ProgramVersion toVersion = ProgramVersion.newInstance( PropsIO.loadFromStream( fileTo.getInputStream() ) );
				
				this.getLog().info( "INIT : generating db update list..." );
				String dataBase = cmsConfig.getGeneralProps().getProperty( "db", "generic" );
				this.getLog().info( "INIT : database : "+dataBase );
				List updateDbList = UpdateDbOp.updateOpAll( fromVersion, toVersion, CmsConfig.CF_CMS_NAME, "/mod-app-cms-jar/update/sql/"+dataBase, jvfs, PropsIO.loadFromStream( fileSql.getInputStream() ) );
				this.getLog().info( "INIT : executing db update list... : "+updateDbList.size() );
				OpExec.doOps( updateDbList , false );
				this.getLog().info( "INIT : db update DONE" );
				
				this.getLog().info( "INIT : updating pages" );
				Properties updateList = PropsIO.loadFromClassLoader( "/org/morozko/java/mod/cms/web/res/updatelist.properties" );
				Enumeration e = updateList.keys();
				while ( e.hasMoreElements() ) {
					String key = (String)e.nextElement();
					String value = updateList.getProperty( key );
					JFile from = jvfs.getJFile( key );
					JFile to = jvfs.getJFile( value );
					this.getLog().info( "INIT : updating : from "+from+" to "+to+" ("+SyncFacade.syncFile( SyncFacade.DEFAULT_CHECKER , from, to)+")" );
				}
			}
			
			
			
		} catch (Exception e) {
			this.getLog().fatal( e );
			throw ( new ServletException( e ) );
		}

		this.getLog().info( "INIT : END" );
	}

	private static String baseUrl;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		this.getLog().debug( "url 1 : "+url );
		url = url.substring( request.getContextPath().length() );
		this.getLog().debug( "url 2 : "+url );
		url = url.substring( getBaseUrl().length() );
		this.getLog().debug( "url 3 : "+url );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			
			// rendering di un documento
			if ( url.indexOf( "/doc/" ) == 0 ) {
				String type = request.getParameter( "type" );
				if ( type == null ) {
					type = url.substring( url.lastIndexOf( "." )+1 );
				}
				
				this.getLog().info( "xml doc renderer : type : "+type );
				
				CmsPageFacade.handle( request, response );
				String data = (String)request.getAttribute( CmsPageModel.ATT_NAME_DATA );

				DocBase docBase = DocFacade.parse( new ByteArrayInputStream( data.getBytes() ) );
//				System.out.println( "RENDERING DOC START" );
//				DocFacade.print( System.out, docBase );
//				System.out.println( "RENDERING DOC END" );
				if ( "html".equalsIgnoreCase( type ) ) {
					this.getLog().info( "rendering html" );
					response.setContentType( "text/html" );
					DocFacade.createHTML(docBase, response.getOutputStream());
				} else if ( "pdf".equalsIgnoreCase( type ) ) {
					this.getLog().info( "rendering pdf" );
					response.setContentType( "application/pdf" );
					DocFacade.createPDF(docBase, response.getOutputStream());
				} else if ( "rtf".equalsIgnoreCase( type ) ) {
					this.getLog().info( "rendering rtf" );
					response.setContentType( "application/rtf" );
					DocFacade.createRTF(docBase, response.getOutputStream());
				}
			} else if ( url.indexOf( "/page/" ) == 0 ) {
				response.setContentType( "text/html" );
				url = url.substring( "/page".length() );
				this.getLog().debug( "url 4 : "+url );
				CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
				CmsPageModel cmsPageModel = cmsPageDAO.loadOneCmsPageUn2( url );
				StreamIO.pipeStream( new ByteArrayInputStream( cmsPageModel.getHtmlData().getBytes() ), baos, StreamIO.MODE_CLOSE_IN_ONLY );
			} else if ( url.indexOf( "/fragment" ) == 0 ) {
				response.setContentType( "text/html" );
				url = url.substring( "/fragment/".length() );
				this.getLog().debug( "url 5 : "+url );
//				CmsFragmentDAO cmsFragmentDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsFragmentDAO();
//				CmsFragmentModel cmsFragmentModel = cmsFragmentDAO.loadOneCmsFragmentUn2( url );
				//baos.write( cmsFragmentModel.getHtmlContent().getBytes() );
			} else if ( url.indexOf( "/file" ) == 0 ) {
				url = url.substring( "/file/".length() );
				this.getLog().debug( "url 6 : "+url );
				CmsFileDescriptionDAO cmsFileDescriptionDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsFileDescriptionDAO();
				CmsFileDescriptionModel cmsFileDescriptionModel = cmsFileDescriptionDAO.loadOneCmsFileDescriptionUn1( url );
				JVFS jvfs = CmsConfig.getInstance().getJvfs();
				JFile file = jvfs.getJFile( cmsFileDescriptionModel.getFilePath() );
				StreamIO.pipeStream( file.getInputStream() , response.getOutputStream(), StreamIO.MODE_CLOSE_IN_ONLY );
			}			
		} catch (Exception e) {
			throw ( new ServletException( e ) );
		}
		baos.writeTo( response.getOutputStream() );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064823697324054197L;

	/**
	 * @return the baseUrl
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}

}
