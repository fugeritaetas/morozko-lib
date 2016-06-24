package org.morozko.java.mod.comment.tld;

import java.io.PrintStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.comment.tld.model.AttributeModel;
import org.morozko.java.mod.comment.tld.model.DefinitionModel;
import org.morozko.java.mod.comment.tld.model.TagModel;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TldParser {

	public static void generateCommentHTML( PrintStream out, String title, DefinitionModel definitionModel ) {
		
		out.println( "<html><head><title>"+title+"</title>" );
		
		out.println( "<style type='text/css'>" );
		out.println( "<!--" );
		out.println( "body { font-family: Arial }" );
		out.println( "table.commentTLD { width: 100%; }" );
		out.println( "-->" );
		out.println( "</style>" );
		
		out.println( "</head><body>" );
		
		out.println( "<table class='commentTLD'>" );
		out.println( "<tr><th>TLD NAME: "+title+"</th></tr>" );
		out.println( "<tr><td>tlibversion</td><td>"+definitionModel.getTlibversion()+"</td></tr>" );
		out.println( "<tr><td>jspversion</td><td>"+definitionModel.getJspversion()+"</td></tr>" );
		out.println( "<tr><td>shortname</td><td>"+definitionModel.getShortname()+"</td></tr>" );
		out.println( "<tr><td>uri</td><td>"+definitionModel.getUri()+"</td></tr>" );
		out.println( "</table>" );
		
		Iterator itTag = definitionModel.getTagList().iterator();
		while ( itTag.hasNext() ) {
			TagModel tagModel = (TagModel) itTag.next();
			out.println( "<table class='commentTLD'>" );
			out.println( "<tr><th>tag: "+tagModel.getName()+"</th></tr>" );
			out.println( "<tr><td>tagclass</td><td>"+tagModel.getTagclass()+"</td></tr>" );
			out.println( "<tr><td>bodycontent</td><td>"+tagModel.getBodycontent()+"</td></tr>" );
			out.println( "</table>" );
			
		}
		
		out.println( "</body>" );
		
	}
	
	public static String parseComment( Element tag ) {
		String comment = "";
		NodeList nodeList = tag.getChildNodes();
		for ( int k=0; k<nodeList.getLength() && comment.length() == 0; k++ ) {
			Node node = nodeList.item( k );
			if ( node.getNodeType() == Node.COMMENT_NODE ) {
				Comment c = (Comment)node;
				comment+= c.getData();
			}
		}
		return comment;
	}
	
	public static DefinitionModel parse( Reader r ) throws Exception {
		DefinitionModel definitionModel = new DefinitionModel();
		System.out.println( "parse start 1" );
		SearchDOM searchDOM = SearchDOM.newInstance( true , true );
		System.out.println( "parse start 2" );
		Document doc = DOMIO.loadDOMDoc( r );
		System.out.println( "parse start 3" );
		Element root = doc.getDocumentElement();
		System.out.println( "parse start 4" );
		
		// elementi base
		Element tlibversionTag = searchDOM.findTag( root , "tlibversion" );
		Element jspversionTag = searchDOM.findTag( root , "jspversion" );
		Element shortnameTag = searchDOM.findTag( root , "shortname" );
		Element uriTag = searchDOM.findTag( root , "uri" );
		definitionModel.setTlibversion( searchDOM.findText( tlibversionTag ) );
		definitionModel.setJspversion( searchDOM.findText( jspversionTag ) );
		definitionModel.setShortname( searchDOM.findText( shortnameTag ) );
		definitionModel.setUri( searchDOM.findText( uriTag ) );
		
		definitionModel.setComment( parseComment( root ) );
		
		List tagList = searchDOM.findAllTags( root , "tag" );
		Iterator tagIt = tagList.iterator();
		while ( tagIt.hasNext() ) {
			Element currentTag = (Element) tagIt.next();
			TagModel tagModel = new TagModel();
		
			tagModel.setComment( parseComment( currentTag ) );
			
			tagModel.setName( searchDOM.findText( searchDOM.findTag( currentTag , "name" ) ) );
			tagModel.setTagclass( searchDOM.findText( searchDOM.findTag( currentTag , "tagclass" ) ) );
			tagModel.setBodycontent( searchDOM.findText( searchDOM.findTag( currentTag , "bodycontent" ) ) );
			
			List attributeList = searchDOM.findAllTags( root , "attribute" );
			Iterator attributeIt = attributeList.iterator();
			while ( attributeIt.hasNext() ) {
				Element currentAttribute = (Element) attributeIt.next();
				AttributeModel attributeModel = new AttributeModel();
				
				attributeModel.setComment( parseComment( currentAttribute ) );
				
				attributeModel.setName( searchDOM.findText( searchDOM.findTag( currentAttribute , "name" ) ) );
				attributeModel.setRequired( searchDOM.findText( searchDOM.findTag( currentAttribute , "required" ) ) );
				attributeModel.setRtexprvalue( searchDOM.findText( searchDOM.findTag( currentAttribute , "rtexprvalue" ) ) );
				
				tagModel.getAttributeList().add( attributeModel );
				
			}
			
			definitionModel.getTagList().add( tagModel );
		}
		
		return definitionModel;
	}
	
}
