/*
 * @(#)UploadDAOHelper.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.dao.helpers
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.dao.helpers;

import java.util.List;
import org.morozko.java.core.util.result.PagedResult;
import org.morozko.java.mod.db.dao.PageInfoDB;
import org.morozko.java.mod.web.upload.dg.model.UploadModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.LoadResult;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di UploadModel.</p>
 *
 * @author Morozko
 */
public class UploadDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 131637056791041L;

    protected static final UploadModelRSEHelper RSE_MAIN = new UploadModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM {0}upload v";

    protected String queryView;

    protected static final String SQL_UPDATE = "{0}upload";

    protected String sqlUpdate;


	 public String getQueryViewString() { return QUERY_VIEW; }
	 public String getSqlUpdateString() { return SQL_UPDATE; }

    private UploadDAOFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( UploadDAOFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public UploadDAOFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    protected UploadModel loadByPkWorker( Object idUpload  ) throws DAOException { 
        String query = "SELECT * FROM ( "+this.queryView+") v WHERE id_upload = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUpload ) ); 
        return ( UploadModel ) this.loadOne( query, fl );
    } 
    public int deleteByPk( org.morozko.java.mod.db.dao.DAOID idUpload  ) throws DAOException { 
        String query = "DELETE FROM "+this.sqlUpdate+" WHERE id_upload = ? ";
        FieldList fl = this.newFieldList(); 
        fl.addField( this.getFieldFactory().newField( idUpload ) ); 
        return this.update( query, fl );
    } 
    public OpDAO newInsertOpDAOMysql( UploadModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_user, id_entity, file_section, time_upload, state, size, file_name, file_path, file_mode, file_type ) VALUES (  ? , ?, ?, ?, ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUser(), -5 ) );
       fl.addField( model.getIdEntity(), -5 );
       fl.addField( model.getFileSection(), 12 );
       fl.addField( model.getTimeUpload(), 93 );
       fl.addField( model.getState(), 4 );
       fl.addField( model.getSize(), 4 );
       fl.addField( model.getFileName(), 12 );
       fl.addField( model.getFilePath(), 12 );
       fl.addField( model.getFileMode(), 4 );
       fl.addField( model.getFileType(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int newInsertMysql( UploadModel model ) throws DAOException { 
       int result = this.update( this.newInsertOpDAO( model ) );
       return result;
    }
    public OpDAO newInsertOpDAO( UploadModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_upload, id_user, id_entity, file_section, time_upload, state, size, file_name, file_path, file_mode, file_type ) VALUES (  ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUpload(), -5 ) );
       fl.addField( model.getIdUser(), -5 );
       fl.addField( model.getIdEntity(), -5 );
       fl.addField( model.getFileSection(), 12 );
       fl.addField( model.getTimeUpload(), 93 );
       fl.addField( model.getState(), 4 );
       fl.addField( model.getSize(), 4 );
       fl.addField( model.getFileName(), 12 );
       fl.addField( model.getFilePath(), 12 );
       fl.addField( model.getFileMode(), 4 );
       fl.addField( model.getFileType(), 4 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( UploadModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }
    public OpDAO newUpdateOpDAO( UploadModel model ) throws DAOException { 
       String query = "UPDATE "+this.sqlUpdate+" SET id_upload=? , id_user = ?, id_entity = ?, file_section = ?, time_upload = ?, state = ?, size = ?, file_name = ?, file_path = ?, file_mode = ?, file_type = ? WHERE id_upload=?     ";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdUpload(), -5 ) );
       fl.addField( model.getIdUser(), -5 );
       fl.addField( model.getIdEntity(), -5 );
       fl.addField( model.getFileSection(), 12 );
       fl.addField( model.getTimeUpload(), 93 );
       fl.addField( model.getState(), 4 );
       fl.addField( model.getSize(), 4 );
       fl.addField( model.getFileName(), 12 );
       fl.addField( model.getFilePath(), 12 );
       fl.addField( model.getFileMode(), 4 );
       fl.addField( model.getFileType(), 4 );
       fl.addField( model.getIdUpload(), -2147483648 );
       return OpDAO.newUpdateOp( query, fl );
    }

    public int update( UploadModel model ) throws DAOException { 
       return this.update( newUpdateOpDAO( model ) );
    }

    public UploadModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (UploadModel)loadOne( sql, fl, RSE_MAIN );
    }
    public UploadModel loadOne( String sql, Field f ) throws DAOException {
        return (UploadModel)loadOne( sql, f, RSE_MAIN );
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
    public LoadResult loadAllResult() throws DAOException {
        String sql = this.queryView; 
        return LoadResult.initResult( this, sql, this.newFieldList(), RSE_MAIN ) ;
    }
    public PagedResult loadAllPaged( int perPage, int loadPage ) throws DAOException {
        return this.loadAllPaged( this.queryView, this.newFieldList(), RSE_MAIN, new PageInfoDB( loadPage, perPage ) );
    }
    public PagedResult loadAllPaged( int perPage, int loadPage, String orderBy ) throws DAOException {
        return this.loadAllPaged( this.queryView, this.newFieldList(), RSE_MAIN, new PageInfoDB( loadPage, perPage, orderBy ) );
    }

    public org.morozko.java.mod.db.dao.BasicDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.db.dao.BasicDAOFactory)this.getDaoFactory();
    }
    public UploadDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public UploadDAOHelper(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (UploadModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(UploadModel model) throws DAOException {

    }

	public UploadModel loadOnePrimary( org.morozko.java.mod.db.dao.DAOID id_upload ) throws DAOException { 
		UploadModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_upload=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_upload);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public UploadModel loadOneByFilePath( String file_path ) throws DAOException { 
		UploadModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_path=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_path);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
	public List loadAllByUser( org.morozko.java.mod.db.dao.DAOID id_user ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllByUser( org.morozko.java.mod.db.dao.DAOID id_user ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_user=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_user);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
	public List loadAllBySection( String file_section ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_section=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_section);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllBySection( String file_section ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_section=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_section);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
	public List loadAllBySectionAndEntity( String file_section , org.morozko.java.mod.db.dao.DAOID id_entity ) throws DAOException { 
		List result = this.newList();
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_section=?  AND v.id_entity=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_section);
		fl.addField(id_entity);
		this.loadAll( result, sql, fl );
		return result;
	}
	public LoadResult loadResultAllBySectionAndEntity( String file_section , org.morozko.java.mod.db.dao.DAOID id_entity ) throws DAOException { 
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.file_section=?  AND v.id_entity=? ";
		FieldList fl = this.newFieldList();
		fl.addField(file_section);
		fl.addField(id_entity);
		LoadResult result = this.loadAllResult( sql, fl, RSE_MAIN );
		return result;
	}
}
