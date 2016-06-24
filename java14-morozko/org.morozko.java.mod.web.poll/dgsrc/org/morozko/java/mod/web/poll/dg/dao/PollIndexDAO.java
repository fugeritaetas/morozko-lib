/*
 * @(#)PollIndexDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollIndexDAOHelper;

/**
 * <p>Classe PollIndexDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollIndexDAO extends PollIndexDAOHelper {

	private final static long serialVersionUID = 125853138725634L;

    public PollIndexDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
