/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)ResizeImage.java
 *
 * @project     : org.morozko.java.mod.tools
 * @package     : org.morozko.java.mod.tools.util.img
 * @creation	: 07/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.tools.util.img;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class ResizeImage {

	private static void resize( File file, int w, int h ) throws Exception {
		Image image = Toolkit.getDefaultToolkit().getImage( file.toURL() );
		ImageIcon icon = new ImageIcon( image.getScaledInstance( w , h, Image.SCALE_SMOOTH ) );
		BufferedImage bi = new BufferedImage( icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB );
        Graphics g = bi.getGraphics();
        g.drawImage( icon.getImage(), 0, 0, null);
        ImageIO.write(bi, "jpg", file);
	}
	
	private static void resize( File file, int p ) throws Exception {
		Image image = Toolkit.getDefaultToolkit().getImage( file.toURL() );
		ImageIcon icon = new ImageIcon( image );
		int w = icon.getIconWidth()*p/100;
		int h = icon.getIconHeight()*p/100;
		icon = new ImageIcon( image.getScaledInstance( w , h, Image.SCALE_SMOOTH ) );
		BufferedImage bi = new BufferedImage( icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB );
        Graphics g = bi.getGraphics();
        g.drawImage( icon.getImage(), 0, 0, null);
        ImageIO.write(bi, "jpg", file);
	}
	
	private static void resize( File file, String w, String h, String p ) throws Exception {
		if ( p != null ) {
			LogFacade.getLog().info( "RESIZING : "+file.getCanonicalPath() );
			resize( file, Integer.parseInt( p ) );
		} else {
			resize( file, Integer.parseInt( w ), Integer.parseInt( h ) );
		}
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			File file = new File( argList.findArgValue( "f" ) );
			String w = argList.findArgValue( "w" );
			String h = argList.findArgValue( "h" );
			String p = argList.findArgValue( "p" );
			if ( file.isFile() ) {
				resize( file , w, h , p );
			} else {
				File[] list = file.listFiles();
				for ( int k=0; k<list.length; k++ ) {
					resize( list[k] , w, h , p );	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
