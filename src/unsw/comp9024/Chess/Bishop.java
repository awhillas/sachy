package unsw.comp9024.Chess;

public class Bishop extends Piece {
	
	public Bishop(Side colour, Square pos) {
		super(colour, 'b', pos);
	}
	
	@Override
	public boolean canMoveTo(Square s, Position position) {
		if(!super.canMoveTo(s, position)) return false;
		if (pos.onDiagonal(s) && position.diagonalIsClear(this.getSquare(), s)) {
			return true;
		}
		return false;
	}	
}
