/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools 

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
 * @(#)ToolUtils.java
 *
 * @project  : org.morozko.java.mod.tools
 * @package  : org.morozko.java.mod.tools
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.tools;

import org.morozko.java.core.util.StringUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ToolUtils {
    
    
    public static void printInfo(String prg, String auth) {
        System.out.println();
        System.out.println("   //////////////////////////////////////////////");
        System.out.println("  // "+StringUtils.resize(prg, 40)+" //");
        System.out.println(" // "+StringUtils.resize(auth, 40)+" //");
        System.out.println("//////////////////////////////////////////////");
        System.out.println();
    }
    
    private ToolUtils() {
        super();
    }

}
