/*
 * @(#)PollAnswerDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollAnswerDAOHelper;

/**
 * <p>Classe PollAnswerDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollAnswerDAO extends PollAnswerDAOHelper {

	private final static long serialVersionUID = 125853138711629L;

    public PollAnswerDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
