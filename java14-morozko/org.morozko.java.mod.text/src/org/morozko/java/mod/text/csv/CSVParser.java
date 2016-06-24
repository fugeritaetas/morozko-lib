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
 * @(#)CSVParser.java
 *
 * @project    : org.morozko.java.mod.text
 * @package    : org.morozko.java.mod.text.csv
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.text.csv;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public interface CSVParser {

	public boolean hasNext() throws Exception;
	
	public String[] nextLine() throws Exception;
	
}
