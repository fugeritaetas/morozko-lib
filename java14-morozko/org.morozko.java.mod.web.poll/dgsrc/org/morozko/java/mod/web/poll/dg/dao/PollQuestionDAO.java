/*
 * @(#)PollQuestionDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 18/11/2009 09/03/07
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollQuestionDAOHelper;

/**
 * <p>Classe PollQuestionDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollQuestionDAO extends PollQuestionDAOHelper {

	private final static long serialVersionUID = 125853138737671L;

    public PollQuestionDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
