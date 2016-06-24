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
 * @(#)PropUtils.java
 *
 * @project	   : xfbclient
 * @package	   : it.finanze.sanita.simoss.lib.ui.swing.props
 * @creation   : 15-giu-2005 8.57.30
 */
package org.morozko.java.core.gui.props;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * <p>PropUtils fornisce funzioni di supporto per la gestione delle proprieta'.</p>
 *
 * @author thomas
 */
public class PropUtils {
    
    /**
     * Restituisce il componente swing relativo al tipo di proprieta' di configurazione
     * 
     * @param type Tipo della proprieta' di configurazione
     * @return Componente swing relativo al tipo
     */
    public static JComponent getComponentType(int type) {
       
        JComponent result =null;
        if (type == ConfProp.TYPE_INT) {
            result = new JLabel();    
        }
        if (type == ConfProp.TYPE_FILE) {
            final JButton button = new JButton("Scegli file");
            result = button;
        }
        if (type == ConfProp.TYPE_STRING){
            result = new JLabel();
        }
		if (type == ConfProp.TYPE_PASSWORD){
					result = new JLabel();
				}
        if (type == ConfProp.TYPE_BOOL) {
            result = new JLabel();
        }
        
        return result;
        
    }

    
    /**
     * <p>Crea una nuova istanza di PropUtils.</p>
     *
     * 
     */
    public PropUtils() {
        super();
    }

}
