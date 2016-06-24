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
 * @(#)CmsRelPmDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/05/2006 10/56/34
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsRelPmModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsRelPmModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPmDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 114854739409362L;

    protected static final CmsRelPmModelRSEHelper RSE_MAIN = new CmsRelPmModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_rel_pm v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_rel_pm";

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



    public OpDAO newInsertOpDAO( CmsRelPmModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_rel_pm, menu_order, id_cms_page, id_cms_menu ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsRelPm(), -5 ) );
       fl.addField( model.getMenuOrder(), 4 );
       fl.addField( model.getIdCmsPage(), -5 );
       fl.addField( model.getIdCmsMenu(), -5 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsRelPmModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }

    public CmsRelPmModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsRelPmModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsRelPmModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsRelPmModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsRelPmDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsRelPmDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsRelPmModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsRelPmModel model) throws DAOException {

    }

	public java.util.List outRelationListCmsRelPm( org.morozko.java.mod.cms.dg.core.model.CmsMenuModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdCmsMenu() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_cms_menu = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public java.util.List outRelationListCmsRelPm( org.morozko.java.mod.cms.dg.core.model.CmsPageModel model ) throws DAOException {
		java.util.List result = this.newList();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdCmsPage() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_cms_page = ? ";
		this.loadAll( result, sql, fl );
		return result;
	}

	public CmsRelPmModel loadOneCmsRelPmPk( Long id_cms_rel_pm ) throws DAOException { 
		CmsRelPmModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_rel_pm=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_rel_pm);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsRelPmModel loadOneCmsRelPmUn1( Long id_cms_page , Long id_cms_menu ) throws DAOException { 
		CmsRelPmModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_page=?  AND v.id_cms_menu=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_page);
		fl.addField(id_cms_menu);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsRelPmModel loadOneCmsRelPmUn2( Long id_cms_page , Integer menu_order ) throws DAOException { 
		CmsRelPmModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_page=?  AND v.menu_order=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_page);
		fl.addField(menu_order);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
