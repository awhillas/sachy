package unsw.comp9024.Graph;

public class Vertex {
	private String name;
	private int value;
	
	public Vertex(String name, int cost) {
		this.name = name;
		this.value = cost;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	
}
