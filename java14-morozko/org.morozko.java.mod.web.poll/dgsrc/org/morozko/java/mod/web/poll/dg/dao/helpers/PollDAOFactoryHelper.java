/*
 * @(#)PollDAOFactoryHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import org.morozko.java.mod.web.poll.dg.dao.PollStatDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollStatDayDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollIndexDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollQuestionDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollOptionDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollDataDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollAnswerDAO;

/**
 * <p>Dao factory.</p>
 *
 * @author Matteo Franci
 */
public class PollDAOFactoryHelper extends org.morozko.java.mod.db.dao.BasicDAOFactory {

	private final static long serialVersionUID = 125965584597334L;

	private PollStatDAO pollStatDAO;
	private PollStatDayDAO pollStatDayDAO;
	private PollIndexDAO pollIndexDAO;
	private PollQuestionDAO pollQuestionDAO;
	private PollOptionDAO pollOptionDAO;
	private PollDataDAO pollDataDAO;
	private PollAnswerDAO pollAnswerDAO;

	public PollDAOFactoryHelper( org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());
		this.pollStatDAO = new PollStatDAO(daoFactory);
		this.pollStatDAO.setModuleDaoFactory(this);
		this.pollStatDayDAO = new PollStatDayDAO(daoFactory);
		this.pollStatDayDAO.setModuleDaoFactory(this);
		this.pollIndexDAO = new PollIndexDAO(daoFactory);
		this.pollIndexDAO.setModuleDaoFactory(this);
		this.pollQuestionDAO = new PollQuestionDAO(daoFactory);
		this.pollQuestionDAO.setModuleDaoFactory(this);
		this.pollOptionDAO = new PollOptionDAO(daoFactory);
		this.pollOptionDAO.setModuleDaoFactory(this);
		this.pollDataDAO = new PollDataDAO(daoFactory);
		this.pollDataDAO.setModuleDaoFactory(this);
		this.pollAnswerDAO = new PollAnswerDAO(daoFactory);
		this.pollAnswerDAO.setModuleDaoFactory(this);
	}

    /** 
     * <p>Restituisce il valore di pollStatDAO</p> 
     * 
     * @return      restituisce il valore di pollStatDAO
     */ 
    public PollStatDAO getPollStatDAO() {
        return this.pollStatDAO;
    }
    /** 
     * <p>Restituisce il valore di pollStatDayDAO</p> 
     * 
     * @return      restituisce il valore di pollStatDayDAO
     */ 
    public PollStatDayDAO getPollStatDayDAO() {
        return this.pollStatDayDAO;
    }
    /** 
     * <p>Restituisce il valore di pollIndexDAO</p> 
     * 
     * @return      restituisce il valore di pollIndexDAO
     */ 
    public PollIndexDAO getPollIndexDAO() {
        return this.pollIndexDAO;
    }
    /** 
     * <p>Restituisce il valore di pollQuestionDAO</p> 
     * 
     * @return      restituisce il valore di pollQuestionDAO
     */ 
    public PollQuestionDAO getPollQuestionDAO() {
        return this.pollQuestionDAO;
    }
    /** 
     * <p>Restituisce il valore di pollOptionDAO</p> 
     * 
     * @return      restituisce il valore di pollOptionDAO
     */ 
    public PollOptionDAO getPollOptionDAO() {
        return this.pollOptionDAO;
    }
    /** 
     * <p>Restituisce il valore di pollDataDAO</p> 
     * 
     * @return      restituisce il valore di pollDataDAO
     */ 
    public PollDataDAO getPollDataDAO() {
        return this.pollDataDAO;
    }
    /** 
     * <p>Restituisce il valore di pollAnswerDAO</p> 
     * 
     * @return      restituisce il valore di pollAnswerDAO
     */ 
    public PollAnswerDAO getPollAnswerDAO() {
        return this.pollAnswerDAO;
    }

}
