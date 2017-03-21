/*****************************************************************
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
*****************************************************************/
/*
 * @(#)PostgresqlSeqIdGenerator.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.idgen
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.dao.idgen;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class PostgresqlSeqIdGenerator extends BasicSeqIdGenerator {

	public static String createSequenceQuery( String seqName) {
		return " SELECT nextval('"+seqName+"'); ";
	}
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicSeqIdGenerator#createSequenceQuery()
	 */
	protected String createSequenceQuery() {
		return createSequenceQuery( this.getSequenceName() ); 
	}

}
