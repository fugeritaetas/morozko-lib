/*
 * @(#)PollStatDAOHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.poll.dg.model.PollStatModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PollStatModel.</p>
 *
 * @author Matteo Franci
 */
public class PollStatDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125965584578354L;

    protected static final PollStatModelRSEHelper RSE_MAIN = new PollStatModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT d.id_poll_index, a.id_poll_option, count(*) AS answer_number 			FROM {0}poll_answer a, {0}poll_data d  			WHERE a.id_poll_data = d.id_poll_data 			GROUP BY d.id_poll_index, a.id_poll_option";

    protected String queryView;

    protected static final String SQL_UPDATE = "";

    protected String sqlUpdate;

    private PollDAOFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( PollDAOFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public PollDAOFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }




    public PollStatModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PollStatModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PollStatModel loadOne( String sql, Field f ) throws DAOException {
        return (PollStatModel)loadOne( sql, f, RSE_MAIN );
    }
    protected void loadAll( List list, String sql, FieldList fl ) throws DAOException {
        this.loadAll( list, sql, fl, RSE_MAIN );
    }
    protected void loadAll( List list, String sql, Field f ) throws DAOException {
        this.loadAll( list, sql, f, RSE_MAIN );
    }
    protected void loadAll( List list, String sql ) throws DAOException {
        this.loadAll( list, sql, this.newFieldList(), RSE_MAIN );
    }
    public void loadAll( List list ) throws DAOException {
        String sql = this.queryView; 
        this.loadAll( list, sql, this.newFieldList(), RSE_MAIN );
    }
    public List loadAll() throws DAOException {
        List list = this.newList();
        this.loadAll( list );
        return list;
    }
    public LoadResult loadAllResult() throws DAOException {
        String sql = this.queryView; 
        return LoadResult.initResult( this, sql, this.newFieldList(), RSE_MAIN ) ;
    }

    public org.morozko.java.mod.db.dao.BasicDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.db.dao.BasicDAOFactory)this.getDaoFactory();
    }
    public PollStatDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
    }
    public PollStatDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PollStatModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PollStatModel model) throws DAOException {

    }

	public List loadAllByPoll( org.morozko.java.mod.db.dao.DAOID id_poll_index ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_index=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_index);
		this.loadAll( result, sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public LoadResult loadResultAllByPoll( org.morozko.java.mod.db.dao.DAOID id_poll_index ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_index=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_index);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
}
