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
 * @(#)MuteExHandler.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang.helpers
 * @creation	: 6-dic-2004 12.41.55
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang.helpers;

import org.morozko.java.core.lang.ExHandler;

/**
 * <p>Implementazione do-nothing di ExHandler.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class MuteExHandler implements ExHandler {

    public static final ExHandler MUTE_HANDLER = new MuteExHandler();
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#fatal(java.lang.Exception)
     */
    public void fatal(Exception e) {
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#error(java.lang.Exception)
     */
    public void error(Exception e) {
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#warning(java.lang.Exception)
     */
    public void warning(Exception e) {
    }

}
