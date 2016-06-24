package org.morozko.java.mod.parser.model;

import java.util.Iterator;
import java.util.List;


public interface RecordModel {

	/**
	 * Restituisce il contenuto "grezzo" di questo record
	 * 
	 * @return
	 */
	public abstract String getContent();

	/**
	 * 
	 * Restituisce un Iterator sui campi del record.
	 * 
	 * @return
	 */
	public abstract Iterator<FieldModel> getFields();

	
	/**
	 * Restituisce il campo con nome dato, o <code>null</code> se non presente.
	 * 
	 * @param name
	 * @return
	 */
	public abstract FieldModel getField(String name);

	/**
	 * Verifica se il record contiene un campo con nome dato.
	 * 
	 * @param name
	 * @return
	 */
	public abstract boolean containsField(String name);

	/**
	 * Rimuove un campo con nome dato.
	 * 
	 * @param field
	 * @return
	 */
	public abstract FieldModel remove(FieldModel field);

	/**
	 * Aggiunge un campo al record, il campo viene aggiunto posizionalmente in coda.
	 * 
	 * @param field
	 * @throws ParserException
	 */
	public abstract void addField(FieldModel field) throws ParserException;
	
	/**
	 * Restituisce eventualmente l'elenco degli errori del record
	 * 
	 * @return
	 */
	public abstract List<ErrorDescriptor> getRecordErrors();
	
	/**
	 * Restituisce eventualmente l'elenco degli errori del campo
	 * 
	 * @return
	 */
	public abstract List<ErrorDescriptor> getFieldErrors();
	
	/**
	 * Restituisce <code>true</code> se non è presente nessun errore (nè di record nè di campo)
	 * 
	 * @return
	 */
	public abstract boolean isValid();
	
	public RecordDescription getRecordDescription();

}