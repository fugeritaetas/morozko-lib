/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.app.cms 

	Copyright (c) 2006 OpenInformatica

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)CmsFilterHandlerDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsFilterHandlerModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsFilterHandlerModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterHandlerDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 11565134782183L;

    protected static final CmsFilterHandlerModelRSEHelper RSE_MAIN = new CmsFilterHandlerModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_filter_handler v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_filter_handler";

    protected String sqlUpdate;

    private CmsCoreDAOFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( CmsCoreDAOFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public CmsCoreDAOFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    protected CmsFilterHandlerModel loadByPkWorker( Object idCmsFilterHandler  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_cms_filter_handler = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFilterHandler ) ); 
        return ( CmsFilterHandlerModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idCmsFilterHandler  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_cms_filter_handler = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFilterHandler ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( CmsFilterHandlerModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_filter_handler, handler_type, handler_config, handler_description, id_cms_filter_chain ) VALUES (  ? , ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFilterHandler(), -5 ) );
       fl.addField( model.getHandlerType(), 12 );
       fl.addField( model.getHandlerConfig(), 12 );
       fl.addField( model.getHandlerDescription(), 12 );
       fl.addField( model.getIdCmsFilterChain(), -5 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsFilterHandlerModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( CmsFilterHandlerModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_cms_filter_handler=? , handler_type = ?, handler_config = ?, handler_description = ?, id_cms_filter_chain = ? WHERE id_cms_filter_handler=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFilterHandler(), -5 ) );
       fl.addField( model.getHandlerType(), 12 );
       fl.addField( model.getHandlerConfig(), 12 );
       fl.addField( model.getHandlerDescription(), 12 );
       fl.addField( model.getIdCmsFilterChain(), -5 );
       fl.addField( model.getIdCmsFilterHandler(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( CmsFilterHandlerModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public CmsFilterHandlerModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsFilterHandlerModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsFilterHandlerModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsFilterHandlerModel)loadOne( sql, f, RSE_MAIN );
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
        this.loadAll( list, this.queryView, this.newFieldList(), RSE_MAIN );
    }
    public List loadAll() throws DAOException {
        List list = this.newList();
        this.loadAll( list );
        return list;
    }

    public org.morozko.java.mod.cms.dg.CmsDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.cms.dg.CmsDAOFactory)this.getDaoFactory();
    }
    public CmsFilterHandlerDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsFilterHandlerDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsFilterHandlerModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsFilterHandlerModel model) throws DAOException {

    }

	public java.util.List outRelationListCmsFilterHandler( org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdCmsFilterChain() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_cms_filter_chain = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public CmsFilterHandlerModel loadOneCmsFilterHandlerPk( org.morozko.java.mod.db.dao.DAOID id_cms_filter_handler ) throws DAOException { 
		CmsFilterHandlerModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_filter_handler=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_filter_handler);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
