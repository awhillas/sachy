/**
 * 
 */
package unsw.comp9024.Chess;

// Didn't want to use generics but kind a forced to as I needed a growable List :-(
import java.util.ArrayList;
import java.util.List;

/**
 * @author alex
 *
 */
public class Piece implements Cloneable {
	
	protected Side colour;
	
	protected char letter;
	
	protected Square pos;
	
	public Piece(Side colour, char letter, Square p) {
		this.colour = colour;
		this.letter = letter;
		this.pos = p;
	}

	public Side getColour() {
		return colour;
	}	
	
	public Square getSquare() {
		return this.pos;
	}

	public void setSquare(Square s) {
		this.pos = s;
	}

	public String toString() {
		return this.letter + ((this.colour == Side.WHITE) ? "l" : "d");
	}
	
	public boolean canMoveTo(Square p, Piece[][] board) {
		if(this.pos.equalTo(p)) {
			return false;
		}		
		return true;
	}
	
	/**
	 * Get all the moves possible for this piece.
	 * @param board
	 * @return	An array of Squares.
	 */
	public Square[] getAllMoves(Piece[][] board) {
		List<Square> out = new ArrayList<Square>();
		for (int r = 0; r < DeepTeal.BOARD_SIZE; r++) {
			for (int c = 0; c < DeepTeal.BOARD_SIZE; c++) {
				Square s = new Square(r, c);
				if (this.canMoveTo(s, board)) {
					out.add(s);
				}
			}
		}
		return out.toArray(new Square[0]);
	}
	
	public boolean rowIsClear(Square p, Piece[][] board) {
		int step = (pos.getColumn() - p.getColumn() > 0) ? -1: 1;
		for(int col = pos.getColumn(); col < p.getColumn(); col += step) {
			if (board[pos.getRow()][col] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean columnIsClear(Square p, Piece[][] board) {
		int step = (pos.getRow() - p.getRow() > 0) ? -1: 1;
		for(int row = pos.getRow(); row < p.getRow(); row += step) {
			if (board[row][pos.getColumn()] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean diagonalIsClear(Square p, Piece[][] board) {
		assert pos.onDiagonal(p) : p +" is not on a diagonal with " + pos;
		
		int row_step = (pos.getRow() - p.getRow() > 0) ? -1: 1;
		int col_step = (pos.getColumn() - p.getColumn() > 0) ? -1: 1;
		for (int i = 1; i < pos.distance(p); i++) {
			int row = pos.getRow() + i * row_step;
			int col = pos.getColumn() + i * col_step;
			if (board[row][col] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isKing() {
		return false;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }	
}