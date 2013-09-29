package unsw.comp9024.Chess;

public class Bishop extends Piece {
	
	public Bishop(Side colour, Square pos) {
		super(colour, 'b', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Piece[][] board) {
		if(!super.canMoveTo(p, board)) return false;
		if (pos.onDiagonal(p) && this.diagonalIsClear(p, board)) {
			return true;
		}
		return false;
	}	
}
