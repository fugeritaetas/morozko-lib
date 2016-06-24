/*
 * @(#)UserGroupuserModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti UserGroupuserModel da un ResultSet.</p>
 *
 * @author Morozko
 */
import org.morozko.java.mod.web.dg.user.model.UserGroupuserModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserGroupuserModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125112004105137L;

    public Object extractNext(ResultSet rs) throws SQLException {
        UserGroupuserModel model = new UserGroupuserModel();
        model.setIdUserGroupuser( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user_groupuser") ) );
        model.setIdUserAccount( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user_account") ) );
        model.setIdUserGroup( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user_group") ) );
        model.setGroupName( rs.getString("group_name") );
        return model;
    }

}
