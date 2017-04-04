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
 * @(#)AbstractXMLValidator.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.opinf.jlib.std.xml.helpers
 * @creation	: 15-dic-2004 16.34.31
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.helpers;

import java.io.ByteArrayInputStream;

import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.fugerit.java.core.xml.XMLException;
import org.fugerit.java.core.xml.XMLValidator;
import org.fugerit.java.core.xml.sax.SAXParseResult;
import org.xml.sax.InputSource;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public abstract class AbstractXMLValidator implements XMLValidator {

    public boolean isValid(SAXParseResult result) {
        return result.isTotalSuccess();
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(org.xml.sax.InputSource, org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public abstract boolean validateXML(InputSource source, SAXParseResult result) throws XMLException;

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(org.xml.sax.InputSource)
     */
    public SAXParseResult validateXML(InputSource source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(org.xml.sax.InputSource)
     */
    public boolean isValidaXML(InputSource source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.io.Reader, org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(Reader source, SAXParseResult result)
            throws XMLException {
        return this.validateXML(new InputSource(source), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.io.Reader)
     */
    public SAXParseResult validateXML(Reader source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(java.io.Reader)
     */
    public boolean isValidaXML(Reader source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.io.InputStream, org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(InputStream source, SAXParseResult result)
            throws XMLException {
        return this.validateXML(new InputSource(source), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.io.InputStream)
     */
    public SAXParseResult validateXML(InputStream source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(java.io.InputStream)
     */
    public boolean isValidaXML(InputStream source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.lang.StringBuffer, org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(StringBuffer source, SAXParseResult result) throws XMLException {
        return this.validateXML(source.toString(), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.lang.StringBuffer)
     */
    public SAXParseResult validateXML(StringBuffer source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(java.lang.StringBuffer)
     */
    public boolean isValidaXML(StringBuffer source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.lang.String, org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(String source, SAXParseResult result)
            throws XMLException {
        return this.validateXML(new StringReader(source), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(java.lang.String)
     */
    public SAXParseResult validateXML(String source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(java.lang.String)
     */
    public boolean isValidaXML(String source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(byte[], org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(byte[] source, SAXParseResult result)
            throws XMLException {
        return this.validateXML(new ByteArrayInputStream(source), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(byte[])
     */
    public SAXParseResult validateXML(byte[] source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(byte[])
     */
    public boolean isValidaXML(byte[] source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(char[], org.fugerit.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(char[] source, SAXParseResult result)
            throws XMLException {
        return this.validateXML(new CharArrayReader(source), result);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(char[])
     */
    public SAXParseResult validateXML(char[] source) throws XMLException {
        SAXParseResult result = new SAXParseResult();
        this.validateXML(source, result);
        return result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#isValidaXML(char[])
     */
    public boolean isValidaXML(char[] source) throws XMLException {
        return this.isValid(this.validateXML(source));
    }

}
