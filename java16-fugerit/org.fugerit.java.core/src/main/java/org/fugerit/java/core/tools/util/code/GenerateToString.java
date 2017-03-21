package org.fugerit.java.core.tools.util.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;

public class GenerateToString {
	
	private static final String DQ = "\"";
	
	private static String fieldName( String method ) {
		StringBuffer result = new StringBuffer();
		if ( method.startsWith( "get" ) ) {
			result.append( method.substring( 3, 4 ).toLowerCase() );
			result.append( method.substring( 4 ) );
		} else {
			result.append( method );
		}
		return result.toString();
	}
	
	private static void createMethod( PrintWriter pw, String className , String excludeMethod ) throws Exception {
		pw.println( "public String toString() { " );
		pw.println( "	StringBuffer buffer = new StringBuffer();" );
		Object obj = ClassHelper.newInstance( className );
		int methodCount = 0;
		Method[] mList = obj.getClass().getMethods();
		for ( int k=0; k<mList.length; k++ ) {
			Method m = mList[k]; 
			String methodName = m.getName();
			if ( Modifier.isPublic( m.getModifiers() ) 
					&& !Modifier.isStatic( m.getModifiers() )
					&& methodName.startsWith( "get" )
					&& m.getParameterTypes().length == 0 
					&& !methodName.equals( "getClass" )
					&& !excludeMethod.contains( methodName+";" ) ) {
				String startWith = null;
				if ( methodCount == 0 ) {
					pw.println( "	buffer.append( this.getClass().getName() );" );
					startWith = "["; 
				} else {
					startWith = ","; 
				}
				pw.println( "	buffer.append( "+DQ+startWith+fieldName( methodName )+"="+DQ+" );" );
				pw.println( "	buffer.append( this."+methodName+"() );" );
				methodCount++;
			}
		}
		pw.println( "	buffer.append( "+DQ+"]"+DQ+" );" );
		pw.println( "	return buffer.toString();" );
		pw.println( "}" );
	}
	
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}
	
	private static Class[] getClasses(String packageName)
	        throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}
	
	public static void main( String[] args ) {
		try {
			ArgList list = ArgUtils.parseArgsProps( args );
			String className = list.findArgValue( "class-name" );
			String outputFile = list.findArgValue( "output-file" );
			String packageName = list.findArgValue( "package-name" );
			String sourcePath = list.findArgValue( "source-path" );
			String excludeMethod = list.findArgValue( "exclude-method" );
			PrintWriter pw = new PrintWriter( System.out, true );
			if ( outputFile != null ) {
				pw = new PrintWriter( new FileWriter( new File( outputFile ) ), true );
			}
			String[] classNames = null;
			if ( className != null ) {
				classNames = className.split( ";" );
			} else if ( packageName != null ) {
				Class[] cList = getClasses( packageName );
				classNames = new String[ cList.length ];
				for ( int k=0; k<cList.length; k++ ) {
					classNames[k] = cList[k].getName();
				}
			}
			for ( int k=0; k<classNames.length; k++ ) {
				pw.println( "// toString() method for class: "+classNames[k] );
				createMethod( pw , classNames[k], excludeMethod+";" );	
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
