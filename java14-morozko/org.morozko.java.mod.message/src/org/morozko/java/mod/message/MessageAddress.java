/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.message 

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
 * @(#)MessageAddress.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class MessageAddress {

	public static String[] reverse( MessageAddress[] addressList ) {
		String[] list = new String[ addressList.length ];
		for ( int k=0; k<addressList.length; k++ ) {
			list[k] = addressList[k].getAddress();
		}
		return list;
	}
	
	public static MessageAddress[] parse( String addressList ) {
		MessageAddress[] result = new MessageAddress[0];
		if ( addressList != null ) {
			String[] list = addressList.split( ";" );
			result = new MessageAddress[ list.length ];
			for ( int k=0; k<result.length; k++ ) {
				result[k] = new MessageAddress( list[k] );
			}			
		}
		return result;
	}
	
	public MessageAddress( String address ) {
		this.address = address;
	}
	
	private String address;

	/**
	 * @return Restituisce il valore di address.
	 */
	public String getAddress() {
		return address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.address;
	}

	
}
