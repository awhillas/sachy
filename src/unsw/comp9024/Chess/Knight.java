package unsw.comp9024.Chess;

import java.util.List;

public class Knight extends Piece {

	public Knight(Side colour, Square pos) {
		super(colour, 'n', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Position position) {
		return true;
	}
}

