package org.morozko.java.mod.db.backup.adaptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.mod.db.backup.BackupAdaptor;
import org.morozko.java.mod.db.backup.DefaultBackupAdaptor;
import org.w3c.dom.Element;

public class BackupAdaptorWrapper implements BackupAdaptor {

	private BackupAdaptor wrapped;
	
	public BackupAdaptorWrapper() {
		this.wrapped = null;
	}
	
	public void set(PreparedStatement ps, ResultSetMetaData rsmd, Object obj, int index) throws SQLException {
		this.wrapped.set(ps, rsmd, obj, index);
	}

	public Object get(ResultSet rs, ResultSetMetaData rsmd, int index) throws SQLException {
		return this.wrapped.get(rs, rsmd, index);
	}

	
	
	public void setWrapped(BackupAdaptor wrapped) {
		this.wrapped = wrapped;
	}

	public BackupAdaptor getWrapped() {
		return wrapped;
	}

	public void configure(Element config) throws Exception {
		Properties atts = DOMUtils.attributesToProperties( config );
		String wrappedAdaptor = atts.getProperty( "wrapped" ); 
		if ( wrappedAdaptor != null ) {
			this.setWrapped( (BackupAdaptor)ClassHelper.newInstance( wrappedAdaptor ) );
			this.getWrapped().configure( config );
		} else {
			this.setWrapped( DefaultBackupAdaptor.DEFAULT );
		}
	}

}
