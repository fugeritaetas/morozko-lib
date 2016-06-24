/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.std 

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
 * @(#)ContentHandlerWrapper.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.core.xml.sax.ch
 * @creation	: 15-dic-2004 23.15.54
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml.sax.ch;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class ContentHandlerWrapper implements ContentHandler {
    
    /**
     * <p>Crea un nuovo ContentHandlerWrapper</p>
     * 
     * 
     */
    public ContentHandlerWrapper(ContentHandler handler) {
        super();
        this.wrappedContentHandler = handler;
    }
    
    private ContentHandler wrappedContentHandler;

    /**
     * <p>Restituisce il valore di wrappedContentHandler.</p>
     * 
     * @return il valore di wrappedContentHandler.
     */
    public ContentHandler getWrappedContentHandler() {
        return wrappedContentHandler;
    }
    
    /**
     * <p>Imposta wrappedContentHandler.</p>
     * 
     * @param wrappedContentHandler il wrappedContentHandler da impostare.
     */
    public void setWrappedContentHandler(ContentHandler wrappedContentHandler) {
        this.wrappedContentHandler = wrappedContentHandler;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        this.getWrappedContentHandler().characters(arg0, arg1, arg2);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument() throws SAXException {
        this.getWrappedContentHandler().endDocument();
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {
        this.getWrappedContentHandler().endElement(arg0, arg1, arg2);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    public void endPrefixMapping(String arg0) throws SAXException {
        this.getWrappedContentHandler().endPrefixMapping(arg0);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
            throws SAXException {
        this.getWrappedContentHandler().ignorableWhitespace(arg0, arg1, arg2);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String arg0, String arg1)
            throws SAXException {
        this.getWrappedContentHandler().processingInstruction(arg0, arg1);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
     */
    public void setDocumentLocator(Locator arg0) {
        this.getWrappedContentHandler().setDocumentLocator(arg0);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String arg0) throws SAXException {
        this.getWrappedContentHandler().skippedEntity(arg0);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        this.getWrappedContentHandler().startDocument();
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String arg0, String arg1, String arg2,
            Attributes arg3) throws SAXException {
        this.getWrappedContentHandler().startElement(arg0, arg1, arg2, arg3);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
     */
    public void startPrefixMapping(String arg0, String arg1)
            throws SAXException {
        this.getWrappedContentHandler().startPrefixMapping(arg0, arg1);
    }
    
}
