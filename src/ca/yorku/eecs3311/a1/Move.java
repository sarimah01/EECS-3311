package ca.yorku.eecs3311.a1;

/**
 * The Move class represents a move made on the Othello board.
 * It stores the row and column of a move and provides methods
 * to access these values.
 *
 * The class is primarily used to store and pass around the coordinates
 * of a move for Othello game logic.
 *
 * @author Ilir
 */
// TODO: Javadoc this class
public class Move {
	private int row, col;
	/**
	 * Constructor for Move class.
	 *
	 * @param row The row where the move is made.
	 * @param col The column where the move is made.
	 */

	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	 * Get the row of the move.
	 *
	 * @return the row of the move.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get the column of the move.
	 *
	 * @return the column of the move.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Return a string representation of the move in the format "(row,col)".
	 *
	 * @return a string representation of the move.
	 */

	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
