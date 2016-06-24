package org.morozko.java.mod.text.bbcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import org.morozko.java.core.lang.helpers.ClassHelper;

public class BBCodeFactory {

	private final static String DEFAULT_CL_PATH = "org/morozko/java/mod/text/bbcode/bbcode-config-default.xml";
	
	public final static String ATT_NAME = "BBCodeFactory.ATT_NAME";
	
	public BBCodeFactory() {
		this.configMap = new HashMap<String, BBResolver>();
	}
	
	private HashMap<String, BBResolver> configMap;
	
	public void registerConfigFile( String name, String path ) {
		this.configMap.put( name , new FileBBResolver( path ) );
	}
	
	public void registerConfigCl( String name, String path ) {
		this.configMap.put( name , new ClassBBResolver( path ) );
	}
	
	public BBCodeHandler newHandler( String name ) throws Exception {
		return this.configMap.get( name ).getHandler();
	}
	
	public static BBCodeHandler getDefaultHandler() throws Exception {
		ClassBBResolver resolver = new ClassBBResolver( DEFAULT_CL_PATH );
		return resolver.getHandler();
	}

	public static String process( String text ) throws Exception {
		return process( getDefaultHandler(), text );
	}
	
	public static String process( BBCodeHandler handler, String text ) {
		String result = text;
		Iterator<BBCode> itBB = handler.getAlwaysProcessList().iterator();
		while ( itBB.hasNext() ) {
			BBCode bb = itBB.next();
			result = result.replaceAll( bb.getRegex() , bb.getReplace() );
		}
		return result;
	}
	
}

abstract class BBResolver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3413630920864341351L;
	
	public abstract BBCodeHandler getHandler() throws Exception;
	
	protected BBCodeHandler getHandler( InputStream is ) throws Exception {
		BBCodeHandler handler = BBCodeHandler.parse( is );
		return handler;
	}
	
}

class FileBBResolver extends BBResolver {

	private String path;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14234324324234L;

	@Override
	public BBCodeHandler getHandler() throws Exception {
		FileInputStream fis = new FileInputStream( new File( this.path ) );
		BBCodeHandler handler = this.getHandler( fis );
		fis.close();
		return handler;
	} 

	public FileBBResolver(String path) {
		super();
		this.path = path;
	}
	
}

class ClassBBResolver extends BBResolver {

	private String path;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14234324324234L;

	@Override
	public BBCodeHandler getHandler() throws Exception {
		return this.getHandler( ClassHelper.getResourceStream( this.path, ClassBBResolver.class ) );
	}

	public ClassBBResolver(String path) {
		super();
		this.path = path;
	}
	
}