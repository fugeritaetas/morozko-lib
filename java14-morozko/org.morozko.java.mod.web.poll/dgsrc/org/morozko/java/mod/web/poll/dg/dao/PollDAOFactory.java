/*
 * @(#)PollDAOFactory.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.web.poll.dg.dao.helpers.PollDAOFactoryHelper;

/**
 * <p>Classe PollDAOFactory.</p>
 *
 * @author Matteo Franci
 */
public class PollDAOFactory extends PollDAOFactoryHelper {

	private static PollDAOFactory instance;
	
	public static PollDAOFactory getInstance() {
		return instance;
	}

	public static void init( ConnectionFactory cf, String tablePrefix ) {
		BasicDAOFactory bdf = new BasicDAOFactory( cf );
		LogFacade.getLog().info( "PollDAOFactory.init : "+tablePrefix );
		if ( tablePrefix != null ) {
			Object[] a = { tablePrefix };
			bdf.setSqlArgs( a );
		}
		instance = new PollDAOFactory( bdf );
		instance.tablePrefix = tablePrefix;
	}
	
	private PollDAOFactory(BasicDAOFactory daoFactory) {
		super(daoFactory);
	}

	private final static long serialVersionUID = 125853138711628L;

	private String tablePrefix;

	public String getTablePrefix() {
		return tablePrefix;
	}
	
}
