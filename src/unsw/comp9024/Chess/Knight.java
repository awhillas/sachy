package unsw.comp9024.Chess;

public class Knight implements Piece {

	private Side colour;
	
	public Knight(Side colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "N";
	}	
}

