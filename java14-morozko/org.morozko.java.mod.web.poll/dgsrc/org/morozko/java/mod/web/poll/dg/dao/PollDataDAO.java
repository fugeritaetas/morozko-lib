/*
 * @(#)PollDataDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollDataDAOHelper;

/**
 * <p>Classe PollDataDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollDataDAO extends PollDataDAOHelper {

	private final static long serialVersionUID = 12585313872366L;

    public PollDataDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
