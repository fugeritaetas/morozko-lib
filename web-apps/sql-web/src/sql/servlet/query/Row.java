package sql.servlet.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sql.servlet.SqlQuery;

public class Row {

	private SqlQuery query;
	
	public Row( SqlQuery query ) {
		this.query = query;
		this.mapCell = new HashMap<String, Cell>();
		this.listCell = new ArrayList<Cell>();
	}
	
	private Map<String, Cell> mapCell;
	
	private List<Cell> listCell;

	public void add( Cell cell ) {
		this.listCell.add( cell );
		this.mapCell.put( cell.getName() , cell );
	}

	public Map<String, Cell> getMapCell() {
		return mapCell;
	}

	public List<Cell> getListCell() {
		return listCell;
	}
	
	public String getKeyValue() {
		return this.mapCell.get( this.query.getKey() ).getValue();
	}
	
}
