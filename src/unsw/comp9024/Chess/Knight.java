package unsw.comp9024.Chess;

public class Knight extends Piece {

	public Knight(Side colour, Square pos) {
		super(colour, 'n', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Piece[][] board) {
		return true;
	}
}

