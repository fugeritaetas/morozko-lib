/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.doc 

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
 * @(#)DocFacade.java
 *
 * @project    : org.morozko.java.mod.doc
 * @package    : org.morozko.java.mod.doc
 * @creation   : 06/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.doc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import jxl.Workbook;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.lang.Result;
import org.morozko.java.core.xml.TransformerXML;
import org.morozko.java.core.xml.XMLValidator;
import org.morozko.java.core.xml.sax.XMLFactorySAX;
import org.morozko.java.core.xml.sax.XMLValidatorSAX;
import org.morozko.java.core.xml.sax.dh.DefaultHandlerComp;
import org.morozko.java.core.xml.sax.er.ByteArrayEntityResolver;
import org.morozko.java.mod.doc.itext.ITextDocHandler;
import org.morozko.java.mod.doc.itext.ItextPdfDocHandler;
import org.morozko.java.mod.doc.itext.mem.ItextDocContentHandler;
import org.morozko.java.mod.doc.type.XlsTypeHandler;
import org.morozko.java.mod.doc.xml.DocContentHandler;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;
import org.xml.sax.InputSource;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DocFacade {

	private static void print( PrintStream s, DocContainer docContainer, String indent ) {
		Iterator it = docContainer.docElements();
		while ( it.hasNext() ) {
			DocElement docElement = (DocElement) it.next();
			s.println( indent+docElement );
			if ( docElement instanceof DocContainer ) {
				print( s, (DocContainer)docElement, indent+"  " );
			}
		}
	}
	
	private static void print( PrintStream s, DocContainer docContainer ) {
		print( s, docContainer, "" );
	}
	
	public static void print( PrintStream s, DocBase doc ) {
		print( s, doc.getDocBody() );
	}
	
	private static final String DTD = "/org/morozko/java/mod/doc/res/doc-1-0.dtd";
	
	public final static String SYSTEM_ID = "http://www.morozko.org/data/java/mod/doc/dtd/doc-1-0.dtd";
	
	public static boolean validate( InputStream is ) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamIO.pipeStream( DocFacade.class.getResourceAsStream( DTD  ), baos );
		ByteArrayEntityResolver er = new ByteArrayEntityResolver( baos.toByteArray(), null, SYSTEM_ID );
		XMLValidator validator = XMLValidatorSAX.newInstance( er );
		Result result = validator.validateXML( is );
		//result.printErrorReport( System.out );
		return result.isTotalSuccess();
	}
	
	public static DocBase parse( Reader is, DocHelper docHelper ) throws Exception {
		SAXParser parser = XMLFactorySAX.makeSAXParser( false ,  false );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamIO.pipeStream( DocFacade.class.getResourceAsStream( DTD  ), baos );
		ByteArrayEntityResolver er = new ByteArrayEntityResolver( baos.toByteArray(), null, SYSTEM_ID );
		DocContentHandler dch =  new DocContentHandler( docHelper );
		DefaultHandlerComp dh = new DefaultHandlerComp( dch );
		dh.setWrappedEntityResolver( er );
		parser.parse( new InputSource(is), dh);
		DocBase docBase = dch.getDocBase();
		is.close();
		return docBase;
	}	
	
	public static DocBase parse( InputStream is, DocHelper docHelper ) throws Exception {
		SAXParser parser = XMLFactorySAX.makeSAXParser( true ,  false );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamIO.pipeStream( DocFacade.class.getResourceAsStream( DTD  ), baos );
		ByteArrayEntityResolver er = new ByteArrayEntityResolver( baos.toByteArray(), null, SYSTEM_ID );
		DocContentHandler dch = new DocContentHandler( docHelper );
		DefaultHandlerComp dh = new DefaultHandlerComp( dch );
		dh.setWrappedEntityResolver( er );
		parser.parse( is, dh);
		DocBase docBase = dch.getDocBase();
		is.close();
		return docBase;
	}	
	
	public static DocBase parse( Reader is ) throws Exception {
		return parse( is, DocHelper.DEFAULT );
	}
	
	public static DocBase parse( InputStream is ) throws Exception {
		return parse( is, DocHelper.DEFAULT );
	}
	
	public static void createPDFItext( DocBase docBase, OutputStream outputStream ) throws Exception {
		Document document = new Document( PageSize.A4, 20, 20, 20, 20 );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance( document, baos );
		ItextPdfDocHandler handler = new ItextPdfDocHandler( document, ITextDocHandler.DOC_OUTPUT_PDF );
		handler.handleDoc( docBase );
		baos.writeTo( outputStream );
		baos.close();
		outputStream.close();		
	}
	
	public static void createXLS( DocBase docBase, OutputStream outputStream ) throws Exception {
		String excelTemplate = docBase.getInfo().getProperty( XlsTypeHandler.PROP_XLS_TEMPLATE );
		Workbook templateXls = null;
		if ( excelTemplate != null ) {
			templateXls = Workbook.getWorkbook( new File( excelTemplate ) );
		}			
		XlsTypeHandler.handleDoc( docBase , outputStream, templateXls );
	}	
	

	
	public static void createPDFMem( InputStream is, OutputStream outputStream ) throws Exception {
		
		DocHelper docHelper = DocHelper.DEFAULT;
		
		SAXParser parser = XMLFactorySAX.makeSAXParser( true ,  false );
		ByteArrayOutputStream baosDTD = new ByteArrayOutputStream();
		StreamIO.pipeStream( DocFacade.class.getResourceAsStream( DTD  ), baosDTD );
		ByteArrayEntityResolver er = new ByteArrayEntityResolver( baosDTD.toByteArray(), null, SYSTEM_ID );
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ItextDocContentHandler dch = new ItextDocContentHandler( baos, docHelper );
		
		DefaultHandlerComp dh = new DefaultHandlerComp( dch );
		dh.setWrappedEntityResolver( er );
		
		
		System.out.println( "BAOS LENGTH 1 -> "+baos.size() );
		
		parser.parse( is, dh);
		
		is.close();
		
		System.out.println( "BAOS LENGTH 2 -> "+baos.size() );
		
		

		

//		// create doc handler
//		ITextDocHandler handler = new ITextDocHandler( document, pdfWriter );
//		
////		if ( "true".equalsIgnoreCase( docBase.getInfo().getProperty( "set-total-page" ) ) ) {
////			handler.handleDoc( docBase );
////			int totalPageCount = pdfWriter.getCurrentPageNumber()-1;
////			document = new Document( PageSize.A4, Integer.parseInt( margins[0] ),
////					Integer.parseInt( margins[1] ),
////					Integer.parseInt( margins[2] ), 
////					Integer.parseInt( margins[3] ) );
////			baos = new ByteArrayOutputStream();
////			pdfWriter = PdfWriter.getInstance( document, baos );
////			handler = new ITextDocHandler(document, pdfWriter, totalPageCount );
////		}
//		
//		handler.handleDoc( docBase );
//		
		baos.writeTo( outputStream );
		baos.close();
		outputStream.close();		
	}	
	
	
	public static void createPDF( DocBase docBase, OutputStream outputStream ) throws Exception {
		
		
		
		String[] margins = docBase.getInfo().getProperty( "margins", "20;20;20;20" ).split( ";" );
		Document document = new Document( PageSize.A4, Integer.parseInt( margins[0] ),
				Integer.parseInt( margins[1] ),
				Integer.parseInt( margins[2] ), 
				Integer.parseInt( margins[3] ) );
		
		// allocate buffer
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// create pdf writer
		PdfWriter pdfWriter = PdfWriter.getInstance( document, baos );
		// create doc handler
		ITextDocHandler handler = new ITextDocHandler( document, pdfWriter );
		
		if ( "true".equalsIgnoreCase( docBase.getInfo().getProperty( "set-total-page" ) ) ) {
			handler.handleDoc( docBase );
			int totalPageCount = pdfWriter.getCurrentPageNumber()-1;
			document = new Document( PageSize.A4, Integer.parseInt( margins[0] ),
					Integer.parseInt( margins[1] ),
					Integer.parseInt( margins[2] ), 
					Integer.parseInt( margins[3] ) );
			baos = new ByteArrayOutputStream();
			pdfWriter = PdfWriter.getInstance( document, baos );
			handler = new ITextDocHandler(document, pdfWriter, totalPageCount );
		}
		
		handler.handleDoc( docBase );
		
		baos.writeTo( outputStream );
		baos.close();
		outputStream.close();		
	}		
	
	public static void createRTF( DocBase docBase, OutputStream outputStream ) throws Exception {
		String[] margins = docBase.getInfo().getProperty( "margins", "20;20;20;20" ).split( ";" );
		Document document = new Document( PageSize.A4, Integer.parseInt( margins[0] ),
				Integer.parseInt( margins[1] ),
				Integer.parseInt( margins[2] ), 
				Integer.parseInt( margins[3] ) );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		RtfWriter2 rtfWriter2 = RtfWriter2.getInstance( document, baos );
		ITextDocHandler handler = new ITextDocHandler( document, rtfWriter2 );
		handler.handleDoc( docBase );
		baos.writeTo( outputStream );
		baos.close();
		outputStream.close();		
	}	
	
	public static void createHTML( DocBase docBase, OutputStream outputStream ) throws Exception {
		Document document = new Document( );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HtmlWriter.getInstance( document, baos );
		ITextDocHandler handler = new ITextDocHandler( document, ITextDocHandler.DOC_OUTPUT_HTML );
		handler.handleDoc( docBase );
		baos.writeTo( outputStream );
		baos.close();
		outputStream.close();		
	}		
	
	public static void main( String[] args ) {
		try {
			
			//ITextDocHandler.registerFont( "default-font", "/font/bookos.ttf");
			
			ArgList list = ArgUtils.parseArgs( args );
			
			File file = new File( list.findArgValue( "xml" ) );
			
			File outputDir = new File( list.findArgValue( "output-dir" ) );
			
			
			String format = list.findArgValue( "format" );
			
			InputStream is = new FileInputStream( file  );
			
			if ( "pdfmem".equalsIgnoreCase( format ) ) {
				
				FileOutputStream fos2 = new FileOutputStream( new File( outputDir, file.getName()+"mem.pdf" ) );
				
				createPDFMem( is, fos2 );			
				
			} else {
				
				DocBase docBase = parse( is );
				
//				MemoryCounter m = new MemoryCounter();
//				System.out.println( "SIZE "+m.estimate( docBase )/1000/1000 );
//				
//				System.out.println( "PARSE DONE ! "+Runtime.getRuntime().freeMemory()/1000/1000+" / "+Runtime.getRuntime().totalMemory()/1000/1000 );
//				Runtime.getRuntime().gc();
//				System.out.println( "GC DONE ! "+Runtime.getRuntime().freeMemory()/1000/1000+" / "+Runtime.getRuntime().totalMemory()/1000/1000 );
				
				
				if ( "xls".equalsIgnoreCase( format ) ) {
					FileOutputStream fos4 = new FileOutputStream( new File( outputDir, file.getName()+".xls" ) );
					createXLS( docBase, fos4 );
				} else if ( "pdf".equalsIgnoreCase( format ) ) {
					FileOutputStream fos2 = new FileOutputStream( new File( outputDir, file.getName()+".pdf" ) );
					createPDF( docBase, fos2 );
				} else if ( "rtf".equalsIgnoreCase( format ) ) {
					FileOutputStream fos3 = new FileOutputStream( new File( outputDir, file.getName()+".rtf" ) );
					createRTF( docBase, fos3 );
				} else if ( "html".equalsIgnoreCase( format ) ) {
					FileOutputStream fos1 = new FileOutputStream( new File( outputDir, file.getName()+".html" ) );
					createHTML( docBase, fos1 );
				} else {
					throw new Exception( "No valid format selected! : "+format );
				}
				
			}
			
			

			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
}

class MemorySizes {
	  private final Map primitiveSizes = new IdentityHashMap() {
	    {
	      put(boolean.class, new Integer(1));
	      put(byte.class, new Integer(1));
	      put(char.class, new Integer(2));
	      put(short.class, new Integer(2));
	      put(int.class, new Integer(4));
	      put(float.class, new Integer(4));
	      put(double.class, new Integer(8));
	      put(long.class, new Integer(8));
	    }
	  };
	  public int getPrimitiveFieldSize(Class clazz) {
	    return ((Integer) primitiveSizes.get(clazz)).intValue();
	  }
	  public int getPrimitiveArrayElementSize(Class clazz) {
	    return getPrimitiveFieldSize(clazz);
	  }
	  public int getPointerSize() {
	    return 4;
	  }
	  public int getClassSize() {
	    return 8;
	  }
	}

class MemoryCounter {
	  private static final MemorySizes sizes = new MemorySizes();
	  private final Map visited = new IdentityHashMap();
	  private final Stack stack = new Stack();

	  public synchronized long estimate(Object obj) {
	    assert visited.isEmpty();
	    assert stack.isEmpty();
	    long result = _estimate(obj);
	    while (!stack.isEmpty()) {
	      result += _estimate(stack.pop());
	    }
	    visited.clear();
	    return result;
	  }

	  private boolean skipObject(Object obj) {
	    if (obj instanceof String) {
	      // this will not cause a memory leak since
	      // unused interned Strings will be thrown away
	      if (obj == ((String) obj).intern()) {
	        return true;
	      }
	    }
	    return (obj == null)
	        || visited.containsKey(obj);
	  }

	  private long _estimate(Object obj) {
	    if (skipObject(obj)) return 0;
	    visited.put(obj, null);
	    long result = 0;
	    Class clazz = obj.getClass();
	    if (clazz.isArray()) {
	      return _estimateArray(obj);
	    }
	    while (clazz != null) {
	      Field[] fields = clazz.getDeclaredFields();
	      for (int i = 0; i < fields.length; i++) {
	        if (!Modifier.isStatic(fields[i].getModifiers())) {
	          if (fields[i].getType().isPrimitive()) {
	            result += sizes.getPrimitiveFieldSize(
	                fields[i].getType());
	          } else {
	            result += sizes.getPointerSize();
	            fields[i].setAccessible(true);
	            try {
	              Object toBeDone = fields[i].get(obj);
	              if (toBeDone != null) {
	                stack.add(toBeDone);
	              }
	            } catch (IllegalAccessException ex) { assert false; }
	          }
	        }
	      }
	      clazz = clazz.getSuperclass();
	    }
	    result += sizes.getClassSize();
	    return roundUpToNearestEightBytes(result);
	  }

	  private long roundUpToNearestEightBytes(long result) {
	    if ((result % 8) != 0) {
	      result += 8 - (result % 8);
	    }
	    return result;
	  }

	  protected long _estimateArray(Object obj) {
	    long result = 16;
	    int length = Array.getLength(obj);
	    if (length != 0) {
	      Class arrayElementClazz = obj.getClass().getComponentType();
	      if (arrayElementClazz.isPrimitive()) {
	        result += length *
	            sizes.getPrimitiveArrayElementSize(arrayElementClazz);
	      } else {
	        for (int i = 0; i < length; i++) {
	          result += sizes.getPointerSize() +
	              _estimate(Array.get(obj, i));
	        }
	      }
	    }
	    return result;
	  }
	}
