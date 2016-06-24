package org.morozko.java.mod.web.servlet.sql.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.morozko.java.mod.web.servlet.sql.SqlQuery;


public class Row {

	private SqlQuery query;
	
	public Row( SqlQuery query ) {
		this.query = query;
		this.mapCell = new HashMap();
		this.listCell = new ArrayList();
	}
	
	private Map mapCell;
	
	private List listCell;

	public void add( Cell cell ) {
		this.listCell.add( cell );
		this.mapCell.put( cell.getName() , cell );
	}

	public Map getMapCell() {
		return mapCell;
	}

	public List getListCell() {
		return listCell;
	}
	
	public String getKeyValue() {
		return (String)((Cell)this.mapCell.get( this.query.getKey() )).getValue();
	}
	
}
