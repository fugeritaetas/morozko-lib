package org.morozko.java.mod.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.xml.config.PropertyXML;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

public class NavMap extends NavNode {

	public NavMap() {
		this.formMap = new HashMap<String, NavForm>();
	}
	
	public HashMap<String, NavForm> getFormMap() {
		return formMap;
	}

	private HashMap<String, NavForm> formMap;
	
	private PropertyXML generalProps;

	public PropertyXML getGeneralProps() {
		return generalProps;
	}

	public void setGeneralProps(PropertyXML generalProps) {
		this.generalProps = generalProps;
	}
	
	public TableConfig getDaogenTable( String name ) {
		TableConfig tc = this.getDaogen( name ).getTable( name );
		return tc;
	}
	
	public DGConfig getDaogen( String tableName ) {
		DGConfig dg = null;
		Iterator<DGConfig> it = this.getDaogenList().iterator();
		while ( dg == null && it.hasNext() ) {
			DGConfig current = it.next();
			if ( current.getTable( tableName ) != null ) {
				dg = current;
			}
		}
		return dg;
	}	
	
	private List<DGConfig> daogenList = new ArrayList<DGConfig>();

	public List<DGConfig> getDaogenList() {
		return daogenList;
	}
	
//	private DGConfig daogen;
//
//	public DGConfig getDaogen() {
//		return daogen;
//	}
//
//	public void setDaogen(DGConfig daogen) {
//		this.daogen = daogen;
//	}
	
}
