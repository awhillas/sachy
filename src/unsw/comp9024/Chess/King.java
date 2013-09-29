package unsw.comp9024.Chess;

public class King extends Piece {
	
	public King(Side colour, Square pos) {
		super(colour, 'k', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Piece[][] board) {
		if(!super.canMoveTo(p, board)) return false;
		
		// Can not move to the same square we are on.
		if(this.pos.distance(p) == 1) {
			// if the square is not occupied same colour
			if(board[p.getRow()][p.getColumn()] == null 
					|| board[p.getRow()][p.getColumn()].getColour() != this.colour) {
				//... and not put king in check i.e. no Piece of opposite Side can move to that square.
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
