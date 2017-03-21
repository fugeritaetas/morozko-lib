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
 * @(#)OptionItemRSE.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.rse
 * @creation   : 12-giu-2006
 */
package org.fugerit.java.core.db.dao.rse;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.fugerit.java.core.db.dao.RSExtractor;
import org.fugerit.java.core.util.collection.OptionItem;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class OptionItemRSE implements RSExtractor {

	public static OptionItemRSE getInstance( String valueField, String labelField ) {
		return new OptionItemRSE( valueField, labelField );
	}
	
	private String valueField;
	
	private String labelField;
	
	private OptionItemRSE( String valueField, String labelField ) {
		this.valueField = valueField;
		this.labelField = labelField;
	}
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.RSExtractor#extractNext(java.sql.ResultSet)
	 */
	public Object extractNext(ResultSet rs) throws SQLException {
		String value = rs.getString( this.valueField );
		String label = rs.getString( this.labelField );
		OptionItem optionItem = new OptionItem( value, label );
		return optionItem;
	}

	/**
	 * @return Restituisce il valore di labelField.
	 */
	public String getLabelField() {
		return labelField;
	}

	/**
	 * @param labelField il valore di labelField da impostare.
	 */
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}

	/**
	 * @return Restituisce il valore di valueField.
	 */
	public String getValueField() {
		return valueField;
	}

	/**
	 * @param valueField il valore di valueField da impostare.
	 */
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

}
