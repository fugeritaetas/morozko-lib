/*
 * @(#)PollStatDayDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 19/11/2009 10/40/59
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollStatDayDAOHelper;

/**
 * <p>Classe PollStatDayDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollStatDayDAO extends PollStatDayDAOHelper {

	private final static long serialVersionUID = 125862365952225L;

    public PollStatDayDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
