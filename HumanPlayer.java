import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer extends Player {

	/**
	 * default constructor
	 */
	public HumanPlayer() {
		
	}
	
	/**
	 * constructor for when a game object is passed
	 * 
	 * @param game
	 */
	public HumanPlayer(GameLogic game) {
		super(game);
	}

	

	/**
	 * Reads player's input from the console.
	 * <p>
	 * return : A string containing the input the player entered.
	 */
	protected String getInputFromConsole() {

		System.out.println("Please enter a command: ");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			// Keep on accepting input from the command-line
			while (true) {
				String command = reader.readLine();

				// Close on an End-of-file (EOF) (Ctrl-D on the terminal)
				if (command == null) {
					// Exit code 0 for a graceful exit
					System.exit(0);
				}

				// Otherwise, (attempt to) process the character
				return command;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());

			return null;
		}

	}

	
}