package org.morozko.java.mod.ws.manager.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.XMLConfigurableObject;
import org.w3c.dom.Element;

public class ContextConfig extends XMLConfigurableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958733184170207447L;
	
	private String name;
	
	private ManagerConfig manager;
	
	public ManagerConfig getManager() {
		return manager;
	}

	public void setManager(ManagerConfig manager) {
		this.manager = manager;
	}

	public ContextConfig() {
		this.moduleMap = new HashMap<String, ModuleConfig>();
	}
	
	public ContextConfig(String name) {
		this();
		this.setName( name );
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ModuleConfig> getModuleMap() {
		return moduleMap;
	}

	public void setModuleMap(Map<String, ModuleConfig> moduleMap) {
		this.moduleMap = moduleMap;
	}

	private Map<String, ModuleConfig> moduleMap;

	@Override
	public void configure(Element tag) throws ConfigException {
	}
	
}
