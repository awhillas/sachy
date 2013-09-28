package unsw.comp9024.Chess;

public class Queen implements Piece{

	private Side colour;
	
	public Queen(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "Q";
	}	
}

