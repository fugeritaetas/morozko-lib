package org.morozko.java.core.log;

import java.util.Properties;

public interface LoggerFactory {

	public Logger createLogger(Object obj);
	
	public Logger getDefaultLogger();
	
	public void configure( Properties props );
	
}
