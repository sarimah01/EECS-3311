package ca.yorku.eecs3311.a1;

import java.util.Scanner;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author ilir
 *
 */
public class OthelloControllerHumanVSGreedy {
	private Othello othello;
	private PlayerGreedy playerGreedy;
	/**
	 * Constructor to initialize the Othello game and the computer player.
	 */
	public OthelloControllerHumanVSGreedy() {
		this.othello = new Othello();
		this.playerGreedy = new PlayerGreedy(othello, OthelloBoard.P2); // Computer is P2
	}
	/**
	 * Method to start the game and handle the turns between the human player and the computer.
	 * The game continues until it is over.
	 * Human player inputs their move through console input, while the computer uses its greedy strategy.
	 */
	public void play() {
		Scanner scanner = new Scanner(System.in);

		while (!othello.isGameOver()) {
			System.out.println(othello.getBoardString());
			System.out.println(othello.getWhosTurn() + " moves next.");

			if (othello.getWhosTurn() == OthelloBoard.P1) {
				// Human player's turn (P1)
				System.out.print("Enter row and column (e.g. 3 4): ");
				int row = scanner.nextInt();
				int col = scanner.nextInt();

				if (othello.move(row, col)) {
					System.out.println("Human (P1) makes move (" + row + "," + col + ")");
				} else {
					System.out.println("Invalid move! Try again.");
				}
			} else {
				// Computer player's turn (P2 - Greedy)
				Move move = playerGreedy.getMove();
				if (move != null && othello.move(move.getRow(), move.getCol())) {
					System.out.println("Computer (P2) makes move (" + move.getRow() + "," + move.getCol() + ")");
				} else {
					System.out.println("Computer (P2) has no valid moves.");
				}
			}
		}

		System.out.println(othello.getBoardString());
		System.out.println("Game Over! Winner: " + othello.getWinner());
		scanner.close();
	}




	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 * @param args Command line arguments
	 */

	public static void main(String[] args) {
		OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
		 oc.play(); // this should work
	}
}
