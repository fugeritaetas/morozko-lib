package org.fugerit.java.core.util.result;

import java.util.List;

public class DefaultPagedResult<T> extends AbstractPagedResult<T> {

	/**
	 * <p>Creates a new paged result</p>
	 * 
	 * @param perPage			maximum number of elements in a page
	 * @param elementCount		total number of elements in all pages
	 * @param currentPage		current page ( range 1 - n )
	 * @param pageElements		elements in the current page
	 * @return					a new paged result
	 */
	public static <T> PagedResult<T>  newPagedResult( int perPage, int elementCount, int currentPage, List<T> pageElements ) {
		int pageCount = calcPageCount( elementCount, perPage );
		AbstractPagedResult<T> result = new DefaultPagedResult<T>( perPage, elementCount, currentPage, pageCount, pageElements );
		return result;
	}
	
	/**
	 * PagedResult per esisti negativi.
	 * 
	 * @param <T>
	 * @param resultCode
	 * @return
	 */
	public static <T>  PagedResult<T>  newPagedResult( int resultCode ) {
		DefaultPagedResult<T> result = new DefaultPagedResult<T>( -1, -1, -1, -1, null );
		result.setResultCode( resultCode );
		return result;
	}
	
	public DefaultPagedResult(int perPage, int elementCount, int currentPage, int pageCount, List<T> pageElements) {
		super(perPage, elementCount, currentPage, pageCount, pageElements);
	}

	private static int calcPageCount( int elementCount, int perPage ) {
		
		int pageCount = 0;
		if(perPage>0)
		{
			pageCount = ( elementCount )/( perPage );
			if ( ( elementCount )%( perPage ) != 0 ) {
				pageCount++;
			}	
		}
		return pageCount;
	}


	@Override
	public Integer getCurrentePage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVirtualSearchKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBufferPageSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagedResult<T> getVirtualPage(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFullResult() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getCurrentePageSize() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
