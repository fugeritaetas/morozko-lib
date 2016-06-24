package org.morozko.java.core.util.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PagedResult extends ResultInfo implements Serializable {

	/**
	 * <p>Page the elements in a List</p>
	 * 
	 * @param allElements	the list of elements to be paged
	 * @param perPage		number of elements per page
	 * @return				array of paged result
	 */
	public static PagedResult[] newPagedResults( List allElements, int perPage ) {
		int elementCount = allElements.size();
		int pageCount = calcPageCount(elementCount, perPage);
		PagedResult[] result = new PagedResult[ pageCount ];
		for ( int k=0; k<pageCount; k++ ) {
			int currentPage = k+1;
			// start index is the current page * elements in a page
			int fromIndex = k*perPage;
			// end index is start index + per page or total elements size ( whichever is lower )
			int toIndex = Math.min( fromIndex + perPage, allElements.size() );
			List pageElements = allElements.subList(fromIndex, toIndex);
			result[k] = new PagedResult( perPage, elementCount, currentPage, pageCount, pageElements );
		}
		return result;
	}
	
	/**
	 * <p>Creates a new paged result</p>
	 * 
	 * @param perPage			maximum number of elements in a page
	 * @param elementCount		total number of elements in all pages
	 * @param currentPage		current page ( range 1 - n )
	 * @param pageElements		elements in the current page
	 * @return					a new paged result
	 */
	public static PagedResult newPagedResult( int perPage, int elementCount, int currentPage, List pageElements ) {
		int pageCount = calcPageCount( elementCount, perPage );
		PagedResult result = new PagedResult( perPage, elementCount, currentPage, pageCount, pageElements );
		return result;
	}
	
	private PagedResult(int perPage, int elementCount, int currentPage, int pageCount, List pageElements) {
		super();
		this.offset = perPage*(currentPage-1);
		this.perPage = perPage;
		this.elementCount = elementCount;
		this.currentPage = currentPage;
		this.pageElements = pageElements;
		this.pageCount = pageCount;
		this.infoMap = new HashMap();
	}

	private static int calcPageCount( int elementCount, int perPage ) {
		int pageCount = ( elementCount )/( perPage );
		if ( ( elementCount )%( perPage ) != 0 ) {
			pageCount++;
		}	
		return pageCount;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2478516776242070877L;
	
	private Map infoMap;
	
	public Map getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map info) {
		this.infoMap = info;
	}

	private int pageCount;
	
	private int offset;
	
	private int perPage;
	
	private int elementCount;
	
	private int currentPage;
	
	private List pageElements;

	/**
	 * <p>The position of the first element of the current pages ( (currentPage-1) * perPage )</p> 
	 * 
	 * @return	offset of the first element in this page
	 */
	public Integer getOffset() {
		return new Integer( this.offset );
	}

	/**
	 * <p>Maximum number of elements in a page</p>
	 * 
	 * @return	maximum number of elements in a page
	 */
	public Integer getPerPage() {
		return new Integer( this.perPage );
	}

	/**
	 * <p>Total number of elements in all pages</p>
	 * 
	 * @return	total number of elements in all pages
	 */
	public Integer getElementCount() {
		return new Integer( this.elementCount );
	}

	/**
	 * <p>Position of current page ( in the range 1 - n )</p>
	 * 
	 * @return	position of current page
	 */
	public Integer getCurrentPage() {
		return new Integer( this.currentPage );
	}
	
	/**
	 * <p>Total number of pages</p>
	 * 
	 * @return	total number of pages
	 */
	public Integer getPageCount() {
		return new Integer( pageCount );
	}		

	/**
	 * <p>Number of elements in current page</p>
	 * 
	 * @return	the size of the current page
	 */
	public Integer getCurrentPageSize() {
		return new Integer( this.pageElements.size() );
	}
	
	/**
	 * <p>Elements in the current page</p>
	 * 
	 * @return	elements in the current page
	 */
	public Iterator getPageElements() {
		return this.pageElements.iterator();
	}

	/**
	 * <p>Elements in the current page</p>
	 * 
	 * @return	elements in the current page
	 */
	public List getPageElementsList() {
		return this.pageElements;
	}
	
	/**
	 * <p>Iterator over page numbers ( 1 - n )</p>
	 * 
	 * @return	iterator over page numbers ( 1 - n )
	 */
	public Iterator getPageCountIterator() {
		List list = new ArrayList();
		for ( int k=1; k<=this.pageCount; k++ ) {
			list.add( new Integer( k ) );
		}
		return list.iterator();
	}
	
}
