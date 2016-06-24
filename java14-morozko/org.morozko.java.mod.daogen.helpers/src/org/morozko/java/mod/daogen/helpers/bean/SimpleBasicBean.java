/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.mod.daogen.helpers 

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
 * @(#)BasicBean.java
 *
 * @project	   : simossPost
 * @package	   : org.opinf.jlib.mod.daogen.generator.helpers.bean
 * @creation   : 15-giu-2005 15.21.26
 */
package org.morozko.java.mod.daogen.helpers.bean;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.StringTokenizer;

import org.apache.struts.validator.ValidatorForm;
import org.morozko.java.mod.daogen.helpers.model.BasicModel;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.types.BlobData;


/**
 * <p>.</p>
 *
 * @author tux2
 */
public class SimpleBasicBean {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 145435436543634L;

	public static String convertComma( String s ) {
		int index = s.indexOf( ',' );
		if ( index!=-1 ) {
			s = s.substring( 0, index )+"."+s.substring( index+1 );
		}
		return s; 	
	}	
	
	public static String removeDots( String s ) {
		StringBuffer r = new StringBuffer();
		StringTokenizer st = new StringTokenizer( s, "." );
		while (st.hasMoreTokens()) {
			r.append( st.nextToken() );
		}
		return r.toString(); 	
	}
	
	public static String prepareNumber( String s ) {
		s = removeDots( s );
		s = convertComma( s );
		return s;
	}
	
    public static final DateFormat DATE_FORMAT = BasicModel.DATE_FORMAT;
    
    public static final DateFormat TIME_FORMAT = BasicModel.TIME_FORMAT;
    
    public static final DateFormat TIMESTAMP_FORMAT = BasicModel.TIMESTAMP_FORMAT;    
    
    public String toString( String v ) {
        return String.valueOf( v );
    }      
    

    public BigInteger toBigInteger( String v ) {
    	BigInteger d = null;
		if (v!=null && !v.equals("")) {
			d = BigInteger.valueOf( Long.valueOf( prepareNumber( v ) ).longValue() );
		}    	
        return d;
    } 
    
    public BigDecimal toBigDecimal( String v ) {
    	BigDecimal d = null;
		if (v!=null && !v.equals("")) {
			d = new BigDecimal( Double.valueOf( prepareNumber( v ) ).doubleValue() );
		}    	
        return d;
    } 
    
    public Double toDouble( String v ) {
		Double d = null;
		if (v!=null && !v.equals("")) {
			d = Double.valueOf( prepareNumber( v ) );
		}    	
        return d;
    }        
   
	public Byte toByte( String v ) {
		Byte l = null;
		if (v!=null && !v.equals("")) {
			l = Byte.valueOf( prepareNumber( v ) );
		}
		return l;
	}       
    
	public Short toShort( String v ) {
		Short l = null;
		if (v!=null && !v.equals("")) {
			l = Short.valueOf( prepareNumber( v ) );
		}
		return l;
	}       
    
	public Long toLong( String v ) {
		Long l = null;
		if (v!=null && !v.equals("")) {
			l = Long.valueOf( prepareNumber( v ) );
		}
		return l;
	}      
    
	public DAOID toDAOID( String v ) {
		DAOID i = null;
		if (v!=null && !v.equals("")) {
			i = DAOID.valueOf( prepareNumber( v ) );
		}
		return i;
	}        
    
    public Integer toInteger( String v ) {
    	Integer i = null;
		if (v!=null && !v.equals("")) {
			i = Integer.valueOf( prepareNumber( v ) );
		}
        return i;
    }    
    
    public Timestamp toTimestamp( String v ) {
    	Timestamp d = null;
    	if (v!=null && !v.equals("")) {
			d = BasicModel.toTimestamp( v );
    	}
        return d;
    }        
    
    public Time toTime( String v ) {
    	Time d = null;
    	if (v!=null && !v.equals("")) {
			d = BasicModel.toTime( v );
    	}
        return d;
    }    
    
    public Date toDate( String v ) {
    	Date d = null;
    	if (v!=null && !v.equals("")) {
			d = BasicModel.toDate( v );
    	}
        return d;
    }
    
    public Boolean toBoolean( String v ) {
    	Boolean d = null;
    	if (v!=null && !v.equals("")) {
			d = Boolean.valueOf( v );
    	}
        return d;
    }
    
    public BlobData toBlobData( String v ) {
    	return null;
    }    
    
    public Clob toClobData( String v ) {
    	return null;
    }        
    
    /**
     * <p>Crea una nuova istanza di BasicBean.</p>
     *
     * 
     */
    public SimpleBasicBean() {
        super();
    }

}
