/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)DialogUtils.java
 *
 * @project	   : xfbclient
 * @package	   : it.finanze.sanita.simoss.lib.ui.swing
 * @creation   : 14-giu-2005 8.38.07
 */
package org.morozko.java.core.gui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.morozko.java.core.gui.props.ConfProp;
import org.morozko.java.core.gui.props.PropMap;
import org.morozko.java.core.gui.props.PropUtils;
import org.morozko.java.core.lang.Op;

/**
 * <p>
 * DialogUtils fornisce un insieme di funzioni per la creazione di finestre di
 * dialogo in modo parametrico. Permette di definirne il tipo (modale o non
 * modale), la dimensione ed il contenuto.
 * </p>
 * 
 * @author thomas
 */
public class DialogUtils {

	/**
	 * Crea una finestra di dialogo appartenente al componente
	 * <code>frame</code> che visualizza il messaggio <code>message</code>,
	 * impostandone il titolo a <code>title </code>.
	 * 
	 * @param title
	 *            Titolo della finestra di dialogo.
	 * @param message
	 *            Messaggio visualizzato all'interno del dialogo.
	 * @param frame
	 *            Frame genitore del pannello.
	 * @return Il pannello di dialogo opportunamente formattato
	 */
	public static JDialog createDialog(String title, String message,
			JFrame frame) {
		return createDialog(frame.getSize().width / 2,
				frame.getSize().height / 2, title, message, frame);
	}

	/**
	 * Crea un pannello di dialogo di dimensioni definite <code>x </code>e
	 * <code>y </code>che mostra come messaggio l'eccezione <code>e</code>.
	 * <br>
	 * Il genitore del pannello e' il componente <code>frame</code>, il
	 * titolo viene impostato a <code>title</code>.
	 * 
	 * @param x
	 *            Larghezza del pannello
	 * @param y
	 *            Altezza del pannello
	 * @param title
	 *            Titolo del pannello
	 * @param e
	 *            Eccezione visualizzata all'interno del pannello
	 * @param frame
	 *            Frame genitore del pannello
	 * @return Il pannello opportunamente formattato
	 */
	public static JDialog createDialog(int x, int y, String title, Exception e,
			JFrame frame) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(baos, true);
		e.printStackTrace(stream);
		stream.flush();
		stream.close();
		String message = "Errore : \n" + e.toString() + "\n\n\nTraccia:\n"
				+ baos.toString();
		return createDialog(x, y, title, message, frame);
	}

	/**
	 * Crea una finestra di dialogo di dimensioni <code>x, y</code>, che
	 * visualizza il messaggio <code>message</code>. Il genitore della
	 * pannello e' il componente <code>frame</code>, il titolo viene
	 * impostato a <code>title.</code>
	 * 
	 * @param x
	 *            Larghezza della finestra
	 * @param y
	 *            Altezza della finestra
	 * @param title
	 *            Titolo della finestra
	 * @param message
	 *            Messaggio visualizzato
	 * @param frame
	 *            Genitore del pannello
	 * @return Il pannello opportunamente formattato.
	 */
	public static JDialog createDialog(int x, int y, String title,
			String message, JFrame frame) {
		JDialog dialog = new JDialog(frame, true);
		dialog.setTitle(title);
		dialog.setSize(x, y);
		JTextArea area = new JTextArea();
		area.setFont(new Font("Courier", 0, 12));
		area.setEditable(false);
		area.setText(message);
		dialog.getContentPane().add(area);
		dialog.setLocationRelativeTo(frame);
		return dialog;
	}

	// public static JDialog createProgressDialog (int x, int y, String
	// title,String message,JFrame frame) {
	// ProgressDialog dialog = new ProgressDialog(frame, message,x,y);
	// return dialog;
	// }
	//	
	/**
	 * Crea una finestra di dialogo formattandone il contenuto in base alle
	 * proprieta' definite dal PropMap <code>props</code>.
	 * 
	 * @param props
	 *            Insieme delle proprietà di configurazione del pannello di
	 *            dialogo
	 * @param frame
	 *            Genitore del pannello
	 * @return Il pannello opportunamente formattato.
	 */
	public static JDialog createDialog(PropMap props, JFrame frame) {
		return createDialog( props, frame, true, null );
	}
	
	public static JDialog createDialog(PropMap props, JFrame frame, boolean modal, Op doOnConfirm) {
		JDialog dialog = new JDialogProp2(frame, true, props, doOnConfirm);
		return dialog;
	}


}

// Modella un JDialog formattandolo in base alle proprietà definite da un
// oggetto di tipo PropMap
class JDialogProp2 extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6453051686988678961L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOK) {
			System.err.println(">>>>configurazione proxy avviata CIAO");
			this.propMap.setConfirm(true);

			// cambia il valore di propField[] a seconda del tipo di input
			// (JSpinner o JTextField)
			if (propMap != null) {
				for (int k = 0; k < propMap.getSize(); k++) {
					if (conf[k].getType() == ConfProp.TYPE_INT) {
						propField[k].setText(((JSpinner) propCompField[k]).getValue().toString());
					} else if (conf[k].getType() == ConfProp.TYPE_PASSWORD) {
						propField[k].setText( new String( ((JPasswordField) propCompField[k]).getPassword() ) );
					} else if (conf[k].getType() == ConfProp.TYPE_STRING) {						
						propField[k].setText(((JTextField) propCompField[k]).getText());
					} else if (conf[k].getType() == ConfProp.TYPE_FILE) {
						propField[k].setText(((JTextField) propCompField[k]).getText());
					}
					conf[k].setValue(this.propField[k].getText());
					System.err.println("nome conf>>>" + conf[k].getName());
					System.err.println("valore conf>>>" + conf[k].getValue());
					System.err.println("tipo conf>>>" + conf[k].getType());
				}
			}
			this.dispose();
			if (doOnConfirm!=null) {
				this.doOnConfirm.doOpSecure();
				System.out.println( "DO OK" );
			} else {
				System.out.println( "DO KO" );
			}
		}
		if (e.getSource() == buttonNO) {
			System.err.println(">>>>configurazione proxy annullata");
			this.propMap.setConfirm(false);
			this.dispose();
		}
		// cambia il valore di propField[] per il tipo di input File o Boolean
		if (propMap != null) {
			for (int k = 0; k < propMap.getSize(); k++) {
				// tipo: File
				if (e.getSource() == buttonDialog[k]) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser
							.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					fileChooser.showOpenDialog(rootPane);
					try {
						File selectedFile = fileChooser.getSelectedFile();
						System.err.println("[" + conf[k].getName() + "]"
								+ "NUOVO FILE " + conf[k].getValue());
						propField[k].setText(selectedFile.getAbsolutePath());
						((JTextField) propCompField[k]).setText(selectedFile
								.getAbsolutePath());
					} catch (NullPointerException exep) {
						conf[k].setValue(conf[k].getValue());
					}
				}
				// tipo: pulsante true
				if (e.getSource() == radiobutton[k][0]) {
					System.err.println("true radio button ");
					propField[k].setText(e.getActionCommand());
				}
				// tipo: pulsante false
				if (e.getSource() == radiobutton[k][1]) {
					System.err.println("false radio button");
					propField[k].setText(e.getActionCommand());
				}
			}
		}

	}

	// versione 2 campi aggiunti necessari alla creazione del dialog
	private PropMap propMap;

	private JButton[] buttonDialog;

	private ConfProp[] conf;

	private JComponent[] propCompField;

	private JRadioButton[][] radiobutton;

	//

	private JTextField[] propField;

	private JButton buttonOK;

	private JButton buttonNO;

	private Op doOnConfirm;
	
	//
	// Crea un JDialog formattandolo in base alle proprietà definite da PropMap
	public JDialogProp2(Frame owner, boolean modal, PropMap props, Op doOnConfirm) throws HeadlessException {
		
		super(owner, props.getName(), modal);
		
		this.doOnConfirm = doOnConfirm;
		
		this.propMap = props;
		this.buttonDialog = new JButton[propMap.getSize()];
		this.conf = new ConfProp[propMap.getSize()];
		this.propCompField = new JComponent[propMap.getSize()];
		this.radiobutton = new JRadioButton[propMap.getSize()][2];
		// pannello delle proprietà
		this.propField = new JTextField[propMap.getSize()];
		JPanel propsPanel = new JPanel(new BorderLayout());
		JPanel miniWPanel = new JPanel(new GridLayout(propMap.getSize(), 3));
		JPanel miniCPanel = new JPanel(new GridLayout(propMap.getSize(), 3));
		JPanel miniEPanel = new JPanel(new GridLayout(propMap.getSize(), 3));
		for (int k = 0; k < this.propMap.getSize(); k++) {
			conf[k] = (this.propMap.get((String) this.propMap.getKeys().get(k)));

			String key = conf[k].getName();
			String value = conf[k].getValue();
			int type = conf[k].getType();

			// componente proprietà di tipo intero
			if (type == ConfProp.TYPE_INT) {
				JSpinner spin = new JSpinner();
				spin.setValue(new Integer(value));
				propCompField[k] = spin;
				// componente proprietà di tipo boolean
			} else if (type == ConfProp.TYPE_BOOL) {
				JPanel radioPanel = new JPanel();
				ButtonGroup buttonGroup = new ButtonGroup();
				radiobutton[k][0] = new JRadioButton("true");
				radiobutton[k][0].setActionCommand("true");
				radiobutton[k][0].addActionListener(this);
				radiobutton[k][1] = new JRadioButton("false");
				radiobutton[k][1].setActionCommand("false");
				radiobutton[k][1].addActionListener(this);
				if ("true".equals(value)) {
					radiobutton[k][0].setSelected(true);
				} else
					radiobutton[k][1].setSelected(true);
				buttonGroup.add(radiobutton[k][0]);
				buttonGroup.add(radiobutton[k][1]);
				radioPanel.add(radiobutton[k][0]);
				radioPanel.add(radiobutton[k][1]);
				propCompField[k] = radioPanel;

				// componente di proprietà di tipo stringa o file
			} else if (type == ConfProp.TYPE_PASSWORD ) {
				propCompField[k] = new JPasswordField( value );
			} else {
				propCompField[k] = new JTextField(value);
			}

			// imposta i valori della configurazione iniziale
			this.propField[k] = new JTextField();
			this.propField[k].setText(value);

			// bottoni per i componenti di tipo file
			JComponent comp = PropUtils.getComponentType(type);
			if (type == ConfProp.TYPE_FILE) {
				buttonDialog[k] = (JButton) comp;
				buttonDialog[k].addActionListener(this);
			}

			System.err.println( "CONF "+type+" : "+propCompField[k] );

			miniWPanel.add(new JLabel(key + " : ", JLabel.RIGHT));
			miniCPanel.add(this.propCompField[k]);
			miniEPanel.add( comp );

			propsPanel.add(BorderLayout.EAST, miniEPanel);
			propsPanel.add(BorderLayout.CENTER, miniCPanel);
			propsPanel.add(BorderLayout.WEST, miniWPanel);
		}

		this.setSize(300, 30 * (this.propMap.getSize()+1));
		this.setLocationRelativeTo(owner);

		// pannello dei bottoni
		this.buttonOK = new JButton("Conferma");
		this.buttonOK.addActionListener(this);
		this.buttonNO = new JButton("Annulla");
		this.buttonNO.addActionListener(this);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(this.buttonOK);
		buttonPanel.add(this.buttonNO);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.CENTER, propsPanel);
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

	}
	//

}