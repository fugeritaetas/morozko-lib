/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)DOMIO.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.xml.dom
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.fugerit.java.core.xml.TransformerXML;
import org.fugerit.java.core.xml.XMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class DOMIO {

    /**
     * Carica un documento XML in forma di albero DOM
     * 
     * @param source    la sorgente da cui prendere il documento xml
     * @return          l'albero DOM
     * @throws XMLException     se si verificano errori durante il parsing
     */
    public static Document loadDOMDoc(String source) throws XMLException {
        return loadDOMDoc(new StringReader(source));
    }

    public static Document loadDOMDoc(InputSource source, EntityResolver er ) throws XMLException {
        Document result = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            parser.setEntityResolver( er );
            result = parser.parse(source);
        } catch (Exception e) {
            throw (new XMLException(e));
        }
        return result;
     }    
    
    /**
     * Carica un documento XML in forma di albero DOM
     * 
     * @param source    la sorgente da cui prendere il documento xml
     * @return          l'albero DOM
     * @throws XMLException     se si verificano errori durante il parsing
     */
    public static Document loadDOMDoc(InputSource source) throws XMLException {
       Document result = null;
       try {
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
           DocumentBuilder parser = factory.newDocumentBuilder();
           result = parser.parse(source);
       } catch (Exception e) {
           throw (new XMLException(e));
       }
//       } catch (IOException ioe) {
//           throw (new XMLException(ioe));
//       } catch (SAXException sax) {
//           throw (new XMLException(sax));
//       }
       return result;
    }

    /**
     * Carica un documento XML in forma di albero DOM
     * 
     * @param source    la sorgente da cui prendere il documento xml
     * @return          l'albero DOM
     * @throws XMLException     se si verificano errori durante il parsing
     */
    public static Document loadDOMDoc(Reader source) throws XMLException {
        return loadDOMDoc(new InputSource(source));
    }

    /**
     * Carica un documento XML in forma di albero DOM
     * 
     * @param source    la sorgente da cui prendere il documento xml
     * @return          l'albero DOM
     * @throws XMLException     se si verificano errori durante il parsing
     */
    public static Document loadDOMDoc(InputStream source) throws XMLException {
        return loadDOMDoc(new InputSource(source));
    }

    /**
     * Carica un documento XML in forma di albero DOM
     * 
     * @param source    la sorgente da cui prendere il documento xml
     * @return          l'albero DOM
     * @throws XMLException     se si verificano errori durante il parsing
     */
    public static Document loadDOMDoc(File source) throws XMLException {
        Document result = null;
        try {
            result = loadDOMDoc(new FileInputStream(source));
        } catch (IOException ioe) {
            throw (new XMLException(ioe));
        }
        return result;
    }    
    
//    /**
//     * Carica un documento XML in forma di albero DOM
//     * 
//     * @param source    la sorgente da cui prendere il documento xml
//     * @return          l'albero DOM
//     * @throws XMLException     se si verificano errori durante il parsing
//     */
//    public static Document loadDOMDoc(JFile source) throws XMLException {
//        Document result = null;
//        try {
//            result = loadDOMDoc(source.getInputStream());
//        } catch (IOException ioe) {
//            throw (new XMLException(ioe));
//        }
//        return result;
//    }        
    
    public static void writeDOM(Node tag, OutputStream stream) throws XMLException {
        Transformer transformer = TransformerXML.newTransformer();
        try {
            transformer.transform(new DOMSource(tag), new StreamResult(stream));
        } catch (TransformerException te) {
            throw (new XMLException(te));
        }
    }
    
    public static void writeDOMIndent(Node tag, OutputStream stream) throws XMLException {
        Transformer transformer = TransformerXML.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        try {
            transformer.transform(new DOMSource(tag), new StreamResult(stream));
        } catch (TransformerException te) {
            throw (new XMLException(te));
        }
    }
    
    private DOMIO() {
        super();
    }
	
	
}
