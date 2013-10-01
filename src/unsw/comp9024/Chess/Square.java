/**
 * 
 */
package unsw.comp9024.Chess;

/**
 * Encapsulates all Position to Position calculations.
 * If we had to output in algebraic chess notation this would do the translation.
 */
public class Square {
	
	private int row;
	
	private int column;
	
	public Square(int row, int column) {
		assert row >= 0 && row < 8 : "Row is off the board: " + row;
		assert column >= 0 && column < 8 : "Column is off the board: " + column;

		this.row = row;
		this.column = column;
	}
	
	public boolean equalTo(Square p) {
		if (p.getColumn() == this.column && p.getRow() == this.row) {
			return true;
		}
		return false;
	}
	
	public int distance(Square p) {
		int abs_col = Math.abs(this.column - p.getColumn());
		if(onRow(p)) {
			return abs_col;
		}
		if(onColumn(p)) {
			return Math.abs(this.row - p.getRow());
		}
		if(onDiagonal(p)) {
			return abs_col;	// return one of them.
		}
		// Else distance doesn't make sense return -1;
		return -1;
	}
	
	/**
	 * @param p	Position to check against.
	 * @return	True if on the same diagonal as p, false otherwise.
	 */
	public boolean onDiagonal(Square p) {	
		return Math.abs(this.row - p.getRow()) == Math.abs(this.column - p.getColumn());
	}
	
	public boolean onRow(Square p) {
		return this.row == p.getRow();
	}
	
	public boolean onColumn(Square p) {
		return this.column == p.getColumn();
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return "(" + row + "," + column + ")";
	}
}
