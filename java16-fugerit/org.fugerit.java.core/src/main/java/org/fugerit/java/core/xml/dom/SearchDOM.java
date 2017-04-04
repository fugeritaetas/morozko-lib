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
 * @(#)SearchDOM.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.xml.dom
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class SearchDOM {

    public static SearchDOM newInstance(boolean trimText, boolean ignoreBlank) {
        return (new SearchDOM(trimText, ignoreBlank));
    }
    
    private static List<Element> makeList(Element base) {
        List<Element> list = new ArrayList<Element>();
        list.add(base);
        return list;
    }
    
    private boolean trimText;
    private boolean ignoreBlank;

    private SearchDOM(boolean trimText, boolean ignoreBlank) {
        this.trimText = trimText;
        this.ignoreBlank = ignoreBlank;
    }
    
    public String findText(Element node) {
        String text = null;
        List<String> list = this.findAllText(node);
        if (!list.isEmpty()) {
            text = (String)list.get(0);
        }
        return text;
    }
    
    public List<String> findAllText(Element node) {
        List<String> result = new ArrayList<String>();
        NodeList kids = node.getChildNodes();
        for (int k=0; k<kids.getLength(); k++) {
            String text = this.getText(kids.item(k));
            if (text!=null) {
                result.add(text);
            }
        }
        return result;
    }       
    
    public List<Element> findAllTags(Element node, String name) {
        List<Element> result = new ArrayList<Element>();
        List<Element> search = makeList(node);
        Element tag = findTag(search, name);
        while (tag!=null) {
            result.add(tag);
            tag = findTag(search, name);
        }
        return result;
    }    
    
    public Element findTag(Element node, String name) {
        return this.findTag(makeList(node), name);
    }
  
    private Element findTag(List<Element> queue, String name) {
        Element tag = null;
        while (tag==null && !queue.isEmpty()) {
            Element current = (Element)queue.remove(0);
            if (current.getTagName().equals(name)) {
                tag = current;
            }
            NodeList kids = current.getChildNodes();
            for (int k=0; k<kids.getLength(); k++) {
                Node child = kids.item(k);
                if (child.getNodeType()==Node.ELEMENT_NODE) {
                    queue.add( (Element)child );
                }
            }
        }
        return tag;
    }
    
    private String getText(Node node) {
        String text = null;
        if (node instanceof CharacterData) {
            text = ((CharacterData)node).getData();
            if (this.trimText) {
                text = text.trim();
            }
            if (this.ignoreBlank && text.length()==0) {
                text = null;
            }
        }
        return text;
    }
	
	
}
