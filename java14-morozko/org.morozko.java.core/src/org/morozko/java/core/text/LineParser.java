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
 * @(#)LineParser.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.text
 * @creation	: 14-feb-2005 10.30.02
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.text;

import java.util.Vector;


/**
 * <p>Liberia per il parsing di testo basata sulle linee.</p>
 *
 * <p>Per parsing basato su linee si intende che la linea corrente del testo
 * costituisce l'unica informazione necessaria al parsing.</p>
 *
 * <p>Per linea si intende una sequenza di caratteri che termina
 * con un 'a capo' e non ne contiene altri.</p>
 *
 * <p>NOTA : questa classe compie funzioni di parsing molto basilari,
 * ma proprio per questo piuttosto efficienti</p>
 *
 * @version 0.2.3 [2004.05.23]
 * @author  Matteo Franci a.k.a. TUX2
 * @since   OCL 0.1
 */
public class LineParser {

    /**
     * <p>Su ciascuna linea di testo elimina la porzione
     * a partire dal tag.</p>
     * 
     * @param text  il testo
     * @param tag   il marcatore
     * @return  il testo con i marcatori eliminati
     */
    public static String removeFrom(String text, String tag) {
        Vector lines = extLines(text);
        int k=0;
        while (k<lines.size()) {
            String currentLine = (String)lines.elementAt(k);
            int commentIndex = currentLine.indexOf(tag);
            if (commentIndex==0) {
                lines.removeElementAt(k);
            } else {
                if (commentIndex > 0)
                    lines.setElementAt(currentLine.substring(0,commentIndex),k);
                k++;
            }
        }
        return linesToText(vectToLines(lines));
    }

    /**
     * Su ciascuna linea tutto quanto si trova dopo il carattere
     * '#' viene considerato un commento e ignorato (vale a dire che non
     * viene riportato nel risultato).
     *
     * @param   text    il testo su cui eseguire il parsing
     * @return  il testo, senza commenti ('#')
     */
    public static String removeUnixComment(String text) {
        return removeFrom(text, "#");
    }

    /**
     * Su ciascuna linea tutto quanto si trova dopo il carattere
     * '//' viene considerato un commento e ignorato (vale a dire che non
     * viene riportato nel risultato).
     *
     * @param   text    il testo su cui eseguire il parsing
     * @return  il testo, senza commenti ('//')
     */
    public static String removeJavaComment(String text) {
        return removeFrom(text, "//");
    }

    /**
     * Su ciascuna linea tutto quanto si trova dopo il carattere
     * '--' viene considerato un commento e ignorato (vale a dire che non
     * viene riportato nel risultato).
     *
     * @param   text    il testo su cui eseguire il parsing
     * @return  il testo, senza commenti ('--')
     */
    public static String removeSQLComment(String text) {
        return removeFrom(text, "--");
    }

    /**
     * Elimina le linne vuote dal testo
     *
     * @param   text    il testo su cui eseguire il parsing
     * @return  il testo, senza commenti le linee vuote
     */
    public static String removeBlankLines(String text) {
        Vector lines = extLines(text);
        int k=0;
        while (k<lines.size())
            if ( ((String)lines.elementAt(k)).equals("") )
                lines.removeElementAt(k);
            else
                k++;
        return linesToText(vectToLines(lines));
    }

    /**
     * Estrae tutti i comandi separati da una ';' dal testo
     *
     * @param   text    il testo su cui eseguire il parsing
     * @return  i comandi che lo compongono
     */
    public static String[] parseCommands(String text) {
        Vector cmd = new Vector();
        String sep = ";";
        int index = text.indexOf(sep);
        while (index > -1) {
            cmd.addElement(text.substring(0,index));
            if (text.length()>=index)
                text = text.substring(index+1);
            else
                text="";
            index = text.indexOf(sep);
        }
        return vectToLines(cmd);
    }

    // converte un vettore di stringe in un array di stringhe
    public static String[] vectToLines(Vector lines) {
        String[] result = new String[lines.size()];
        for (int k=0; k<lines.size(); k++)
            result[k] = (String)lines.elementAt(k);
        return result;
    }

    // converte un isnieme di linee in un testo unico
    public static String linesToText(String[] lines) {
        String result = "";
        for (int k=0; k<lines.length; k++)
            result+= lines[k]+'\n';
        return result;
    }

    // estrae le linee che compongono una stringa in forma di array di stringhe
    public static Vector extLines(String text) {
        Vector lines = new Vector();
        int endLine = endLineIndex(text);
        while (endLine>-1 && text.length()>0) {
            lines.addElement(text.substring(0,endLine));
            if (text.length()>endLine)
                text = text.substring(endLine+1);
            else
                text = new String();
            endLine = endLineIndex(text);
        }
        return lines;
    }

    public static String vectToText(Vector lines) {
        return linesToText(vectToLines(lines));
    }

    public static String[] textToLines(String text) {
        return vectToLines(extLines(text));
    }


    // ***** campi privati *****

    // trova il prossimo indice di un carattere in una stringa (-1 se inesistente)
    private static int indexOf(String text, char c) { return text.indexOf(""+c); }

    // trova il prossimo indice di un carattere 'a capo' in una stringa (-1 se inesistente)
    private static int endLineIndex(String text) { return indexOf(text,'\n'); }

}
