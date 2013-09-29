package unsw.comp9024.Chess;

public class Queen extends Piece{

	public Queen(Side colour, Square pos) {
		super(colour, 'q', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Piece[][] board) {
		if(!super.canMoveTo(p, board)) return false;
		if(pos.onColumn(p) && this.columnIsClear(p, board)) {
			return true;
		}
		if (pos.onRow(p) && this.rowIsClear(p, board)) {
			return true;
		}
		if (pos.onDiagonal(p) && this.diagonalIsClear(p, board)) {
			return true;
		}
		return false;
	}	
}

