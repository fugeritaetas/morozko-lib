/*
 * @(#)PollStatDayModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollStatDayModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollStatDayModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollStatDayModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584580399L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollStatDayModel model = new PollStatDayModel();
        model.setDataTime( rs.getString("data_time") );
        model.setIdPollIndex( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_index") ) );
        model.setIdPollOption( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_option") ) );
        model.setAnswerNumber( new Long( rs.getLong("answer_number" ) ) );
        return model;
    }

}
