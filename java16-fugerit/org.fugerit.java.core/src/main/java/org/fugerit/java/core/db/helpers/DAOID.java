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
 * @(#)DAOID.java
 *
 * @project	   : secinShared
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 10-ott-05 0.16.16
 */
package org.fugerit.java.core.db.helpers;

import java.util.StringTokenizer;

/**
 * <p>.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public class DAOID extends Number {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594596221094010047L;
	
	
	
	public DAOID( long value ) {
		this.id = new Long( value );
	}

	public static DAOID valueOf( long value ) {
		return new DAOID( value );
	}
	
	public static DAOID valueOfNullZero( long value ) {
		DAOID id = null;
		if ( value != 0 ) {
			id = new DAOID( value );
		}
		return id;
	}	
	
	public static DAOID valueOf( int value ) {
		return new DAOID( value );
	}
	
	public static DAOID valueOf( short value ) {
		return new DAOID( value );
	}
	
	public static DAOID valueOf( byte value ) {
		return new DAOID( value );
	}	
	
	public static DAOID valueOf( String s ) {
		StringTokenizer st = new StringTokenizer( s, "." );
		String result = "";
		while (st.hasMoreTokens()) {
			result+=st.nextToken();
		}
		return new DAOID( Long.parseLong( result ) );
	}

	private Long id;

	/* (non-Javadoc)
	 * @see java.lang.Number#intValue()
	 */
	public int intValue() {
		return this.id.intValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Number#longValue()
	 */
	public long longValue() {
		return this.id.longValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Number#floatValue()
	 */
	public float floatValue() {
		return this.id.floatValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Number#doubleValue()
	 */
	public double doubleValue() {
		return this.id.doubleValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.id.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean eq = false;
		if ( obj instanceof DAOID ) {
			DAOID daoid = (DAOID)obj;
			eq = this.id.equals( daoid.id );
		}
		return eq;
	}

}
