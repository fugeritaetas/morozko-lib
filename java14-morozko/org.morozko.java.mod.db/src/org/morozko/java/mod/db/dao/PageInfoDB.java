package org.morozko.java.mod.db.dao;

import org.morozko.java.core.util.result.PageInfo;

public class PageInfoDB extends PageInfo {

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
