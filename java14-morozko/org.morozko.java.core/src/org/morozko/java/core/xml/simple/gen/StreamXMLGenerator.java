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
 * @(#)StreamXMLGenerator.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.xml.simple.gen
 * @creation	: 27-dic-2004 15.27.03
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml.simple.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Properties;

import org.morozko.java.core.io.line.LineIOUtils;
import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.xml.XMLException;
import org.morozko.java.core.xml.simple.XMLGenerator;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class StreamXMLGenerator implements XMLGenerator {

	public final static String DEFAULT_SPACER = "  ";
	
	private int currentLevel;
	
    private LineWriter writer;

    private String spacer;
    
    public StreamXMLGenerator(JFile file) throws IOException {
        this(file.getWriter());
    }
    
    public StreamXMLGenerator(File file) throws IOException {
        this(new FileWriter(file));
    }
    
    public StreamXMLGenerator(LineWriter writer) {
        this.writer = writer;
        this.setSpacer( DEFAULT_SPACER );
    }

    public StreamXMLGenerator(OutputStream writer) {
        this(LineIOUtils.createLineWriter(writer)); 
    }    
    
    public StreamXMLGenerator(Writer writer) {
        this(LineIOUtils.createLineWriter(writer)); 
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#addTag(java.lang.String, java.util.Properties, java.lang.String)
     */
    public void addTag(String name, Properties att, String text) {
        this.openTag(name, att);
        this.addText(text);
        this.closeTag(name);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#addTag(java.lang.String, java.util.Properties)
     */
    public void addTag(String name, Properties att) {
        this.openTag(name, att);
        this.closeTag(name);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#addTag(java.lang.String, java.lang.String)
     */
    public void addTag(String name, String text) {
        this.openTag(name);
        this.addText(text);
        this.closeTag(name);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#addText(java.lang.String)
     */
    public void addText(String text) {
        this.append(this.prepareText(text));
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#closeTag(java.lang.String)
     */
    public void closeTag(String name) {
    	this.currentLevel--;
        this.append("</"+this.prepareTagName(name)+">");
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#emptyTag(java.lang.String, java.util.Properties)
     */
    public void emptyTag(String name, Properties att) {
        this.append("<"+this.prepareTagName(name)+this.prepareAttributes(att)+"/>");
    }
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#emptyTag(java.lang.String)
     */
    public void emptyTag(String name) {
        this.emptyTag(name, null);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#openTag(java.lang.String, java.util.Properties)
     */
    public void openTag(String name, Properties att) {
        this.append("<"+this.prepareTagName(name)+this.prepareAttributes(att)+">");
        this.currentLevel++;
    }
    
    private void append(String s) {
    	String prefix = "";
    	for ( int k=0; k<currentLevel; k++ ) {
    		prefix+= this.getSpacer();
    	}
        this.writer.println( prefix+s );
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#openTag(java.lang.String)
     */
    public void openTag(String name) {
        this.openTag(name, null);
    }
    
    protected String prepareText(String text) {
        return text;
    }
    
    protected String prepareTagName(String tagName) {
        return tagName;
    }
    
    protected String prepareAttributes(Properties att) {
        String attList = "";
        if (att!=null) {
            Enumeration e = att.keys();
            while (e.hasMoreElements()) {
                String k = (String)e.nextElement();
                String current = att.getProperty(k);
                current = current.replaceAll( "\"", "&quot;" );
                attList+= " "+k+"=\""+current+"\" ";
            }
        }
        return attList;
    }
    
    
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.xml.simple.XMLGenerator#close()
     */
    public void close() throws XMLException {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw (new XMLException(e));
        }
    }

	/**
	 * @return Restituisce il valore di spacer.
	 */
	public String getSpacer() {
		return spacer;
	}

	/**
	 * @param spacer il valore di spacer da impostare.
	 */
	public void setSpacer(String spacer) {
		this.spacer = spacer;
	}
}
