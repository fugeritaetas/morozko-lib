/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.sync 

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
 * @(#)ChangeChecker.java
 *
 * @project    : org.morozko.java.mod.sync
 * @package    : org.morozko.java.mod.sync.check
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.sync.check;

import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.mod.sync.SyncException;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public interface ChangeChecker {

	public boolean checkModification( JFile fileFrom, JFile fileTo ) throws SyncException;
	
}
