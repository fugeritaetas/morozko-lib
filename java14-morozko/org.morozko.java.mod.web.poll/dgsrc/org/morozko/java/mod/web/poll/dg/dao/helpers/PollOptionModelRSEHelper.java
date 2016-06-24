/*
 * @(#)PollOptionModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollOptionModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollOptionModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollOptionModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584591329L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollOptionModel model = new PollOptionModel();
        model.setIdPollOption( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_option") ) );
        model.setIdPollQuestion( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_question") ) );
        model.setOptionText( rs.getString("option_text") );
        model.setOptionType( new Double( rs.getDouble("option_type") ) );
        model.setOptionDefault( rs.getString("option_default") );
        model.setIdPollOptionParent( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_option_parent") ) );
        model.setOrderBy( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("order_by") ) );
        return model;
    }

}
