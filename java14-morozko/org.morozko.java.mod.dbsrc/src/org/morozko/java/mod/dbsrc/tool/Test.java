package org.morozko.java.mod.dbsrc.tool;

import java.io.BufferedReader;
import java.io.FileReader;

import com.csvreader.CsvReader;

public class Test {

	public static void main( String[] args ) {
		try {
			FileReader fr = new FileReader( "M:\\devel\\java\\workspace-main\\test-dbsrc\\saniterm-sync\\saniterm-import.csv" );
			CsvReader reader = new CsvReader( fr );
			int count = 0;
			while ( reader.readRecord() ) {
				String raw = reader.getRawRecord();
				System.out.println( "raw : "+raw );
			}
			reader.close();
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
