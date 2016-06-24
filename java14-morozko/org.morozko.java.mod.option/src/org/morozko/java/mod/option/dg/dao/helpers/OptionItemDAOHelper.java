/*
 * @(#)OptionItemDAOHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.dao.helpers;

import java.util.List;
import org.morozko.java.mod.option.dg.model.OptionItemModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di OptionItemModel.</p>
 *
 * @author Matteo Franci aka Morozko
 */
public class OptionItemDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 120646245579115L;

    protected static final OptionItemModelRSEHelper RSE_MAIN = new OptionItemModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* , c.catalog_key 		FROM {0}OPTION_ITEM v, {0}option_catalog c 		WHERE v.id_option_catalog = c.id_option_catalog";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}OPTION_ITEM";

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


    protected OptionItemModel loadByPkWorker( Object idOptionItem  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_option_item = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idOptionItem ) ); 
        return ( OptionItemModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idOptionItem  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_option_item = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idOptionItem ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( OptionItemModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_option_item, item_value, enabled, ordering, id_option_catalog ) VALUES (  ? , ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdOptionItem(), 2 ) );
       fl.addField( model.getItemValue(), 12 );
       fl.addField( model.getEnabled(), 2 );
       fl.addField( model.getOrdering(), 2 );
       fl.addField( model.getIdOptionCatalog(), 2 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( OptionItemModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( OptionItemModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_option_item=? , item_value = ?, enabled = ?, ordering = ?, id_option_catalog = ? WHERE id_option_item=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdOptionItem(), 2 ) );
       fl.addField( model.getItemValue(), 12 );
       fl.addField( model.getEnabled(), 2 );
       fl.addField( model.getOrdering(), 2 );
       fl.addField( model.getIdOptionCatalog(), 2 );
       fl.addField( model.getIdOptionItem(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( OptionItemModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public OptionItemModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (OptionItemModel)loadOne( sql, fl, RSE_MAIN );
    }
    public OptionItemModel loadOne( String sql, Field f ) throws DAOException {
        return (OptionItemModel)loadOne( sql, f, RSE_MAIN );
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
    public OptionItemDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public OptionItemDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (OptionItemModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(OptionItemModel model) throws DAOException {

    }

	public java.util.List outRelationListOptionItem( org.morozko.java.mod.option.dg.model.OptionCatalogModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdOptionCatalog() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.ID_OPTION_CATALOG = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public OptionItemModel loadOneOptionItemPk( org.morozko.java.mod.db.dao.DAOID id_option_item ) throws DAOException { 
		OptionItemModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_option_item=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_option_item);
		result = this.loadOne( sql, fl );
		return result;
	}
	public OptionItemModel loadOneOptionItemSk1( org.morozko.java.mod.db.dao.DAOID id_option_catalog , String item_value ) throws DAOException { 
		OptionItemModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_option_catalog=?  AND v.item_value=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_option_catalog);
		fl.addField(item_value);
		result = this.loadOne( sql, fl );
		return result;
	}
}
