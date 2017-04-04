package test.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitTestHelper {

	private static Logger logger = LoggerFactory.getLogger( JUnitTestHelper.class );
	
	public static String okMessage( String message ) {
		return "[JUNIT-OK]"+message;
	}
	
	public static String failMessage( String message ) {
		return "[JUNIT-FAIL]"+message;
	}
	
	public static void okLog( String message ) {
		logger.info( okMessage( message ) );
	}
	
	public static void failLog( String message ) {
		logger.info( failMessage( message ) );
	}

	public static Logger getLogger() {
		return logger;
	}
	
}
