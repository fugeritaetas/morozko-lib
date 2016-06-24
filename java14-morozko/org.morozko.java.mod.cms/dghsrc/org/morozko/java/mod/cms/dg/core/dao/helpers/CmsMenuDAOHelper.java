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
 * @(#)CmsMenuDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/05/2006 10/55/35
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsMenuModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsMenuModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsMenuDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 114854733590684L;

    protected static final CmsMenuModelRSEHelper RSE_MAIN = new CmsMenuModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_menu v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_menu";

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



    public OpDAO newInsertOpDAO( CmsMenuModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_menu, name, description ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsMenu(), -5 ) );
       fl.addField( model.getName(), 12 );
       fl.addField( model.getDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsMenuModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }

    public CmsMenuModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsMenuModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsMenuModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsMenuModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsMenuDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsMenuDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsMenuModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsMenuModel model) throws DAOException {
    	this.loadRelationListCmsMenuItem( model );
    	this.loadRelationListCmsRelPm( model );

    }
    public void loadRelationListCmsMenuItem( CmsMenuModel model ) throws DAOException {
		if ( model != null) {
    		org.morozko.java.mod.cms.dg.core.dao.CmsMenuItemDAO listCmsMenuItemDAO = this.getModuleDaoFactory().getCmsMenuItemDAO();
    		java.util.List listCmsMenuItem = listCmsMenuItemDAO.outRelationListCmsMenuItem( model );
    		model.setListCmsMenuItem(listCmsMenuItem);
		}
	}
    public void loadRelationListCmsRelPm( CmsMenuModel model ) throws DAOException {
		if ( model != null) {
    		org.morozko.java.mod.cms.dg.core.dao.CmsRelPmDAO listCmsRelPmDAO = this.getModuleDaoFactory().getCmsRelPmDAO();
    		java.util.List listCmsRelPm = listCmsRelPmDAO.outRelationListCmsRelPm( model );
    		model.setListCmsRelPm(listCmsRelPm);
		}
	}

	public CmsMenuModel loadOneCmsMenuPk( Long id_cms_menu ) throws DAOException { 
		CmsMenuModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_menu=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_menu);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
