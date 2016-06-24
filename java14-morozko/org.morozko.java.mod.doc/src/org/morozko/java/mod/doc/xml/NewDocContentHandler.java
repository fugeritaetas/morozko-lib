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
 * @(#)DocContentHandler.java
 *
 * @project    : org.morozko.java.mod.doc
 * @package    : org.morozko.java.mod.doc.xml
 * @creation   : 06/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.doc.xml;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.morozko.java.mod.doc.DocBase;
import org.morozko.java.mod.doc.DocBorders;
import org.morozko.java.mod.doc.DocCell;
import org.morozko.java.mod.doc.DocContainer;
import org.morozko.java.mod.doc.DocFooter;
import org.morozko.java.mod.doc.DocHeader;
import org.morozko.java.mod.doc.DocHeaderFooter;
import org.morozko.java.mod.doc.DocHelper;
import org.morozko.java.mod.doc.DocImage;
import org.morozko.java.mod.doc.DocInfo;
import org.morozko.java.mod.doc.DocPageBreak;
import org.morozko.java.mod.doc.DocPara;
import org.morozko.java.mod.doc.DocPhrase;
import org.morozko.java.mod.doc.DocRow;
import org.morozko.java.mod.doc.DocTable;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NewDocContentHandler {//implements ContentHandler {
	
//	private static final String[] ELEMENT_CONTAINER = { "table", "row", "cell", "body", "meta", "metadata", "header", "footer", "header-ext", "footer-ext" };
//	
//	private static final List CONTAINER_LIST = Arrays.asList( ELEMENT_CONTAINER );
//	
//	private NewDocContext context;
//	
//	public NewDocContentHandler( DocHelper docHelper ) {
//		this.context = new NewDocContext();
//		this.context.setDocHelper( docHelper );
//	}
//	
//	public NewDocContentHandler() {
//		this( new DocHelper() );
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
//	 */
//	public void characters(char[] ch, int start, int length) throws SAXException {
//		String text = this.context.getDocHelper().filterText( new String( ch, start, length ) );
//		if ( text.trim().length() > 0 && this.context.getCurrentElement() instanceof DocPhrase ) {	
//			DocPhrase docPhrase = (DocPhrase)this.context.getCurrentElement();
//			docPhrase.setText( docPhrase.getText()+text );
//		} else if ( text.trim().length() > 0 && this.context.getCurrentElement() instanceof DocPara ) {
//			DocPara docPara = (DocPara)this.context.getCurrentElement();
//			docPara.setText( docPara.getText()+text );
//		} else if ( text.trim().length() > 0 && this.context.getCurrentElement() instanceof DocInfo ) {
//			DocInfo docInfo = (DocInfo)this.context.getCurrentElement();
//			docInfo.getContent().append( text );
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#endDocument()
//	 */
//	public void endDocument() throws SAXException {
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
//	 */
//	public void endPrefixMapping(String prefix) throws SAXException {
//		
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
//	 */
//	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
//		this.characters(ch, start, length);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
//	 */
//	public void processingInstruction(String target, String data) throws SAXException {
//		
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
//	 */
//	public void setDocumentLocator(Locator locator) {
//		
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
//	 */
//	public void skippedEntity(String name) throws SAXException {
//	
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#startDocument()
//	 */
//	public void startDocument() throws SAXException {
//		this.context.setParents( new LinkedList() );
//		this.context.setCurrentContainer( null );
//		this.context.setCurrentElement( null );
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
//	 */
//	public void endElement(String uri, String localName, String qName) throws SAXException {
//		if ( CONTAINER_LIST.contains( qName ) ) {
//			if ( !this.context.getParents().isEmpty() ) {
//				this.context.setCurrentContainer( (DocContainer)this.context.getParents().remove( this.context.getParents().size()-1 ) );	
//			} else {
//				this.context.setCurrentContainer( null );
//			}
//		}
//	}	
//	
//	private static int getAlign( String align ) {
//		int result = DocPara.ALIGN_UNSET;
//		if ( "center".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_CENTER;
//		} else if ( "right".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_RIGHT;
//		} else if ( "left".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_LEFT;
//		} else if ( "justify".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_JUSTIFY;
//		} else if ( "justifyall".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_JUSTIFY_ALL;
//		}
//		return result;
//	}	
//	
//	private static int getValign( String align ) {
//		int result = DocPara.ALIGN_UNSET;
//		if ( "middle".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_MIDDLE;
//		} else if ( "top".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_TOP;
//		} else if ( "bottom".equalsIgnoreCase( align ) ) {
//			result = DocPara.ALIGN_BOTTOM;
//		} 
//		return result;
//	}
//	
//	private static void handleHeaderFooter( DocHeaderFooter headerFooter, Properties atts ) {
//		String align = atts.getProperty( "align" );
//		headerFooter.setAlign( getAlign( align ) );
//		String numbered = atts.getProperty( "numbered" );
//		headerFooter.setNumbered( Boolean.valueOf( numbered ).booleanValue() );
//		String borderWidth = atts.getProperty( "border-width", "0" );
//		headerFooter.setBorderWidth( Integer.valueOf( borderWidth ).intValue() );
//	}
//	
//	private DocBorders createBorders( Properties atts ) {
//		DocBorders docBorders = new DocBorders();
//		docBorders.setBorderColorBottom( atts.getProperty( "border-color-bottom", atts.getProperty( "border-color" ) ) );
//		docBorders.setBorderColorTop( atts.getProperty( "border-color-top", atts.getProperty( "border-color" ) ) );
//		docBorders.setBorderColorLeft( atts.getProperty( "border-color-left", atts.getProperty( "border-color" ) ) );
//		docBorders.setBorderColorRight( atts.getProperty( "border-color-right", atts.getProperty( "border-color" ) ) );
//		docBorders.setBorderWidthBottom( Integer.parseInt( atts.getProperty( "border-width-bottom", atts.getProperty( "border-width", "-1" ) ) ) );
//		docBorders.setBorderWidthTop( Integer.parseInt( atts.getProperty( "border-width-top", atts.getProperty( "border-width", "-1" ) ) ) );
//		docBorders.setBorderWidthLeft( Integer.parseInt( atts.getProperty( "border-width-left", atts.getProperty( "border-width", "-1" ) ) ) );
//		docBorders.setBorderWidthRight( Integer.parseInt( atts.getProperty( "border-width-right", atts.getProperty( "border-width", "-1" ) ) ) );
//		docBorders.setPaddingBottom( Integer.parseInt( atts.getProperty( "padding-bottom", atts.getProperty( "padding", "-1" ) ) ) );
//		docBorders.setPaddingTop( Integer.parseInt( atts.getProperty( "padding-top", atts.getProperty( "padding", "-1" ) ) ) );
//		docBorders.setPaddingLeft( Integer.parseInt( atts.getProperty( "padding-left", atts.getProperty( "padding", "-1" ) ) ) );
//		docBorders.setPaddingRight( Integer.parseInt( atts.getProperty( "padding-right", atts.getProperty( "padding", "-1" ) ) ) );
//		return docBorders;
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
//	 */
//	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
//		Properties props =  new Properties();
//		for ( int k=0; k<atts.getLength(); k++ ) {
//			String key = atts.getQName( k );
//			String value = atts.getValue( k );
//			props.setProperty(key, value);
//		}
//		if ( "doc".equalsIgnoreCase( qName ) ) {
//			this.docBase = new DocBase();
//		} else if ( "meta".equalsIgnoreCase( qName ) || "metadata".equalsIgnoreCase( qName ) ) {
//			DocContainer docMeta = this.docBase.getDocMeta();
//			this.currentElement = docMeta;
//		} else if ( "info".equalsIgnoreCase( qName ) ) {
//			DocInfo docInfo = new DocInfo();
//			docInfo.setName( props.getProperty( "name" ) );
//			this.currentElement = docInfo;
//		} else if ( "header".equalsIgnoreCase( qName ) || "header-ext".equalsIgnoreCase( qName )  ) {
//			DocHeader docHeader = this.docBase.getDocHeader();
//			handleHeaderFooter( docHeader , props );
//			docHeader.setUseHeader( true );
//			if ( "header-ext".equalsIgnoreCase( qName ) ) {
//				docHeader.setBasic( false );
//			} else {
//				docHeader.setBasic( true );
//			}
//			this.currentElement = docHeader;		
//		} else if ( "footer".equalsIgnoreCase( qName ) || "footer-ext".equalsIgnoreCase( qName ) ) {
//			DocFooter docFooter = this.docBase.getDocFooter();
//			handleHeaderFooter( docFooter , props );
//			docFooter.setUseFooter( true );
//			if ( "footer-ext".equalsIgnoreCase( qName ) ) {
//				docFooter.setBasic( false );
//			} else {
//				docFooter.setBasic( true );
//			}
//			this.currentElement = docFooter;				
//		} else if ( "body".equalsIgnoreCase( qName ) ) {
//			DocContainer docBody = this.docBase.getDocBody();
//			this.currentElement = docBody;
//			Properties info = this.docBase.getInfo();
//		} else if ( "image".equalsIgnoreCase( qName ) ) {
//			DocImage docImage = new DocImage();
//			// setting paragraph style
//			String url = props.getProperty( "url" );
//			docImage.setUrl( url );
//			String scaling = props.getProperty( "scaling" );
//			if ( scaling != null ) {
//				docImage.setScaling( Integer.valueOf( scaling ) );	
//			} else {
//				docImage.setScaling( null );
//			}
//			this.currentElement = docImage;			
//		} else if ( "para".equalsIgnoreCase( qName ) ) {
//			DocPara docPara = new DocPara();
//			// setting paragraph style
//			String style = props.getProperty( "style" );
//			docPara.setStyle( DocPara.parseStyle( style ) );
//			// setting paragraph align
//			String align = props.getProperty( "align" );
//			docPara.setAlign( getAlign( align ) );
//			String fontName = props.getProperty(  "font-name" );
//			docPara.setFontName( fontName );
//			String leading = props.getProperty( "leading" );
//			docPara.setBackColor( props.getProperty( "back-color" ) );
//			docPara.setForeColor( props.getProperty( "fore-color" ) );
//			docPara.setType( props.getProperty( "type" ) );
//			if ( leading != null ) {
//				docPara.setLeading( Float.valueOf( leading ) );
//			}
//			// setting paragraph size
//			docPara.setSize( Integer.parseInt( props.getProperty( "size", "-1" ) ) );
//			String spaceBefore = props.getProperty( "space-before" );
//			String spaceAfter = props.getProperty( "space-after" );
//			if ( spaceBefore != null ) {
//				docPara.setSpaceBefore( Float.valueOf( spaceBefore ) );
//			}
//			if ( spaceAfter != null ) {
//				docPara.setSpaceAfter( Float.valueOf( spaceAfter ) );
//			}				
//			this.currentElement = docPara;
//		} else if ( "phrase".equalsIgnoreCase( qName ) ) {
//			DocPhrase docPhrase = new DocPhrase();
//			// setting phrase size
//			docPhrase.setSize( Integer.parseInt( props.getProperty( "size", "-1" ) ) );
//			// setting phrase style
//			String style = props.getProperty( "style" );
//			docPhrase.setStyle( DocPara.parseStyle( style ) );
//			//leading
//			String leading = props.getProperty( "leading" );
//			if ( leading != null ) {
//				docPhrase.setLeading( Float.valueOf( leading ) );
//			}
//			this.currentElement = docPhrase;			
//		} else if ( "table".equalsIgnoreCase( qName ) ) {
//			DocTable docTable = new DocTable();
//			docTable.setColumns( Integer.parseInt( props.getProperty( "columns" ) )  );
//			docTable.setWidth( Integer.parseInt( props.getProperty( "width", "-1" ) )  );
//			docTable.setBackColor( props.getProperty( "back-color" ) );
//			docTable.setForeColor( props.getProperty( "fore-color" ) ); 
//			String cols = props.getProperty( "colwidths" );
//			if ( cols != null ) {
//				String[] colsParsed = cols.split( ";" );
//				int[] withds = new int[colsParsed.length];
//				for ( int k=0; k<withds.length; k++ ) {
//					withds[k] = Integer.parseInt( colsParsed[k] );
//				}
//				docTable.setColWithds( withds );
//			}
//			String spaceBefore = props.getProperty( "space-before" );
//			String spaceAfter = props.getProperty( "space-after" );
//			if ( spaceBefore != null ) {
//				docTable.setSpaceBefore( Float.valueOf( spaceBefore ) );
//			}
//			if ( spaceAfter != null ) {
//				docTable.setSpaceAfter( Float.valueOf( spaceAfter ) );
//			}			
//			this.currentElement = docTable;
//		} else if ( "row".equalsIgnoreCase( qName ) ) {
//			DocRow docRow = new DocRow();
//			this.currentElement = docRow;
//		} else if ( "cell".equalsIgnoreCase( qName ) ) {
//			DocCell docCell = new DocCell();
//			docCell.setCSpan( Integer.parseInt( props.getProperty( "colspan", "1" ) ) );
//			docCell.setRSpan( Integer.parseInt( props.getProperty( "rowspan", "1" ) ) );
//			docCell.setBackColor( props.getProperty( "back-color" ) );
//			docCell.setForeColor( props.getProperty( "fore-color" ) );
//			docCell.setType( props.getProperty( "type" ) );
//			docCell.setHeader( "true".equalsIgnoreCase( props.getProperty( "header" ) ) );
//			// h align
//			String align = props.getProperty( "align" );		
//			docCell.setAlign( getAlign( align ) );
//			// v align
//			String valign = props.getProperty( "valign" );
//			docCell.setValign( getValign( valign ) );
//			docCell.setDocBorders( this.createBorders( props ) );
//			this.currentElement = docCell;
//		} else if ( "page-break".equalsIgnoreCase( qName ) ) {
//			this.currentElement = new DocPageBreak();
//		}
//		// processamenti finali
//		if ( this.currentContainer != null && this.currentContainer != this.currentElement ) {
//			this.currentContainer.addElement( this.currentElement );
//		}
//		if ( CONTAINER_LIST.contains( qName ) ) {
//			this.parents.add( this.currentContainer );
//			this.currentContainer = (DocContainer)this.currentElement;
//		}
//		// setting id
//		String id = props.getProperty( "id" );
//		if ( id != null ) {
//			this.docBase.setId(id, this.currentElement );
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
//	 */
//	public void startPrefixMapping(String prefix, String uri) throws SAXException {
//		
//	}
//
//	/**
//	 * @return the docBase
//	 */
//	public DocBase getDocBase() {
//		return docBase;
//	}
	
}
