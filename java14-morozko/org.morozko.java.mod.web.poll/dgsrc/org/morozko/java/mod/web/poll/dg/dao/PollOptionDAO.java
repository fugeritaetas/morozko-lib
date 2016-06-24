/*
 * @(#)PollOptionDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollOptionDAOHelper;

/**
 * <p>Classe PollOptionDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollOptionDAO extends PollOptionDAOHelper {

	private final static long serialVersionUID = 125853138733610L;

    public PollOptionDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
