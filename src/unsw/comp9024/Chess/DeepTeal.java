/**
 * 
 */
package unsw.comp9024.Chess;

import unsw.comp9024.Chess.d4AcceptanceTests.*;



/**
 * @author Alexander Whillas <z3446737@student.unsw.edu.au>
 *
 */
public class DeepTeal implements ChessThinker {
	
	public static final int BOARD_SIZE = 8;
	
	// Would be much easier to have a resizable list since Piece know their position.
	// But can't use generics :(
	//private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
	//private List<Piece> position = new ArrayList<Piece>();
	
	private Position position = new Position();

	/**
	 * @param board	String in the Design04 format describing a chess board position.
	 */
	public DeepTeal () {
	}

	/**
	 * @param design04	Board position represented in Design04 notation.
	 */
	public DeepTeal (String design04) {
		fromString(design04);
	}	
	
	/**
	 * @param design04	Board position represented in Design04 notation.
	 */
	@Override
	public void fromString(String boardString) {
		assert boardString.length() > 0 : "Requre a position description in Design04 notation. Empty string given.";
		
		String[] squares = boardString.substring(1).split("\\|");
		
		assert squares.length > 9 * 8 - 2 : "Not enough squares in the board";
		
		for (int i = 0; i < DeepTeal.BOARD_SIZE; i++) {
			for (int j = 0; j < DeepTeal.BOARD_SIZE; j++) {
				int coord = (i * (DeepTeal.BOARD_SIZE + 1)) + j;
				if(squares[coord].length() > 0) {
					char c = squares[coord].charAt(0);
					if (c != ' ') {
						position.add(Piece.makePiece(c, (squares[coord].charAt(1) == 'l') ? Side.WHITE : Side.BLACK, new Square(i, j)));
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < DeepTeal.BOARD_SIZE; i++) {
			out += "|";
			for(int j = 0; j < DeepTeal.BOARD_SIZE; j++) {
				Piece p = position.getPieceAt(new Square(i, j));
				if(p != null) {
					out += p.toString() + "|";
				} else {
					out += "  |";
				}
			}
			out += "=\n";
		}
		return out;
	}
	

	
	@Override
	public boolean blackIsInCheck() {
		return position.isInCheck(Side.BLACK);
	}

	@Override
	public boolean blackIsInCheckMate() {
		return isInCheckMateAt(Side.BLACK, position);
	}
	
	/**
	 * Checks if the Side has no legal moves. 
	 * Not a real test of checkmate as the king doesn't have to be in check :-/
	 * @param colour	Side to check
	 * @param position	Position to check in.
	 * @return
	 */
	private boolean isInCheckMateAt(Side colour, Position position) {
		if (position.isInCheck(colour)) {
			Piece king = position.findKing(colour);
			Square[] moves = king.getAllMoves(position);
			for (Square m : moves) {
				if(!position.movePieceTo(king, m).isInCheck(colour)) {
					return false;
				}
			}
			return true;
		}
		return false;		
	}
	
	@Override
	public boolean whiteCanMateInOneMove() {
		// Get all the WHITE Pieces
		Piece[] whites = position.getAllPiecesOf(Side.WHITE);
		for(Piece p : whites) {
			Square[] moves = p.getAllMoves(position);
			for(Square m : moves) {
				if(isInCheckMateAt(Side.BLACK, position.movePieceTo(p, m))) {
					return true;
				}
			}
		}
		// Check if King is in Checkmate after each one.
		return false;
	}
	
	@Override
	public void makeWhiteMateMove() {
		Piece[] whites = position.getAllPiecesOf(Side.WHITE);
		for(Piece p : whites) {
			Square[] moves = p.getAllMoves(position);
			for(Square m : moves) {
				Position newPosition = position.movePieceTo(p, m);
				if(isInCheckMateAt(Side.BLACK, newPosition)) {
					this.position = newPosition;
					return;
				}
			}
		}
	}
	
	// output ASCII representation of the board.
	public void display() {
		System.out.print(this.toString());
	}

	/**
	 * Acceptance testing
	 */
	public static void main(String[] args) {		
		Test[] tests = new Test[12];
		
		// Do King first as this is fundamental
		tests[0] = new TestKing();
		tests[1] = new AcceptanceTest_King();
		// Next do Rook, Bishop & Queen as these should be easiest to test.
		tests[2] = new TestRook();
		tests[3] = new AcceptanceTest_Rook();
		tests[4] = new TestBishop();
		tests[5] = new AcceptanceTest_Bishop();
		// Queen is just a combo of Rook and Bishop
		tests[6] = new TestQueen();
		tests[7] = new AcceptanceTest_Queen();
		// Knight and Pawn are the two weird ones.
		tests[8] = new TestKnight();
		tests[9] = new AcceptanceTest_Knight();
		tests[10] = new TestPawn();
		tests[11] = new AcceptanceTest_Pawn();
		
		for (Test t : tests) {
			System.out.println("Running " + t);
			t.run();
			System.out.println("PASSED!");
		}
		
	}
}
