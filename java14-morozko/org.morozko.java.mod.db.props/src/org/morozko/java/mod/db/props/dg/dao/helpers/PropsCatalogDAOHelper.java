/*
 * @(#)PropsCatalogDAOHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.db.props.dg.model.PropsCatalogModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PropsCatalogModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsCatalogDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 120783766652118L;

    protected static final PropsCatalogModelRSEHelper RSE_MAIN = new PropsCatalogModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}PROPS_CATALOG v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}PROPS_CATALOG";

    protected String sqlUpdate;

    private PropsDaoFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( PropsDaoFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public PropsDaoFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    // id generator START

    private IdGenerator idGenerator;

    public org.morozko.java.mod.db.dao.DAOID generateId() throws DAOException {
    	if ( this.idGenerator == null ) { 
    		try {
    			java.util.Properties props = new java.util.Properties();
    			props.setProperty( "sequenceName","seq_id_props" );
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


    protected PropsCatalogModel loadByPkWorker( Object idPropsCatalog  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_PROPS_CATALOG = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPropsCatalog ) ); 
        return ( PropsCatalogModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPropsCatalog  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_PROPS_CATALOG = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPropsCatalog ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( PropsCatalogModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_props_catalog, prop_cat, prop_description ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPropsCatalog(), 2 ) );
       fl.addField( model.getPropCat(), 12 );
       fl.addField( model.getPropDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PropsCatalogModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PropsCatalogModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_props_catalog=? , prop_cat = ?, prop_description = ? WHERE ID_PROPS_CATALOG=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPropsCatalog(), 2 ) );
       fl.addField( model.getPropCat(), 12 );
       fl.addField( model.getPropDescription(), 12 );
       fl.addField( model.getIdPropsCatalog(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PropsCatalogModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PropsCatalogModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PropsCatalogModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PropsCatalogModel loadOne( String sql, Field f ) throws DAOException {
        return (PropsCatalogModel)loadOne( sql, f, RSE_MAIN );
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

    public org.morozko.java.mod.db.dao.BasicDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.db.dao.BasicDAOFactory)this.getDaoFactory();
    }
    public PropsCatalogDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PropsCatalogDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PropsCatalogModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PropsCatalogModel model) throws DAOException {

    }

	public java.util.List outRelationListPropsCatalog( org.morozko.java.mod.db.props.dg.model.PropsValuesModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdPropsCatalog() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_PROPS_CATALOG = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public PropsCatalogModel loadOnePropsCatalogPk( org.morozko.java.mod.db.dao.DAOID id_props_catalog ) throws DAOException { 
		PropsCatalogModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_props_catalog=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_props_catalog);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public PropsCatalogModel loadOnePropsCatalogSk( String prop_cat ) throws DAOException { 
		PropsCatalogModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.prop_cat=? ";
		FieldList fl = this.newFieldList();
		fl.addField(prop_cat);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
