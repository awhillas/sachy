/**
 * 
 */
package unsw.comp9024.Chess;

/**
 * @author Alexander Whillas <z3446737@student.unsw.edu.au>
 *
 */
public class DeepTeal implements ChessThinker {

	private Piece[][] pieces = new Piece[8][8];
	
	/**
	 * @param board	String in the Design04 format describing a chess board position.
	 */
	public DeepTeal () {
	}

	public DeepTeal (String board) {
		fromString(board);
	}	
	
	@Override
	public void fromString(String boardString) {
		assert ( boardString.length() > 0 );
		String[] squares = boardString.substring(1).split("\\|");
		// 8x8 board with extra column at end of row.
		assert( squares.length > 9 * 8 - 1);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int coord = (i * 9) + j;
				if(squares[coord].length() > 0) {
					System.out.println(squares[coord]);
					char c = squares[coord].charAt(0);
					if (c != ' ') {
						this.pieces[i][j] = makePiece(c, squares[coord].charAt(1));
					}
				}
			}
		}
	}
	
	private Piece makePiece(char type, char side) {
		Side colour = (side == 'l') ? Side.WHITE : Side.BLACK ;
		Piece p = null;
		switch (type) {
			case 'r':
				p = new Rook(colour);
				break;
			case 'b':
				p = new Bishop(colour);
				break;
			case 'n':
				p = new Knight(colour);
				break;
			case 'k':
				p = new King(colour);
				break;
			case 'q':
				p = new Queen(colour);
				break;
			case 'p':
				p = new Pawn(colour);
				break;
		}
		return p;
	}
	
	@Override
	public String toString() {
		return "";
	}

	@Override
	public boolean blackIsInCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean blackIsInCheckMate() {
		// TODO Auto-generated method stub
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
		for (int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(this.pieces[i][j] != null) {
					System.out.print(this.pieces[i][j].toString() + "|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.println("");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			DeepTeal dt = new DeepTeal(args[0]);
			dt.display();
		}
	}
}
