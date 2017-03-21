/*******************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
*******************************************************/
/*
 * @(#)DataFilter.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.filter
 * @creation   : 15/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.filter;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.dao.DAOException;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public interface DataFilter {

	public void applyFilter( ConnectionFactory cf, String table, String[] columnsName, String[] columnsKeyName ) throws DAOException;

}
