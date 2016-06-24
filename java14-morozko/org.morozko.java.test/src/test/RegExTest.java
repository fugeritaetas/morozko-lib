package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegExTest {

	private static final SimpleDateFormat DF_MIRTEL_INPUT = new SimpleDateFormat("yyMMddHHmmSS");
	private static final SimpleDateFormat DF_MIRTEL_OUTPUT = new SimpleDateFormat("dd-MM-yyyy");

	public static String convertiData( String input ) throws ParseException {
		return DF_MIRTEL_OUTPUT.format( DF_MIRTEL_INPUT.parse( input ) );
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String input = "090212134629";

		try {
			
			String output = convertiData( input );
			
			System.out.println( "input  : "+input );
			System.out.println( "output : "+output );
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
