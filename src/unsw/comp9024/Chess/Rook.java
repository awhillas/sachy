package unsw.comp9024.Chess;

public class Rook extends Piece {

	public Rook(Side colour, Square pos) {
		super(colour, 'r', pos);
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
		return false;
	}	
}

