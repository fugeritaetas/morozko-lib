package org.morozko.java.mod.parser.facade;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.parser.ds.DataSource;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.filter.FilterChain;
import org.morozko.java.mod.parser.filter.RecordFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {

	public static final SearchDOM SEARCH_DOM = SearchDOM.newInstance( true , true );
	
	public static ParserConfig read( InputStream is ) throws ParserFatalException {
		ParserConfig config = new ParserConfig();
		try {
			
			Document doc = DOMIO.loadDOMDoc( is );
			
			Element dataSourceListTag = SEARCH_DOM.findTag( doc.getDocumentElement() , "data-source-list" );
			
			List<Element> dsTagList = SEARCH_DOM.findAllTags( dataSourceListTag , "data-source" );
			for ( int k=0; k<dsTagList.size(); k++ ) {
				Element currentTagDS = dsTagList.get( k );
				Properties dsProps = DOMUtils.attributesToProperties( currentTagDS );
				DataSource currentDS = (DataSource)ClassHelper.newInstance( dsProps.getProperty( "type" ) );
				currentDS.setId( dsProps.getProperty( "id" ) );
				currentDS.configure( currentTagDS );
				config.addDS( currentDS.getId() , currentDS );
			}

			Element filterListTag = SEARCH_DOM.findTag( doc.getDocumentElement() , "filter-list" );
			if ( filterListTag != null ) {
				List<Element> tagList = SEARCH_DOM.findAllTags( filterListTag , "filter" );
				for ( int k=0; k<tagList.size(); k++ ) {
					Element currentTag = tagList.get( k );
					Properties props = DOMUtils.attributesToProperties( currentTag );
					RecordFilter current = (RecordFilter)ClassHelper.newInstance( props.getProperty( "type" ) );
					config.getMapFilter().put( props.getProperty( "id" ) , current );
				}
			}
			
			Element filterChainListTag = SEARCH_DOM.findTag( doc.getDocumentElement() , "filter-chain-list" );
			if ( filterListTag != null ) {
				List<Element> tagList = SEARCH_DOM.findAllTags( filterChainListTag , "filter-chain" );
				for ( int k=0; k<tagList.size(); k++ ) {
					Element currentTag = tagList.get( k );
					Properties props = DOMUtils.attributesToProperties( currentTag );
					List<Element> tagList1 = SEARCH_DOM.findAllTags( currentTag , "filter" );
					FilterChain chain = new FilterChain( props.getProperty( "id" ) );
					for ( int i=0; i<tagList1.size(); i++ ) {
						Element currentTag1 = tagList1.get( i );
						Properties props1 = DOMUtils.attributesToProperties( currentTag1 );
						String filterId = props1.getProperty( "id" );
						RecordFilter filter = config.getMapFilter().get( filterId );
						if ( filter != null ) {
							chain.getListFilter().add( filter );
						} else {
							throw new Exception( "No filter with id : "+filterId );
						}
					}
					config.getMapChain().put( chain.getChainId() , chain );
				}
			}

		} catch (Exception e) {
			throw new ParserFatalException( e );
		}
		return config;
	}
	
}
