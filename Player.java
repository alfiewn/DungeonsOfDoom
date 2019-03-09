
public class Player {

	protected GameLogic game;
	
	public Player() {
		
	}
	
	public Player(GameLogic game) {
		this.game = game;
	}
	
	/**
	 * Processes the command. It should return a reply in form of a String, as the
	 * protocol dictates. Otherwise it should return the string "Invalid".
	 *
	 * @param command
	 *            : Input entered by the user.
	 * @return : Processed output or Invalid if the @param command is wrong.
	 */
	protected String getNextAction(String command) {
		if (command.equals("HELLO")) {
			String goldRequired = game.hello();
			return "Gold to win: " + goldRequired;
		} else if (command.contains("MOVE")) {
			char direction = command.charAt(5);
			game.switchPlayerTurn(game.getPlayerTurn());
			return game.move(direction);
		} else if (command.equals("PICKUP")) {
			game.switchPlayerTurn(game.getPlayerTurn());
			return game.pickup() + ". Gold owned: " + game.getGoldOwned();
		} else if (command.equals("LOOK")) {
			return game.look();
		} else if (command.equals("QUIT")) {
			game.quitGame();
		} else {
			return "Invalid";
		}
		return "";
	}
	
}
