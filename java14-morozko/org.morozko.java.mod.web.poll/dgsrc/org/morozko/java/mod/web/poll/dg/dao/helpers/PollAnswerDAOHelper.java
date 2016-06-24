/*
 * @(#)PollAnswerDAOHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.poll.dg.model.PollAnswerModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PollAnswerModel.</p>
 *
 * @author Matteo Franci
 */
public class PollAnswerDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125965584594376L;

    protected static final PollAnswerModelRSEHelper RSE_MAIN = new PollAnswerModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}POLL_ANSWER v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}POLL_ANSWER";

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



    // id generator START

    private IdGenerator idGenerator;

    public org.morozko.java.mod.db.dao.DAOID generateId() throws DAOException {
    	if ( this.idGenerator == null ) { 
    		try {
    			java.util.Properties props = new java.util.Properties();
    			props.setProperty( "sequenceName","seq_id_mor_poll" );
    			this.idGenerator = (IdGenerator)org.morozko.java.core.lang.helpers.ClassHelper.newInstance( "org.morozko.java.mod.db.dao.idgen.GenericSeqIdGenerator" );
    			this.idGenerator.setConnectionFactory( this.getMainDAOFactory().getConnectionFactory() ); 
    			this.idGenerator.configure( props ); 
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	return this.idGenerator.generateId();
    }

    // id generator END


    protected PollAnswerModel loadByPkWorker( Object idPollAnswer  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_POLL_ANSWER = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollAnswer ) ); 
        return ( PollAnswerModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPollAnswer  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_POLL_ANSWER = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollAnswer ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( PollAnswerModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_option, id_poll_data, option_value ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollOption(), 2 ) );
       fl.addField( model.getIdPollData(), 2 );
       fl.addField( model.getOptionValue(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( PollAnswerModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( PollAnswerModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_answer, id_poll_option, id_poll_data, option_value ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollAnswer(), 2 ) );
       fl.addField( model.getIdPollOption(), 2 );
       fl.addField( model.getIdPollData(), 2 );
       fl.addField( model.getOptionValue(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PollAnswerModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PollAnswerModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_poll_answer=? , id_poll_option = ?, id_poll_data = ?, option_value = ? WHERE ID_POLL_ANSWER=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollAnswer(), 2 ) );
       fl.addField( model.getIdPollOption(), 2 );
       fl.addField( model.getIdPollData(), 2 );
       fl.addField( model.getOptionValue(), 12 );
       fl.addField( model.getIdPollAnswer(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PollAnswerModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PollAnswerModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PollAnswerModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PollAnswerModel loadOne( String sql, Field f ) throws DAOException {
        return (PollAnswerModel)loadOne( sql, f, RSE_MAIN );
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
    public PollAnswerDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PollAnswerDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PollAnswerModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PollAnswerModel model) throws DAOException {

    }

	public java.util.List outRelationListPollAnswer( org.morozko.java.mod.web.poll.dg.model.PollDataModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdPollData() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_POLL_DATA = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public PollAnswerModel loadOnePollAnswerPk( org.morozko.java.mod.db.dao.DAOID id_poll_answer ) throws DAOException { 
		PollAnswerModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_answer=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_answer);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
