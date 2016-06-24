/*
 * @(#)PollStatModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollStatModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollStatModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollStatModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584575350L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollStatModel model = new PollStatModel();
        model.setIdPollIndex( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_index") ) );
        model.setIdPollOption( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_option") ) );
        model.setAnswerNumber( new Long( rs.getLong("answer_number" ) ) );
        return model;
    }

}
