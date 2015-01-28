package ai.sachy;

public class Rook extends Piece {

	public Rook(Side colour, Square pos) {
		super(colour, 'r', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Position position) {
		if(!super.canMoveTo(p, position)) return false;
		if(pos.onColumn(p) && position.columnIsClear(this.getSquare(), p)) {
			return true;
		}
		if (pos.onRow(p) && position.rowIsClear(this.getSquare(), p)) {
			return true;
		}		
		return false;
	}	
}

