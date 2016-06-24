/*
 * @(#)UserGroupDAOHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.dg.user.model.UserGroupModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di UserGroupModel.</p>
 *
 * @author Morozko
 */
public class UserGroupDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 125112004103175L;

    protected static final UserGroupModelRSEHelper RSE_MAIN = new UserGroupModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM user_group v";

    protected String queryView;

    protected static final String SQL_UPDATE = "user_group";

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



    protected UserGroupModel loadByPkWorker( Object idUserGroup  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_user_group = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserGroup ) ); 
        return ( UserGroupModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idUserGroup  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_user_group = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUserGroup ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( UserGroupModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_user_group, group_name ) VALUES (  ? , ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserGroup(), 4 ) );
       fl.addField( model.getGroupName(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( UserGroupModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( UserGroupModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_user_group=? , group_name = ? WHERE id_user_group=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUserGroup(), 4 ) );
       fl.addField( model.getGroupName(), 12 );
       fl.addField( model.getIdUserGroup(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( UserGroupModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public UserGroupModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (UserGroupModel)loadOne( sql, fl, RSE_MAIN );
    }
    public UserGroupModel loadOne( String sql, Field f ) throws DAOException {
        return (UserGroupModel)loadOne( sql, f, RSE_MAIN );
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
    public UserGroupDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public UserGroupDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (UserGroupModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(UserGroupModel model) throws DAOException {

    }

	public UserGroupModel loadOnePrimary( org.morozko.java.mod.db.dao.DAOID id_user_group ) throws DAOException { 
		UserGroupModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user_group=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user_group);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public UserGroupModel loadOneUserGroupSk1( String group_name ) throws DAOException { 
		UserGroupModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.group_name=? ";
		FieldList fl = this.newFieldList();
		fl.addField(group_name);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
