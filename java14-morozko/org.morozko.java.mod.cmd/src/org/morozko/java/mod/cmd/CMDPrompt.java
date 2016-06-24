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
 * @(#)CMDPrompt.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 22-dic-2004 10.21.36
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.morozko.java.core.io.line.LineIOUtils;
import org.morozko.java.core.io.line.LineReader;
import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.mod.cmd.format.TableCMDFormat;
import org.morozko.java.mod.cmd.helpers.CMDWrapper;
import org.morozko.java.mod.cmd.helpers.PaddedCMDOutput;

/**
 * <p>Un prompt per la gestione di un CMD.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDPrompt extends CMDWrapper {

    private CMDOutputFormat format;
    
    public static final String DEF_PROMPT = ">> ";
    
    private LineReader inputHandler;
    
    private LineWriter outputHandler;

    private String prompt;
    
    public CMDPrompt(LineReader reader, LineWriter writer, CMD cmd, String prompt, CMDOutputFormat format) {
        super(cmd);
        this.inputHandler = reader;
        this.outputHandler = writer;
        this.prompt = prompt;
        this.format = format;
    }
    
    public CMDPrompt(LineReader reader, LineWriter writer, CMD cmd, String prompt) {
        this(reader, writer, cmd, prompt, new TableCMDFormat(writer));
    }
    
    public CMDPrompt(Reader reader, Writer writer, CMD cmd, String prompt) {
        this(LineIOUtils.createLineReader(reader),
                LineIOUtils.createLineWriter(writer),
                cmd, prompt);
    }
    
    public CMDPrompt(InputStream reader, OutputStream writer, CMD cmd, String prompt) {
        this(new InputStreamReader(reader), new OutputStreamWriter(writer), cmd, prompt);
    }

    public CMDPrompt(CMD cmd) {
        this(System.in, System.out, cmd, DEF_PROMPT);
    }
    
    private void printPrompt() {
        this.outputHandler.print(this.prompt);
    }
    
    public String readCommand() throws CMDException {
        String command = null;
        try {
            this.printPrompt();
            command = this.inputHandler.readLine();
        } catch (IOException e) {
            throw (new CMDException(e));
        }
        return command;
    }

    public void handleOutput(CMDOutput output) {
        //PaddedCMDOutput pad = new PaddedCMDOutput(output);
        try {
            this.format.printCMDOutput(new PaddedCMDOutput(output));
        } catch (CMDException e) {
            this.outputHandler.println("Error "+e);
        }
    }
    
    
    // funzioni spostate dentro l'interfaccia CMDOutputFormat
//    private void printSep(String[] row) {
//        String line = "+ ";
//        for (int k=0; k<row.length; k++) {
//           line+= StringUtils.pad("", row[k].length(), '-')+" +";
//        }
//        this.outputHandler.println(line);
//    }
//    
//    private void printHead(String[] row) {
//        printSep(row);
//        printRow(row);
//        printSep(row);
//    }
//    
//    private void printRow(String[] row) {
//        String line = "| ";
//        for (int k=0; k<row.length; k++) {
//           line+= row[k]+" |";
//        }
//        this.outputHandler.println(line);
//    }
    
    public void start(String exitCommand) {
        try {
            String command = this.readCommand();
            while (!command.equalsIgnoreCase(exitCommand)) {
                this.handleOutput(this.handleCommand(command));
                command = this.readCommand();
            }
        } catch (CMDException e) {
            this.outputHandler.println("Error "+e);
        }
    }
    
}
