/*
 * @(#)PollIndexDAOHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.poll.dg.model.PollIndexModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PollIndexModel.</p>
 *
 * @author Matteo Franci
 */
public class PollIndexDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 12596558458732L;

    protected static final PollIndexModelRSEHelper RSE_MAIN = new PollIndexModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}POLL_INDEX v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}POLL_INDEX";

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


    protected PollIndexModel loadByPkWorker( Object idPollIndex  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_POLL_INDEX = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollIndex ) ); 
        return ( PollIndexModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPollIndex  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_POLL_INDEX = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollIndex ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( PollIndexModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( questionary_type, questionary_name, questionary_description ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getQuestionaryType(), 2 ) );
       fl.addField( model.getQuestionaryName(), 12 );
       fl.addField( model.getQuestionaryDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( PollIndexModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( PollIndexModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_index, questionary_type, questionary_name, questionary_description ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollIndex(), 2 ) );
       fl.addField( model.getQuestionaryType(), 2 );
       fl.addField( model.getQuestionaryName(), 12 );
       fl.addField( model.getQuestionaryDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PollIndexModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PollIndexModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_poll_index=? , questionary_type = ?, questionary_name = ?, questionary_description = ? WHERE ID_POLL_INDEX=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollIndex(), 2 ) );
       fl.addField( model.getQuestionaryType(), 2 );
       fl.addField( model.getQuestionaryName(), 12 );
       fl.addField( model.getQuestionaryDescription(), 12 );
       fl.addField( model.getIdPollIndex(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PollIndexModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PollIndexModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PollIndexModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PollIndexModel loadOne( String sql, Field f ) throws DAOException {
        return (PollIndexModel)loadOne( sql, f, RSE_MAIN );
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
    public PollIndexDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PollIndexDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PollIndexModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PollIndexModel model) throws DAOException {
    	this.loadRelationListPollQuestion( model );

    }
    public void loadRelationListPollQuestion( PollIndexModel model ) throws DAOException {
		if ( model != null  && model.getIdPollIndex() != null  ) {
    		org.morozko.java.mod.web.poll.dg.dao.PollQuestionDAO listPollQuestionDAO = this.getModuleDaoFactory().getPollQuestionDAO();
    		java.util.List listPollQuestion = listPollQuestionDAO.outRelationListPollQuestion( model );
    		model.setListPollQuestion(listPollQuestion);
		}
	}

	public PollIndexModel loadOnePollIndexPk( org.morozko.java.mod.db.dao.DAOID id_poll_index ) throws DAOException { 
		PollIndexModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_index=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_index);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public PollIndexModel loadOneByName( String questionary_name ) throws DAOException { 
		PollIndexModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.questionary_name=? ";
		FieldList fl = this.newFieldList();
		fl.addField(questionary_name);
		result = this.loadOne( sql, fl );
		return result;
	}
}
