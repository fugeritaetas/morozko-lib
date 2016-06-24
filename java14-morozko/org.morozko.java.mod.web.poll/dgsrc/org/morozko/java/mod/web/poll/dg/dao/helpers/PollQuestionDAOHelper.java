/*
 * @(#)PollQuestionDAOHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.poll.dg.model.PollQuestionModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PollQuestionModel.</p>
 *
 * @author Matteo Franci
 */
public class PollQuestionDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125965584589349L;

    protected static final PollQuestionModelRSEHelper RSE_MAIN = new PollQuestionModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}POLL_QUESTION v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}POLL_QUESTION";

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


    protected PollQuestionModel loadByPkWorker( Object idPollQuestion  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_POLL_QUESTION = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollQuestion ) ); 
        return ( PollQuestionModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPollQuestion  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_POLL_QUESTION = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollQuestion ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( PollQuestionModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_question, question_text, order_by ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollQuestion(), 2 ) );
       fl.addField( model.getQuestionText(), 12 );
       fl.addField( model.getOrderBy(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( PollQuestionModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( PollQuestionModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_index, id_poll_question, question_text, order_by ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollIndex(), 2 ) );
       fl.addField( model.getIdPollQuestion(), 2 );
       fl.addField( model.getQuestionText(), 12 );
       fl.addField( model.getOrderBy(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PollQuestionModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PollQuestionModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_poll_index=? , id_poll_question = ?, question_text = ?, order_by = ? WHERE ID_POLL_QUESTION=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollIndex(), 2 ) );
       fl.addField( model.getIdPollQuestion(), 2 );
       fl.addField( model.getQuestionText(), 12 );
       fl.addField( model.getOrderBy(), 2 );
       fl.addField( model.getIdPollQuestion(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PollQuestionModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PollQuestionModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PollQuestionModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PollQuestionModel loadOne( String sql, Field f ) throws DAOException {
        return (PollQuestionModel)loadOne( sql, f, RSE_MAIN );
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
		sql+="  ORDER BY  order_by ASC, id_poll_question ASC  ";
        this.loadAll( list, sql, this.newFieldList(), RSE_MAIN );
    }
    public List loadAll() throws DAOException {
        List list = this.newList();
        this.loadAll( list );
        return list;
    }
    public LoadResult loadAllResult() throws DAOException {
        String sql = this.queryView; 
		sql+="  ORDER BY  order_by ASC, id_poll_question ASC  ";
        return LoadResult.initResult( this, sql, this.newFieldList(), RSE_MAIN ) ;
    }

    public org.morozko.java.mod.db.dao.BasicDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.db.dao.BasicDAOFactory)this.getDaoFactory();
    }
    public PollQuestionDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PollQuestionDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PollQuestionModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PollQuestionModel model) throws DAOException {
    	this.loadRelationListPollOption( model );

    }
    public void loadRelationListPollOption( PollQuestionModel model ) throws DAOException {
		if ( model != null  && model.getIdPollQuestion() != null  ) {
    		org.morozko.java.mod.web.poll.dg.dao.PollOptionDAO listPollOptionDAO = this.getModuleDaoFactory().getPollOptionDAO();
    		java.util.List listPollOption = listPollOptionDAO.outRelationListPollOption( model );
    		model.setListPollOption(listPollOption);
		}
	}

	public java.util.List outRelationListPollQuestion( org.morozko.java.mod.web.poll.dg.model.PollIndexModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdPollIndex() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_POLL_INDEX = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public PollQuestionModel loadOnePollQuestionPk( org.morozko.java.mod.db.dao.DAOID id_poll_question ) throws DAOException { 
		PollQuestionModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_question=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_question);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public List loadAllByIndex( org.morozko.java.mod.db.dao.DAOID id_poll_index ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_index=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_question ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_index);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllByIndex( org.morozko.java.mod.db.dao.DAOID id_poll_index ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_index=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_question ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_index);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
}
