package org.morozko.java.mod.web.poll.config;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.web.poll.dg.dao.PollDAOFactory;
import org.morozko.java.mod.web.servlet.config.BasicConfig;

public class PollConfig extends BasicConfig {

	@Override
	public void configure(Properties props) throws ConfigException {
		this.getLog().info( "configure start" );
		String cfName = props.getProperty( "cf-name" );
		this.getLog().info( "cf-name : "+cfName );
		String prefix = props.getProperty( "table-prefix" );
		this.getLog().info( "table-prefix : "+prefix );
		ConnectionFactory cf = ConnectionFacade.getFactory( cfName );
		this.getLog().info( "cf      : "+cf );
		PollDAOFactory.init( cf, prefix );
		this.getLog().info( "configure [OK]" );
	}

}
