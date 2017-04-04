package org.fugerit.java.core.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.fugerit.java.core.lang.helpers.ClassHelper;

public class StreamIO {


	public static final String PATH_CLASSLOADER = "cl://";
	
	public static final String PATH_JNDI = "jndi://";
	
	public static final String PATH_FILE = "file://";
	
	public static InputStream resolveStream( String path ) throws Exception {
		return resolveStream( path, null );
	}
	
	public static InputStream resolveStream( String path, String basePath ) throws Exception {
		return resolveStream( path, basePath, StreamIO.class );
	}
	
	public static InputStream resolveStream( String path, String basePath, Class c ) throws Exception {	
		InputStream is = null;
		if ( path.indexOf( PATH_CLASSLOADER ) == 0 ) {
			// class loader
			path = path.substring( PATH_CLASSLOADER.length() );
			is = ClassHelper.getResourceStream(path, c);
		} else {
			// default : file
			if ( path.indexOf( PATH_FILE ) == 0 ) {
				path = path.substring( PATH_FILE.length() );
			}
			File f = new File( path );
			if ( !f.exists() ) {
				f = new File( basePath, path );
			}
			if ( !f.exists() ) {
				throw ( new FileNotFoundException( f.getAbsolutePath() ) );
			} else {
				is = new FileInputStream( f );
			}
		}
		return is;
	}	
	
	public static String readString( InputStream is ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		pipeStream( is , baos, MODE_CLOSE_BOTH);
		return baos.toString();
	}	
	
	public static byte[] readBytes( Reader r ) throws IOException {
		StringWriter w = new StringWriter();
		pipeChar( r , w, MODE_CLOSE_BOTH);		
		return w.toString().getBytes();
	}	
	
	public static String readString( Reader r ) throws IOException {
		StringWriter w = new StringWriter();
		pipeChar( r , w, MODE_CLOSE_BOTH);
		return w.toString();
	}	
	
	public static byte[] readBytes( InputStream is ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		pipeStream( is , baos, MODE_CLOSE_BOTH);
		return baos.toByteArray();
	}
	
    /**
     * <p>Riversa un Reader in un Writer.</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @param mode      la modalita di riversamento
     * @param buffer    le dimensioni del buffer
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */
    public static int pipeChar(Reader src, Writer dst, int mode, int buffer) throws IOException {
        return ((new StreamIO(mode, buffer)).pipe(src, dst));
    }
    
    /**
     * <p>Riversa un Reader in un Writer (Viene usato il buffer di
     * default).</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @param mode      la modalita di riversamento
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */    
    public static int pipeChar(Reader src, Writer dst, int mode) throws IOException {
        return pipeChar(src, dst, mode, BUFFERSIZE_DEFAULT);
    }
    
    /**
     * <p>Riversa un Reader in un Writer (Viene usato il buffer di
     * default e non viene chiuso nessuno dei due stream).</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */    
    public static int pipeChar(Reader src, Writer dst) throws IOException {
        return pipeChar(src, dst, MODE_CLOSE_NONE, BUFFERSIZE_DEFAULT);
    }        
    
    /**
     * <p>Riversa un InputStream in un OutputStream.</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @param mode      la modalita di riversamento
     * @param buffer    le dimensioni del buffer
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */
    public static int pipeStream(InputStream src, OutputStream dst, int mode, int buffer) throws IOException {
        return ((new StreamIO(mode, buffer)).pipe(src, dst));
    }
    
    /**
     * <p>Riversa un InputStream in un OutputStream (Viene usato il buffer di
     * default).</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @param mode      la modalita di riversamento
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */    
    public static int pipeStream(InputStream src, OutputStream dst, int mode) throws IOException {
        return pipeStream(src, dst, mode, BUFFERSIZE_DEFAULT);
    }
    
    /**
     * <p>Riversa un InputStream in un OutputStream (Viene usato il buffer di
     * default e non viene chiuso nessuno dei due stream).</p>
     * 
     * @param src       la sorgente
     * @param dst       la destinazione
     * @return          il numero di byte effettivamente riversati
     * @throws IOException      se si verificano problemi durante la scrittura
     */    
    public static int pipeStream(InputStream src, OutputStream dst) throws IOException {
        return pipeStream(src, dst, MODE_CLOSE_NONE, BUFFERSIZE_DEFAULT);
    }    
    
    /**
     * <p>Modalita che lascia aperti entrambi gli stream alla fine dell'operazione.</p>
     */
    public static final int MODE_CLOSE_NONE = 0;
    
    /**
     * <p>Modalita che chiude entrambi gli stream alla fine dell'operazione.</p>
     */    
    public static final int MODE_CLOSE_BOTH = 4;
    
    /**
     * <p>Modalita che chiude il solo stream di input alla fine dell'operazione.</p>
     */        
    public static final int MODE_CLOSE_IN_ONLY = 1;
    
    
    /**
     * <p>Modalita che chiude il solo stream di output alla fine dell'operazione.</p>
     */        
    public static final int MODE_CLOSE_OUT_ONLY = 2;
    
    
    /**
     * <p>Buffer di piccole dimensioni.</p>
     */    
    public static final int BUFFERSIZE_LOW = 512;
    
    /**
     * <p>Buffer di medie dimensioni.</p>
     */        
    public static final int BUFFERSIZE_MEDIUM = 1024;
    
    /**
     * <p>Buffer di grandi dimensioni.</p>
     */        
    public static final int BUFFERSIZE_HIGH = 2048;
    
    /**
     * <p>Buffer di dimensioni predefinite.</p>
     */        
    public static final int BUFFERSIZE_DEFAULT = BUFFERSIZE_MEDIUM;
    
    /**
     * <p>Nessun buffer.</p>
     */    
    public static final int BUFFERSIZE_NOBUFFER = 1;
    
    public int mode;        // la modalita di riversamento
    
    public int bufferSize;  // le dimensioni del buffer di lettura usato
    
    /**
     * <p>Restituisce il valore di mode.</p>
     * 
     * @return il valore di mode.
     */
    public int getMode() {
        return mode;
    }
    
    /**
     * <p>Restituisce il valore di bufferSize.</p>
     * 
     * @return il valore di bufferSize.
     */
    public int getBufferSize() {
        return bufferSize;
    }    
    
    /**
     * <p>Crea un nuovo StreamIO</p>
     * 
     * 
     */
    public StreamIO(int mode, int bufferSize) {
        this.mode = mode;
        this.bufferSize = bufferSize;
    }
    
    /**
     * <p>Riversa il contenuto di un InputStream in un OutputStream.</p>
     * 
     * @param src   la sorgente
     * @param dst   la destinazione
     * @return      il numero di byte effettivamente riversati
     * @throws IOException  se si verificano problemi durante la scrittura
     */
    public int pipe(InputStream src, OutputStream dst) throws IOException {
        int result = 0;
        byte[] buffer = new byte[bufferSize];
        int read = src.read(buffer);
        while (read>0) {
            dst.write(buffer, 0, read);
            result+=read;
            read = src.read(buffer);
        }
        if (this.isModeCloseIn()) {
            src.close();
        }
        if (this.isModeCloseOut()) {
            dst.close();
        }        
        return result;
    }

    /**
     * <p>Riversa il contenuto di un Reader in un Writer.</p>
     * 
     * @param src   la sorgente
     * @param dst   la destinazione
     * @return      il numero di char effettivamente riversati
     * @throws IOException  se si verificano problemi durante la scrittura
     */    
    public int pipe(Reader src, Writer dst) throws IOException {
        int result = 0;
        char[] buffer = new char[bufferSize];
        int read = src.read(buffer);
        while (read>0) {
            dst.write(buffer, 0, read);
            result+=read;
            read = src.read(buffer);
        }
        if (this.isModeCloseIn()) {
            src.close();
        }
        if (this.isModeCloseOut()) {
            dst.close();
        }
        return result;
    }    

    public boolean isModeCloseNone() {
        return this.mode==MODE_CLOSE_NONE;
    }    
    
    public boolean isModeCloseBoth() {
        return this.mode==MODE_CLOSE_BOTH;
    }
    
    public boolean isModeCloseOut() {
        return this.mode==MODE_CLOSE_OUT_ONLY || this.mode==MODE_CLOSE_BOTH;
    }    
    
    public boolean isModeCloseIn() {
        return this.mode==MODE_CLOSE_IN_ONLY || this.mode==MODE_CLOSE_BOTH;
    }
	
	
}
