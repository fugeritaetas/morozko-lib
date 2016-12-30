package org.fugerit.java.core.util;

import static org.junit.Assert.fail;

import org.junit.Test;

import test.helpers.JUnitTestHelper;

public class BinaryCalcTest {

	@Test
	public void testHexToLong() {
		String input = "AA";
		input = "ff";
		long res = BinaryCalc.hexToLong( input );
		String logText = "BinaryCalc.testHexToLong() '"+input+"' to '"+res+"'";
		if ( res < 0 ) {
			JUnitTestHelper.failLog( logText );
			fail( JUnitTestHelper.failMessage( logText ) );
		} else {
			JUnitTestHelper.okLog( logText );
		}
	}

}
