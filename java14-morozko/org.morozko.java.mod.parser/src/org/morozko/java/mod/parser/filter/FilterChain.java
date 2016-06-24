package org.morozko.java.mod.parser.filter;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.parser.model.RecordModel;

public class FilterChain {

	
	public FilterChain(String chainId) {
		super();
		this.chainId = chainId;
		this.listFilter = new ArrayList<RecordFilter>();
	}

	public String getChainId() {
		return chainId;
	}

	public List<RecordFilter> getListFilter() {
		return listFilter;
	}

	private String chainId;
	
	private List<RecordFilter> listFilter;

	public boolean accept( RecordModel record ) {
		boolean ok = true;
		for ( int k=0; k<listFilter.size() && ok; k++ ) {
			ok = listFilter.get( k ).accept( record );
		}
		return ok;
	}
	
}
