package org.fugerit.java.core.util.collection;

import java.util.Comparator;

public class OptionItem implements KeyObject<String> {

	public final static Comparator<OptionItem> LABEL_SORTER = new Comparator<OptionItem>() {
		public int compare(OptionItem object1, OptionItem object2) {
			return object1.getLabel().compareTo( object2.getLabel() );
		}
	};
	
	public final static Comparator<OptionItem> VALUE_SORTER = new Comparator<OptionItem>() {
		public int compare(OptionItem object1, OptionItem object2) {
			return object1.getValue().compareTo( object2.getValue() );
		}
	};
	
	private String value;
	
	private String label;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getKey() {
		return this.getValue();
	}

	public OptionItem(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
	
	public OptionItem( String valueAndLabel ) {
		this( valueAndLabel, valueAndLabel );
	}
	
}
