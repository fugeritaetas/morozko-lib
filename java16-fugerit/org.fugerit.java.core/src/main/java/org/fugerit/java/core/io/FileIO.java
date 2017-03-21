/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @(#)FileIO.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.io
 * @creation	: 3-dic-2004 17.02.21
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import org.fugerit.java.core.io.file.FileFunSecure;
import org.fugerit.java.core.io.file.ZipFileFun;
import org.fugerit.java.core.lang.helpers.ExHandler;
import org.fugerit.java.core.lang.helpers.Result;
import org.fugerit.java.core.lang.helpers.ResultExHandler;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		Permette di compiere operazioni di lettura e scrittura su file.
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Permts I/O operations on files.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class FileIO {

	public static boolean createFullFile( File file ) throws IOException {
		boolean created = true;
		if ( file.exists() ) {
			created = false;
		} else {
			if ( file.isDirectory() ) {
				created = file.mkdirs();
			} else {
				file.getParentFile().mkdirs();
				created = file.createNewFile();
			}
		}
		return created;
	}
	
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>
     * 		Crea un archivio zip di tutti i file in una data cartella.
	 * 		La risorsione viene fatta in modo sicuro, vale a dire che non vengono 
     * 		lanciate eccezioni. 
     * 		</jdl:text>
     * 		<jdl:text lang='en'>
     * 		Creates a zip archive of all files in a given directory.
     * 		The recursion is done in a secure mode, no exception is thrown.
     * 		</jdl:text>  
     *	</jdl:section>
     * </p> 
     *
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>L'archivio zip da creare.</jdl:text>
     * 								<jdl:text lang='en'>The zip archive to create.</jdl:text>  
     *							</jdl:section>   
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il risultato della ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The result of the recursion.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */    	
    public static Result zipFileSecure(File file, File dir) throws IOException {
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(file));
        FileFun fun = new ZipFileFun(stream, dir);
        Result result = null;
        try {
             result = recurseDirSecure(dir, fun);
        } catch (IOException ioe) {
            throw (ioe);
        } finally {
            stream.close();            
        }
        return result;
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>
     * 		Crea un archivio zip di tutti i file in una data cartella.
	 * 		La risorsione viene fatta in modo sicuro, vale a dire che non vengono 
     * 		lanciate eccezioni. 
     * 		</jdl:text>
     * 		<jdl:text lang='en'>
     * 		Creates a zip archive of all files in a given directory.
     * 		The recursion is done in a secure mode, no exception is thrown.
     * 		</jdl:text>  
     *	</jdl:section>
     * </p> 
     *
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>L'archivio zip da creare.</jdl:text>
     * 								<jdl:text lang='en'>The zip archive to create.</jdl:text>  
     *							</jdl:section>   
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */        
    public static void zipFileSecure(File file, File dir, ExHandler handler) throws IOException {
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(file));
        FileFun fun = new ZipFileFun(stream, dir);
        try {
            recurseDirSecure(dir, fun, handler);
        } catch (IOException ioe) {
            throw (ioe);
        } finally {
            stream.close();            
        }
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>
     * 		Crea un archivio zip di tutti i file in una data cartella.
     * 		</jdl:text>
     * 		<jdl:text lang='en'>
     * 		Creates a zip archive of all files in a given directory.
     * 		</jdl:text>  
     *	</jdl:section>
     * </p> 
     *
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>L'archivio zip da creare.</jdl:text>
     * 								<jdl:text lang='en'>The zip archive to create.</jdl:text>  
     *							</jdl:section>   
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */    
    public static void zipFile(File file, File dir) throws IOException {
        ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(file));
        FileFun fun = new ZipFileFun(stream, dir);
        try {
            recurseDir(dir, fun);
        } catch (IOException ioe) {
            throw (ioe);
        } finally {
            stream.close();            
        }
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>
     * 		Applica una <code>FileFun</code> a tutti i file contenuti in una data directory.
     * 		La risorsione viene fatta in modo sicuro, vale a dire che non vengono 
     * 		lanciate eccezioni.
     * 		</jdl:text>
     * 		<jdl:text lang='en'>
     * 		Apply a <code>FileFun</code> to every file in a given directory.
     * 		The recursion is done in a secure mode, no exception is thrown.
     * 		</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @param fun				<jdl:section>
     * 								<jdl:text lang='it'>La funzione da applicare.</jdl:text>
     * 								<jdl:text lang='en'>The function to apply.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il risultato della ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The result of the recursion.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */    
    public static Result recurseDirSecure(File dir, FileFun fun) throws IOException {
        Result result = new Result();;
        recurseDirSecure(dir, fun, new ResultExHandler(result));
        return result;
    }

    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>
     * 		Applica una <code>FileFun</code> a tutti i file contenuti in una data directory.
     * 		La risorsione viene fatta in modo sicuro, vale a dire che non vengono 
     * 		lanciate eccezioni.
     * 		</jdl:text>
     * 		<jdl:text lang='en'>
     * 		Apply a <code>FileFun</code> to every file in a given directory.
     * 		The recursion is done in a secure mode, no exception is thrown.
     * 		</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @param fun				<jdl:section>
     * 								<jdl:text lang='it'>La funzione da applicare.</jdl:text>
     * 								<jdl:text lang='en'>The function to apply.</jdl:text>  
     *							</jdl:section>
     * @param handler			<jdl:section>
     * 								<jdl:text lang='it'>L' handler di eccezioni.</jdl:text>
     * 								<jdl:text lang='en'>The exception handler.</jdl:text>  
     *							</jdl:section>*
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static void recurseDirSecure(File dir, FileFun fun, ExHandler handler) throws IOException {
        recurseDir(dir, new FileFunSecure(fun, handler));
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Applica una <code>FileFun</code> a tutti i file contenuti in una data directory.</jdl:text>
     * 		<jdl:text lang='en'>Apply a <code>FileFun</code> to every file in a given directory.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param dir				<jdl:section>
     * 								<jdl:text lang='it'>La cartella su cui eseguire la ricorsione.</jdl:text>
     * 								<jdl:text lang='en'>The directory to recurse.</jdl:text>  
     *							</jdl:section>
     * @param fun				<jdl:section>
     * 								<jdl:text lang='it'>La funzione da applicare.</jdl:text>
     * 								<jdl:text lang='en'>The function to apply.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */     
    public static void recurseDir(File dir, FileFun fun) throws IOException {
        if (dir.isDirectory()) {
            File[] list = dir.listFiles();
            for (int k=0; k<list.length; k++) {
                recurseDir(list[k], fun);
                fun.handleFile(list[k]);
            }
        }
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>java.lang.StringBuffer</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>java.lang.StringBuffer</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static StringBuffer readStringBuffer(String file) throws IOException {
        return readStringBuffer(new File(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>java.lang.String</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>java.lang.String</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static String readString(String file) throws IOException {
        return readString(new File(file));
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>byte[]</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>byte[]</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static byte[] readBytes(String file) throws IOException {
        return readBytes(new File(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>char[]</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>char[]</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */     
    public static char[] readChars(String file) throws IOException {
        return readChars(new File(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>java.lang.String</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>java.lang.String</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static StringBuffer readStringBuffer(File file) throws IOException {
        return (new StringBuffer(readString(file)));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>java.lang.String</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>java.lang.String</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static String readString(File file) throws IOException {
        return (new String(readBytes(file)));
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>byte[]</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>byte[]</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static byte[] readBytes(File file) throws IOException {
        ByteArrayOutputStream dst = new ByteArrayOutputStream();
        StreamIO.pipeStream(new FileInputStream(file), dst);
        return dst.toByteArray();
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Legge il contenuto di un file file.</jdl:text>
     * 		<jdl:text lang='en'>Read the content of a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il contenuto del file come un array di <code>char[]</code>.</jdl:text>
     * 								<jdl:text lang='en'>The content of the file as a <code>char[]</code> array.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static char[] readChars(File file) throws IOException {
        CharArrayWriter dst = new CharArrayWriter();
        StreamIO.pipeChar(new FileReader(file), dst);
        return dst.toCharArray();
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static int writeStringBuffer(StringBuffer data, String file ) throws IOException {
        return writeStringBuffer(data, new File(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static int writeString(String data, String file ) throws IOException {
        return writeString(data, new File(file));
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di byte effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of bytes effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */   
    public static int writeBytes(byte[] data, String file ) throws IOException {
        return writeBytes(data, new File(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static int writeChars(char[] data, String file ) throws IOException {
        return writeChars(data, new File(file));
    }
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */   
    public static int writeStringBuffer(StringBuffer data, File file ) throws IOException {
        return writeString(data.toString(), file);
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */  
    public static int writeString(String data, File file ) throws IOException {
        return writeChars(data.toCharArray(), file);
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di byte effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of bytes effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */ 
    public static int writeBytes(byte[] data, File file ) throws IOException {
        return StreamIO.pipeStream(new ByteArrayInputStream(data), new FileOutputStream(file));
    }    
    
    /**
     * <p> 
     *	<jdl:section>
     * 		<jdl:text lang='it'>Scrive in un file.</jdl:text>
     * 		<jdl:text lang='en'>Write to a file.</jdl:text>  
     *	</jdl:section>
     * </p> 
     * 
     * @param data				<jdl:section>
     * 								<jdl:text lang='it'>Dati da scrivere nel file.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @param file				<jdl:section>
     * 								<jdl:text lang='it'>Il file su cui scrivere.</jdl:text>
     * 								<jdl:text lang='en'>Data to write to the files.</jdl:text>  
     *							</jdl:section>
     * @return					<jdl:section>
     * 								<jdl:text lang='it'>Il numero di charatteri effettivamente scritti.</jdl:text>
     * 								<jdl:text lang='en'>The number of char effectively written.</jdl:text>  
     *							</jdl:section>
     * @throws IOException		<jdl:section>
	 * 								<jdl:text lang='it'>Se qualcosa va male durante l'elaborazione.</jdl:text>
	 * 								<jdl:text lang='en'>If something goes wrong during elaboration.</jdl:text>  
	 *							</jdl:section>
     */
    public static int writeChars(char[] data, File file ) throws IOException {
        return StreamIO.pipeChar(new CharArrayReader(data), new FileWriter(file));
    }
    
    private FileIO() {
        super();
    }

}
