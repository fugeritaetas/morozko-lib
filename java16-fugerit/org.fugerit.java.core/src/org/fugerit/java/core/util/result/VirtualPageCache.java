package org.fugerit.java.core.util.result;

import java.util.HashMap;

public class VirtualPageCache<T> {

	private HashMap<String, CacheWrapper<T>> cache;
	
	// 12 hours
	private final static long DEFAULT_TTL = 12*60*60*1000;
	
	// <code>true</code> it the wrapper is still valid
	private boolean checkTtl( CacheWrapper<T> wrapper ) {
		return wrapper.getCacheTime()>(System.currentTimeMillis()-DEFAULT_TTL);
	}
	
	/**
	 * Returns a virtual page contained in the real page.
	 * 
	 * @param virtualKey
	 * @param currentPage
	 * @return
	 */
	public PagedResult<T> getCachedPage( String virtualKey, Integer currentPage ) {
		CacheWrapper<T> wrapper = this.cache.get( virtualKey );
		PagedResult<T> page = null;
		if ( wrapper != null ) {
			if ( this.checkTtl( wrapper ) ) {
				page = wrapper.getPage().getVirtualPage( currentPage );
			} else {
				this.cache.remove( wrapper );
			}
		}
		return page;
	}
	
	public void addPageToCache( String virtualKey, PagedResult<T> bufferPage ) {
		this.cache.put( virtualKey , new CacheWrapper<T>( bufferPage ) );
	}
	
}

class CacheWrapper<T> {
	
	public CacheWrapper(PagedResult<T> page) {
		super();
		this.page = page;
		this.cacheTime = System.currentTimeMillis();
	}

	private PagedResult<T> page;
	
	private long cacheTime;

	public PagedResult<T> getPage() {
		return page;
	}

	public void setPage(PagedResult<T> page) {
		this.page = page;
	}

	public long getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(long cacheTime) {
		this.cacheTime = cacheTime;
	}
	
	
	
}
