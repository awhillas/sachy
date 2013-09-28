package unsw.comp9024.Chess;

public class Bishop implements Piece {

	private Side colour;
	
	public Bishop(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "B";
	}
}
