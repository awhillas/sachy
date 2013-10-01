package unsw.comp9024.Chess;

public class King extends Piece {
	
	public King(Side colour, Square pos) {
		super(colour, 'k', pos);
	}
	
	@Override
	public boolean canMoveTo(Square s, Position position) {
		if(!super.canMoveTo(s, position)) return false;
		
		if(this.pos.distance(s) == 1) {
			// if the square is not occupied same colour
			Piece p = position.getPieceAt(s);
			if(p == null || p.getColour() != this.colour) {
				// Does not consider if the square puts the King in check...
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isKing() {
		return true;
	}	
	
	public static boolean inCheckAt(Square p, Piece[][] board) {
		return false;
	}
}
