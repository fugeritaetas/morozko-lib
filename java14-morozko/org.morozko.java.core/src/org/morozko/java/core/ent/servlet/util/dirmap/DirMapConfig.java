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
 * @(#)DirMapConfig.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.util.dirmap
 * @creation   : 3-mag-2006
 */
package org.morozko.java.core.ent.servlet.util.dirmap;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class DirMapConfig {
	
	public DirMapConfig() {
		this.welcomeFileList = new ArrayList();
	}
	
	public static DirMapConfig configure( Document doc ) {
		SearchDOM searchDOM = SearchDOM.newInstance( true, true );
		DirMapConfig dirMapConfig = new DirMapConfig();
		Element root = doc.getDocumentElement();
		Element generalConfig = searchDOM.findTag( root, "general-config" );
		Properties props = DOMUtils.attributesToProperties( generalConfig );
		dirMapConfig.setAllowListing( "1".equalsIgnoreCase( props.getProperty( "allow-listing" ) ) );
		dirMapConfig.setDirectoryMapPath( props.getProperty( "directory-map-path" ) );
		dirMapConfig.setImageBaseUrl( props.getProperty( "image-base-url" ) );
		dirMapConfig.setExcludePath( props.getProperty( "exclude-path" ) );
		Element welcomeFileListTag = searchDOM.findTag( root , "welcome-file-list" );
		if ( welcomeFileListTag != null ) {
			List welcomeFileTags = searchDOM.findAllTags( welcomeFileListTag , "welcome-file" );
			Iterator welcomeFileTagsIt = welcomeFileTags.iterator();
			while ( welcomeFileTagsIt.hasNext() ) {
				Element welcomeFile = (Element)welcomeFileTagsIt.next();
				String welcomeFileText = searchDOM.findText( welcomeFile );
				dirMapConfig.welcomeFileList.add( welcomeFileText );
			}
		}
		return dirMapConfig;
	}
	
	public File renderFile( String filePath ) {
		if ( filePath.indexOf( this.getExcludePath() )==0 ) {
			filePath = filePath.substring( this.excludePath.length() );
		}
		File file = new File( this.directoryMapPath, filePath );
		return file;
	}
	
	public boolean isRoot( File file ) throws IOException {
		File rootFile = new File( this.getDirectoryMapPath() );
		File testFile = new File( file.getCanonicalPath()+File.separator );
		return rootFile.equals( file ) || rootFile.equals( testFile );
	}
	
	private String excludePath;
	
	private String directoryMapPath;
	
	private String imageBaseUrl;
	
	private boolean allowListing;
	
	private List welcomeFileList;

	public boolean isAllowListing() {
		return allowListing;
	}

	public boolean isWelcomeFile( String fileName ) {
		return this.welcomeFileList.contains( fileName );
	}
	
	public void setAllowListing(boolean allowListing) {
		this.allowListing = allowListing;
	}

	public String getDirectoryMapPath() {
		return directoryMapPath;
	}

	public void setDirectoryMapPath(String directoryMapPath) {
		this.directoryMapPath = directoryMapPath;
	}

	public String getImageBaseUrl() {
		return imageBaseUrl;
	}

	public void setImageBaseUrl(String imageBaseUrl) {
		this.imageBaseUrl = imageBaseUrl;
	}

	/**
	 * @return Restituisce il valore di excludePath.
	 */
	public String getExcludePath() {
		return excludePath;
	}

	/**
	 * @param excludePath il valore di excludePath da impostare.
	 */
	public void setExcludePath(String excludePath) {
		this.excludePath = excludePath;
	}
	
}
