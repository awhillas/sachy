package unsw.comp9024.Chess;

public class Queen extends Piece{

	public Queen(Side colour, Square pos) {
		super(colour, 'q', pos);
	}
	
	@Override
	public boolean canMoveTo(Square s, Position position) {
		if(!super.canMoveTo(s, position)) return false;
		if(pos.onColumn(s) && position.columnIsClear(this.getSquare(), s)) {
			return true;
		}
		if (pos.onRow(s) && position.rowIsClear(this.getSquare(), s)) {
			return true;
		}
		if (pos.onDiagonal(s) && position.diagonalIsClear(this.getSquare(), s)) {
			return true;
		}
		return false;
	}	
}

