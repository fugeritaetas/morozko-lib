package org.morozko.java.mod.dbsrc.data;

public class BasicColumnDescriptor implements ColumnDescriptor {

	private String name;
	
	private int type;

	protected BasicColumnDescriptor(String name, int type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}
	
}
