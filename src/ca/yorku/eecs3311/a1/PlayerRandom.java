package ca.yorku.eecs3311.a1;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author ilir
 *
 */
public class PlayerRandom {

	private Othello othello;
	private char player;
	private Random rand = new Random();

	// Constructor to initialize the game and player
	public PlayerRandom(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	public Move getMove() {
		// List to store all valid moves
		ArrayList<Move> validMoves = new ArrayList<>();

		// Loop through the board to find all valid moves
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				// Simulate the move and check if it's valid
				Othello tempOthello = new Othello(othello);  // Copy of the current state
				if (tempOthello.move(row, col)) {  // Check if the move is valid by attempting it
					validMoves.add(new Move(row, col));  // If valid, add to the list
				}
			}
		}

		// If no valid moves are available, return null
		if (validMoves.isEmpty()) {
			return null;
		}

		// Randomly select a move from the list of valid moves
		return validMoves.get(rand.nextInt(validMoves.size()));
	}
}


