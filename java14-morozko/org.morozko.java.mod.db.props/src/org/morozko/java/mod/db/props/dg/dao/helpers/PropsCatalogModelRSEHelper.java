/*
 * @(#)PropsCatalogModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PropsCatalogModel da un ResultSet.</p>
 *
 * @author Matteo Franci aka Morozko
 */
import org.morozko.java.mod.db.props.dg.model.PropsCatalogModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PropsCatalogModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 120783766651120L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PropsCatalogModel model = new PropsCatalogModel();
        model.setIdPropsCatalog( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_props_catalog") ) );
        model.setPropCat( rs.getString("prop_cat") );
        model.setPropDescription( rs.getString("prop_description") );
        return model;
    }

}
