/*
 * @(#)OptionCatalogDAOHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.option.dg.model.OptionCatalogModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di OptionCatalogModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionCatalogDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 120646245574015L;

    protected static final OptionCatalogModelRSEHelper RSE_MAIN = new OptionCatalogModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}OPTION_CATALOG v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}OPTION_CATALOG";

    protected String sqlUpdate;

    private OptionDaoFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( OptionDaoFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public OptionDaoFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    // id generator START

    private IdGenerator idGenerator;

    public org.morozko.java.mod.db.dao.DAOID generateId() throws DAOException {
    	if ( this.idGenerator == null ) { 
    		try {
    			java.util.Properties props = new java.util.Properties();
    			props.setProperty( "sequenceName","seq_id_option" );
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


    protected OptionCatalogModel loadByPkWorker( Object idOptionCatalog  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_option_catalog = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idOptionCatalog ) ); 
        return ( OptionCatalogModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idOptionCatalog  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_option_catalog = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idOptionCatalog ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( OptionCatalogModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_option_catalog, catalog_key, description, catalog_type ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdOptionCatalog(), 2 ) );
       fl.addField( model.getCatalogKey(), 12 );
       fl.addField( model.getDescription(), 12 );
       fl.addField( model.getCatalogType(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( OptionCatalogModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( OptionCatalogModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_option_catalog=? , catalog_key = ?, description = ?, catalog_type = ? WHERE id_option_catalog=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdOptionCatalog(), 2 ) );
       fl.addField( model.getCatalogKey(), 12 );
       fl.addField( model.getDescription(), 12 );
       fl.addField( model.getCatalogType(), 2 );
       fl.addField( model.getIdOptionCatalog(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( OptionCatalogModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public OptionCatalogModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (OptionCatalogModel)loadOne( sql, fl, RSE_MAIN );
    }
    public OptionCatalogModel loadOne( String sql, Field f ) throws DAOException {
        return (OptionCatalogModel)loadOne( sql, f, RSE_MAIN );
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
    public OptionCatalogDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public OptionCatalogDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (OptionCatalogModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(OptionCatalogModel model) throws DAOException {
    	this.loadRelationListOptionItem( model );

    }
    public void loadRelationListOptionItem( OptionCatalogModel model ) throws DAOException {
		if ( model != null  && model.getIdOptionCatalog() != null  ) {
    		org.morozko.java.mod.option.dg.dao.OptionItemDAO listOptionItemDAO = this.getModuleDaoFactory().getOptionItemDAO();
    		java.util.List listOptionItem = listOptionItemDAO.outRelationListOptionItem( model );
    		model.setListOptionItem(listOptionItem);
		}
	}

	public OptionCatalogModel loadOneOptionCatalogPk( org.morozko.java.mod.db.dao.DAOID id_option_catalog ) throws DAOException { 
		OptionCatalogModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_option_catalog=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_option_catalog);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public OptionCatalogModel loadOneOptionCatalogSk1( String catalog_key ) throws DAOException { 
		OptionCatalogModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.catalog_key=? ";
		FieldList fl = this.newFieldList();
		fl.addField(catalog_key);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
