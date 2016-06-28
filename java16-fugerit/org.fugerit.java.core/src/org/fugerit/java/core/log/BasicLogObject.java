package org.fugerit.java.core.log;

import java.util.logging.Logger;

public class BasicLogObject implements LogObject {
	
	private Logger logger = Logger.getLogger( this.getClass().getName() );

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.log.LogObject#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	
	
}
