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
 * @(#)CmsFileDescriptionDAOHelper.java
 *
 * @project    : OpinfCMS
 * @package    : org.morozko.java.mod.cms.dg.core.dao.helpers
 * @creation   : 11/08/2006 15/13/08
 */
package org.morozko.java.mod.cms.dg.core.dao.helpers;

import java.util.List;

import org.morozko.java.mod.cms.dg.core.model.CmsFileDescriptionModel;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.OpDAO;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di CmsFileDescriptionModel.</p>
 *
 * @author Matteo Franci
 */
public class CmsFileDescriptionDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 115530198845382L;

    protected static final CmsFileDescriptionModelRSEHelper RSE_MAIN = new CmsFileDescriptionModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM cms_file_description v";

    protected String queryView;

    protected static final String SQL_UPDATE = "cms_file_description";

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



    protected CmsFileDescriptionModel loadByPkWorker( Object idCmsFile  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_cms_file = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFile ) ); 
        return ( CmsFileDescriptionModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idCmsFile  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_cms_file = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idCmsFile ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAO( CmsFileDescriptionModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_cms_file, file_path, file_display, file_description ) VALUES (  ? , ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFile(), -5 ) );
       fl.addField( model.getFilePath(), 12 );
       fl.addField( model.getFileDisplay(), 12 );
       fl.addField( model.getFileDescription(), 12 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( CmsFileDescriptionModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( CmsFileDescriptionModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_cms_file=? , file_path = ?, file_display = ?, file_description = ? WHERE id_cms_file=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdCmsFile(), -5 ) );
       fl.addField( model.getFilePath(), 12 );
       fl.addField( model.getFileDisplay(), 12 );
       fl.addField( model.getFileDescription(), 12 );
       fl.addField( model.getIdCmsFile(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( CmsFileDescriptionModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public CmsFileDescriptionModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (CmsFileDescriptionModel)loadOne( sql, fl, RSE_MAIN );
    }
    public CmsFileDescriptionModel loadOne( String sql, Field f ) throws DAOException {
        return (CmsFileDescriptionModel)loadOne( sql, f, RSE_MAIN );
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
    public CmsFileDescriptionDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public CmsFileDescriptionDAOHelper(org.morozko.java.mod.cms.dg.CmsDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (CmsFileDescriptionModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(CmsFileDescriptionModel model) throws DAOException {

    }

	public CmsFileDescriptionModel loadOneCmsFileDescriptionPk( org.morozko.java.mod.db.dao.DAOID id_cms_file ) throws DAOException { 
		CmsFileDescriptionModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_cms_file=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_cms_file);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public CmsFileDescriptionModel loadOneCmsFileDescriptionUn1( String file_path ) throws DAOException { 
		CmsFileDescriptionModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_path=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_path);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
