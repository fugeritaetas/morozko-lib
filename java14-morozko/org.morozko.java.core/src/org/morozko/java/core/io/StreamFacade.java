/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)StreamFacade.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.io
 * @creation   : 20-giu-2005 7.39.49
 */
package org.morozko.java.core.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

/**
 * <p>.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public class StreamFacade {

	public static byte[] readUrl( String url ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		URL u = new URL( url );
		StreamIO.pipeStream( u.openStream() , baos, StreamIO.MODE_CLOSE_IN_ONLY );
		return baos.toByteArray();
	}
	
    public static void pipeChar( Reader r, Writer w, int bufferSize, boolean closeIn, boolean closeOut ) throws IOException {
    	int mode = StreamIO.MODE_CLOSE_NONE;
    	if ( closeIn && closeOut ) {
    		mode = StreamIO.MODE_CLOSE_BOTH;
    	} else if ( closeIn ) {
    		mode = StreamIO.MODE_CLOSE_IN_ONLY;
    	} else if ( closeOut ) {
    		mode = StreamIO.MODE_CLOSE_OUT_ONLY;
    	}
        StreamIO sio = new StreamIO( bufferSize, mode );
        sio.pipe( r, w );
    }    
    
    public static void pipeStream( InputStream is, OutputStream os ) throws IOException {
        pipeStream( is, os, StreamIO.BUFFERSIZE_DEFAULT, true, false );
    }
    
    public static void pipeStream( InputStream is, OutputStream os, int bufferSize, boolean close ) throws IOException {
        pipeStream( is, os, bufferSize, close, close );
    }
    
    public static void pipeStream( InputStream is, OutputStream os, int bufferSize, boolean closeIn, boolean closeOut ) throws IOException {
    	int mode = StreamIO.MODE_CLOSE_NONE;
    	if ( closeIn && closeOut ) {
    		mode = StreamIO.MODE_CLOSE_BOTH;
    	} else if ( closeIn ) {
    		mode = StreamIO.MODE_CLOSE_IN_ONLY;
    	} else if ( closeOut ) {
    		mode = StreamIO.MODE_CLOSE_OUT_ONLY;
    	}    	
        StreamIO sio = new StreamIO( bufferSize, mode );
        sio.pipe( is, os );
    }
    
    private StreamFacade() {
        super();
    }

}
