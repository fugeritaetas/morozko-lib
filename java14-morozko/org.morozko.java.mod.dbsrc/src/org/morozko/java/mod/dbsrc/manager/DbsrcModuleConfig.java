package org.morozko.java.mod.dbsrc.manager;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigurationData;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.dbsrc.config.op.DbsrcOperation;
import org.morozko.java.mod.ws.manager.config.ModuleConfig;
import org.w3c.dom.Element;

public class DbsrcModuleConfig extends ModuleConfig {

	public Dbsrc getDbsrc() {
		return dbsrc;
	}

	public void setDbsrc(Dbsrc dbsrc) {
		this.dbsrc = dbsrc;
	}

	private Dbsrc dbsrc;
	
	@Override
	public void configure(Element tag) throws ConfigException {
		super.configure(tag);
		File baseDir = this.getContext().getManager().getBaseDir();
		File configFile = new File( baseDir, tag.getAttribute( "dbsrc-config" ) );
		try {
			this.getLog().info( "dbsrc-config >>>>>>>>>>> "+configFile.getCanonicalPath() );
			FileReader reader = new FileReader( configFile );
			this.dbsrc = ConfigurationData.parseConfiguration( reader );
			Iterator<DbsrcOperation> itOp = this.dbsrc.getOperationList().iterator();
			while ( itOp.hasNext() ) {
				DbsrcOperation op = itOp.next();
				DbsrcOperationConfig operation = new DbsrcOperationConfig();
				operation.setModule( this );
				operation.setName( op.getName() );
				operation.setDescription( op.getDescription() );
				this.getOperationMap().put( op.getName() , operation );
			}
			reader.close();	
		} catch (Exception e ){
			e.printStackTrace();
			throw new ConfigException( e );
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -288642906636782887L;

}
