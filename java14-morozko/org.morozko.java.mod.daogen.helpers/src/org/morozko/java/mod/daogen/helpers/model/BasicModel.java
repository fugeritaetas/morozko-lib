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
 * @(#)BasicModel.java
 *
 * @project	   : simossPost
 * @package	   : org.opinf.jlib.mod.daogen.generator.helpers.model
 * @creation   : 15-giu-2005 15.19.55
 */
package org.morozko.java.mod.daogen.helpers.model;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.morozko.java.mod.db.dao.DAOID;

/**
 * <p>.</p>
 *
 * @author tux2
 */
public class BasicModel implements Serializable {

	private Map infoMap;
	
	public Map getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map infoMap) {
		this.infoMap = infoMap;
	}

	private static final ResourceBundle CONVERT = ResourceBundle.getBundle( "org.morozko.java.mod.daogen.helpers.model.convert" );
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4357057459045551829L;

	public static Timestamp toTimestamp( String v ) {
        return new java.sql.Timestamp( TIMESTAMP_FORMAT.parse( v , new ParsePosition( 0 ) ).getTime() );
    }        
    
    public static Time toTime( String v ) {
        return new java.sql.Time( TIME_FORMAT.parse( v , new ParsePosition( 0 ) ).getTime() );
    }    
    
    public static Date toDate( String v ) {
        return new java.sql.Date( DATE_FORMAT.parse( v , new ParsePosition( 0 ) ).getTime() );
    }    
    
    public static final String DEF_DATE_FORMAT = CONVERT.getString( "date.format" );
    public static final String DEF_TIME_FORMAT = CONVERT.getString( "time.format" );
    public static final String DEF_TIMESTAMP_FORMAT = CONVERT.getString( "timestamp.format" );
    
    /**
     * @deprecated Deprecato da sostituire con newDateFormat()
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat( DEF_DATE_FORMAT );
    
    /**
     * @deprecated Deprecato da sostituire con newTimeFormat()
     */    
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat( DEF_TIME_FORMAT );
    
    /**
     * @deprecated Deprecato da sostituire con newTimestampFormat()
     */    
    public static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat( DEF_TIMESTAMP_FORMAT );
    
    private static DateFormat newFormat( String pattern ) {
    	DateFormat df = new SimpleDateFormat( pattern );
    	df.setLenient( "true".equalsIgnoreCase( CONVERT.getString( "dateformat.lenient" ) ) );
    	return df;
    }
    
    public static DateFormat newDateFormat() {
    	return newFormat( DEF_DATE_FORMAT );
    }
    
    public static DateFormat newTimeFormat() {
    	return newFormat( DEF_TIME_FORMAT );
    }
    
    public static DateFormat newTimestampFormat() {
    	return newFormat( DEF_TIMESTAMP_FORMAT );
    }    
    
    public String formatObject( Object obj ) {
        String result = "";
        if (obj != null) {
            if ( obj instanceof java.sql.Date ) {
                result = newDateFormat().format( (java.sql.Date)obj );                
            } else if ( obj instanceof java.sql.Time ) {
                result = newTimeFormat().format( (java.sql.Time)obj );
            } else if ( obj instanceof java.util.Date ) {
                result = newTimestampFormat().format( (java.util.Date)obj );
			} else if ( obj instanceof DAOID ) {      
				result = obj.toString();
			} else if ( obj instanceof java.lang.Number ) {
				MessageFormat mf = new MessageFormat( CONVERT.getString( "number.format" ) );
				Object[] args = { obj };
				result = mf.format( args );
            } else {
                result = obj.toString();    
            }
        }
        return result;
    }
    
    /**
     * <p>Crea una nuova istanza di BasicModel.</p>
     *
     * 
     */
    public BasicModel() {
        super();
        this.infoMap = new HashMap();
    }

}
