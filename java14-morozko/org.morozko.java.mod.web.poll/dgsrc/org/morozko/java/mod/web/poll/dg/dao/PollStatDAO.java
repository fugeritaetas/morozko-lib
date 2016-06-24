/*
 * @(#)PollStatDAO.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao
 * @creation   : 19/11/2009 09/22/08
 */
package org.morozko.java.mod.web.poll.dg.dao;

import org.morozko.java.mod.web.poll.dg.dao.helpers.PollStatDAOHelper;

/**
 * <p>Classe PollStatDAO.</p>
 *
 * @author Matteo Franci
 */
public class PollStatDAO extends PollStatDAOHelper {

	private final static long serialVersionUID = 12586189283985L;

    public PollStatDAO(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
        this.init(daoFactory);
    }

}
