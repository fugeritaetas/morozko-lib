/*
 * @(#)WebAccessDataDAOHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.core.dao.helpers
 * @creation   : 03/10/2007 08/35/38
 */
package org.morozko.java.mod.web.dg.core.dao.helpers;

import java.util.List;
import org.morozko.java.mod.web.dg.core.model.WebAccessDataModel;
import org.morozko.java.mod.db.dao.Field;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;

/**
 * <p>Classe per la gestione dell' accesso al DB per oggetti di WebAccessDataModel.</p>
 *
 * @author Morozko
 */
public class WebAccessDataDAOHelper extends org.morozko.java.mod.db.dao.BasicDAO {

	private final static long serialVersionUID = 11913933387590L;

    protected static final WebAccessDataModelRSEHelper RSE_MAIN = new WebAccessDataModelRSEHelper();

    protected static final String QUERY_VIEW = "SELECT v.* FROM web_access_data v";

    protected String queryView;

    protected static final String SQL_UPDATE = "web_access_data";

    protected String sqlUpdate;

    private WebCoreDAOFactoryHelper moduleDaoFactory;

    /** 
     * <p>Imposta il valore di moduleDaoFactory</p> 
     * 
     * @param      moduleDaoFactory il valore di moduleDaoFactory da impostare
     */ 
    public void setModuleDaoFactory( WebCoreDAOFactoryHelper moduleDaoFactory ) {
        this.moduleDaoFactory = moduleDaoFactory;
    }

    /** 
     * <p>Restituisce il valore di moduleDaoFactory</p> 
     * 
     * @return      restituisce il valore di moduleDaoFactory
     */ 
    public WebCoreDAOFactoryHelper getModuleDaoFactory() {
        return this.moduleDaoFactory;
    }



    // id generator START

    private IdGenerator idGenerator;

    public org.morozko.java.mod.db.dao.DAOID generateId() throws DAOException {
    	if ( this.idGenerator == null ) { 
    		try {
    			java.util.Properties props = new java.util.Properties();
    			props.setProperty( "sequenceName","seq_id_jlib_mod_web" );
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


    public OpDAO newInsertOpDAO( WebAccessDataModel model ) throws DAOException { 
       String query = "INSERT INTO "+this.sqlUpdate+" ( id_web_access_data, session_id, request_uri, request_url, request_method, request_query_string, request_context_path, request_remote_addr, request_remote_host, request_remote_user, request_remote_port, request_server_name, request_server_port, request_scheme, response_status_code, application_host, request_start, request_end ) VALUES (  ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
       FieldList fl = this.newFieldList();
       fl.addField( this.getFieldFactory().newField( model.getIdWebAccessData(), -5 ) );
       fl.addField( model.getSessionId(), 12 );
       fl.addField( model.getRequestUri(), 12 );
       fl.addField( model.getRequestUrl(), 12 );
       fl.addField( model.getRequestMethod(), 12 );
       fl.addField( model.getRequestQueryString(), 12 );
       fl.addField( model.getRequestContextPath(), 12 );
       fl.addField( model.getRequestRemoteAddr(), 12 );
       fl.addField( model.getRequestRemoteHost(), 12 );
       fl.addField( model.getRequestRemoteUser(), 12 );
       fl.addField( model.getRequestRemotePort(), 4 );
       fl.addField( model.getRequestServerName(), 12 );
       fl.addField( model.getRequestServerPort(), 4 );
       fl.addField( model.getRequestScheme(), 12 );
       fl.addField( model.getResponseStatusCode(), 4 );
       fl.addField( model.getApplicationHost(), 12 );
       fl.addField( model.getRequestStart(), 93 );
       fl.addField( model.getRequestEnd(), 93 );
       return OpDAO.newUpdateOp( query, fl );
    }
    public int insert( WebAccessDataModel model ) throws DAOException { 
       return this.update( this.newInsertOpDAO( model ) );
    }

    public WebAccessDataModel loadOne( String sql, FieldList fl ) throws DAOException {
        return (WebAccessDataModel)loadOne( sql, fl, RSE_MAIN );
    }
    public WebAccessDataModel loadOne( String sql, Field f ) throws DAOException {
        return (WebAccessDataModel)loadOne( sql, f, RSE_MAIN );
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

    public org.morozko.java.mod.web.dg.WebDAOFactory getMainDAOFactory() {
        return (org.morozko.java.mod.web.dg.WebDAOFactory)this.getDaoFactory();
    }
    public WebAccessDataDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory, String queryView, String sqlUpdate ) {
        super(daoFactory);
        this.init(daoFactory);
        this.queryView = queryView;
        this.sqlUpdate = sqlUpdate;
    }
    public WebAccessDataDAOHelper(org.morozko.java.mod.web.dg.WebDAOFactory daoFactory ) {
        this( daoFactory, QUERY_VIEW, SQL_UPDATE );
    }
    protected void loadAllRelations( List list ) throws DAOException { 
        for ( int k=0; k<list.size(); k++) { 
        	this.loadAllRelations( (WebAccessDataModel)list.get(k) ); 
        } 
    }

	public void loadAllRelations(WebAccessDataModel model) throws DAOException {

    }

	public WebAccessDataModel loadOneWebAccessDataPk( org.morozko.java.mod.db.dao.DAOID id_web_access_data ) throws DAOException { 
		WebAccessDataModel result = null;
		String sql = "SELECT v.* FROM ("+this.queryView+") v WHERE  v.id_web_access_data=? ";
		FieldList fl = this.newFieldList();
		fl.addField(id_web_access_data);
		result = this.loadOne( sql, fl );
		this.loadAllRelations( result );
		return result;
	}
}
