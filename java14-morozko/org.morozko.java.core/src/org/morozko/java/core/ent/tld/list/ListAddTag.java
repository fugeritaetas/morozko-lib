/*******************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
*******************************************************/
/*
 * @(#)ListAddTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.list
 * @creation   : 06/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class ListAddTag extends ListTagHelper {

	private String elementName;
	
	private String elementProperty;
	
	private String elementValue;
	
	private String insertAt;
	
	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo insertAt.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of insertAt.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo insertAt.</jdl:text>
	 *         		<jdl:text lang='en'>the value of insertAt.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public String getInsertAt() {
		return insertAt;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo insertAt.</jdl:text>
	 * 		<jdl:text lang='en'>Sets insertAt.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>insertAt il valore di insertAt da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>insertAt the insertAt to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setInsertAt(String insertAt) {
		this.insertAt = insertAt;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		List list = this.findList();
		Object element = this.findObject( this.getElementName() , this.getElementProperty(), this.getElementValue() );
		if ( this.getInsertAt() != null ) {
			list.add( Integer.parseInt( this.getInsertAt() ), element );	
		} else {
			list.add( element );
		}
		return EVAL_PAGE;
	}

		/**
		 *	<jdl:section>
		 * 		<jdl:text lang='it'>.</jdl:text>
		 * 		<jdl:text lang='en'>.</jdl:text>  
		 *	</jdl:section>
		 */	
	private static final long serialVersionUID = 8674447784573805254L;

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo elementName.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of elementName.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo elementName.</jdl:text>
		 *         		<jdl:text lang='en'>the value of elementName.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getElementName() {
			return elementName;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo elementName.</jdl:text>
		 * 		<jdl:text lang='en'>Sets elementName.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>elementName il valore di elementName da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>elementName the elementName to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setElementName(String elementName) {
			this.elementName = elementName;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo elementProperty.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of elementProperty.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo elementProperty.</jdl:text>
		 *         		<jdl:text lang='en'>the value of elementProperty.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getElementProperty() {
			return elementProperty;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo elementProperty.</jdl:text>
		 * 		<jdl:text lang='en'>Sets elementProperty.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>elementProperty il valore di elementProperty da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>elementProperty the elementProperty to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setElementProperty(String elementProperty) {
			this.elementProperty = elementProperty;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo elementValue.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of elementValue.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo elementValue.</jdl:text>
		 *         		<jdl:text lang='en'>the value of elementValue.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getElementValue() {
			return elementValue;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo elementValue.</jdl:text>
		 * 		<jdl:text lang='en'>Sets elementValue.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>elementValue il valore di elementValue da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>elementValue the elementValue to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setElementValue(String elementValue) {
			this.elementValue = elementValue;
		}

}
