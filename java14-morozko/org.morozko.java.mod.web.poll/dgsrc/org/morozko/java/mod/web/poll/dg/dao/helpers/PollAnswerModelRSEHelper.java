/*
 * @(#)PollAnswerModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollAnswerModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollAnswerModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollAnswerModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584594371L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollAnswerModel model = new PollAnswerModel();
        model.setIdPollAnswer( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_answer") ) );
        model.setIdPollOption( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_option") ) );
        model.setIdPollData( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_data") ) );
        model.setOptionValue( rs.getString("option_value") );
        return model;
    }

}
