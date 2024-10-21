package ca.yorku.eecs3311.a1;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 * @author ilir
 *
 */

public class PlayerGreedy {
	private Othello othello;
	private char player;

	/**
	 * Constructs a PlayerGreedy instance with the specified Othello game and player identifier.
	 *
	 * @param othello The Othello game instance
	 * @param player The character representing the player (P1 or P2)
	 */
	public PlayerGreedy(Othello othello, char player) {
		this.othello = othello;
		this.player = player; // P2 (computer)
	}

	/**
	 * Determines the best move for the greedy player by evaluating all possible moves.
	 * It simulates each valid move and counts the resulting tokens for the player.
	 *
	 * @return A Move object representing the best move (row, col) for the player,
	 *         or null if no valid moves are available.
	 */
	public Move getMove() {
		int bestRow = -1;
		int bestCol = -1;
		int maxTokens = -1;  // To store the maximum number of tokens

		// Loop over all possible board positions
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				// Create a copy of the current game to simulate the move
				Othello tempOthello = new Othello(othello);  // Deep copy of the current state

				// Try making the move on the copy. If the move is successful, it's a valid move.
				if (tempOthello.move(row, col)) {
					// Count the tokens for the player after the move
					int tokenCount = tempOthello.getCount(player);

					// If this move results in more tokens, or tie-breaking applies, update the best move
					if (tokenCount > maxTokens ||
							(tokenCount == maxTokens && (row < bestRow ||
									(row == bestRow && col < bestCol)))) {
						maxTokens = tokenCount;
						bestRow = row;
						bestCol = col;
					}
				}
			}
		}

		// Return the best move, or null if no valid moves
		if (bestRow != -1 && bestCol != -1) {
			return new Move(bestRow, bestCol);
		} else {
			return null;  // No valid moves available
		}
	}

}

