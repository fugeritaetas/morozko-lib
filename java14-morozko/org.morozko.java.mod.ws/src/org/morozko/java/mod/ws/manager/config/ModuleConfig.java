package org.morozko.java.mod.ws.manager.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.XMLConfigurableObject;
import org.w3c.dom.Element;

public class ModuleConfig extends XMLConfigurableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -676547739796531218L;
	
	private String name;
	
	private ContextConfig context;
	
	public ContextConfig getContext() {
		return context;
	}

	public void setContext(ContextConfig context) {
		this.context = context;
	}

	public ModuleConfig() {
		this.operationMap = new HashMap<String, OperationConfig>();
	}
	
	public ModuleConfig(String name) {
		this();
		this.setName( name );
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, OperationConfig> getOperationMap() {
		return operationMap;
	}

	public void setOperationMap(Map<String, OperationConfig> operationMap) {
		this.operationMap = operationMap;
	}

	private Map<String, OperationConfig> operationMap;

	@Override
	public void configure(Element tag) throws ConfigException {
		
	}
	
}
