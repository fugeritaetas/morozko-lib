/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)OpDAO.java
 *
 * @project	   : secinShared
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 5-ott-05 9.00.26
 */
package org.fugerit.java.core.db.dao;
/**
 * <p>.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public class OpDAO {


	public static OpDAO newQueryOp( String sql, RSExtractor rse ) {
		return newQueryOp(sql, new FieldList( new FieldFactory() ), rse );
	}

	public static OpDAO newUpdateOp( String sql ) {
		return newUpdateOp(sql, new FieldList( new FieldFactory() ) );
	}
	
	public static OpDAO newExecuteOp( String sql ) {
		return newExecuteOp(sql, new FieldList( new FieldFactory() ) );
	}
	
	public static OpDAO newExecuteOp( String sql, FieldList fl ) {
		OpDAO op = new OpDAO();
		op.setType( TYPE_EXECUTE );
		op.setFieldList( fl );
		op.setSql( sql );
		return op;
	}
	
	public static OpDAO newQueryOp( String sql, FieldList fl, RSExtractor rse ) {
		OpDAO op = new OpDAO();
		op.setType( TYPE_QUERY );
		op.setFieldList( fl );
		op.setSql( sql );
		op.setRsExtractor( rse );
		return op;
	}
	
	public static OpDAO newUpdateOp( String sql, FieldList fl ) {
		OpDAO op = new OpDAO();
		op.setType( TYPE_UPDATE );
		op.setFieldList( fl );
		op.setSql( sql );
		return op;
	}

	public final static int TYPE_UPDATE = 0;
	public final static int TYPE_QUERY = 1;
	public final static int TYPE_EXECUTE = 2;

	private String sql;
	
	private FieldList fieldList;
	
	private RSExtractor rsExtractor;
	
	private int type;

	/**
	 * @return
	 */
	public FieldList getFieldList() {
		return fieldList;
	}

	/**
	 * @return
	 */
	public RSExtractor getRsExtractor() {
		return rsExtractor;
	}

	/**
	 * @return
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param list
	 */
	public void setFieldList(FieldList list) {
		fieldList = list;
	}

	/**
	 * @param extractor
	 */
	public void setRsExtractor(RSExtractor extractor) {
		rsExtractor = extractor;
	}

	/**
	 * @param string
	 */
	public void setSql(String string) {
		sql = string;
	}

	/**
	 * @param i
	 */
	public void setType(int i) {
		type = i;
	}

	protected void finalize() throws Throwable {
		super.finalize();
		this.fieldList = null;
		this.sql = null;
	}

}
