package org.morozko.java.mod.log.log4j;

import java.util.Properties;

import org.morozko.java.core.log.Logger;
import org.morozko.java.core.log.LoggerFactory;

public class Log4JMainFactory implements LoggerFactory {

	private Logger logger;
	
	public void configure(Properties props) {
		this.logger = new Log4JLoggerWrapper( org.apache.log4j.Logger.getRootLogger() );
	}

	public Logger createLogger(Object obj) {
		String cat = "root";
		if ( obj != null ) {
			cat = obj.getClass().getName();
		}
		return new Log4JLoggerWrapper( org.apache.log4j.Logger.getLogger( cat ) );
	}

	public Logger getDefaultLogger() {
		return logger;
	}

}

