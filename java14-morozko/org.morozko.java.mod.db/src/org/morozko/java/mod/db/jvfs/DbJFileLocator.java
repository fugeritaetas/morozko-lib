/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)DbJFileLocator.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import org.morozko.java.core.util.path.PathResolver;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFileLocator {

	private static String set( String value, String def ) {
		String result = value;
		if ( value == null ) {
			result = def;
		}
		return result;
	}
	
	public String toString() {
		return this.getClass().getName()+"[filePath:"+this.getFilePath()+",fileName:"+this.getFileName()+"]";
	}
	
	public static DbJFileLocator parse( String fullPath, PathResolver pathResolver ) {
		DbJFileLocator dbJFileLocator = new DbJFileLocator();
		dbJFileLocator.setFileName( set( pathResolver.getName( fullPath ), null ) );
		dbJFileLocator.setFilePath( set( pathResolver.getParent( fullPath ), "/" ) );
		return dbJFileLocator;
	}
	
	private String fileName;
	
	private String filePath;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
