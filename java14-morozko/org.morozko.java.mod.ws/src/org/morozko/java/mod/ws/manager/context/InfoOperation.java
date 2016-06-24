package org.morozko.java.mod.ws.manager.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.ws.manager.config.InfoResult;
import org.morozko.java.mod.ws.manager.config.OperationConfig;
import org.morozko.java.mod.ws.manager.config.OperationResult;
import org.w3c.dom.Element;

public class InfoOperation extends OperationConfig {

	private static final SearchDOM SEARCH = SearchDOM.newInstance( true , true );
	
	private List<InfoResult> info;
	
	public InfoOperation() {
		this.info = new ArrayList<InfoResult>();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1880164442144612926L;

	@Override
	public void configure(Element tag) throws ConfigException {
		List<Element> listInfo = SEARCH.findAllTags( tag , "info" );
		Iterator<Element> itInfo = listInfo.iterator();
		while ( itInfo.hasNext() ) {
			Element current = itInfo.next();
			String name = current.getAttribute( "name" );
			String value = SEARCH.findText( current );
			this.info.add( new InfoResult(name, value) );
		}
	}

	@Override
	public OperationResult apply(ParamMap paramMap) {
		OperationResult result = OperationResult.newResultOk();
		result.getInfoList().addAll( this.info );
		return result;
	}

}
