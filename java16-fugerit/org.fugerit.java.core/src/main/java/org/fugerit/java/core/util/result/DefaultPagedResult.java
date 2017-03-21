package org.fugerit.java.core.util.result;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

public class DefaultPagedResult<T> extends AbstractPagedResult<T>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4104228790597768353L;

	private int realPerPage;
	
	private int realCurrentPage;
	
	private String virtualKey;
	
	/**
	 * <p>Creates a new paged result</p>
	 * 
	 * @param perPage			maximum number of elements in a page
	 * @param elementCount		total number of elements in all pages
	 * @param currentPage		current page ( range 1 - n )
	 * @param pageElements		elements in the current page
	 * @return					a new paged result
	 */
	public static <T> PagedResult<T>  newPagedResult( int perPage, long elementCount, int currentPage, List<T> pageElements ) {
		int pageCount = calcPageCount( elementCount, perPage );
		AbstractPagedResult<T> result = new DefaultPagedResult<T>( perPage, elementCount, currentPage, pageCount, pageElements, perPage, currentPage, null );
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
	public static <T> PagedResult<T>  newPagedResult( int perPage, long elementCount, int currentPage, List<T> pageElements, int realPerPage, int realCurrentPage, String virtualKey ) {
		int pageCount = calcPageCount( elementCount, perPage );
		AbstractPagedResult<T> result = new DefaultPagedResult<T>( perPage, elementCount, currentPage, pageCount, pageElements, realPerPage, realCurrentPage, virtualKey );
		return result;
	}
	
	/**
	 * PagedResult per esisti negativi.
	 * 
	 * @param <T>
	 * @param resultCode
	 * @return
	 */
	protected static <T>  PagedResult<T>  newPagedResult( int resultCode ) {
		DefaultPagedResult<T> result = new DefaultPagedResult<T>( -1, -1, -1, -1, null, -1, -1, null );
		result.setResultCode( resultCode );
		return result;
	}
	
	protected DefaultPagedResult(int perPage, long elementCount, int currentPage, int pageCount, List<T> pageElements, int realPerPage, int realCurrentPage, String virtualKey) {
		super(perPage, elementCount, currentPage, pageCount, pageElements);
		this.realCurrentPage = realCurrentPage;
		this.realPerPage = realPerPage;
		this.virtualKey = virtualKey;
	}

	private static int calcPageCount( long elementCount, int perPage ) {
		int pageCount = 0;
		if(perPage>0)
		{
			pageCount = (int) ( ( elementCount )/( perPage ) );
			if ( ( elementCount )%( perPage ) != 0 ) {
				pageCount++;
			}	
		}
		return pageCount;
	}


	@Override
	public String getVirtualSearchKey() {
		return this.virtualKey;
	}

	@Override
	public Integer getRealPerPage() {
		return this.realPerPage;
	}
	
	@Override
	public Integer getRealCurrentPage() {
		return this.realCurrentPage;
	}

	@Override
	public PagedResult<T> getVirtualPage( int currentPage ) {
		int offset =  ((this.getRealCurrentPage()-1)*this.getRealPerPage());
		int virtualStart = (currentPage-1)*this.getPerPage()-offset;
		int virtualEnd = virtualStart+this.getPerPage();
		this.getLogger().debug( "current page : "+currentPage+" size : "+this.getCurrentPageSize()+" vs : "+virtualStart+" ve : "+virtualEnd+" rps:"+this.getRealPerPage()+" , rp:"+this.getRealCurrentPage() );
		List<T> elements = this.getPageElementsList().subList( virtualStart , virtualEnd );
		return newPagedResult( this.getPerPage(), this.getElementCount(), currentPage, elements );
	}

	@Override
	public boolean isFullResult() {
		return false;
	}
	
	
}
