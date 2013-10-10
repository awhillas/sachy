package unsw.comp9024.Graph;

public class Vertex {
	
	private String name;
	
	public Vertex(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	boolean equals(Vertex v) {
		return name.equals(v.getName());
	}
}