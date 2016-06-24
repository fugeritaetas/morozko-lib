/*
 * @(#)OptionItemModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti OptionItemModel da un ResultSet.</p>
 *
 * @author Matteo Franci aka Morozko
 */
import org.morozko.java.mod.option.dg.model.OptionItemModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OptionItemModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 12064624557709L;

    public Object extractNext(ResultSet rs) throws SQLException {
        OptionItemModel model = new OptionItemModel();
        model.setIdOptionItem( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_option_item") ) );
        model.setItemValue( rs.getString("item_value") );
        model.setEnabled( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("enabled") ) );
        model.setOrdering( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("ordering") ) );
        model.setIdOptionCatalog( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_option_catalog") ) );
        model.setCatalogKey( rs.getString("catalog_key") );
        return model;
    }

}
