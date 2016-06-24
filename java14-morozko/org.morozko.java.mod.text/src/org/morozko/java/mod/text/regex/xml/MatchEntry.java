/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.text 

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
 * @(#)BbCode.java
 *
 * @project    : org.opinf.jlib.mod.bbcode
 * @package    : org.opinf.jlib.mod.bbcode.xml
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 * 
 * Modification from Rafael Steil's JForum class net.jforum.util.bbcode.BBCode 
 * ($Id: MatchEntry.java,v 1.1 2007/10/16 14:38:18 cvsuser3 Exp $)
 * Copyright (c) 2003, Rafael Steil
 * All rights reserved.  
 * 
 */
package org.morozko.java.mod.text.regex.xml;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class MatchEntry {

	private String tagName = "";
	private String regex;
	private String replace;
	private boolean removQuotes;
	private boolean alwaysProcess;
	
	public MatchEntry() {}

	/**
	 * BBCode class constructor
	 * @param tagName The tag name we are going to match
	 * @param regex Regular expression relacted to the tag
	 * @param replace The replacement string
	 */
	public MatchEntry(String tagName, String regex, String replace)
	{
		this.tagName = tagName;
		this.regex = regex;
		this.replace = replace;
	}

	/**
	 * Gets the regex
	 * @return String witht the regex
	 */
	public String getRegex() 
	{
		return this.regex;
	}

	/**
	 * Gets the replacement string
	 * @return string with the replacement data
	 */
	public String getReplace() 
	{
		return this.replace;
	}

	/**
	 * Getst the tag name
	 * @return The tag name
	 */
	public String getTagName() 
	{
		return this.tagName;
	}
	
	public boolean removeQuotes()
	{
		return this.removQuotes;
	}

	/**
	 * Sets the regular expression associated to the tag
	 * @param regex Regular expression string
	 */
	public void setRegex(String regex) 
	{
		this.regex = regex;
	}

	/**
	 * Sets the replacement string, to be aplyied when matching the code
	 * @param replace The replacement string data
	 */
	public void setReplace(String replace) 
	{
		this.replace = replace;
	}

	/**
	 * Setst the tag name
	 * @param tagName The tag name
	 */
	public void setTagName(String tagName) 
	{
		this.tagName = tagName;
	}
	
	public void enableAlwaysProcess()
	{
		this.alwaysProcess = true;
	}
	
	public boolean alwaysProcess()
	{
		return this.alwaysProcess;
	}
	
	public void enableRemoveQuotes()
	{
		this.removQuotes = true;
	}	
	
}
