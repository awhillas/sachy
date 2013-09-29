package unsw.comp9024.Chess;

public class Pawn extends Piece {

	public Pawn(Side colour, Square pos) {
		super(colour, 'p', pos);
	}
	
	@Override
	public boolean canMoveTo(Square p, Piece[][] board) {
		Square infront = this.getForwardSquare();
		// Check if something is in front of it
		if(p.equalTo(infront) && board[p.getRow()][p.getColumn()] == null) {
			return true;
		}
		// Check if it can take right
		if(infront.getColumn() + 1 < DeepTeal.BOARD_SIZE
				&& p.equalTo(new Square(infront.getRow(), infront.getColumn() + 1))) {
			return true;
		}
		// ... and left.
		if(infront.getColumn() - 1 >= 0
				&& p.equalTo(new Square(infront.getRow(), infront.getColumn() + 1))) {
			return true;
		}
		
		return false;
	}
	
	private Square getForwardSquare() {
		if (this.colour == Side.WHITE) {
			return new Square(pos.getRow() - 1, pos.getColumn());
		} else {
			return new Square(pos.getRow() + 1, pos.getColumn());
		}
	}
	
}
