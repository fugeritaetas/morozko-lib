package org.morozko.java.mod.parser.ds;

import org.w3c.dom.Element;

/**
 * <p>Sorgente dati per la gestione di flussi di record.</p>
 * 
 * @author fugerit (m@fugerit.org)
 *
 */
public interface DataSource {

	/**
	 * Restituisce l'identificativo della Data Source
	 * 
	 * @return	l'identificativo della Data Source
	 */
	public abstract String getId();
	
	/**
	 * Imposta l'identificativo della Data Source
	 * 
	 * @param id	l'identificativo da impostare
	 */
	public abstract void setId( String id );
	
	/**
	 * Metodo che configura la data source grazie ad un Tag di configurazione
	 * 
	 * @param config	il tag di configurazione
	 * @throws ParserFatalException	se si verifica qualche errore non recuperabile
	 */
	public abstract void configure( Element config ) throws ParserFatalException;
	
	/**
	 * Analizza l'input tramite le regole della Data Source
	 * 
	 * @param input		l'input da analizzare
	 * @return			l'output dell'analisi
	 * @throws ParserFatalException	se si verifica qualche errore non recuperabile
	 */
	public abstract ParserOutput parse( ParserInput input ) throws ParserFatalException;
	
	/**
	 * Esegue un processo
	 * 
	 * @param input		l'input del processo
	 * @return			l'output del processo
	 * @throws ParserFatalException se si verifica qualche errore non recuperabile
	 */
	public abstract ProcessOutput process( ProcessInput input ) throws ParserFatalException;
	
	/**
	 * Fa il rendering 
	 * 
	 * @param input		l'input		l'input da renderizzare 
	 * @return			l'output	l'output del rendering
	 * @throws ParserFatalException se si verifica qualche errore non recuperabile
	 */
	public abstract RenderOutput render( RenderInput input ) throws ParserFatalException;
	
}
