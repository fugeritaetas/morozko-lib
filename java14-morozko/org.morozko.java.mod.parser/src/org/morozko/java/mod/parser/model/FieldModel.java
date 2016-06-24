package org.morozko.java.mod.parser.model;

import java.util.List;

public interface FieldModel {

	/**
	 * Restituisce <code>true</code> se il campo non contiene errori.
	 * 
	 * @return
	 */
	public abstract boolean isValid();
	
	/**
	 * Restituisce l'elenco degli errori eventualimente associati al campo.
	 * 
	 * @return
	 */
	public abstract List<ErrorDescriptor> getErrors();
	
	/**
	 * Restituisce il nome del campo
	 * 
	 * @return
	 */
	public abstract String getName();

	/** 
	 * Restituisce il valore "grezzo" del campo
	 * 
	 * @return
	 */
	public abstract String getValue();
	
	public FieldDescription getFieldDescription();

}