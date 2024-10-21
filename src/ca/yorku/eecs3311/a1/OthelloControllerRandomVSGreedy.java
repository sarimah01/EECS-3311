package ca.yorku.eecs3311.a1;

/**
 * The goal here is to print out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy. What is your conclusion, which is the better strategy?
 * @author ilir
 *
 */
public class OthelloControllerRandomVSGreedy {
	private int p1wins = 0; // Count of wins for Player 1 (Random)
	private int p2wins = 0; // Count of wins for Player 2 (Greedy)
	private int numGames;   // Total number of games to simulate

	// Constructor to initialize the number of games
	public OthelloControllerRandomVSGreedy(int numGames) {
		this.numGames = numGames;
	}
	/**
	 * Runs the simulation of games between a Random player (P1) and a Greedy player (P2).
	 */
	public void runSimulation() {
		for (int i = 0; i < numGames; i++) {
			Othello othello = new Othello(); // Create a new game for each simulation
			PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1); // P1: Random player
			PlayerGreedy player2 = new PlayerGreedy(othello, OthelloBoard.P2); // P2: Greedy player

			// Simulate the game until it's over
			while (!othello.isGameOver()) {
				if (othello.getWhosTurn() == OthelloBoard.P1) {
					Move move = player1.getMove(); // Get a random move for P1
					if (move != null && othello.move(move.getRow(), move.getCol())) {
						// Move was successful, continue to next turn
						continue;
					}
				} else if (othello.getWhosTurn() == OthelloBoard.P2) {
					Move move = player2.getMove(); // Get a greedy move for P2
					if (move != null && othello.move(move.getRow(), move.getCol())) {
						// Move was successful, continue to next turn
						continue;
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
	 * Prints the results after the simulation: win probabilities for P1 (Random) and P2 (Greedy).
	 */
	public void printResults() {
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}


	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {
		
		int numGames = 10000; // Simulate 10,000 games

		// Create the controller and run the simulation
		OthelloControllerRandomVSGreedy controller = new OthelloControllerRandomVSGreedy(numGames);
		controller.runSimulation();
		controller.printResults();
		
	}
}

