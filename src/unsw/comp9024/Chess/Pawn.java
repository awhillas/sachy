package unsw.comp9024.Chess;

public class Pawn implements Piece {

	private Side colour;
	
	public Pawn(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "P";
	}	
}
