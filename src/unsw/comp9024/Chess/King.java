package unsw.comp9024.Chess;

public class King implements Piece {

	private Side colour;
	
	public King(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "K";
	}
}
