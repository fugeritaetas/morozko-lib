/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)OpExec.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.lang
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class OpExec {

	public static int doOps( List l, boolean transaction ) throws Exception {
		int count = 0;
		Iterator it = l.iterator();
		boolean rollback = false;
		List exec = new ArrayList();
		while ( it.hasNext() && !rollback ) {
			Op op = (Op)it.next();
			if ( op.doOpSecure() ) {
				count++;
				if ( transaction ) {
					exec.add( op );
				}
			} else {
				rollback = true;
			}
		}
		if ( rollback && transaction) {
			for ( int k=exec.size()-1; k>=0; k-- ) {
				Op op = (Op)exec.get( k );
				if ( op.undoOp() ) {
					count--;
				}
			}
		}
		return count;
	}
	
}
