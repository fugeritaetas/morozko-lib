package org.morozko.java.mod.ws.manager.config;

import java.io.Serializable;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.XMLConfigurableObject;
import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.w3c.dom.Element;

public class OperationConfig extends XMLConfigurableObject implements Serializable {

	public OperationConfig() {
	}
	
	public OperationConfig(String name) {
		this();
		this.setName( name );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5495293121990397093L;


	public OperationResult apply( ParamMap paramMap ) {
		return OperationResult.RESULT_NOT_IMPLEMENTED;
	}
	
	private String name;
	
	private ModuleConfig module;

	public ModuleConfig getModule() {
		return module;
	}

	public void setModule(ModuleConfig module) {
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void configure(Element tag) throws ConfigException {	
	}
	
}
