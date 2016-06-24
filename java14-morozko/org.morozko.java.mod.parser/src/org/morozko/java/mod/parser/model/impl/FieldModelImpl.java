package org.morozko.java.mod.parser.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.parser.model.ErrorDescriptor;
import org.morozko.java.mod.parser.model.FieldDescription;
import org.morozko.java.mod.parser.model.FieldModel;

public class FieldModelImpl implements FieldModel {
	


	public FieldModelImpl( String name, String value ) {
		this.name = name;
		this.value = value;
		this.errors = new ArrayList<ErrorDescriptor>();
	}
	
	private String name;
	
	private String value;
	
	private List<ErrorDescriptor> errors;

	public List<ErrorDescriptor> getErrors() {
		return errors;
	}

	@Override
	public boolean isValid() {
		return !this.getErrors().isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.FieldModel#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.parser.model.imp.FieldModel#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private FieldDescription fieldDescription;

	public FieldDescription getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(FieldDescription fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	
}
