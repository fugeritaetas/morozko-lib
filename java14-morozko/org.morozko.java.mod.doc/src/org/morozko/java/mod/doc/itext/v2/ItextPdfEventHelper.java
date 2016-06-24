package org.morozko.java.mod.doc.itext.v2;

import java.util.Iterator;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.doc.DocFooter;
import org.morozko.java.mod.doc.DocHeader;
import org.morozko.java.mod.doc.itext.ITextDocHandler;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * handler test1
 * 
 * @author mttfranci
 *
 */
public class ItextPdfEventHelper extends PdfPageEventHelper {
	
	private PdfContentByte pdfContentByte;
	
	public PdfContentByte getPdfContentByte() {
		return pdfContentByte;
	}

	public void setPdfContentByte(PdfContentByte pdfContentByte) {
		this.pdfContentByte = pdfContentByte;
	}

	public ItextPdfEventHelper( ITextHelper docHelper ) {
		this.docHelper = docHelper;
	}
	
	private DocHeader docHeader;
	
	private DocFooter docFooter;

	private ITextHelper docHelper;
	
	private int pageNumber;
	
	public void onCloseDocument(PdfWriter writer, Document doc) {
		this.onEndPage(writer, doc);
	}

	public void onEndPage( PdfWriter writer, Document doc ) {
		if ( this.getDocFooter() != null ) {
			try {
				float textBase = doc.bottom() - 50;
				this.pdfContentByte.setTextMatrix( (doc.right() / 2), textBase);
				int pn = this.pageNumber;
				this.docHelper.getParams().setProperty( ITextDocHandler.PARAM_PAGE_CURRENT , String.valueOf( pn ) );
				Iterator it = this.getDocFooter().docElements();
				int count = 0;
				while ( it.hasNext() ) {
					System.out.println( "test 1>>>"+it.next().getClass().getName()+" c:"+count+" -> "+pn );
					count++;
				}
				doc.add( new Phrase( "test aaaaaaaaaaaaaaaaaaaaaaaaaa "+pn ) );
				//ITextDocHandler.handleElements(doc, this.getDocFooter().docElements(), docHelper);
			} catch (Exception e) {
				LogFacade.getLog().error( "ITextDocHandler - PdfHelper.onEndPage : "+e );
				throw new RuntimeException( e );
			}
		}
	}

	
	
	public void onStartPage(PdfWriter writer, Document doc ) {
		this.pageNumber = writer.getPageNumber();
		if ( this.getDocHeader() != null ) {
			try {
				Iterator it = this.getDocHeader().docElements();
				while ( it.hasNext() ) {
					System.out.println( "test 2>>>"+it.next().getClass().getName() );	
				}
				this.docHelper.getParams().setProperty( ITextDocHandler.PARAM_PAGE_CURRENT , String.valueOf( writer.getPageNumber() ) );
				ITextDocHandler.handleElements(doc, this.getDocHeader().docElements(), docHelper );
			} catch (Exception e) {
				LogFacade.getLog().error( "ITextDocHandler - PdfHelper.onStartPage : "+e );
				throw new RuntimeException( e );
			}
		}
	}

	public DocHeader getDocHeader() {
		return docHeader;
	}

	public void setDocHeader(DocHeader docHeader) {
		this.docHeader = docHeader;
	}

	public DocFooter getDocFooter() {
		return docFooter;
	}

	public void setDocFooter(DocFooter docFooter) {
		this.docFooter = docFooter;
	}
	
}