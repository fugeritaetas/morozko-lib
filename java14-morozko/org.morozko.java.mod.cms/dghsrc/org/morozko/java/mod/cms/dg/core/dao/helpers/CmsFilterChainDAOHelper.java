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
 * @(#)CmsFilterChainDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 25/08/2006 15/44/38
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsFilterChainModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFilterChainDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 115651347812522L;

    protected static final CmsFilterChainModelRSEHelper RSE_MAIN = new CmsFilterChainModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_filter_chain v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_filter_chain";

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



    protected CmsFilterChainModel loadByPkWorker( Object idCmsFilterChain  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_cms_filter_chain = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFilterChain ) ); 
        return ( CmsFilterChainModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idCmsFilterChain  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_cms_filter_chain = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFilterChain ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( CmsFilterChainModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_filter_chain, chain_name, chain_description ) VALUES (  ? , ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFilterChain(), -5 ) );
       fl.addField( model.getChainName(), 12 );
       fl.addField( model.getChainDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsFilterChainModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( CmsFilterChainModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_cms_filter_chain=? , chain_name = ?, chain_description = ? WHERE id_cms_filter_chain=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFilterChain(), -5 ) );
       fl.addField( model.getChainName(), 12 );
       fl.addField( model.getChainDescription(), 12 );
       fl.addField( model.getIdCmsFilterChain(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( CmsFilterChainModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public CmsFilterChainModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsFilterChainModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsFilterChainModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsFilterChainModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsFilterChainDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsFilterChainDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsFilterChainModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsFilterChainModel model) throws DAOException {
    	this.loadRelationListCmsFilterHandler( model );

    }
    public void loadRelationListCmsFilterHandler( CmsFilterChainModel model ) throws DAOException {
		if ( model != null  && model.getIdCmsFilterChain() != null  ) {
    		org.morozko.java.mod.cms.dg.core.dao.CmsFilterHandlerDAO listCmsFilterHandlerDAO = this.getModuleDaoFactory().getCmsFilterHandlerDAO();
    		java.util.List listCmsFilterHandler = listCmsFilterHandlerDAO.outRelationListCmsFilterHandler( model );
    		model.setListCmsFilterHandler(listCmsFilterHandler);
		}
	}

	public CmsFilterChainModel outRelationCmsFilterChain( org.morozko.java.mod.cms.dg.core.model.CmsPageModel model ) throws DAOException {
		CmsFilterChainModel result = new CmsFilterChainModel();
		FieldList fl = this.newFieldList();
		fl.addField( model.getIdCmsFilterChain() );
		String sql =  "SELECT v.* FROM ( "+this.queryView+" ) v WHERE 1=1  AND v.id_cms_filter_chain = ? ";
		result = this.loadOne( sql, fl );
		return result;
	}

	public CmsFilterChainModel loadOneCcmsFilterChainUn1( String chain_name ) throws DAOException { 
		CmsFilterChainModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.chain_name=? ";
		FieldList fl = this.newFieldList();
		fl.addField(chain_name);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsFilterChainModel loadOneCmsFilterChainPk( org.morozko.java.mod.db.dao.DAOID id_cms_filter_chain ) throws DAOException { 
		CmsFilterChainModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_filter_chain=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_filter_chain);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
