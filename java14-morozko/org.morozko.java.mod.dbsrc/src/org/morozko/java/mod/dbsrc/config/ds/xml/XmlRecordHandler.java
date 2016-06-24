package org.morozko.java.mod.dbsrc.config.ds.xml;

import java.io.File;
import java.io.FileOutputStream;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlRecordHandler extends BasicRecordHandler {

	public XmlRecordHandler( XmlFile file, File xmlFile, Document doc, Element root, ConfigTag config) {
		super( config );
		this.file = file;
		this.xmlFile = xmlFile;
		this.doc = doc;
		this.root = root;
		this.counter = 0;
	}
	
	private File xmlFile;
	
	private XmlFile file;
	
	private Document doc;
	
	private Element root;
	
	private int counter;
	
	@Override
	public int release() throws DbsrcException {
		try {
			FileOutputStream fos = new FileOutputStream( this.xmlFile );
			DOMIO.writeDOMIndent( this.root , fos );
			fos.close();
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return this.counter;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		Element currentRec = doc.createElement( "Fattura" );
		for ( int k=0; k<record.length(); k++ ) {
			Field f = record.getField( k );
			
			String current = "";
			if ( f.getData() != null ) {
				current = String.valueOf( f.getData() );
			}
			try {
				Element fTag = doc.createElement( this.file.getDescriptor()[k].getName() );
				fTag.appendChild( doc.createCDATASection( current ) );
				currentRec.appendChild( fTag );
			} catch (Exception e) {
				throw ( new DbsrcException( e ) );
			}
		}
		this.root.appendChild( currentRec );
		this.counter++;
	}

	
	
}
