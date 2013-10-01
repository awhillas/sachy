/**
 * 
 */
package unsw.comp9024.Chess;

import java.util.List;

import unsw.comp9024.Chess.d4AcceptanceTests.*;

/**
 * @author Alexander Whillas <z3446737@student.unsw.edu.au>
 *
 */
public class DeepTeal implements ChessThinker {
	
	public static final int BOARD_SIZE = 8;
	
	// Would be much easier to have a resizable list since Piece know their position.
	// But can't use generics :(
	private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

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
						this.board[i][j] = makePiece(c, squares[coord].charAt(1), new Square(i, j));
					}
				}
			}
		}
	}
	
	private Piece makePiece(char type, char side, Square pos) {
		Side colour = (side == 'l') ? Side.WHITE : Side.BLACK ;
		Piece p = null;
		switch (type) {
			case 'r':
				p = new Rook(colour, pos);
				break;
			case 'b':
				p = new Bishop(colour, pos);
				break;
			case 'n':
				p = new Knight(colour, pos);
				break;
			case 'k':
				p = new King(colour, pos);
				break;
			case 'q':
				p = new Queen(colour, pos);
				break;
			case 'p':
				p = new Pawn(colour, pos);
				break;
		}
		return p;
	}
	
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < DeepTeal.BOARD_SIZE; i++) {
			out += "|";
			for(int j = 0; j < DeepTeal.BOARD_SIZE; j++) {
				if(board[i][j] != null) {
					out += board[i][j].toString() + "|";
				} else {
					out += "  |";
				}
			}
			out += "=\n";
		}
		return out;
	}

	private Piece findKing(Side colour) {
		for (int r = 0; r < BOARD_SIZE; r++) {
			for (int c = 0; c < BOARD_SIZE; c++) {
				if(board[r][c] != null 
						&& board[r][c].isKing() 
						&& board[r][c].getColour() == colour) {
					return board[r][c];
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean blackIsInCheck() {
		Piece king = this.findKing(Side.BLACK);
		return isInCheckAt(king.getColour(), king.getSquare(), this.board);
	}
	
	/**
	 * Go thought all the piece on the board and see if the other side can move to the given Square
	 * @param king
	 * @param p
	 * @return
	 */
	private boolean isInCheckAt(Side colour, Square square, Piece[][] board) {
		Side opposite = (colour == Side.WHITE)? Side.BLACK: Side.WHITE;
		for (int r = 0; r < BOARD_SIZE; r++) {
			for (int c = 0; c < BOARD_SIZE; c++) {
				Piece p = board[r][c];
				if(p != null
						&& p.getColour() == opposite 
						&& p.canMoveTo(square, board)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean blackIsInCheckMate() {
		if (blackIsInCheck()) {
			Piece king = this.findKing(Side.BLACK);
			Square[] moves = king.getAllMoves(board);
			for (Square m : moves) {
				if(!isInCheckAt(king.getColour(), m, movePieceTo(king, m, board))) {
					return false;
				}	
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Moves a piece and returns the new board setup
	 * Assumes you have checked to see if the piece can move to that square.
	 * @param p		Piece to move
	 * @param s		New location of the piece
	 * @param board	Board in current state.
	 * @return Board in new state after the piece has been moved.
	 */
	private Piece[][] movePieceTo(Piece p, Square s, Piece[][] board) {
		Piece[][] newBoard = board.clone();	// shallow copy
		try {
			Piece newPiece = (Piece) p.clone();
			newPiece.setSquare(s);
			newBoard[p.getSquare().getRow()][p.getSquare().getColumn()] = null;
			newBoard[s.getRow()][s.getColumn()] = newPiece;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return newBoard;
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
	 * @param args
	 */
	public static void main(String[] args) {		
		Test[] tests = new Test[12];
		
		tests[0] = new TestKing();
		tests[1] = new AcceptanceTest_King();
		tests[2] = new TestQueen();
		tests[3] = new AcceptanceTest_Queen();
		tests[4] = new TestBishop();
		tests[5] = new AcceptanceTest_Bishop();
		tests[6] = new TestKnight();
		tests[7] = new AcceptanceTest_Knight();
		tests[8] = new TestRook();
		tests[9] = new AcceptanceTest_Rook();
		tests[10] = new TestPawn();
		tests[11] = new AcceptanceTest_Pawn();
		
		for (Test t : tests) {
			System.out.println("Running " + t);
			t.run();
			System.out.println("PASSED!");
		}
		
	}
}
