package ca.yorku.eecs3311.a1;

import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 *
 */
public class Othello {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private char whosTurn = OthelloBoard.P1; // P1 moves first!
	private int numMoves = 0;
	OthelloBoard board;
	/**
	 * Constructor to initialize the Othello game and its board.
	 */
	public Othello() {
		this.board = new OthelloBoard(DIMENSION);
	}

	/**
	 * Copy constructor for Othello to create a new game based on another game instance.
	 *
	 * @param other The Othello game instance to copy.
	 */
	public Othello(Othello other) {
		this.whosTurn = other.whosTurn;
		this.numMoves = other.numMoves;
		this.board = new OthelloBoard(other.board); // Assuming deep copy of OthelloBoard
	}


	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
		//return ' ';
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row position
	 * @param col position
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		// Attempt to make the move for the current player
		if (this.board.move(row, col, this.whosTurn)) {
			numMoves++; // Increment move count
			this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn); // Switch the turn
			return true;
		}
		return false; // Move was invalid
		//return true;
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		return this.board.getCount(player);
		//return 0;
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if (!isGameOver()) {
			return OthelloBoard.EMPTY; // Game is still in progress
		}
		int p1Count = getCount(OthelloBoard.P1);
		int p2Count = getCount(OthelloBoard.P2);

		if (p1Count > p2Count) {
			return OthelloBoard.P1;
		} else if (p2Count > p1Count) {
			return OthelloBoard.P2;
		} else {
			return OthelloBoard.EMPTY; // It's a tie
		}
	}
	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return !board.hasValidMove(OthelloBoard.P1) && !board.hasValidMove(OthelloBoard.P2);
	}

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return board.toString();
		//return "";
	}

	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();

		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while (!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if (o.move(row, col)) {
				System.out.println("makes move (" + row + "," + col + ")");
				System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
			}
		}

	}
}
