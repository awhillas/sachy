package unsw.comp9024.Chess;

public class Knight extends Piece {

	public Knight(Side colour, Square pos) {
		super(colour, 'n', pos);
	}
	
	@Override
	public boolean canMoveTo(Square s, Position position) {
		int abs_row = Math.abs(this.pos.getRow() - s.getRow());
		int abs_col = Math.abs(this.pos.getColumn() - s.getColumn());
		if((abs_row == 2 && abs_col == 1) || (abs_row == 1 && abs_col == 2)) {
			return true;
		}
		return false;
	}
}

