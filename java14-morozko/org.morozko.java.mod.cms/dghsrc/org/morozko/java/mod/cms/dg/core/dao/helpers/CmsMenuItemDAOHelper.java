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
 * @(#)CmsMenuItemDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsMenuItemModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsMenuItemModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuItemDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 11485473359534L;

    protected static final CmsMenuItemModelRSEHelper RSE_MAIN = new CmsMenuItemModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_menu_item v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_menu_item";

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



    public OpDAO newInsertOpDAO( CmsMenuItemModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_menu_item, menu_item_type, id_cms_page, display, href, target, id_cms_menu ) VALUES (  ? , ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsMenuItem(), -5 ) );
       fl.addField( model.getMenuItemType(), 4 );
       fl.addField( model.getIdCmsPage(), -5 );
       fl.addField( model.getDisplay(), 12 );
       fl.addField( model.getHref(), 12 );
       fl.addField( model.getTarget(), 12 );
       fl.addField( model.getIdCmsMenu(), -5 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsMenuItemModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }

    public CmsMenuItemModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsMenuItemModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsMenuItemModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsMenuItemModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsMenuItemDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsMenuItemDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsMenuItemModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsMenuItemModel model) throws DAOException {

    }

	public java.util.List outRelationListCmsMenuItem( org.morozko.java.mod.cms.dg.core.model.CmsMenuModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdCmsMenu() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_cms_menu = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public CmsMenuItemModel loadOneCmsMenuitemPk( Long id_cms_menu_item ) throws DAOException { 
		CmsMenuItemModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_menu_item=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_menu_item);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
