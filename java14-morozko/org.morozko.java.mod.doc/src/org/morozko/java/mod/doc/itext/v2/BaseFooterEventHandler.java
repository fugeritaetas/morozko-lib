package org.morozko.java.mod.doc.itext.v2;

import java.io.IOException;

import org.morozko.java.mod.doc.itext.ITextDocHandler;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * handler test2
 * 
 * @author mttfranci
 *
 */
public class BaseFooterEventHandler extends PdfPageEventHelper {

	public BaseFooterEventHandler( ITextHelper docHelper ) {
		this.docHelper = docHelper;
	}
	
	protected BaseFont baseFont;
    private PdfTemplate totalPages;
    private float footerTextSize = 8f;
    private int pageNumberAlignment = Element.ALIGN_CENTER;
 
    private ITextHelper docHelper;
    
    private int currentPageNumber;

 
    public void onStartPage(PdfWriter writer, Document document) {
    	this.currentPageNumber = writer.getPageNumber();
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
        totalPages = writer.getDirectContent().createTemplate(100, 100);
        totalPages.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		//this.baseFont = ITextDocHandler.findFont( this.docHelper.getDefFontName() );
		//System.out.println( "BASE FONT "+this.docHelper.getDefFontName()+" - "+this.baseFont );
        try {
			this.baseFont = BaseFont.createFont( BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
 
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        
        String originalText = "Pagina "+this.currentPageNumber;
        String text = ITextDocHandler.createText( docHelper.getParams(), originalText );
 
        float textBase = document.bottom() - 20;
        float textSize = baseFont.getWidthPoint(text, footerTextSize);
         
        cb.beginText();
        cb.setFontAndSize(baseFont, footerTextSize);
        if(Element.ALIGN_CENTER == pageNumberAlignment) {
            cb.setTextMatrix((document.right() / 2), textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(totalPages, (document.right() / 2) + textSize, textBase);
        } else if(Element.ALIGN_LEFT == pageNumberAlignment) {
            cb.setTextMatrix(document.left(), textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(totalPages, document.left() + textSize, textBase);
        } else {
            float adjust = baseFont.getWidthPoint("0", footerTextSize);
            cb.setTextMatrix(document.right() - textSize - adjust, textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(totalPages, document.right() - adjust, textBase);
        }
        cb.restoreState();
    }
 
    public void onCloseDocument(PdfWriter writer, Document document) {
        totalPages.beginText();
        totalPages.setFontAndSize(baseFont, footerTextSize);
        totalPages.setTextMatrix(0, 0);
        totalPages.showText(String.valueOf(writer.getPageNumber() - 1));
        totalPages.endText();
    }
 
    public void setPageNumberAlignment(int pageNumberAlignment) {
        this.pageNumberAlignment = pageNumberAlignment;
    }
    
}