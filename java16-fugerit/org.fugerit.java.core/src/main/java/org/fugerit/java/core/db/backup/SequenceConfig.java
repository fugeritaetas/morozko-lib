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
 * @(#)SequenceConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup
 * @creation   : 30/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup;

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
public class SequenceConfig {

	public String sequenceName;

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo sequenceName.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of sequenceName.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo sequenceName.</jdl:text>
	 *         		<jdl:text lang='en'>the value of sequenceName.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo sequenceName.</jdl:text>
	 * 		<jdl:text lang='en'>Sets sequenceName.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>sequenceName il valore di sequenceName da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>sequenceName the sequenceName to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	
}
