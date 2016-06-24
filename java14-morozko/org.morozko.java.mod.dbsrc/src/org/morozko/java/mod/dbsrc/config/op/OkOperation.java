package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public class OkOperation extends BasicDbsrcOperation {

	@Override
	public int apply() throws DbsrcException {
		return EXIT_CODE_OK;
	}

}
