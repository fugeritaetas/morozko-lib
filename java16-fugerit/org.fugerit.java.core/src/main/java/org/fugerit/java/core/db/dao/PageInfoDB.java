package org.fugerit.java.core.db.dao;

import org.fugerit.java.core.util.result.PageInfo;

public class PageInfoDB extends PageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9056191900813843138L;

	public PageInfoDB(int number, int size) {
		super(number, size);
	}
	
	public PageInfoDB(int number, int size, String order) {
		this(number, size);
		this.setOrder( order );
	}
	
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
