package org.morozko.java.mod.dbsrc.config.ds.xml;

import java.io.File;
import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.dbsrc.config.ds.csv.CSVColumn;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordIterator;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;
import org.w3c.dom.Element;

public class XmlRecordIterator extends BasicRecordIterator {

	private File file;
	
	private XmlFile xmlFile;
	
	private Element root;
	
	private List<Element> iterator;
	
	private int currentRow;
	
	public XmlRecordIterator( XmlFile xmlFile, File file ) {
		super( xmlFile.getDescriptor() );
		this.file = file;
		this.xmlFile = xmlFile;
		this.currentRow = -1;
	}
	
	public boolean end()  throws DbsrcException {
		boolean ok = true;
		try {
			
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public boolean hasNext()  throws DbsrcException {
		boolean ok = true;
		try {
			ok = ( this.currentRow < this.iterator.size() );
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public Record next()  throws DbsrcException {
		Field[] fl = null;
		try {
			SearchDOM search = SearchDOM.newInstance( true , true );
			LogFacade.getLog().debug( "XmlRecordIterator.next() "+this.currentRow );
			Element currentRow = this.iterator.get( this.currentRow );
			fl = new Field[ this.xmlFile.getDescriptor().length ];
			for ( int k=0; k<this.xmlFile.getDescriptor().length; k++ ) {
				CSVColumn currentCol = this.xmlFile.getDescriptor()[k];
				Element currentTag =  search.findTag( currentRow , currentCol.getName() );
				if ( currentTag == null ) {
					String def = this.xmlFile.getUnsafeDefault().get( currentCol.getName() );
					if ( def == null ) {
						throw ( new DbsrcException( "No value for column '"+currentCol.getName()+"'" ) );	
					} else {
						fl[k] = currentCol.getField( def );		
					}
					
				} else {
					String content = search.findText( currentTag );
					if ( content == null ) {
						content = "";
					}
					fl[k] = currentCol.getField( content );					
				}
			}
		} catch (Exception e) {
			String message = e.getMessage();
			throw ( new DbsrcException( message , e ) );
		} finally {
			this.currentRow++;
		}
		return Record.newRecord( fl, this.xmlFile.getDescriptor() );
	}

	public boolean start()  throws DbsrcException {
		boolean ok = true;
		try {
			this.root = DOMIO.loadDOMDoc( this.file ).getDocumentElement();
			SearchDOM search = SearchDOM.newInstance( true , true );
			this.iterator = search.findAllTags(root , this.xmlFile.getRecordTag() );
			this.currentRow = 0;
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	
}
