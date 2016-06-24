/*
 * @(#)PollOptionDAOHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.poll.dg.model.PollOptionModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PollOptionModel.</p>
 *
 * @author Matteo Franci
 */
public class PollOptionDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125965584592386L;

    protected static final PollOptionModelRSEHelper RSE_MAIN = new PollOptionModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}POLL_OPTION v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}POLL_OPTION";

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


    protected PollOptionModel loadByPkWorker( Object idPollOption  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_POLL_OPTION = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollOption ) ); 
        return ( PollOptionModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPollOption  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_POLL_OPTION = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPollOption ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( PollOptionModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_question, option_text, option_type, option_default, id_poll_option_parent, order_by ) VALUES (  ? , ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollQuestion(), 2 ) );
       fl.addField( model.getOptionText(), 12 );
       fl.addField( model.getOptionType(), 2 );
       fl.addField( model.getOptionDefault(), 12 );
       fl.addField( model.getIdPollOptionParent(), 2 );
       fl.addField( model.getOrderBy(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( PollOptionModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( PollOptionModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_poll_option, id_poll_question, option_text, option_type, option_default, id_poll_option_parent, order_by ) VALUES (  ? , ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollOption(), 2 ) );
       fl.addField( model.getIdPollQuestion(), 2 );
       fl.addField( model.getOptionText(), 12 );
       fl.addField( model.getOptionType(), 2 );
       fl.addField( model.getOptionDefault(), 12 );
       fl.addField( model.getIdPollOptionParent(), 2 );
       fl.addField( model.getOrderBy(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PollOptionModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PollOptionModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_poll_option=? , id_poll_question = ?, option_text = ?, option_type = ?, option_default = ?, id_poll_option_parent = ?, order_by = ? WHERE ID_POLL_OPTION=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPollOption(), 2 ) );
       fl.addField( model.getIdPollQuestion(), 2 );
       fl.addField( model.getOptionText(), 12 );
       fl.addField( model.getOptionType(), 2 );
       fl.addField( model.getOptionDefault(), 12 );
       fl.addField( model.getIdPollOptionParent(), 2 );
       fl.addField( model.getOrderBy(), 2 );
       fl.addField( model.getIdPollOption(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PollOptionModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PollOptionModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PollOptionModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PollOptionModel loadOne( String sql, Field f ) throws DAOException {
        return (PollOptionModel)loadOne( sql, f, RSE_MAIN );
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
		sql+="  ORDER BY  order_by ASC, id_poll_option ASC  ";
        this.loadAll( list, sql, this.newFieldList(), RSE_MAIN );
    }
    public List loadAll() throws DAOException {
        List list = this.newList();
        this.loadAll( list );
        return list;
    }
    public LoadResult loadAllResult() throws DAOException {
        String sql = this.queryView; 
		sql+="  ORDER BY  order_by ASC, id_poll_option ASC  ";
        return LoadResult.initResult( this, sql, this.newFieldList(), RSE_MAIN ) ;
    }

    public org.morozko.java.mod.db.dao.BasicDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.db.dao.BasicDAOFactory)this.getDaoFactory();
    }
    public PollOptionDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PollOptionDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PollOptionModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PollOptionModel model) throws DAOException {
    	this.loadRelationListPollOptionKids( model );

    }
    public void loadRelationListPollOptionKids( PollOptionModel model ) throws DAOException {
		if ( model != null  && model.getIdPollOptionParent() != null  ) {
    		org.morozko.java.mod.web.poll.dg.dao.PollOptionDAO listPollOptionKidsDAO = this.getModuleDaoFactory().getPollOptionDAO();
    		java.util.List listPollOptionKids = listPollOptionKidsDAO.outRelationListPollOptionKids( model );
    		model.setListPollOptionKids(listPollOptionKids);
		}
	}

	public java.util.List outRelationListPollOption( org.morozko.java.mod.web.poll.dg.model.PollQuestionModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdPollQuestion() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_POLL_QUESTION = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public java.util.List outRelationListPollOptionKids( org.morozko.java.mod.web.poll.dg.model.PollOptionModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdPollOptionParent() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_POLL_OPTION_PARENT = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public PollOptionModel loadOnePollOptionPk( org.morozko.java.mod.db.dao.DAOID id_poll_option ) throws DAOException { 
		PollOptionModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_option=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_option);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public List loadAllByQuestion( org.morozko.java.mod.db.dao.DAOID id_poll_question ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_question=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_option ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_question);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllByQuestion( org.morozko.java.mod.db.dao.DAOID id_poll_question ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_question=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_option ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_question);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
	public List loadAllByParent( org.morozko.java.mod.db.dao.DAOID id_poll_option_parent ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_option_parent=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_option ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_option_parent);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllByParent( org.morozko.java.mod.db.dao.DAOID id_poll_option_parent ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_poll_option_parent=? ";
		sql+=" ORDER BY  order_by ASC, id_poll_option ASC  ";
		FieldList fl = this.newFieldList();
		fl.addField(id_poll_option_parent);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
}
