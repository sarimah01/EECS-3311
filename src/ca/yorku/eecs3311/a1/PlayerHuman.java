package ca.yorku.eecs3311.a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman allows a human player to input their moves in the Othello game.
 * It reads input from the console for the row and column of the move, ensuring
 * the inputs are valid numbers within the acceptable range.
 *
 * The class handles invalid input and I/O errors gracefully, prompting the user
 * to re-enter their move if the input is not valid.
 *
 * @author ilir
 */
public class PlayerHuman {
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private Othello othello;
	private char player;
	/**
	 * Constructs a PlayerHuman instance with the given Othello game and player identifier.
	 *
	 * @param othello The Othello game instance
	 * @param player The character representing the player (P1 or P2)
	 */
	public PlayerHuman(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}
	/**
	 * Prompts the player for their move (row and column) and returns it as a Move object.
	 *
	 * @return A Move object representing the player's chosen row and column
	 */
	public Move getMove() {
		
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col); // Return the Move object
	}
	/**
	 * Prompts the user for input and validates that it is within the acceptable range (1-8).
	 *
	 * @param message The prompt message to display to the user
	 * @return The validated move input
	 */
	private int getMove(String message) {
		int move, lower = 0, upper = 7; // Valid range for moves (1 to 8)
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE); // Handle I/O errors
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE); // Handle invalid number format
			}
		}
		return -1; // Return -1 in case of an error (though this should not occur in normal gameplay)
	}
}

