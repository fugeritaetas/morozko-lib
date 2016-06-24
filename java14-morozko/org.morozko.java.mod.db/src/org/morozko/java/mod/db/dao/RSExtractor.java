/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)RSExtractor.java
 *
 * @project	   : simoss
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 27-mag-2005 19.49.35
 */
package org.morozko.java.mod.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>.</p>
 *
 * @author tux2
 */
public interface RSExtractor {

    public Object extractNext(ResultSet rs) throws SQLException;
    
}
