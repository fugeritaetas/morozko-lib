package org.morozko.java.mod.parser.ds.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.parser.ds.ParserFatalException;
import org.morozko.java.mod.parser.ds.ParserInput;
import org.morozko.java.mod.parser.ds.ParserOutput;
import org.morozko.java.mod.parser.ds.ProcessInput;
import org.morozko.java.mod.parser.ds.ProcessOutput;
import org.morozko.java.mod.parser.ds.RenderInput;
import org.morozko.java.mod.parser.ds.RenderOutput;
import org.morozko.java.mod.parser.ds.helpers.AbstractDataSource;
import org.morozko.java.mod.parser.facade.ConfigReader;
import org.w3c.dom.Element;

public class JdbcDataSource extends AbstractDataSource {

	public JdbcDataSource() {
		this.metadataMap = new HashMap<String, JdbcMetadata>();
	}
	
	private HashMap<String, JdbcMetadata> metadataMap;
	
	@Override
	public void configure(Element config) throws ParserFatalException {
		this.getLog().info( "config> start" );
		List<Element> metadataTagList = ConfigReader.SEARCH_DOM.findAllTags( config , "metadata" );
		Iterator<Element> metadataTagIt = metadataTagList.iterator();
		
		Properties configProps = DOMUtils.attributesToProperties( config );
		System.out.println( "configProps > "+configProps );
		ConnectionFactory cf = null;
		try {
			cf = ConnectionFactoryImpl.newInstance( configProps );
		} catch (Exception e) {
			throw new ParserFatalException( "cannot connect to data source", e );
		}
		 
		
		while ( metadataTagIt.hasNext() ) {
			Element currentMetadataTag = metadataTagIt.next();
			Properties currentMetadataProps = DOMUtils.attributesToProperties( currentMetadataTag );
			String currentMetadataId = currentMetadataProps.getProperty( "id" );
			this.getLog().info( "metadata : "+currentMetadataId );
			JdbcMetadata metadata = new JdbcMetadata();
			metadata.setId( currentMetadataId );
			metadata.setName( currentMetadataProps.getProperty( "metadata-name" ) );
			this.metadataMap.put( currentMetadataId , metadata );
			JdbcMetadata jdbcMetadata = metadata;
			jdbcMetadata.setId( currentMetadataId );
			Element currentSqlTag = ConfigReader.SEARCH_DOM.findTag( currentMetadataTag , "sql" );
			String query = ConfigReader.SEARCH_DOM.findText( currentSqlTag );
			try {
				Connection conn = cf.getConnection();
				jdbcMetadata.setConnectionFactory( cf );
				jdbcMetadata.setSql( query );
				JdbcRecordDescription jdbcRecord = new JdbcRecordDescription();
				jdbcRecord.setId( currentMetadataProps.getProperty( "record-id" ) );
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery( "SELECT v.* FROM ( "+query+" ) v WHERE  1=0" );
				ResultSetMetaData rsmd = rs.getMetaData();
				for ( int k = 0; k<rsmd.getColumnCount(); k++ ) {
					JdbcFieldDescription field = new JdbcFieldDescription();
					field.setId( rsmd.getColumnName( k+1 ) );
					jdbcRecord.getFieldDescriptionList().add( field );
				}
				jdbcMetadata.getRecordDescriptionList().add( jdbcRecord );
				rs.close();
				stm.close();
				conn.close();
			} catch (Exception e) {
				throw new ParserFatalException( "Cannto connect to data source" );
			}
		}
		this.getLog().info( "config> end" );
	}

	public JdbcMetadata getMetadata( String id ) {
		return this.metadataMap.get( id );
	}
	
	@Override
	public ParserOutput parse( ParserInput input ) throws ParserFatalException {
		JdbcRecordIterator pri = new JdbcRecordIterator( input, this );
		ParserOutput output = new ParserOutput( pri );
		return output;
	}

	@Override
	public ProcessOutput process(ProcessInput input) throws ParserFatalException {
		if ( true ) {
			throw new ParserFatalException( "Not implemented" );
		}
		return null;
	}

	@Override
	public RenderOutput render(RenderInput input) throws ParserFatalException {
		if ( true ) {
			throw new ParserFatalException( "Not implemented" );
		}
		return null;
	}	

}
