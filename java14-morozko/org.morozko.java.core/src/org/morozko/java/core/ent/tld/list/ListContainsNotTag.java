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
 * @(#)ListContainsNotTag.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.tld.list
 * @creation   : 28/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.tld.list;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.util.CheckUtils;

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
public class ListContainsNotTag extends ListTagHelper {

		/**
		 *	<jdl:section>
		 * 		<jdl:text lang='it'>.</jdl:text>
		 * 		<jdl:text lang='en'>.</jdl:text>  
		 *	</jdl:section>
		 */	
	private static final long serialVersionUID = -1929774856686921225L;

	private String compareString;
	
	private String containsName;
	
	private String containsProperty;
	
	private String containsValue;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.tld.list.ListTagHelper#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int result = SKIP_BODY;
		List list = this.findList();
		Object contains = findObject( this.getContainsName(), this.getContainsProperty(), this.getContainsValue() );
		if ( CheckUtils.isTrue( this.getCompareString() ) ) {
			contains = String.valueOf( contains );
		}		
		if ( !list.contains( contains ) ) {
			result = EVAL_PAGE;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo containsName.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of containsName.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo containsName.</jdl:text>
		 *         		<jdl:text lang='en'>the value of containsName.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getContainsName() {
			return containsName;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo containsName.</jdl:text>
		 * 		<jdl:text lang='en'>Sets containsName.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>containsName il valore di containsName da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>containsName the containsName to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setContainsName(String containsName) {
			this.containsName = containsName;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo containsProperty.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of containsProperty.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo containsProperty.</jdl:text>
		 *         		<jdl:text lang='en'>the value of containsProperty.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getContainsProperty() {
			return containsProperty;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo containsProperty.</jdl:text>
		 * 		<jdl:text lang='en'>Sets containsProperty.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>containsProperty il valore di containsProperty da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>containsProperty the containsProperty to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setContainsProperty(String containsProperty) {
			this.containsProperty = containsProperty;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo containsValue.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of containsValue.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo containsValue.</jdl:text>
		 *         		<jdl:text lang='en'>the value of containsValue.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getContainsValue() {
			return containsValue;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo containsValue.</jdl:text>
		 * 		<jdl:text lang='en'>Sets containsValue.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>containsValue il valore di containsValue da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>containsValue the containsValue to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setContainsValue(String containsValue) {
			this.containsValue = containsValue;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Restituisce il valore del campo compareString.</jdl:text>
		 * 		<jdl:text lang='en'>Returns the value of compareString.</jdl:text>  
		 *  </jdl:section>
		 * </p>
		 *
		 * @return <jdl:section>
		 *         		<jdl:text lang='it'>il valore del campo compareString.</jdl:text>
		 *         		<jdl:text lang='en'>the value of compareString.</jdl:text> 
		 * 		   </jdl:section>
		 */
		public String getCompareString() {
			return compareString;
		}

		/**
		 * <p>
		 *  <jdl:section>
		 * 		<jdl:text lang='it'>Imposta il campo compareString.</jdl:text>
		 * 		<jdl:text lang='en'>Sets compareString.</jdl:text>  
		 *	</jdl:section>
		 * </p>
		 *
		 * @param 	<jdl:section>
		 * 				<jdl:text lang='it'>compareString il valore di compareString da impostare.</jdl:text>
		 * 				<jdl:text lang='en'>compareString the compareString to set.</jdl:text>
		 * 			</jdl:section>
		 */
		public void setCompareString(String compareString) {
			this.compareString = compareString;
		}	
	
}
