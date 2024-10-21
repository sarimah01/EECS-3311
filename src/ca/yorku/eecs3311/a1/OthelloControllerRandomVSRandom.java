package ca.yorku.eecs3311.a1;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 * 
 * @author ilir
 *
 */
public class OthelloControllerRandomVSRandom {
	private int p1wins = 0;
	private int p2wins = 0;
	private int numGames;
	// Constructor to initialize the number of games
	public OthelloControllerRandomVSRandom(int numGames) {
		this.numGames = numGames;
	}
	/**
	 * Runs the simulation of games between two Random players (P1 and P2).
	 */
	public void runSimulation() {
		for (int i = 0; i < numGames; i++) {
			Othello othello = new Othello(); // Create a new game for each simulation
			PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1); // P1: Random player
			PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2); // P2: Random player

			// Simulate the game until it's over
			while (!othello.isGameOver()) {
				// Check if Player 1 can make a move
				if (othello.getWhosTurn() == OthelloBoard.P1) {
					Move move = player1.getMove(); // Get a random move for P1
					if (move != null && othello.move(move.getRow(), move.getCol())) {
						continue; // Move was successful, continue to next turn
					}
				}

				// Check if Player 2 can make a move
				if (othello.getWhosTurn() == OthelloBoard.P2) {
					Move move = player2.getMove(); // Get a random move for P2
					if (move != null && othello.move(move.getRow(), move.getCol())) {
						continue; // Move was successful, continue to next turn
					}
				}

				// If both players cannot make a move, the game ends
				break;
			}

			// Determine the winner after the game is over
			char winner = othello.getWinner();
			if (winner == OthelloBoard.P1) {
				p1wins++;
			} else if (winner == OthelloBoard.P2) {
				p2wins++;
			}
		}
	}
	/**
	 * Prints the results after the simulation: win probabilities for P1 (Random) and P2 (Random).
	 */
	public void printResults() {
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {
		int numGames = 10000;

		// Create the controller and run the simulation
		OthelloControllerRandomVSRandom controller = new OthelloControllerRandomVSRandom(numGames);
		controller.runSimulation();
		controller.printResults();
	}
}

