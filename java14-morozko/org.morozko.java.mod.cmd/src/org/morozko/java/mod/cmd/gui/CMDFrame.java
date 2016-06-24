/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.cmd 

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
 * @(#)CMDFrame.java
 *
 * @project  : org.morozko.java.mod.cmd
 * @package  : org.morozko.java.mod.cmd.gui
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.cmd.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.core.io.line.helpers.JTextAreaLineWriter;
import org.morozko.java.core.lang.Op;
import org.morozko.java.core.xml.simple.gen.StreamXMLGenerator;
import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.CMDOutputFormat;
import org.morozko.java.mod.cmd.format.LineFormat;
import org.morozko.java.mod.cmd.format.TableCMDFormat;
import org.morozko.java.mod.cmd.format.XMLCMDOutputFormat;
import org.morozko.java.mod.cmd.helpers.CMDUtils;

/**
 * <p>Finestra che fa da interfaccia per un interprete di comando.</p>
 * 
 * <p>Supporta la possibilità di fare buffering e padding sull' output dei comandi
 * (entrambi abilitati per default).</p>
 * 
 * <p>Supporta formattazione tabulare o CSV dell' output.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDFrame extends JFrame implements WindowListener, ActionListener, KeyListener {

	public static final String VERSION = "0.3.2 [2005-09-18]";
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6839765328590277024L;

	/**
	 * <p>Restituisce il valore di separator.</p>
	 *
	 * @return il valore di separator.
	 */
	public String getSeparator() {
		return separator;
	}
	/**
	 * <p>Imposta il valore di separator.</p>
	 *
	 * @param separator il valore da impostare.
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public void append(String message) {
        this.outputArea.append(message);
    }
    
    private Op doOnExit;
    
    private CMD frameCMD;
    
    private JTextArea inputArea;
    private JTextArea outputArea;
    
    private JMenuItem saveOutputMI;
    
    private JMenuItem executeMI;
    private JMenuItem clearInputMI;
    private JMenuItem clearOutputMI;
    private JMenuItem clearAllMI;
    
    private JMenuItem helpMI;
    private JMenuItem infoMI;
    
    private JCheckBoxMenuItem paddedOutputMI;
    private JCheckBoxMenuItem bufferOutputMI;
    private JCheckBoxMenuItem noHeadOutputMI;
    private JCheckBoxMenuItem csvFormatMI;
    private JCheckBoxMenuItem htmlFormatMI;

    private String helpText;
    private String infoText;
    
    private JFileChooser fileChooser;
    
    /**
     * <p>Restituisce il valore di helpText.</p>
     * 
     * @return il valore di helpText.
     */
    public String getHelpText() {
        return helpText;
    }
    /**
     * <p>Imposta helpText.</p>
     * 
     * @param helpText il helpText da impostare.
     */
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
    /**
     * <p>Restituisce il valore di infoText.</p>
     * 
     * @return il valore di infoText.
     */
    public String getInfoText() {
        return infoText;
    }
    /**
     * <p>Imposta infoText.</p>
     * 
     * @param infoText il infoText da impostare.
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource()==executeMI) {
        	this.executeCommand();
        } else if (arg0.getSource()==clearInputMI) {
            this.clearInput();
        } else if (arg0.getSource()==clearOutputMI) {
            this.clearOutput();
        } else if (arg0.getSource()==clearAllMI) {
            this.clearAll();
        } else if (arg0.getSource()==helpMI) {
            this.outputArea.setText(this.getHelpText());
        } else if (arg0.getSource()==infoMI) {
            this.outputArea.setText(this.getInfoText());
        } else if (arg0.getSource()==infoMI) {
            this.fileChooser = new JFileChooser();
            this.fileChooser.setVisible(true);
            this.fileChooser.addActionListener(this);
        }
    }
    
    // action methods
    private void executeCommand() {
        String input = this.inputArea.getSelectedText();
        if ( input==null || input.equals("") ) {
        	input = this.inputArea.getText();
        }
        String[] cmdList = { input };
        if (this.separator != null) {
        	cmdList = input.split( this.separator );
        }
        for (int k=0; k<cmdList.length; k++) {
        	this.executeCommand( cmdList[k]);	
        }
    }
    private void executeCommand( String command ) {
        try {
            String input = command;
            CMDOutput output = this.frameCMD.handleCommand(input);
            if (this.bufferOutputMI.getState()) {
                output = CMDUtils.bufferedOutput(output);
            }
            if (this.paddedOutputMI.getState()) {
                output = CMDUtils.paddedOutput(output);
            }
            if (this.noHeadOutputMI.getState()) {
                output = CMDUtils.noHeadOutput(output);
            }                
            // output vero e proprio
            CMDOutputFormat format = null;
            LineWriter writer = new JTextAreaLineWriter(this.outputArea);
            if (this.csvFormatMI.getState()) {
                format = LineFormat.getFormatCSV(writer);
            } else if (this.htmlFormatMI.getState()) {
                format = new XMLCMDOutputFormat(new StreamXMLGenerator(writer));
            } else {
                format = new TableCMDFormat(writer);
            }
            format.printCMDOutput(output);
        } catch (CMDException ioe) {
            this.outputArea.append("Error : "+ioe.toString()+"\n");
        }    	
    }
    private void clearInput() {
    	this.inputArea.setText("");
    }
    private void clearOutput() {
    	this.outputArea.setText("");
    }
    private void clearAll() {
    	this.clearInput();
    	this.clearOutput();
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
     */
    public void windowActivated(WindowEvent arg0) {

    }
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
     */
    public void windowClosed(WindowEvent arg0) {
        this.windowClosing(arg0);
    }
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing(WindowEvent arg0) {
    	this.doOnExit.doOpSecure();
        this.dispose();
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
     */
    public void windowDeactivated(WindowEvent arg0) {
    }
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
     */
    public void windowDeiconified(WindowEvent arg0) {
    }
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
     */
    public void windowIconified(WindowEvent arg0) {
    }
    /* (non-Javadoc)
     * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
     */
    public void windowOpened(WindowEvent arg0) {
    }
    
    private void setComponent(Component c) {
        c.setFont(new Font("Courier", 0, 16));
        if (this.backColor!=null) {
        	c.setBackground(this.backColor);	
        }
        if (this.foreColor!=null) {
        	c.setForeground(this.foreColor);        	
        }
    }
    
    private Color backColor;
    private Color foreColor;
    
    private CMDFrame(String title, CMD cmd, Op doOnExit, Color bc, Color fc) {
        super(title);
        
        this.separator = ";";
        
        this.backColor = bc;
        this.foreColor = fc;
        
        this.helpText = "CMDFrame 0.1 by Matteo a.k.a. TUX2";
        this.infoText = this.helpText;
        
        this.doOnExit = doOnExit;

        // menu bar
        
        this.saveOutputMI = new JMenuItem("Save Output Buffer");
        this.saveOutputMI.addActionListener(this);
        JMenu fileJMenu = new JMenu("File");
        fileJMenu.add(saveOutputMI);
        
        this.bufferOutputMI = new JCheckBoxMenuItem("Buffered", true);
        this.paddedOutputMI = new JCheckBoxMenuItem("Padded", true);
        this.noHeadOutputMI = new JCheckBoxMenuItem("No Head", false);
        JMenu typeJMenu = new JMenu("Type");
        typeJMenu.add(this.bufferOutputMI);
        typeJMenu.add(this.paddedOutputMI);
        typeJMenu.add(this.noHeadOutputMI);
        this.bufferOutputMI.addActionListener(this);
        this.paddedOutputMI.addActionListener(this);
        this.noHeadOutputMI.addActionListener(this);
        this.csvFormatMI = new JCheckBoxMenuItem("CSV", false);
        this.htmlFormatMI = new JCheckBoxMenuItem("HTML", false);
        JMenu formatJMenu = new JMenu("Format");
        formatJMenu.add(this.csvFormatMI);
        formatJMenu.add(this.htmlFormatMI);
        this.csvFormatMI.addActionListener(this);
        JMenu settingsJMenu = new JMenu("Settings");
        settingsJMenu.add(typeJMenu);
        settingsJMenu.add(formatJMenu);       
        
        this.executeMI = new JMenuItem("Exceute" );
        this.executeMI.setMnemonic( KeyEvent.VK_E );
        this.executeMI.addActionListener(this);
        this.clearInputMI = new JMenuItem("Clear Input" );
        this.clearInputMI.setMnemonic( KeyEvent.VK_I );
        this.clearInputMI.addActionListener(this);
        this.clearOutputMI = new JMenuItem("Clear Output" );
        this.clearOutputMI.setMnemonic( KeyEvent.VK_O );
        this.clearOutputMI.addActionListener(this);
        this.clearAllMI = new JMenuItem("Clear All", KeyEvent.VK_D );
        this.clearAllMI.setMnemonic( KeyEvent.VK_D );
        this.clearAllMI.addActionListener(this);        
        JMenu actionJMenu = new JMenu( "Action" );
        actionJMenu.setMnemonic( KeyEvent.VK_A );
        actionJMenu.add(executeMI);
        actionJMenu.add(clearInputMI);
        actionJMenu.add(clearOutputMI);
        actionJMenu.add(clearAllMI);

        
        this.helpMI = new JMenuItem("Help");
        this.helpMI.addActionListener(this);
        this.infoMI = new JMenuItem("Info");
        this.infoMI.addActionListener(this);
        JMenu helpJMenu = new JMenu("?");
        helpJMenu.add(helpMI);
        helpJMenu.add(infoMI);

        JMenuBar menuBar = new JMenuBar();
        //menuBar.add(fileJMenu);
        menuBar.add(actionJMenu);
        menuBar.add(settingsJMenu);
        menuBar.add(helpJMenu);
        //menuBar.setHelpJMenu(helpJMenu);
        this.setJMenuBar(menuBar);
        
        // main panel
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.inputArea = new JTextArea();
        Cursor c = this.inputArea.getCursor();
        this.inputArea.setCursor(c);
        this.inputArea.addKeyListener( this );
        this.setComponent(inputArea);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        this.setComponent(inputScrollPane);
        this.outputArea = new JTextArea();
        this.outputArea.setEditable(false);
        this.setComponent(outputArea);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        this.setComponent(outputScrollPane);
        splitPane.setTopComponent(inputScrollPane);
        splitPane.setBottomComponent(outputScrollPane);
        
        JPanel mainPane = new JPanel(new GridLayout(1, 1));
        mainPane.add(splitPane);
        
        // layout
        this.getContentPane().setLayout(new GridLayout(1,1));
        this.getContentPane().add(mainPane);
        
        // final setting
        this.frameCMD = cmd;
        this.addWindowListener(this);
        this.setSize(640, 480);
        this.show();
        splitPane.setDividerLocation(0.4);
    }

    public static CMDFrame getInstance(String title, CMD cmd, Op doOnExit) {
        return getInstance(title, cmd, doOnExit, null, null);
    }
    
    private String separator;
    
    public static CMDFrame getInstance(String title, CMD cmd, Op doOnExit, Color bc, Color fc) {
        CMDFrame frame = new CMDFrame(title, cmd, doOnExit, bc, fc);
        return frame;
    }
    
    private boolean controlKey = false;
    
    private boolean altKey = false;
    
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		if ( kc == KeyEvent.VK_CONTROL ) {
			this.controlKey = true;
		} else if ( kc == KeyEvent.VK_ALT ) {
			this.altKey = true;
		} else if ( kc == KeyEvent.VK_E && this.controlKey ) {
			this.executeCommand();
		} else if ( kc == KeyEvent.VK_I && this.controlKey ) {
			this.clearInput();
		} else if ( kc == KeyEvent.VK_O && this.controlKey ) {
			this.clearOutput();
		} else if ( kc == KeyEvent.VK_D && this.controlKey ) {
			this.clearAll();
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		int kc = e.getKeyCode();
		if ( kc == KeyEvent.VK_CONTROL ) {
			this.controlKey = false;
		} else if ( kc == KeyEvent.VK_ALT ) {
			this.altKey = false;
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
		if ( this.altKey ) {
		
		}
	}
    
}

