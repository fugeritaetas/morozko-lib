package org.morozko.java.mod.parser.filter;

import org.morozko.java.core.cfg.ConfigurableObject;
import org.morozko.java.mod.parser.model.RecordModel;

public interface RecordFilter extends ConfigurableObject {

	public boolean accept( RecordModel record );
	
}
