package org.fugerit.java.core.log;

import org.slf4j.Logger;

public class BasicLogObject implements LogObject {
	
	private Logger logger = LogFacade.newLogger( this );

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.log.LogObject#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	
	
}
