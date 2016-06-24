/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)ProgramVersion.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.util.ver
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.util.ver;

import java.sql.Date;
import java.util.Properties;

import org.morozko.java.core.util.PropsIO;
import org.morozko.java.core.util.TypedProperties;

/**
 * <p>Oggetto che incapsula un oggetto <code>java.util.Properties</code>
 * contenente informazioni concernenti la versione corrente di un software.</p>
 *
 * @author Morozko
 *
 */
public class ProgramVersion {

	public static final String PROP_SOFTWARE_NAME = "software-name";
	public static final String PROP_APPLICATION_SITE = "application-site";
	public static final String PROP_AUTHOR_NAME = "author-name";
	public static final String PROP_MAJOR_VERSION = "major-version";
	public static final String PROP_MINOR_VERSION = "minor-version";
	public static final String PROP_VERSION_DATE = "version-date";
	public static final String PROP_SOFTWARE_DESCRIPTION = "software-description";
	
	public static ProgramVersion newInstance( Properties props ) {
		return new ProgramVersion( props );
	}
	
	public String toString() {
		return this.getClass().getName()+"["+this.getVersion()+"]";
	}

	public String getSoftwareName() {
		return this.properties.getProperty( PROP_MAJOR_VERSION );
	}
	
	public String getMajorVersion() {
		return this.properties.getProperty( PROP_MAJOR_VERSION );
	}	
	
	public String getMinorVersion() {
		return this.properties.getProperty( PROP_MINOR_VERSION );
	}
	
	public String getVersion() {
		return this.getMajorVersion()+"."+this.getMinorVersion()+"["+this.getVersionDate()+"]";
	}
	
	public Date getVersionDate() {
		return this.properties.getDateProperty( PROP_VERSION_DATE );
	}	
	
	public String getAuthorName() {
		return this.properties.getProperty( PROP_AUTHOR_NAME );
	}
	
	public String getApplicationSite() {
		return this.properties.getProperty( PROP_APPLICATION_SITE );
	}	
	
	public String getSoftwareDescription() {
		return this.properties.getProperty( PROP_SOFTWARE_DESCRIPTION );
	}		
	
	public String getMajorMinorVersion() {
		return this.getMajorVersion()+"."+this.getMinorVersion();
	}
	
	public boolean isEqualVersioneThan( ProgramVersion pv ) {
		return this.getVersion().compareTo( pv.getMajorMinorVersion() )==0;
	}
	
	public boolean isOlderVersionThan( ProgramVersion pv ) {
		return this.getVersion().compareTo( pv.getMajorMinorVersion() )<0;
	}	
	
	public boolean isNewerVersionThan( ProgramVersion pv ) {
		return this.getVersion().compareTo( pv.getMajorMinorVersion() )>0;
	}	
	
	private TypedProperties properties;
	
	private ProgramVersion( Properties props ) {
		this.properties = new TypedProperties();
		PropsIO.fill(props, this.properties);
	}
	
}
