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
 * @(#)CmsPageDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 29/08/2006 08/27/11
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.IdGenerator;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsPageModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsPageDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 115683283148436L;

    protected static final CmsPageModelRSEHelper RSE_MAIN = new CmsPageModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.*, c.kids_count 			FROM cms_page v, 				(SELECT v.id_cms_page , count(*) AS kids_count 				FROM cms_page v LEFT JOIN cms_page p ON v.id_cms_page=p.id_cms_page_parent 				GROUP BY v.id_cms_page) c 			WHERE v.id_cms_page=c.id_cms_page";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_page";

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



    // id generator START

    private IdGenerator idGenerator;

    public org.morozko.java.mod.db.dao.DAOID generateId() throws DAOException {
    	if ( this.idGenerator == null ) { 
    		try {
    			java.util.Properties props = new java.util.Properties();
    			props.setProperty( "sequenceName","seq_id_cms" );
    			this.idGenerator = (IdGenerator)ClassHelper.newInstance( "org.morozko.java.mod.db.dao.idgen.GenericSeqIdGenerator" );
    			this.idGenerator.setConnectionFactory( this.getMainDAOFactory().getConnectionFactory() ); 
    			this.idGenerator.configure( props ); 
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	return this.idGenerator.generateId();
    }

    // id generator END


    protected CmsPageModel loadByPkWorker( Object idCmsPage  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_cms_page = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsPage ) ); 
        return ( CmsPageModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idCmsPage  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_cms_page = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsPage ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( CmsPageModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_page, cms_page_code, cms_page_type, cms_page_state, save_date, display, title, description, page_url, id_cms_page_parent, id_cms_filter_chain ) VALUES (  ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsPage(), -5 ) );
       fl.addField( model.getCmsPageCode(), -5 );
       fl.addField( model.getCmsPageType(), 4 );
       fl.addField( model.getCmsPageState(), 4 );
       fl.addField( model.getSaveDate(), 93 );
       fl.addField( model.getDisplay(), 12 );
       fl.addField( model.getTitle(), 12 );
       fl.addField( model.getDescription(), 12 );
       fl.addField( model.getPageUrl(), 12 );
       fl.addField( model.getIdCmsPageParent(), -5 );
       fl.addField( model.getIdCmsFilterChain(), -5 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsPageModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( CmsPageModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_cms_page=? , cms_page_code = ?, cms_page_type = ?, cms_page_state = ?, save_date = ?, display = ?, title = ?, description = ?, page_url = ?, id_cms_page_parent = ?, id_cms_filter_chain = ? WHERE id_cms_page=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsPage(), -5 ) );
       fl.addField( model.getCmsPageCode(), -5 );
       fl.addField( model.getCmsPageType(), 4 );
       fl.addField( model.getCmsPageState(), 4 );
       fl.addField( model.getSaveDate(), 93 );
       fl.addField( model.getDisplay(), 12 );
       fl.addField( model.getTitle(), 12 );
       fl.addField( model.getDescription(), 12 );
       fl.addField( model.getPageUrl(), 12 );
       fl.addField( model.getIdCmsPageParent(), -5 );
       fl.addField( model.getIdCmsFilterChain(), -5 );
       fl.addField( model.getIdCmsPage(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( CmsPageModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public CmsPageModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsPageModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsPageModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsPageModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsPageDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsPageDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsPageModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsPageModel model) throws DAOException {
    	this.loadRelationCmsFilterChain( model );

    }
    public void loadRelationCmsFilterChain( CmsPageModel model ) throws DAOException {
		if ( model != null  && model.getIdCmsFilterChain() != null  ) {
    		org.morozko.java.mod.cms.dg.core.dao.CmsFilterChainDAO cmsFilterChainDAO = this.getModuleDaoFactory().getCmsFilterChainDAO();
    		org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel cmsFilterChain = cmsFilterChainDAO.outRelationCmsFilterChain( model );
    		model.setCmsFilterChain(cmsFilterChain);
		}
	}

	public CmsPageModel loadOneCmsPagePk( org.morozko.java.mod.db.dao.DAOID id_cms_page ) throws DAOException { 
		CmsPageModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_page=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_page);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsPageModel loadOneCmsPageUn1( org.morozko.java.mod.db.dao.DAOID cms_page_code , java.sql.Timestamp save_date ) throws DAOException { 
		CmsPageModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.cms_page_code=?  AND v.save_date=? ";
		FieldList fl = this.newFieldList();
		fl.addField(cms_page_code);
		fl.addField(save_date);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsPageModel loadOneCmsPageUn2( String page_url ) throws DAOException { 
		CmsPageModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.page_url=? ";
		FieldList fl = this.newFieldList();
		fl.addField(page_url);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public List loadAllByParent( org.morozko.java.mod.db.dao.DAOID id_cms_page_parent ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_page_parent=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_page_parent);
		this.loadAll( result, sql, fl );
		return result;
	}
}
