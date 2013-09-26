/**
 * 
 */
package unsw.comp9024.Chess;

/**
 * @author Alexander Whillas <z3446737@student.unsw.edu.au>
 *
 */
public class DeepTeal implements ChessThinker {

	private Piece[][] pieces;
	
	/**
	 * @param board	String in the Design04 format describing a chess board position.
	 */
	public DeepTeal (String board) {
		String[] squares = board.split("\\|");
	}
	
	@Override
	public void fromString(String boardString) {
		// TODO Auto-generated method stub
		
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
				
			}
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
