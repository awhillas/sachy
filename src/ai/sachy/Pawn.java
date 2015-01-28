package ai.sachy;

public class Pawn extends Piece {

	public Pawn(Side colour, Square pos) {
		super(colour, 'p', pos);
	}
	
	@Override
	public boolean canMoveTo(Square s, Position position) {
		Square infront = this.getForwardSquare();
		if(infront != null) {
			// Check if something is in front of it
			if(s.equalTo(infront) && position.getPieceAt(s) == null) {
				return true;
			}
			// If there is a piece there to take...
			if(position.getPieceAt(s) != null) {
				// Check if it can take right
				if(infront.getColumn() + 1 < DeepTeal.BOARD_SIZE
						&& s.equalTo(new Square(infront.getRow(), infront.getColumn() + 1))) {
					return true;
				}
				// ... and left.
				if(infront.getColumn() - 1 >= 0
						&& s.equalTo(new Square(infront.getRow(), infront.getColumn() - 1))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Square getForwardSquare() {
		if (this.colour == Side.WHITE) {
			if(pos.getRow() > 0) {
				return new Square(pos.getRow() - 1, pos.getColumn());
			}
		} else {
			if(pos.getRow() < DeepTeal.BOARD_SIZE) {
				return new Square(pos.getRow() + 1, pos.getColumn());
			}
		}
		return null;
	}
	
}
