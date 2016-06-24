/*
 * @(#)PollIndexModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollIndexModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollIndexModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollIndexModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584586379L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollIndexModel model = new PollIndexModel();
        model.setIdPollIndex( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_index") ) );
        model.setQuestionaryType( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("questionary_type") ) );
        model.setQuestionaryName( rs.getString("questionary_name") );
        model.setQuestionaryDescription( rs.getString("questionary_description") );
        return model;
    }

}
