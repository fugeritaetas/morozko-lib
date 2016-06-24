/*
 * @(#)PollQuestionModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollQuestionModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollQuestionModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollQuestionModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584588343L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollQuestionModel model = new PollQuestionModel();
        model.setIdPollIndex( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_index") ) );
        model.setIdPollQuestion( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_question") ) );
        model.setQuestionText( rs.getString("question_text") );
        model.setOrderBy( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("order_by") ) );
        return model;
    }

}
