package org.morozko.java.mod.log.log4j;

import java.io.FileInputStream;
import java.util.Properties;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.log.LogFacade;

public class TestLog4j {

	public static void main( String[] args ) {
		
		try {
			Properties props = new Properties();
			props.load( new FileInputStream( "test/log4j/logcfg.properties" ) );
			LogFacade.configure( props );
			BasicLogObject test = new BasicLogObject();
			test.getLog().info( "test log info" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
