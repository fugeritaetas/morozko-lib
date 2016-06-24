/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)AbstractLookupDispatchAction.java
 *
 * @project     : org.morozko.java.mod.web
 * @package     : org.morozko.java.mod.web.actions
 * @creation	: 16/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.web.actions;

import org.apache.struts.actions.LookupDispatchAction;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.log.Logger;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public abstract class AbstractLookupDispatchAction extends LookupDispatchAction {

	private Logger logger;
	
	public AbstractLookupDispatchAction() {
		this.logger = LogFacade.createLogger( this );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.LogObject#getLog()
	 */
	public Logger getLog() {
		return this.logger;
	}	
	
	public static final String STANDARD_FORWARD_SUCCESS = AbstractAction.STANDARD_FORWARD_SUCCESS;
	public static final String STANDARD_FORWARD_FAILURE = AbstractAction.STANDARD_FORWARD_FAILURE;

}
