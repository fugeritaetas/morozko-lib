package org.fugerit.java.core.util.result;

import java.util.Collection;

/**
 * Inteface for handling paged result 
 * 
 * @author fugerit
 *
 * @param <E>
 */
public interface PagedResult<E> {

	/**
	 * The method getElementCount() returns this value if the element count is unavalable
	 */
	public final static Integer ELEMENT_COUNT_UNAVAILABLE = -1;
	
	/**
	 * Return the page size
	 * 
	 * @return
	 */
	public Integer getPerPage();

	/**
	 * Current page size
	 * 
	 * @return
	 */
	public Integer getCurrentPageSize();
	
	/**
	 * Total element count
	 * 
	 * @return
	 */
	public Integer getElementCount();
	
	/**
	 * The current page index
	 * 
	 * @return
	 */
	public Integer getCurrentePage();
	
	/**
	 * <code>true</code> if the the page contains the full result
	 * 
	 * @return
	 */
	public boolean isFullResult();
	
	/**
	 * Return the page elements
	 * 
	 * @return
	 */
	public Collection<E> getPageElements();
	
	
}
