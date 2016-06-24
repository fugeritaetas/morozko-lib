package org.morozko.java.mod.tools.mp3.change;

import java.io.File;

import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class ChangeForAndroid {

	private static Mp3TagChanger createTagChanger( MP3File mp3, String use ) {
		Mp3TagChanger tagChanger = null;
		if ( "id3v1".equalsIgnoreCase( use ) ) {
			ID3v1 tc = mp3.getID3v1Tag();
			tagChanger = Mp3TagChanger.newTagChanger( tc );
		} else {
			AbstractID3v2 tc = mp3.getID3v2Tag();
			//AbstractMP3Tag tc = mp3.getID3v2Tag();
			tagChanger = Mp3TagChanger.newTagChanger( tc );
		}
		return tagChanger;
	}
	
	public static void recurseDir( File folder, ArgList argList ) throws Exception {
		String dirFilter = argList.findArgValue( "dir-filter" );
		String remove = argList.findArgValue( "remove" );
		boolean removeWhiteSpace = "true".equals( argList.findArgValue( "remove_white_space" ) );
		boolean addTrackNumber = "true".equalsIgnoreCase( argList.findArgValue( "addTrackNumber" ) );
		File[] list = folder.listFiles();
		for ( int k=0; k<list.length; k++ ) {
			File current = list[k];
			if ( current.isDirectory() && ( dirFilter == null || current.getName().contains( dirFilter ) ) ) {
				recurseDir( current, argList );
			} else {
				
				if ( current.getName().indexOf( ".mp3" ) != -1 ) {
					System.out.println( "FILE : "+current.getCanonicalPath() );
					// remove from file name
					if ( remove != null && current.getName().indexOf( remove ) != -1 ) {
						String newFileName = current.getName().replace( remove , "" );
						File newFile = new File( folder, newFileName );
						System.out.println( "RENAMING TO : "+newFile.getCanonicalPath() );
						current.renameTo( newFile );
						current = newFile;
								
					}
					// remove white spaces
					if ( removeWhiteSpace ) {
						String newFileName = current.getName().replaceAll( " " , "_" );
						File newFile = new File( folder, newFileName );
						if ( !newFile.getName().equals( current.getName() ) ) {
							System.out.println( "RENAMING TO : "+newFile.getCanonicalPath() );
							current.renameTo( newFile );
							current = newFile;	
						}
					}
					
					// open as mp3
					MP3File mp3 = new MP3File( current );
					
					String useRead = argList.findArgValue( "read" );
					String useWrite = argList.findArgValue( "write" );
					
					if ( useRead != null ) {
						String trackNumber = null;
						String songTitle = null;
						boolean stateChange = false;
						
						
						Mp3TagChanger tagChangerRead = createTagChanger( mp3 , useRead );
						Mp3TagChanger tagChangerWrite = createTagChanger( mp3 , useWrite );
//						System.out.println( "tc read  : "+tagChangerRead );
//						System.out.println( "tc write : "+tagChangerWrite );
						
						trackNumber = tagChangerRead.getTrackNumber();
						songTitle = tagChangerRead.getSongTitle();
//						// add track number to title
						if ( addTrackNumber ) {
							try {
								trackNumber = trackNumber.trim();
								int tn = Integer.parseInt( trackNumber );
								trackNumber = ("00"+tn);
								trackNumber = trackNumber.substring( trackNumber.length()-2 );
								if ( tagChangerWrite.getSongTitle().startsWith( trackNumber+" - " ) ) {
									System.out.println( "Song title already starts with track number : "+trackNumber );
								} else {
									String newTitle = trackNumber+" - "+songTitle;
									newTitle = new String( newTitle.getBytes(), "UTF-8" );
									System.out.println( "adding track number to title : "+newTitle );
									tagChangerWrite.setSongTitle( newTitle );
									stateChange = true;	
								}
							} catch (NumberFormatException nfe) {
								System.out.println( "NO VALID track number : "+nfe+" ("+songTitle+")" );
							}						
						}
						
						if ( stateChange ) {
							tagChangerWrite.save( mp3 );
						} else {
							System.out.println( "track show "+trackNumber+" - "+songTitle.replace( " " , "" ) );
						}
					}
	
				}
				
			}
		}
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			File folder = new File( argList.findArgValue( "dir" ) );
			recurseDir( folder, argList );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

abstract class Mp3TagChanger {
	
	public abstract void setSongTitle( String title );
	
	public abstract String getSongTitle();
	
	public abstract void setTrackNumber( String tn );
	
	public abstract String getTrackNumber();

	public abstract void save( MP3File file ) throws Exception;
	
	public static Mp3TagChanger newTagChanger( ID3v1 tagChanger ) {
		return new Idv3v1TagChanger( tagChanger );
	}
	
//	public static Mp3TagChanger newTagChanger( AbstractMP3Tag tagChanger ) {
//		return new Mp3RealTagChanger( tagChanger );
//	}
//	
	
	public static Mp3TagChanger newTagChanger( AbstractID3v2 tagChanger ) {
		return new Idv3v2TagChanger( tagChanger );
	}
	
}

class Idv3v1TagChanger extends Mp3TagChanger {
	
	private ID3v1 tagChanger;

	public Idv3v1TagChanger(ID3v1 tagChanger) {
		super();
		this.tagChanger = tagChanger;
	}

	public String getSongTitle() {
		return tagChanger.getSongTitle();
	}

	public void setSongTitle(String arg0) {
		tagChanger.setSongTitle(arg0);
	}

	public String getTrackNumber() {
		return tagChanger.getTrackNumberOnAlbum();
	}

	public void setTrackNumber(String arg0) {
		tagChanger.setTrackNumberOnAlbum(arg0);
	}

	@Override
	public void save(MP3File file) throws Exception {
		System.out.println( "SAVING id3v1 tags for file : "+file.getFilenameTag() );
		file.setID3v1Tag( this.tagChanger );
		file.save();
	}
	
	
	
}

class Idv3v2TagChanger extends Mp3TagChanger {
	
	private AbstractID3v2 tagChanger;

	public Idv3v2TagChanger(AbstractID3v2 tagChanger) {
		super();
		this.tagChanger = tagChanger;
	}

	public String getSongTitle() {
		return tagChanger.getSongTitle();
	}

	public void setSongTitle(String arg0) {
		tagChanger.setSongTitle(arg0);
	}
	
	public String getTrackNumber() {
		return tagChanger.getTrackNumberOnAlbum();
	}

	public void setTrackNumber(String arg0) {
		tagChanger.setTrackNumberOnAlbum(arg0);
	}

	@Override
	public void save(MP3File file) throws Exception {
		file.save();
	}
	
}

class Mp3RealTagChanger extends Mp3TagChanger {
	
	private AbstractMP3Tag tagChanger;

	public Mp3RealTagChanger(AbstractMP3Tag tagChanger) {
		super();
		this.tagChanger = tagChanger;
	}

	public String getSongTitle() {
		return tagChanger.getSongTitle();
	}

	public void setSongTitle(String arg0) {
		tagChanger.setSongTitle(arg0);
	}
	
	public String getTrackNumber() {
		return tagChanger.getTrackNumberOnAlbum();
	}

	public void setTrackNumber(String arg0) {
		tagChanger.setTrackNumberOnAlbum(arg0);
	}

	@Override
	public void save(MP3File file) throws Exception {
		file.setID3v2Tag( this.tagChanger );
		file.save();
	}
	
}