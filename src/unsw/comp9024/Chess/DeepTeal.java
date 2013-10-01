/**
 * 
 */
package unsw.comp9024.Chess;

import java.util.List;
import java.util.ArrayList;

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
		if (blackIsInCheck()) {
			Piece king = position.findKing(Side.BLACK);
			Square[] moves = king.getAllMoves(position);
			for (Square m : moves) {
				if(!position.movePieceTo(king, m).isInCheck(Side.BLACK)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean whiteCanMateInOneMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeWhiteMateMove() {
		// TODO Auto-generated method stub
		
	}
	
	// output ASCII rep. of the board.
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
