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
 * @(#)SequenceReset.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.backup.seq
 * @creation   : 30/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.backup;

import java.sql.Connection;
import java.sql.SQLException;

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
public interface SequenceReset {

	public long loadNextValue( Connection conn, String sequenceName ) throws SQLException;
	
	public void saveNextValue( Connection conn, String sequenceName, long value ) throws SQLException;
	
}
