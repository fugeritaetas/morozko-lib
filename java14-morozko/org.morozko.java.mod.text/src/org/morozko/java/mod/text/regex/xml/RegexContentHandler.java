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
 * @(#)BbCodeHandler.java
 *
 * @project    : org.opinf.jlib.mod.bbcode
 * @package    : org.opinf.jlib.mod.bbcode.xml
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 * 
 * Modification from Rafael Steil's JForum class net.jforum.util.bbcode.BBCodeHandler 
 * ($Id: RegexContentHandler.java,v 1.1 2007/10/16 14:38:18 cvsuser3 Exp $)
 * Copyright (c) 2003, Rafael Steil
 * All rights reserved.  
 *  
 */
package org.morozko.java.mod.text.regex.xml;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class RegexContentHandler extends DefaultHandler {

	private Map bbMap = new LinkedHashMap();
	private Map alwaysProcessMap = new LinkedHashMap();
	private boolean matchOpen = false;
	private String tagName = "";
	private StringBuffer sb;	
	private MatchEntry bb;
	
	public RegexContentHandler() { }
	
	public static RegexContentHandler parse( InputStream bbConfig ) throws Exception
	{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		InputSource input = new InputSource( bbConfig );
		RegexContentHandler bbParser = new RegexContentHandler();
		parser.parse(input, bbParser);
		
		return bbParser;  
	}
	
	public void addBb(MatchEntry bb)
	{
		if (bb.alwaysProcess()) {
			this.alwaysProcessMap.put(bb.getTagName(), bb);
		}
		else {
			this.bbMap.put(bb.getTagName(), bb);
		}
	}
	
	public Collection getMatchEntryList()
	{
		return this.bbMap.values();
	}
	
	public Collection getAlwaysProcessList()
	{
		return this.alwaysProcessMap.values();
	}
	
	public MatchEntry findByName(String tagName)
	{
		return (MatchEntry)this.bbMap.get(tagName);
	}
	
	public void startElement(String uri, String localName, String tag, Attributes attrs)
	{
		if (tag.equals("match")) {
			this.matchOpen = true;
			this.sb = new StringBuffer();
			this.bb = new MatchEntry();
			
			String tagName = attrs.getValue("name");
			if (tagName != null) {
				this.bb.setTagName(tagName);
			}
			
			// Shall we remove the infamous quotes?
			String removeQuotes = attrs.getValue("removeQuotes");
			if (removeQuotes != null && removeQuotes.equals("true")) {
				this.bb.enableRemoveQuotes();
			}
			
			String alwaysProcess = attrs.getValue("alwaysProcess");
			if (alwaysProcess != null && "true".equals(alwaysProcess)) {
				this.bb.enableAlwaysProcess();
			}
		}
		this.tagName = tag;
	}

	public void endElement(String uri, String localName, String tag)
	{	
		if (tag.equals("match")) {
			this.matchOpen = false;
			this.addBb(this.bb);
		}
		else if (this.tagName.equals("replace")) {
			this.bb.setReplace(this.sb.toString().trim());
			this.sb.delete(0, this.sb.length());
		}
		else if (this.tagName.equals("regex")) {
			this.bb.setRegex(this.sb.toString().trim());
			this.sb.delete(0, this.sb.length());
		}
	
		this.tagName = "";
	}

	public void characters(char ch[], int start, int length)
	{
		if (this.tagName.equals("replace") || this.tagName.equals("regex"))
			this.sb.append(ch, start, length);
	}

	public void error(SAXParseException exception) throws SAXException 
	{
		throw exception;
	}	
	
}
