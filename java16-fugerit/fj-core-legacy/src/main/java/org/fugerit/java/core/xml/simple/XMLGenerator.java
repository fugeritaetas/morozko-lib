/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @(#)XMLGenerator.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.xml.simple
 * @creation	: 27-dic-2004 15.16.58
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.simple;

import java.util.Properties;

import org.fugerit.java.core.xml.XMLException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public interface XMLGenerator {

    public void addText(String text);
    
    public void openTag(String name);
    
    public void openTag(String name, Properties att);
    
    public void closeTag(String name);
    
    public void addTag(String name, String text);
    
    public void addTag(String name, Properties att);
    
    public void addTag(String name, Properties att, String text);

    public void emptyTag(String name);
    
    public void emptyTag(String name, Properties att);
    
    public void close() throws XMLException;
    
}
