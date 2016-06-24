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
 * @(#)XMLValidatorSAX.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.core.xml.sax
 * @creation	: 15-dic-2004 16.41.40
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml.sax;

import java.io.IOException;

import javax.xml.parsers.SAXParser;

import org.morozko.java.core.xml.XMLException;
import org.morozko.java.core.xml.helpers.AbstractXMLValidator;
import org.morozko.java.core.xml.sax.dh.DefaultHandlerComp;
import org.morozko.java.core.xml.sax.eh.ResultErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class XMLValidatorSAX extends AbstractXMLValidator {
	
    final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    
    public static XMLValidatorSAX newInstance(EntityResolver er) throws XMLException {
        return newInstance(er, false);
    }    
    
    public static XMLValidatorSAX newInstance(EntityResolver er, boolean nsa) throws XMLException {
        SAXParser parser = XMLFactorySAX.makeSAXParser(true, nsa);
        try {


            
        } catch (Exception e) {
            throw (new XMLException(e));
        }
        return (new XMLValidatorSAX(parser, er));
    }    

    public static XMLValidatorSAX newInstance() throws XMLException {
        return newInstance(null);
    }    
    
    public static XMLValidatorSAX newInstance(boolean nsa) throws XMLException {
        return newInstance(null, nsa);
    }
    
    private XMLValidatorSAX(SAXParser parser, EntityResolver resolver) {
        this.parser = parser;
        this.resolver = resolver;
    }
    
    private SAXParser parser;
    
    private EntityResolver resolver;
    
    /* (non-Javadoc)
     * @see org.opinf.jlib.std.xml.XMLValidator#validateXML(org.xml.sax.InputSource, org.morozko.java.core.xml.sax.SAXParseResult)
     */
    public boolean validateXML(InputSource source, SAXParseResult result) throws XMLException {
        try {
            if (this.resolver!=null) {
                parser.parse(source, new DefaultHandlerComp(this.resolver, new ResultErrorHandler(result)));
            } else {
                parser.parse(source, new DefaultHandlerComp(new ResultErrorHandler(result)));
            }
        } catch (IOException ioe) {
            throw (new XMLException(ioe));
        } catch (SAXException sax) {
            throw (new XMLException(sax));
        }
        return this.isValid(result);
    }

}
