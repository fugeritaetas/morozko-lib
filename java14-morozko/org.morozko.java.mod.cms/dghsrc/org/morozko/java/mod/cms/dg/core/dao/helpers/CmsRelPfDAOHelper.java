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
 * @(#)CmsRelPfDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 07/06/2006 14/39/20
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsRelPfModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsRelPfModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsRelPfDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 114968396076586L;

    protected static final CmsRelPfModelRSEHelper RSE_MAIN = new CmsRelPfModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_rel_pf v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_rel_pf";

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



    public OpDAO newInsertOpDAO( CmsRelPfModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_rel_pf, id_cms_page, id_cms_fragment ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsRelPf(), -5 ) );
       fl.addField( model.getIdCmsPage(), -5 );
       fl.addField( model.getIdCmsFragment(), -5 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsRelPfModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }

    public CmsRelPfModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsRelPfModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsRelPfModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsRelPfModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsRelPfDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsRelPfDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsRelPfModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsRelPfModel model) throws DAOException {

    }

	public CmsRelPfModel loadOneCmsRelPfPk( org.morozko.java.mod.db.dao.DAOID id_cms_rel_pf ) throws DAOException { 
		CmsRelPfModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_rel_pf=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_rel_pf);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsRelPfModel loadOneCmsRelPfUn1( org.morozko.java.mod.db.dao.DAOID id_cms_page , org.morozko.java.mod.db.dao.DAOID id_cms_fragment ) throws DAOException { 
		CmsRelPfModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_page=?  AND v.id_cms_fragment=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_page);
		fl.addField(id_cms_fragment);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
