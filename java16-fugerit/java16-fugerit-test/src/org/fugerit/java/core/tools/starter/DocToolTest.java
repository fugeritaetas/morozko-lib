package org.fugerit.java.core.tools.starter;

import static org.junit.Assert.*;

import org.fugerit.java.core.tools.util.args.Arg;
import org.fugerit.java.core.tools.util.args.ArgList;
import org.junit.Test;

import test.helpers.JUnitTestHelper;

public class DocToolTest {

	private static final String OUTPUTDIR = "output/core/doc";
	
	private static final String[] XMLS = { "input/core/doc/example-01.xml" };
	private static final String[] FORMATS = { "pdf", "rtf", "xls" };
	
	private static ArgList newArgs( String format, String xml ) {
		ArgList argList = new ArgList();
		argList.add( new Arg( DocTool.ARG_FORMAT, format ) );
		argList.add( new Arg( DocTool.ARG_XML, xml ) );
		argList.add( new Arg( DocTool.ARG_OUTPUTDIR, OUTPUTDIR ) );
		return argList;
	}
	
	@Test
	public void testHandleDoc() {
		String xmlInput = null;
		try {
			for ( int i=0; i<XMLS.length; i++ ) {
				for ( int j=0; j<FORMATS.length; j++ ) {
					String xml = XMLS[i];
					String format = FORMATS[j];
					xmlInput = xml;
					DocTool.handleDoc( newArgs( format, xml ) );
					JUnitTestHelper.okMessage( xml+", "+format );
				}
			}
		} catch (Exception e) {
			JUnitTestHelper.getLogger().error( "Error on file : "+xmlInput, e );
			fail( JUnitTestHelper.failMessage( e.getMessage() ) );
		}
	}

}
