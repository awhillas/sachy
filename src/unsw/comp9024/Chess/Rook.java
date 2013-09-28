package unsw.comp9024.Chess;

public class Rook implements Piece {

	private Side colour;
	
	public Rook(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "R";
	}	
}

