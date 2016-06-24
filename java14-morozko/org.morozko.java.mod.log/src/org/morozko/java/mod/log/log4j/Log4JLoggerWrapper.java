package org.morozko.java.mod.log.log4j;

import org.morozko.java.core.log.Logger;

public class Log4JLoggerWrapper implements Logger {

    public Log4JLoggerWrapper(org.apache.log4j.Logger logger)
    {
        this.logger = logger;
    }

    public void debug(Object arg0)
    {
        logger.debug(arg0);
    }

    public void debug(Object arg0, Throwable arg1)
    {
        logger.debug(arg0, arg1);
    }

    public void error(Object arg0)
    {
        logger.error(arg0);
    }

    public void error(Object arg0, Throwable arg1)
    {
        logger.error(arg0, arg1);
    }

    public void fatal(Object arg0)
    {
        logger.fatal(arg0);
    }

    public void fatal(Object arg0, Throwable arg1)
    {
        logger.fatal(arg0, arg1);
    }

    public void info(Object arg0)
    {
        logger.info(arg0);
    }

    public void info(Object arg0, Throwable arg1)
    {
        logger.info(arg0, arg1);
    }

    public void warn(Object arg0)
    {
        logger.warn(arg0);
    }

    public void warn(Object arg0, Throwable arg1)
    {
        logger.warn(arg0, arg1);
    }

    private org.apache.log4j.Logger logger;
}
