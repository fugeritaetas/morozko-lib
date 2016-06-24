/*
 * @(#)OptionCatalogModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.option
 * @package    : org.morozko.java.mod.option.dg.dao.helpers
 * @creation   : 25/03/2008 17/27/35
 */
package org.morozko.java.mod.option.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti OptionCatalogModel da un ResultSet.</p>
 *
 * @author Matteo Franci aka Morozko
 */
import org.morozko.java.mod.option.dg.model.OptionCatalogModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OptionCatalogModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 120646245573049L;

    public Object extractNext(ResultSet rs) throws SQLException {
        OptionCatalogModel model = new OptionCatalogModel();
        model.setIdOptionCatalog( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_option_catalog") ) );
        model.setCatalogKey( rs.getString("catalog_key") );
        model.setDescription( rs.getString("description") );
        model.setCatalogType( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("catalog_type") ) );
        return model;
    }

}
