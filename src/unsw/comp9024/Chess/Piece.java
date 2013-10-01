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
	
	/**
	 * Copy constructor. 
	 * Nicer than implementing Cloneable.
	 * @see http://www.artima.com/intv/bloch13.html
	 * @see http://www.javapractices.com/topic/TopicAction.do?Id=12
	 * @param p
	 */
	public Piece(Piece p) {
		this(p.getColour(), p.getLetter(), p.getSquare());
	}

	/**
	 * Piece Factory function.
	 * @see http://en.wikipedia.org/wiki/Factory_method_pattern
	 * @param type
	 * @param side
	 * @param pos
	 * @return new Piece
	 */
	public static Piece makePiece(char type, Side colour, Square pos) {
		//Side colour = (side == 'l') ? Side.WHITE : Side.BLACK ;
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
			default:
				throw new IllegalArgumentException( "Unknown Piece type: " + type);
		}
		return p;
	}	
	
	public Side getColour() {
		return colour;
	}
	
	public char getLetter() {
		return this.letter;
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
	
	public boolean canMoveTo(Square p, Position position) {
		// Can not move to the same square we are on.
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
	public Square[] getAllMoves(Position position) {
		List<Square> out = new ArrayList<Square>();
		for (int r = 0; r < DeepTeal.BOARD_SIZE; r++) {
			for (int c = 0; c < DeepTeal.BOARD_SIZE; c++) {
				Square s = new Square(r, c);
				if (this.canMoveTo(s, position)) {
					out.add(s);
				}
			}
		}
		return out.toArray(new Square[0]);
	}
		
	public boolean isKing() {
		return false;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }	
}