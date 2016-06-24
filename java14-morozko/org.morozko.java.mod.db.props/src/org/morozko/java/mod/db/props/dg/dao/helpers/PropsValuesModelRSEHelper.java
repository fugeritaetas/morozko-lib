/*
 * @(#)PropsValuesModelRSEHelper.java
 *
 * @project    : org.morozko.java.mod.db.props
 * @package    : org.morozko.java.mod.db.props.dg.dao.helpers
 * @creation   : 10/04/2008 16/27/46
 */
package org.morozko.java.mod.db.props.dg.dao.helpers;

/**
 * <p>Classe per l' estrazione di oggetti PropsValuesModel da un ResultSet.</p>
 *
 * @author Matteo Franci aka Morozko
 */
import org.morozko.java.mod.db.props.dg.model.PropsValuesModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PropsValuesModelRSEHelper implements org.morozko.java.mod.db.dao.RSExtractor {

	private final static long serialVersionUID = 120783766658114L;

    public Object extractNext(ResultSet rs) throws SQLException {
        PropsValuesModel model = new PropsValuesModel();
        model.setIdPropsValue( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_props_value") ) );
        model.setPropKey( rs.getString("prop_key") );
        model.setPropValue( rs.getString("prop_value") );
        model.setIdPropsCatalog( org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong("id_props_catalog") ) );
        model.setPropCat( rs.getString("prop_cat") );
        return model;
    }

}
