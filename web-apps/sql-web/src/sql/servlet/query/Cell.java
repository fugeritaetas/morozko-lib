package sql.servlet.query;

public class Cell {

	@Override
	public String toString() {
		return this.value;
	}

	public Cell(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
