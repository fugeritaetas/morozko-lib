/*
 * @(#)UploadModelRSEHelper.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.dao.helpers
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti UploadModel da un ResultSet.</p>
 *
 * @author Morozko
 */
import org.morozko.java.mod.web.upload.dg.model.UploadModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UploadModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 131637056787980L;

    public Object extractNext(ResultSet rs) throws SQLException {
        UploadModel model = new UploadModel();
        model.setIdUpload( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_upload") ) );
        model.setIdUser( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user") ) );
        model.setIdEntity( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_entity") ) );
        model.setFileSection( rs.getString("file_section") );
        model.setTimeUpload( rs.getTimestamp("time_upload") );
        model.setState( new Integer( rs.getInt("state") ) );
        model.setSize( new Integer( rs.getInt("size") ) );
        model.setFileName( rs.getString("file_name") );
        model.setFilePath( rs.getString("file_path") );
        model.setFileMode( new Integer( rs.getInt("file_mode") ) );
        model.setFileType( new Integer( rs.getInt("file_type") ) );
        return model;
    }

}
