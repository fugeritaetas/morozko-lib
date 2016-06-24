package org.morozko.java.mod.doc.itext;

import java.awt.Color;
import java.net.URL;
import java.util.Iterator;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.math.BinaryCalc;
import org.morozko.java.mod.doc.DocBase;
import org.morozko.java.mod.doc.DocBorders;
import org.morozko.java.mod.doc.DocCell;
import org.morozko.java.mod.doc.DocElement;
import org.morozko.java.mod.doc.DocFooter;
import org.morozko.java.mod.doc.DocHandler;
import org.morozko.java.mod.doc.DocHeader;
import org.morozko.java.mod.doc.DocHeaderFooter;
import org.morozko.java.mod.doc.DocImage;
import org.morozko.java.mod.doc.DocInfo;
import org.morozko.java.mod.doc.DocPageBreak;
import org.morozko.java.mod.doc.DocPara;
import org.morozko.java.mod.doc.DocPhrase;
import org.morozko.java.mod.doc.DocRow;
import org.morozko.java.mod.doc.DocStyle;
import org.morozko.java.mod.doc.DocTable;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.HtmlTags;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class ItextPdfDocHandler implements DocHandler {

	private static void setStyle( DocStyle parent, DocStyle current ) {
		if ( current.getBackColor() == null ) {
			current.setBackColor( parent.getBackColor() );
		}
		if ( current.getForeColor() == null ) {
			current.setForeColor( parent.getForeColor() );
		}
	}
	
	private static Color parseHtmlColor( String c ) {
		int r = (int)BinaryCalc.hexToLong( c.substring( 1, 3 ) );
		int g = (int)BinaryCalc.hexToLong( c.substring( 3, 5 ) );
		int b = (int)BinaryCalc.hexToLong( c.substring( 5, 7 ) );
		return new Color( r, g, b );
	}
	
	private Document document;
	
	private String docType;
	
	public final static String DOC_OUTPUT_HTML = "html";
	
	public final static String DOC_OUTPUT_PDF = "pdf";
	
	public final static String DOC_OUTPUT_RTF = "rtf";
	
	public ItextPdfDocHandler( Document document, String docType ) {
		this.document = document;
		this.docType = docType;
	}
	
	private static int getAlign( int align ) {
		int r = Element.ALIGN_LEFT;
		if ( align == DocPara.ALIGN_RIGHT ) {
			r = Element.ALIGN_RIGHT;
		} else if ( align == DocPara.ALIGN_CENTER ) {
			r = Element.ALIGN_CENTER;	
		}
		return r;
	}
	
	private Image createImage( DocImage docImage ) {
		Image image = null;
		String url = docImage.getUrl();
		try {
			image = Image.getInstance( new URL( url )  );
			if ( docImage.getScaling() != null ) {
				image.scalePercent( docImage.getScaling().floatValue() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}	
	
	private Phrase createPhrase( DocPhrase docPhrase ) {
		String text = docPhrase.getText();
		System.out.println( "TEST 1 :" +text );
		Phrase p = new Phrase( text, new Font( Font.HELVETICA, 10, 0 ) );
		return p;
	}	
	
	private Paragraph createPara( DocPara docPara ) {
		int style = docPara.getStyle();
		if ( style == DocPara.STYLE_BOLD ) {
			style = Font.BOLD;
		} else {
			style = Font.NORMAL;
		}
		String text = docPara.getText();
//		if ( DOC_OUTPUT_HTML.equals( this.docType ) ) {
//			int count = 0;
//			StringBuffer buffer = new StringBuffer();
//			while ( count < text.length() && text.indexOf( " " )==count ) {
//				count++;
//			}
//			buffer.append( text.substring( count ) );
//			text = buffer.toString();
//		}
		Paragraph p = new Paragraph( new Phrase( text, new Font( Font.HELVETICA, docPara.getSize(), style ) ) );
		if ( docPara.getForeColor() != null ) {
			Color c = parseHtmlColor( docPara.getForeColor() );
			Font f = new Font( Font.HELVETICA, docPara.getSize(), style, c );
			p = new Paragraph( new Phrase( text, f ) );
		}
		if ( docPara.getAlign() != DocPara.ALIGN_UNSET ) {
			p.setAlignment( getAlign( docPara.getAlign() ) );
		}
		return p;
	}
	
	private PdfPTable createTable( DocTable docTable ) throws Exception {
		PdfPTable table = new PdfPTable( docTable.getColumns() );
		table.setWidthPercentage( docTable.getWidth() );
		int[] cw = docTable.getColWithds();
		if (  cw != null ) {
			//System.out.println( "cw : "+cw );
			float[] w = new float[ cw.length ];
			for ( int k=0; k<w.length; k++ ) {
				w[k] = (float)((float)cw[k]/(float)100);
			}
			table.setWidths( w );
		}
		Iterator itRow = docTable.docElements();
		while ( itRow.hasNext() ) {
			DocRow docRow = (DocRow)itRow.next();
			Iterator itCell = docRow.docElements();
			while ( itCell.hasNext() ) {
				DocCell docCell = (DocCell)itCell.next();
				setStyle( docTable, docCell );
				PdfPCell cell = new PdfPCell();
				cell.setColspan( docCell.getCSpan() );
				//cell.setRowspan( docCell.getRSpan() );
				DocBorders docBorders = docCell.getDocBorders();
				if ( docBorders != null ) {
					if ( docBorders.getBorderColorBottom() != null ) {
						cell.setBorderColorBottom(  parseHtmlColor( docBorders.getBorderColorBottom() ) );
					}
					if ( docBorders.getBorderColorTop() != null ) {
						cell.setBorderColorTop(  parseHtmlColor( docBorders.getBorderColorTop() ) );
					}
					if ( docBorders.getBorderColorLeft() != null ) {
						cell.setBorderColorLeft(  parseHtmlColor( docBorders.getBorderColorLeft() ) );
					}
					if ( docBorders.getBorderColorRight() != null ) {
						cell.setBorderColorRight(  parseHtmlColor( docBorders.getBorderColorRight() ) );
					}
					if ( docBorders.getBorderWidthBottom() != -1 ) {
						cell.setBorderWidthBottom( docBorders.getBorderWidthBottom() );
					}
					if ( docBorders.getBorderWidthTop() != -1 ) {
						cell.setBorderWidthTop( docBorders.getBorderWidthTop() );
					}
					if ( docBorders.getBorderWidthLeft() != -1 ) {
						cell.setBorderWidthLeft( docBorders.getBorderWidthLeft() );
					}
					if ( docBorders.getBorderWidthRight() != -1 ) {
						cell.setBorderWidthRight( docBorders.getBorderWidthRight() );
					}
				}
				if ( docCell.getBackColor() != null ) {
					cell.setBackgroundColor( parseHtmlColor( docCell.getBackColor() ) );
				}
				if ( docCell.getAlign() != DocPara.ALIGN_UNSET ) {
					cell.setHorizontalAlignment( getAlign( docCell.getAlign() ) );
				}
				PdfPCellParent cellParent = new PdfPCellParent( cell );
				Iterator itCurrent = docCell.docElements();
				while ( itCurrent.hasNext() ) {
					DocElement docElement = (DocElement) itCurrent.next();
					if ( docElement instanceof DocPara ) {
						DocPara docPara = (DocPara)docElement;
						setStyle( docCell , docPara );
						if ( docCell.getAlign() != DocPara.ALIGN_UNSET ) {
							docPara.setAlign( docCell.getAlign() );
						}
						cellParent.add( createPara( docPara ) );
					} else if ( docElement instanceof DocPhrase ) {
						DocPhrase docPhrase = (DocPhrase)docElement;
						//setStyle( docCell , docPara );
						LogFacade.getLog().info( "phrase" );
						cellParent.add( createPhrase( docPhrase ) );						
					} else if ( docElement instanceof DocTable ) {
						LogFacade.getLog().debug( "nested table" );
						cell.addElement( createTable( (DocTable)docElement ) );
					} else if ( docElement instanceof DocImage ) {
						LogFacade.getLog().debug( "cell DocImage : "+docElement );
						cell.addElement( this.createImage( (DocImage)docElement) );
					}
				}
				table.addCell( cell );
			}
		}
		return table;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.doc.DocHandler#handleDoc(org.morozko.java.mod.doc.DocBase)
	 */
	public void handleDoc(DocBase docBase) throws Exception {
		Iterator itInfo = docBase.getDocMeta().docElements();
		while ( itInfo.hasNext() ) {
			DocElement docElement = (DocElement)itInfo.next();
			if ( docElement instanceof DocInfo ) {
				DocInfo docInfo = (DocInfo) docElement;
				if ( DOC_OUTPUT_HTML.equalsIgnoreCase( this.docType ) ) {
					if ( DocInfo.INFO_NAME_CSS_LINK.equalsIgnoreCase( docInfo.getName() ) ) {
						this.document.add( new Header( HtmlTags.STYLESHEET, docInfo.getContent().toString() ) );
					} else {
						this.document.add( new Header( HtmlTags.STYLE, docInfo.getContent().toString() ) );
					}				
				}
				if ( DOC_OUTPUT_PDF.equalsIgnoreCase( this.docType ) || DOC_OUTPUT_RTF.equalsIgnoreCase( this.docType ) ) {
					Rectangle size = this.document.getPageSize();
					if ( DocInfo.INFO_NAME_PAGE_ORIENT.equalsIgnoreCase( docInfo.getName() ) ) {
						if ( "horizontal".equalsIgnoreCase( docInfo.getContent().toString() ) ) {
							size = new Rectangle( size.getHeight(), size.getWidth() );
							this.document.setPageSize( size );
						}
					}
				}				
			}
		}		
		
		DocHeader docHeader = docBase.getDocHeader();
		if ( docHeader != null && docHeader.isUseHeader() ) {
			HeaderFooter header = this.createHeaderFoter( docHeader, docHeader.getAlign() );
			this.document.setHeader( header );
		}
		
		DocFooter docFooter = docBase.getDocFooter();
		if ( docFooter != null && docFooter.isUseFooter() ) {
			HeaderFooter footer = this.createHeaderFoter( docFooter, docFooter.getAlign() );
			this.document.setFooter( footer );
		}		
		
		this.document.open();
		
		DocumentParent documentParent = new DocumentParent( this.document );
		
		Iterator itDoc = docBase.getDocBody().docElements();
		while ( itDoc.hasNext() ) {
			DocElement docElement = (DocElement)itDoc.next();
			//System.out.println( "docElement "+docElement );
			if ( docElement instanceof DocPara ) {
				documentParent.add( createPara( (DocPara)docElement ) );
			} else if ( docElement instanceof DocTable ) {
				this.document.add( this.createTable( (DocTable)docElement ) );
			} else if ( docElement instanceof DocImage ) {
				LogFacade.getLog().debug( "body DocImage : "+docElement );
				documentParent.add( this.createImage( (DocImage)docElement ) );
			} else if ( docElement instanceof DocPageBreak ) {
				this.document.newPage();
			}
		}
		
		this.document.close();
	}
	
	private HeaderFooter createHeaderFoter( DocHeaderFooter container, int align ) throws Exception {
		Iterator it = container.docElements(); 
		Phrase phrase = new Phrase();
		while ( it.hasNext() ) {
			DocElement docElement = (DocElement)it.next();
			if ( docElement instanceof DocPara ) {
				DocPara docPara = (DocPara) docElement;
				Chunk ck = new Chunk( docPara.getText() );
				phrase.add( ck );
			} else if ( docElement instanceof DocImage ) {
				DocImage docImage = (DocImage)docElement;
				Image img = this.createImage( docImage );
				Chunk ck = new Chunk( img, 0, 0, true );
				phrase.add( ck );
			}
		}
		HeaderFooter headerFooter  = new HeaderFooter( phrase, container.isNumbered() );
		if ( align == DocPara.ALIGN_UNSET ) {
			align = DocPara.ALIGN_CENTER;
		}
		headerFooter.setAlignment( getAlign( align ) );
		//headerFooter.setUseVariableBorders( true );
		return headerFooter;
	}
	
}

class PdfPCellParent implements ParentElement {
	
	private PdfPCell cell;
	
	public PdfPCellParent( PdfPCell cell ) {
		this.cell = cell;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.doc.itext.ParentElement#add(com.lowagie.text.Element)
	 */
	public void add(Element element) throws Exception {
		this.cell.addElement( element );
	}
	
}