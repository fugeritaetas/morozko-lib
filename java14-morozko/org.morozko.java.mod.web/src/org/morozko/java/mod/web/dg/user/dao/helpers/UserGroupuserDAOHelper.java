/*
 * @(#)UserGroupuserDAOHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.dg.user.model.UserGroupuserModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di UserGroupuserModel.</p>
 *
 * @author Morozko
 */
public class UserGroupuserDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125112004106156L;

    protected static final UserGroupuserModelRSEHelper RSE_MAIN = new UserGroupuserModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.*, g.group_name FROM user_groupuser v, user_group g WHERE v.id_user_group = g.id_user_group";

    protected String queryView;

    protected static final String SQL_UPDATE = "user_groupuser";

    protected String sqlUpdate;

    private UserDAOFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( UserDAOFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public UserDAOFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    protected UserGroupuserModel loadByPkWorker( Object idUserGroupuser  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_user_groupuser = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserGroupuser ) ); 
        return ( UserGroupuserModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idUserGroupuser  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_user_groupuser = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserGroupuser ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( UserGroupuserModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_user_account, id_user_group ) VALUES (  ? , ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserAccount(), 4 ) );
       fl.addField( model.getIdUserGroup(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( UserGroupuserModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( UserGroupuserModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_user_groupuser, id_user_account, id_user_group ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserGroupuser(), 4 ) );
       fl.addField( model.getIdUserAccount(), 4 );
       fl.addField( model.getIdUserGroup(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( UserGroupuserModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( UserGroupuserModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_user_groupuser=? , id_user_account = ?, id_user_group = ? WHERE id_user_groupuser=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserGroupuser(), 4 ) );
       fl.addField( model.getIdUserAccount(), 4 );
       fl.addField( model.getIdUserGroup(), 4 );
       fl.addField( model.getIdUserGroupuser(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( UserGroupuserModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public UserGroupuserModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (UserGroupuserModel)loadOne( sql, fl, RSE_MAIN );
    }
    public UserGroupuserModel loadOne( String sql, Field f ) throws DAOException {
        return (UserGroupuserModel)loadOne( sql, f, RSE_MAIN );
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

    public org.morozko.java.mod.web.dg.WebDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.web.dg.WebDAOFactory)this.getDaoFactory();
    }
    public UserGroupuserDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public UserGroupuserDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (UserGroupuserModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(UserGroupuserModel model) throws DAOException {

    }

	public java.util.List outRelationListGroup( org.morozko.java.mod.web.dg.user.model.UserAccountModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdUserAccount() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_user_account = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public UserGroupuserModel loadOnePrimary( org.morozko.java.mod.db.dao.DAOID id_user_groupuser ) throws DAOException { 
		UserGroupuserModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user_groupuser=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user_groupuser);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public UserGroupuserModel loadOneUserGroupuserSk1( org.morozko.java.mod.db.dao.DAOID id_user_account , org.morozko.java.mod.db.dao.DAOID id_user_group ) throws DAOException { 
		UserGroupuserModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user_account=?  AND v.id_user_group=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user_account);
		fl.addField(id_user_group);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
