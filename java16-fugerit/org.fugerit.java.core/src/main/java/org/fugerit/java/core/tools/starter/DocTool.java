package org.fugerit.java.core.tools.starter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;

import org.fugerit.java.core.doc.DocBase;
import org.fugerit.java.core.doc.DocFacade;
import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;

public class DocTool {

	public static final String ARG_FORMAT = "format";
	public static final String ARG_XML = "xml";
	public static final String ARG_OUTPUTDIR = "output-dir";
	
	public static void handleDoc( ArgList list ) throws Exception {
		File file = new File(list.findArgValue( ARG_XML ));
		File outputDir = new File( list.findArgValue( ARG_OUTPUTDIR ) );
		String format = list.findArgValue( ARG_FORMAT );
		Reader reader = new FileReader(file);
		FileOutputStream fos = null;
		try {
			DocBase docBase = DocFacade.parse(reader);
			if ("xls".equalsIgnoreCase(format)) {
				File outputFile = new File( outputDir, file.getName() + ".xls" );
				fos = new FileOutputStream ( outputFile );
				DocFacade.createXLS(docBase, fos );
			} else if ("pdf".equalsIgnoreCase(format)) {
				File outputFile = new File( outputDir, file.getName() + ".pdf" );
				fos = new FileOutputStream ( outputFile );
				DocFacade.createPDF(docBase, fos );
			} else if ("rtf".equalsIgnoreCase(format)) {
				File outputFile = new File( outputDir, file.getName() + ".rtf" );
				fos = new FileOutputStream ( outputFile );
				DocFacade.createRTF(docBase, fos );
			} else if ("html".equalsIgnoreCase(format)) {
				File outputFile = new File( outputDir, file.getName() + ".html" );
				fos = new FileOutputStream ( outputFile );
				DocFacade.createHTML(docBase, fos );
			} else {
				throw new Exception("No valid format selected! : " + format);
			}
		} catch ( Exception e) {
			throw e;
		} finally {
			if ( reader != null ) {
				reader.close();	
			}
			if ( fos != null ) {
				fos.close();
			}
		}

	}
	
	public static void main(String[] args) {
		try {
			ArgList list = ArgUtils.parseArgs(args);
			handleDoc( list );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
