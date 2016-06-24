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
 * @(#)XMLValidator.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.xml
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml;

import java.io.InputStream;
import java.io.Reader;

import org.morozko.java.core.xml.sax.SAXParseResult;
import org.xml.sax.InputSource;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public interface XMLValidator {

	/**
	 * <p>Valida un documento XML.</p>
	 * 
	 * @param source	la sorgente della validazione
	 * @param result	il result dove mettere le eccezioni generare dalla validazione
	 * @return			<code>true</code> se la validazione &egrave; andata a buon fine,
	 * 					<code>false</code> altrimenti (restituisce lo stesso risultato
	 * 					del metodo SAXParseRusult.isTotalSuccess()).
	 * @throws XMLException		in caso di eccezioni durante la validazione
	 * @see    org.morozko.java.core.xml.sax.SAXParseRsult#isTotalSuccess()
	 */
    public boolean validateXML(InputSource source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(InputSource source) throws XMLException;
    
    public boolean isValidaXML(InputSource source) throws XMLException;
    
    public boolean validateXML(Reader source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(Reader source) throws XMLException;
    
    public boolean isValidaXML(Reader source) throws XMLException;
    
    public boolean validateXML(InputStream source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(InputStream source) throws XMLException;
    
    public boolean isValidaXML(InputStream source) throws XMLException;
    
    public boolean validateXML(StringBuffer source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(StringBuffer source) throws XMLException;
    
    public boolean isValidaXML(StringBuffer source) throws XMLException;
    
    public boolean validateXML(String source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(String source) throws XMLException;
    
    public boolean isValidaXML(String source) throws XMLException;
    
    public boolean validateXML(byte[] source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(byte[] source) throws XMLException;
    
    public boolean isValidaXML(byte[] source) throws XMLException;
    
    public boolean validateXML(char[] source, SAXParseResult result) throws XMLException;
    
    public SAXParseResult validateXML(char[] source) throws XMLException;
    
    public boolean isValidaXML(char[] source) throws XMLException;
	
	
}
