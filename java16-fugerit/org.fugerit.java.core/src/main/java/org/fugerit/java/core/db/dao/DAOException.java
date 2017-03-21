package org.fugerit.java.core.db.dao;
/*
 * @(#)DAOException.java
 *
 * @project	   : simoss
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 27-mag-2005 7.54.10
 */

/**
 * <p>.</p>
 *
 * @author tux2
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459978395011496700L;

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param message
	 * @param t
	 */
	public DAOException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param t
	 */
	public DAOException(Throwable t) {
		super(t);
	}

}
