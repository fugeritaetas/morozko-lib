/*
 * @(#)UserAccountDAOHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.dg.user.model.UserAccountModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di UserAccountModel.</p>
 *
 * @author Morozko
 */
public class UserAccountDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125112004101168L;

    protected static final UserAccountModelRSEHelper RSE_MAIN = new UserAccountModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM user_account v";

    protected String queryView;

    protected static final String SQL_UPDATE = "user_account";

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



    protected UserAccountModel loadByPkWorker( Object idUserAccount  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_user_account = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserAccount ) ); 
        return ( UserAccountModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idUserAccount  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_user_account = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserAccount ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( UserAccountModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( user_name, user_pass, user_enabled ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getUserName(), 12 ) );
       fl.addField( model.getUserPass(), 12 );
       fl.addField( model.getUserEnabled(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( UserAccountModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( UserAccountModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_user_account, user_name, user_pass, user_enabled ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserAccount(), 4 ) );
       fl.addField( model.getUserName(), 12 );
       fl.addField( model.getUserPass(), 12 );
       fl.addField( model.getUserEnabled(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( UserAccountModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( UserAccountModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_user_account=? , user_name = ?, user_pass = ?, user_enabled = ? WHERE id_user_account=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserAccount(), 4 ) );
       fl.addField( model.getUserName(), 12 );
       fl.addField( model.getUserPass(), 12 );
       fl.addField( model.getUserEnabled(), 4 );
       fl.addField( model.getIdUserAccount(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( UserAccountModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public UserAccountModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (UserAccountModel)loadOne( sql, fl, RSE_MAIN );
    }
    public UserAccountModel loadOne( String sql, Field f ) throws DAOException {
        return (UserAccountModel)loadOne( sql, f, RSE_MAIN );
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
    public UserAccountDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public UserAccountDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (UserAccountModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(UserAccountModel model) throws DAOException {
    	this.loadRelationListGroup( model );

    }
    public void loadRelationListGroup( UserAccountModel model ) throws DAOException {
		if ( model != null  && model.getIdUserAccount() != null  ) {
    		org.morozko.java.mod.web.dg.user.dao.UserGroupuserDAO listGroupDAO = this.getModuleDaoFactory().getUserGroupuserDAO();
    		java.util.List listGroup = listGroupDAO.outRelationListGroup( model );
    		model.setListGroup(listGroup);
		}
	}

	public UserAccountModel loadOnePrimary( org.morozko.java.mod.db.dao.DAOID id_user_account ) throws DAOException { 
		UserAccountModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user_account=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user_account);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public UserAccountModel loadOneUserAccountSk1( String user_name ) throws DAOException { 
		UserAccountModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.user_name=? ";
		FieldList fl = this.newFieldList();
		fl.addField(user_name);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
