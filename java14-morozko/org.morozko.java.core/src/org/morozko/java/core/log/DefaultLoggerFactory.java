package org.morozko.java.core.log;

import java.util.Properties;

public class DefaultLoggerFactory implements LoggerFactory {

	public void updateCurrentConfig( String prop, String value ) {
		currentConfig().setProperty( prop, value );
		configure( currentConfig() );
	}
	
	public Properties currentConfig() {
		return CFG;
	}
	
	private static void logInit( String message ) {
		System.out.println( "[LogFacade-INIT]:"+message );
	}
	
	private Properties CFG = new Properties();
	
	private static LogLevelPointer logLevel = new LogLevelPointer( 4 );
	
	private String logExcludePrefix = null;
	
	private MultiAppender APPENDER = new MultiAppender( StreamAppender.out );

	private Logger LOGGER = new DefaultLogger( "MAIN-LOGGER", logLevel, APPENDER );
	
	public Logger getDefaultLogger() {
		return LOGGER;
	}

	public void configure( Properties props ) {
		logInit( "START" );
		CFG = props;
		APPENDER.clear();
		APPENDER.add( StreamAppender.out );
		logLevel.setLogLevel( Integer.parseInt( props.getProperty( "log.level", String.valueOf( logLevel.getLogLevel() ) ) ) );
		logExcludePrefix = CFG.getProperty( "log.exclude.prefix" );
		logInit( "logLevel         : "+logLevel );
		logInit( "logExcludePrefix : "+logExcludePrefix );
		logInit( "END" );
		LOGGER = new DefaultLogger( "MAIN-LOGGER", logLevel, APPENDER );
		LOGGER.info( "LOG CONFIG DONE : "+APPENDER );
	}	
	
	public Logger createLogger(Object obj) {
    	String prefix = obj.getClass().getName();
    	if ( logExcludePrefix != null ) {
        	int index = prefix.indexOf( logExcludePrefix );
        	if ( index==0 ) {
        		prefix = prefix.substring( logExcludePrefix.length() );
        	}    		
    	}
        Logger logger = new DefaultLogger( prefix, logLevel, APPENDER );
        return logger;
	}

}

class LogLevelPointer {
	
	public LogLevelPointer(int logLevel) {
		super();
		this.setLogLevel( logLevel );
	}

	private int logLevel;
	
	public synchronized void setLogLevel( int logLevel ) {
		this.logLevel = logLevel;
	}
	
	public int getLogLevel() {
		return this.logLevel;
	}
	
}

class DefaultLogger implements Logger {
    
	public String toString() {
		return this.getClass().getName()+"["+this.logLevel.getLogLevel()+"]";
	}

	private Appender appender;
	
    private String prefix;

    private LogLevelPointer logLevel;
    
    public DefaultLogger(String prefix, LogLevelPointer logLevel, Appender appender) {
        this.prefix = "["+prefix+"]";
        this.logLevel = logLevel;
        this.appender = appender;
    }
    
    public static final int LL_ON = 6;
    public static final int LL_DEBUG = 5;
    public static final int LL_INFO = 4;
    public static final int LL_WARN = 3;
    public static final int LL_ERROR = 2;
    public static final int LL_FATAL = 1;
    public static final int LL_OFF = 0;
    
    private static final String[] LL_DES = { 	"[0-OFF  ]", 
            								  	"[1-FATAL]",
            								  	"[2-ERROR]", 
            								  	"[3-WARN ]",
            								  	"[4-INFO ]",
            								  	"[5-DEBUG]",
            								  	"[2-ON   ]" };
    
    private void log(int level, Object obj, Throwable t) {
        this.log(level, obj);
        if (t!=null) {
            this.appender.write( t , level );
        }
    }
    
    private void log(int level, Object obj) {
    	if (level<=this.logLevel.getLogLevel()) {
			long time = System.currentTimeMillis();
			String sTime = "["+new java.sql.Date(time)+" "+new java.sql.Time(time)+"]";
			this.appender.write( sTime+LL_DES[level]+this.prefix+obj, level);
    	}
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#debug(java.lang.Object, java.lang.Throwable)
     */
    public void debug(Object obj, Throwable t) {
        this.log(LL_DEBUG, obj, t);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#debug(java.lang.Object)
     */
    public void debug(Object obj) {
        this.log(LL_DEBUG, obj);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#error(java.lang.Object, java.lang.Throwable)
     */
    public void error(Object obj, Throwable t) {
        this.log(LL_ERROR, obj, t);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#error(java.lang.Object)
     */
    public void error(Object obj) {
        this.log(LL_ERROR, obj);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#fatal(java.lang.Object, java.lang.Throwable)
     */
    public void fatal(Object obj, Throwable t) {
        this.log(LL_FATAL, obj, t);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#fatal(java.lang.Object)
     */
    public void fatal(Object obj) {
        this.log(LL_FATAL, obj);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#info(java.lang.Object, java.lang.Throwable)
     */
    public void info(Object obj, Throwable t) {
        this.log(LL_INFO, obj, t);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#info(java.lang.Object)
     */
    public void info(Object obj) {
        this.log(LL_INFO, obj);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#warn(java.lang.Object, java.lang.Throwable)
     */
    public void warn(Object obj, Throwable t) {
        this.log(LL_WARN, obj, t);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.SimossLogger#warn(java.lang.Object)
     */
    public void warn(Object obj) {
        this.log(LL_WARN, obj);
    }

}
