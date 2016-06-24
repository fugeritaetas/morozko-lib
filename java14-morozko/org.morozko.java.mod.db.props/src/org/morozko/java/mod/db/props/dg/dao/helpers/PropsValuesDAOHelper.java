/*
 * @(#)PropsValuesDAOHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.db.props.dg.model.PropsValuesModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di PropsValuesModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class PropsValuesDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 120783766658172L;

    protected static final PropsValuesModelRSEHelper RSE_MAIN = new PropsValuesModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT p.*, c.prop_cat 		 FROM {0}props_values p, {0}props_catalog c 		 WHERE  p.id_props_catalog = c.id_props_catalog";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}PROPS_VALUES";

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


    protected PropsValuesModel loadByPkWorker( Object idPropsValue  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE ID_PROPS_VALUE = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPropsValue ) ); 
        return ( PropsValuesModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idPropsValue  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE ID_PROPS_VALUE = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idPropsValue ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( PropsValuesModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_props_value, prop_key, prop_value, id_props_catalog ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPropsValue(), 2 ) );
       fl.addField( model.getPropKey(), 12 );
       fl.addField( model.getPropValue(), 12 );
       fl.addField( model.getIdPropsCatalog(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( PropsValuesModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( PropsValuesModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_props_value=? , prop_key = ?, prop_value = ?, id_props_catalog = ? WHERE ID_PROPS_VALUE=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdPropsValue(), 2 ) );
       fl.addField( model.getPropKey(), 12 );
       fl.addField( model.getPropValue(), 12 );
       fl.addField( model.getIdPropsCatalog(), 2 );
       fl.addField( model.getIdPropsValue(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( PropsValuesModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public PropsValuesModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (PropsValuesModel)loadOne( sql, fl, RSE_MAIN );
    }
    public PropsValuesModel loadOne( String sql, Field f ) throws DAOException {
        return (PropsValuesModel)loadOne( sql, f, RSE_MAIN );
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
    public PropsValuesDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public PropsValuesDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (PropsValuesModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(PropsValuesModel model) throws DAOException {
    	this.loadRelationListPropsCatalog( model );

    }
    public void loadRelationListPropsCatalog( PropsValuesModel model ) throws DAOException {
		if ( model != null  && model.getIdPropsCatalog() != null  ) {
    		org.morozko.java.mod.db.props.dg.dao.PropsCatalogDAO listPropsCatalogDAO = this.getModuleDaoFactory().getPropsCatalogDAO();
    		java.util.List listPropsCatalog = listPropsCatalogDAO.outRelationListPropsCatalog( model );
    		model.setListPropsCatalog(listPropsCatalog);
		}
	}

	public PropsValuesModel loadOnePropsValuesPk( org.morozko.java.mod.db.dao.DAOID id_props_value ) throws DAOException { 
		PropsValuesModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_props_value=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_props_value);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public PropsValuesModel loadOnePropsValuesSk1( org.morozko.java.mod.db.dao.DAOID id_props_catalog , String prop_key ) throws DAOException { 
		PropsValuesModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_props_catalog=?  AND v.prop_key=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_props_catalog);
		fl.addField(prop_key);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public List loadAllByCatalog( String prop_cat ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.prop_cat=? ";
		FieldList fl = this.newFieldList();
		fl.addField(prop_cat);
		this.loadAll( result, sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
