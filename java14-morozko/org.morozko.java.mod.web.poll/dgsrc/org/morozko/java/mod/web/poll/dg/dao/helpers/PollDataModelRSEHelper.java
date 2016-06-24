/*
 * @(#)PollDataModelRSEHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.dao.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PollDataModel da un ResultSet.</p>
 *
 * @author Matteo Franci
 */
import org.morozko.java.mod.web.poll.dg.model.PollDataModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PollDataModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 125965584593326L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PollDataModel model = new PollDataModel();
        model.setIdPollData( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_data") ) );
        model.setDataTime( rs.getTimestamp("data_time") );
        model.setIdPollIndex( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_poll_index") ) );
        return model;
    }

}
