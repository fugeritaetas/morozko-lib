/*
 * @(#)UserGroupModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti UserGroupModel da un ResultSet.</p>
 *
 * @author Morozko
 */
import org.morozko.java.mod.web.dg.user.model.UserGroupModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserGroupModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125112004103148L;

    public Object extractNext(ResultSet rs) throws SQLException {
        UserGroupModel model = new UserGroupModel();
        model.setIdUserGroup( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user_group") ) );
        model.setGroupName( rs.getString("group_name") );
        return model;
    }

}
