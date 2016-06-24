/*
 * @(#)UploadDAOFactory.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.dao
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.dao;

import org.morozko.java.mod.web.upload.dg.dao.helpers.UploadDAOFactoryHelper;

/**
 * <p>Classe UploadDAOFactory.</p>
 *
 * @author Morozko
 */
public class UploadDAOFactory extends UploadDAOFactoryHelper {

	private final static long serialVersionUID = 131637056794132L;

    private static UploadDAOFactory instance;
    public static UploadDAOFactory getInstance() {
        return instance;
    }
    public static void init( org.morozko.java.mod.db.dao.BasicDAOFactory bdf ) {
        instance = new UploadDAOFactory( bdf );
    }
    public static void init( org.morozko.java.mod.db.connect.ConnectionFactory cf ) {
        instance = new UploadDAOFactory( new org.morozko.java.mod.db.dao.BasicDAOFactory( cf ) );
    }
    public UploadDAOFactory(org.morozko.java.mod.db.dao.BasicDAOFactory daoFactory ) {
        super(daoFactory);
    }

}
