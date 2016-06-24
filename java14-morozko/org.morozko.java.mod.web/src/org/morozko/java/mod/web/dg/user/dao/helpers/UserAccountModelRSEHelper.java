/*
 * @(#)UserAccountModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.dao.helpers
 * @creation   : 24/08/2009 15/20/40
 */
package org.morozko.java.mod.web.dg.user.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti UserAccountModel da un ResultSet.</p>
 *
 * @author Morozko
 */
import org.morozko.java.mod.web.dg.user.model.UserAccountModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserAccountModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125112004098054L;

    public Object extractNext(ResultSet rs) throws SQLException {
        UserAccountModel model = new UserAccountModel();
        model.setIdUserAccount( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_user_account") ) );
        model.setUserName( rs.getString("user_name") );
        model.setUserPass( rs.getString("user_pass") );
        model.setUserEnabled( new Integer( rs.getInt("user_enabled") ) );
        return model;
    }

}
