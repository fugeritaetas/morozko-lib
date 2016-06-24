/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

	Copyright (c) 2006 Morozko

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
 * @(#)DBExFrame.java
 *
 * @project  : org.morozko.java.mod.tools.dbex
 * @package  : org.morozko.java.mod.tools.dbex.gui
 * @creation : 14-mar-2006
 */
package org.morozko.java.mod.tools.dbex.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DBExFrame extends JFrame implements WindowListener, ActionListener {

	private void log( String line ) {
		this.outputArea.append( line+"\n" );
	}
	
	private final static String TITLE = "DBExtractor v. 0.1 (Matteo Franci)";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2428493087711835635L;

	private JTextField textFieldFileOutput;
	
	private JTextField textFieldFileDriver;
	
	private JTextField textFieldDBUrl;
	
	private JTextField textFieldDBUsr;
	
	private JTextField textFieldDBPwd;

	private JTextField textFieldDBDrv;
	
	private JTextField textFieldTable;
	
	private JMenuItem chooseDriverJar;
	
	private JMenuItem exitMenu;
	
	private DBExtFramePrefs prefs;
	
	private JTextArea outputArea;
	
	private JButton exportButton;
	
	public DBExFrame() {
		super( TITLE );
		
		prefs = new DBExtFramePrefs();
		prefs.load();
		
		// pannell intestazione
		JLabel header = new JLabel( TITLE, JLabel.CENTER );
		
		// barra dei menu
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu( "File" );
		this.chooseDriverJar = new JMenuItem( "Selezione jar del driver" );
		this.chooseDriverJar.addActionListener( this );
		this.exitMenu = new JMenuItem( "Exit" );
		this.exitMenu.addActionListener( this );
		fileMenu.add( this.chooseDriverJar );
		fileMenu.add( this.exitMenu );
		menuBar.add( fileMenu );
		
		// pannello delle proprietà di connessione al db
		int cols = 60;
		JPanel dbPanel = new JPanel( new BorderLayout() );
		// etichette
		JPanel dbPanel1 = new JPanel( new GridLayout( 7, 1 ) );
		dbPanel1.add( new JLabel( "jar: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "driver: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "url: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "user: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "password: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "file output: " , JLabel.RIGHT ) );
		dbPanel1.add( new JLabel( "tabella da esportare: " , JLabel.RIGHT ) );
		dbPanel.add( dbPanel1, BorderLayout.WEST );
		// aree di testo
		JPanel dbPanel2 = new JPanel( new GridLayout( 7, 1 ) );
		this.textFieldFileDriver = new JTextField( this.prefs.getFileDrv(), cols );
		dbPanel2.add( this.textFieldFileDriver );
		this.textFieldDBDrv = new JTextField( this.prefs.getDBDrv(), cols );
		dbPanel2.add( this.textFieldDBDrv );
		this.textFieldDBUrl = new JTextField( this.prefs.getDBUrl(), cols );
		dbPanel2.add( this.textFieldDBUrl );
		this.textFieldDBUsr = new JTextField( this.prefs.getDBUsr(), cols );
		dbPanel2.add( this.textFieldDBUsr );
		this.textFieldDBPwd = new JTextField( this.prefs.getDBPwd(), cols );
		dbPanel2.add( this.textFieldDBPwd );
		this.textFieldFileOutput = new JTextField( this.prefs.getFileOutput(), cols );
		dbPanel2.add( this.textFieldFileOutput );
		this.textFieldTable = new JTextField( "" , cols );
		dbPanel2.add( this.textFieldTable );
		dbPanel.add( dbPanel2, BorderLayout.CENTER );
		// bottone di estrazione
		this.exportButton = new JButton( "Estrai!" );
		this.exportButton.addActionListener( this );
		dbPanel.add( this.exportButton, BorderLayout.SOUTH );
		 
		JPanel inputPanel = new JPanel( new FlowLayout() );
		inputPanel.add( dbPanel );
		
		JPanel outputPanel = new JPanel( new GridLayout( 1, 1 ) );
		this.outputArea = new JTextArea();
		JScrollPane outputScroll = new JScrollPane( this.outputArea );
		outputPanel.add( outputScroll );
		
		// pannello principale
		JPanel mainPanel = new JPanel( new GridLayout( 2, 1 ) );
		mainPanel.add( inputPanel );
		mainPanel.add( outputPanel );
		
		// la finestra
		Container cp = this.getContentPane();
		cp.setLayout( new BorderLayout() );
		cp.add( header, BorderLayout.NORTH );
		cp.add( mainPanel, BorderLayout.CENTER );
		
		// ultime proprietà
		this.setJMenuBar( menuBar );
		this.addWindowListener( this );
		this.setSize(800, 600 );
		this.setResizable( false );
		this.setVisible( true );
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	public void windowActivated(WindowEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	public void windowClosed(WindowEvent e) {
		this.handleExit();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e) {
		this.windowClosed( e );
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	public void windowDeactivated(WindowEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	public void windowDeiconified(WindowEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	public void windowIconified(WindowEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	public void windowOpened(WindowEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if ( src == this.chooseDriverJar ) {
			this.handleChooseJar();
		} else if ( src == this.exportButton ) {
			this.handleExport();
		} else if ( src == this.exitMenu ) {
			this.handleExit();
		}
		this.savePrefs();
	}

	private static String prepareString( String v ) {
		return v.replaceAll( "'", "''" );
	}
	
	private static String addCurrent( ResultSet rs, ResultSetMetaData rsmd, int index ) throws SQLException {
		String current = "";
		int type = rsmd.getColumnType( index );
		Object obj = rs.getObject( index );
		if ( obj == null ) {
			current = "null";
		} else if ( type == Types.VARCHAR || type == Types.CHAR || type == Types.DATE || type == Types.TIME || type == Types.TIMESTAMP ){
			current = "'"+prepareString( obj.toString() )+"'";
		} else {
			current =  obj.toString();
		}
		return current;
	}	
	
	private void handleExport() {
		String table = this.textFieldTable.getText();
		this.log( "Inizio l'estrazione della tabella : "+table );
		Connection conn = null;
		
		try {
			
			URL[] url = { ( new File( this.textFieldFileDriver.getText() ) ).toURL() };
			
			ClassLoader cl = new URLClassLoader( url );
			
			Class c = cl.loadClass( this.textFieldDBDrv.getText() );
			
			Driver d = (Driver)c.newInstance();
			
			DriverManager.registerDriver( d );
			
			this.log( "Driver caricato : "+d.getClass().getName()+" "+d.getMajorVersion()+"."+d.getMinorVersion() );
			
			PrintWriter pw = new PrintWriter( new FileWriter( new File( this.textFieldFileOutput.getText()  ) ) );
			
			String sql = " SELECT * FROM "+table;
			
			Properties info = new Properties();
			info.setProperty( "user", this.textFieldDBUsr.getText() );
			info.setProperty( "password", this.textFieldDBPwd.getText() );
			
			conn = d.connect( this.textFieldDBUrl.getText(), info );
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int cols = rsmd.getColumnCount();
			String basic = " INSERT INTO "+table+" ( ";
			for ( int k=1; k<cols; k++ ) {
				basic+= rsmd.getColumnName( k )+" ,";
			}
			basic+= rsmd.getColumnName( cols )+" ) VALUES ( ";			
			
			while ( rs.next() ) {
				String current = basic;
				for ( int k=1; k<cols; k++ ) {
					current+= addCurrent( rs, rsmd, k )+" , ";
				}
				current+= addCurrent( rs, rsmd, cols )+" ); ";
				pw.println( current );
			}
			
			stm.close();
			conn.close();
			
			pw.close();
			
			this.log( "Tutto OK!" );
		} catch (Exception e) {
			this.log( "Errore : "+e );
			e.printStackTrace();
		} finally {
			if ( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		this.log( "Fine estrazione!" );
		this.log( "" );
		this.log( "" );
	}
	
	private void savePrefs() {
		this.prefs.setDBDrv( this.textFieldDBDrv.getText() );
		this.prefs.setDBUrl( this.textFieldDBUrl.getText() );
		this.prefs.setDBUsr( this.textFieldDBUsr.getText() );
		this.prefs.setDBPwd( this.textFieldDBPwd.getText() );
		this.prefs.setFileOutput( this.textFieldFileOutput.getText() );
		this.prefs.setFileDrv( this.textFieldFileDriver.getText() );
		this.prefs.save();
	}
	
	private void handleExit() {
		System.exit( 0 );
	}
	
	private void handleChooseJar() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog( this );
		File selectedFile = fileChooser.getSelectedFile();
		if ( selectedFile != null ) {
			this.textFieldFileDriver.setText( selectedFile.getAbsolutePath() );
		}
	}
	
	public static void main( String[] args ) {
		new DBExFrame();
	}

}

class DBExtFramePrefs {
	
	private final static File DEF_PREF = new File( System.getProperty( "user.home" ), "dbexgui.pref" );

	public boolean save() {
		return this.save( DEF_PREF );
	}	
	
	public boolean load() {
		return this.load( DEF_PREF );
	}
	
	public boolean load( File file ) {
		boolean load = true;
		try {
			FileInputStream fis = new FileInputStream( file );
			this.props.load( fis );
			fis.close();
		} catch (Exception e) {
			load = false;
			e.printStackTrace();
		}
		return load;
	}
	
	public boolean save( File file ) {
		boolean save = true;
		try {
			FileOutputStream fos = new FileOutputStream( file );
			this.props.store( fos, "" );
			fos.close();
		} catch (Exception e) {
			save = false;
			e.printStackTrace();
		}
		return save;
	}	
	
	public DBExtFramePrefs() {
		this.props = new Properties();
	}

	public void setDBDrv( String p  ) {
		this.props.setProperty( PROP_DB_DRV, p );
	}
	
	public void setDBUrl( String p  ) {
		this.props.setProperty( PROP_DB_URL, p );
	}
	
	public void setDBUsr( String p  ) {
		this.props.setProperty( PROP_DB_USR, p );
	}
	
	public void setDBPwd( String p  ) {
		this.props.setProperty( PROP_DB_PWD, p );
	}	
	
	public void setFileDrv( String p  ) {
		this.props.setProperty( PROP_FILE_DRV, p );
	}		
	
	public void setFileOutput( String p  ) {
		this.props.setProperty( PROP_FILE_OUTPUT, p );
	}		
	
	public String getFileOutput() {
		return this.props.getProperty( PROP_FILE_OUTPUT, "" );
	}		
	
	public String getFileDrv() {
		return this.props.getProperty( PROP_FILE_DRV, "" );
	}	
	
	public String getDBDrv() {
		return this.props.getProperty( PROP_DB_DRV, "" );
	}
	
	public String getDBUrl() {
		return this.props.getProperty( PROP_DB_URL, "" );
	}
	
	public String getDBUsr() {
		return this.props.getProperty( PROP_DB_USR, "" );
	}	
	
	public String getDBPwd() {
		return this.props.getProperty( PROP_DB_PWD, "" );
	}
	
	private Properties props;
	
	private static final String PROP_DB_URL = "db.url";
	private static final String PROP_DB_USR = "db.usr";
	private static final String PROP_DB_PWD = "db.pwd";
	private static final String PROP_DB_DRV = "db.drv";
	
	private static final String PROP_FILE_OUTPUT = "file.out";
	private static final String PROP_FILE_DRV = "file.drv";
	
}

