package org.morozko.java.mod.dbsrc.config.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.morozko.java.core.log.Appender;
import org.morozko.java.core.log.LogFacade;

public class DbsrcLogger {

	private static final SimpleDateFormat SDF = new SimpleDateFormat( "dd/MM/yy hh:mm:ss" );
	
	private static DbsrcLogger errorLogger;
	
	public static DbsrcLogger getErrorLogger() {
		return errorLogger;
	}
	
	private Appender app;
	
	public void log( String message ) {
		this.app.write( "["+SDF.format( new Date() )+"]"+message , LogFacade.LL_INFO );
	}
	
	public DbsrcLogger( Appender app ) {
		this.app = app;
	}

	@Override
	public String toString() {
		return this.getClass().getName()+"["+this.app+"]";
	}
	
}
