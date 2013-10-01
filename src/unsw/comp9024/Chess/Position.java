/**
 * 
 */
package unsw.comp9024.Chess;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a List of Pieces.
 */
public class Position {

	private List<Piece> pieces = new ArrayList<Piece>();
	
	public Position() {
	}

	public Position(List<Piece> pieces) {
		this.pieces = new ArrayList<Piece>(pieces);
	}

	/**
	 * Copy constructor (instead if implementing Cloneable).
	 * @param p
	 */
	public Position(Position p) {
		this(p.getPieces());
	}	

	/**
	 * Moves a piece and returns the new board Position
	 * Assumes you have checked to see if the piece can move to that square.
	 * @param p			Piece to move
	 * @param s			New location of the piece
	 * @param position2	Board in current state.
	 * @return Board in new state after the piece has been moved.
	 */
	public Position movePieceTo(Piece p, Square to) {
		List<Piece> newPosition = new ArrayList<Piece>(this.pieces);	// shallow copy
		newPosition.remove(p);			// remove the old version of itself
		Piece taken = getPieceAt(to);	// Check if it has taken anything...
		if(taken != null && !taken.isKing()) {
			newPosition.remove(taken);	// ...and remove it.
		}
		newPosition.add(Piece.makePiece(p.getLetter(), p.getColour(), to));
		return new Position(newPosition);
	}
	
	
	public List<Piece> getPieces() {
		return this.pieces;
	}

	public void add(Piece p) {
		this.pieces.add(p);
	}
	
	/**
	 * Look to see if there is a Piece at the given Square.
	 * @param s	Square to examine.
	 * @return	The Piece if one is found, null otherwise.
	 */
	public Piece getPieceAt(Square s) {
		for (Piece p : this.pieces) {
			if (p.getSquare().equalTo(s)) {
				return p;
			}
		}
		return null;
	}
	
	public Piece findKing(Side colour) {
		for (Piece p : this.pieces) {
			if (p.isKing() && p.getColour() == colour) {
				return p;
			}
		}
		return null;
	}
	
	public Piece[] getAllPiecesOf(Side colour) {
		List<Piece> out = new ArrayList<Piece>();
		for (Piece p : this.pieces) {
			if (p.getColour() == colour) {
				out.add(p);
			}
		}
		return out.toArray(new Piece[0]);
	}

	/**
	 * Go thought all the piece on the board and see if the other side can move to the given Square
	 * @param king
	 * @param p
	 * @return
	 */
	private boolean isInCheckAt(Piece king, Square square) {
		Side opposite = (king.getColour() == Side.WHITE)? Side.BLACK: Side.WHITE;
		for (Piece p : this.pieces) {
			if(p != king
					&& p.getColour() == opposite 
					&& p.canMoveTo(square, this)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if the given Side is in check at its current location.
	 * @param colour	Side to check King of.
	 * @return	True if its in check, false otherwise.
	 */
	public boolean isInCheck(Side colour) {
		Piece king = this.findKing(colour);
		return isInCheckAt(king, king.getSquare());
	}
	
	/**
	 * @param s	
	 * @param position	Board position.
	 * @return	True if no Piece between this Piece and the given Square, false otherwise.
	 */
	public boolean rowIsClear(Square from, Square to) {
		assert from.getRow() == to.getRow() : "Squares should be on the same row";
		
		for (Piece p : pieces) {
			if(p.getSquare().getRow() == to.getRow()) {
				int piece_col = p.getSquare().getColumn();
				if (from.getColumn() > to.getColumn() 
						&& piece_col > to.getColumn() 
						&& piece_col < from.getColumn()) {
					return false;
				} else if(piece_col < to.getColumn() 
						&& piece_col > from.getColumn()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @param s	
	 * @param position	Board position.
	 * @return	True if no Piece between this Piece and the given Square, false otherwise.
	 */	
	public boolean columnIsClear(Square from, Square to) {
		assert from.getColumn() == to.getColumn() : "Squares should be on the same column";
		
		for (Piece p : pieces) {
			if(p.getSquare().getColumn() == to.getColumn()) {
				int piece_row = p.getSquare().getRow();
				if (from.getRow() > to.getRow()
						&& piece_row > to.getRow() 
						&& piece_row < from.getRow()) {
					return false;
				} else if(piece_row < to.getRow() 
						&& piece_row > from.getRow()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean diagonalIsClear(Square from, Square to) {
		assert from.onDiagonal(to) : to +" is not on a diagonal with " + from;
		
		int row_step = (from.getRow() - to.getRow() > 0) ? -1: 1;
		int col_step = (from.getColumn() - to.getColumn() > 0) ? -1: 1;
		for (int i = 1; i < from.distance(to); i++) {
			int row = from.getRow() + i * row_step;
			int col = from.getColumn() + i * col_step;
			for (Piece p : pieces) {
				if (p.getSquare().equalTo(new Square(row, col))) {
					return false;
				}
			}
		}
		return true;
	}
}
