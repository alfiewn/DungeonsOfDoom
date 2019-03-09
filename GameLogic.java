import java.util.Arrays;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

	/* declare class variables */
	/* variable to hold instance of Map */
	private Map map;

	/* Gold owned by player */
	private String goldOwned;

	/* the current location of the player */
	private int playerLocation[] = { 1, 1 };

	/* the current location of the bot */
	private int botLocation[] = { 1, 1 };

	/**
	 * the original value of the last pace moved into by the player
	 */
	private char previousSpace;

	/**
	 * the original value of the last pace moved into by the bot
	 */
	private char previousSpaceBot;

	/* whether it is the player's turn */
	private boolean playerTurn;

	/* whether the game is running or not */
	private boolean gameRunning;

	/**
	 * Default constructor
	 */
	public GameLogic() {
		map = new Map("example_map.txt");
		setGoldOwned("0");
		setPlayerLocation(map.generateStartPosition());
		setBotLocation(map.generateStartPosition());
		setPlayerTurn(true);
		setGameRunning(true);

	}

	/**
	 * @return if the game is running.
	 */
	protected boolean gameRunning() {
		return gameRunning;
	}

	protected void setGameRunning(boolean state) {
		gameRunning = state;
	}

	/**
	 * @return : Returns back gold player requires to exit the Dungeon.
	 */
	protected String hello() {
		return Integer.toString(map.getGoldRequired());
	}

	/**
	 * Checks if movement is legal and updates player's location on the map. If it
	 * is the players turn, updates the player's location, else update the bot's
	 * location. Then call methods to check for player or bot win.
	 * 
	 * @param direction
	 *            : The direction of the movement.
	 * @return : Protocol if success or not.
	 */
	protected String move(char direction) {
		if (direction == 'N') {
			int[] newPlayerPosition = { getPlayerLocation()[0] - 1, getPlayerLocation()[1] };
			int[] newBotPosition = { getBotLocation()[0] - 1, getBotLocation()[1] };
			if (map.getMap()[newPlayerPosition[0]][newPlayerPosition[1]] == '#') {
				return "FAIL";
			} else if (getPlayerTurn() == true) {
				if (map.getMap()[newBotPosition[0]][newBotPosition[1]] == '#') {
					return "FAIL";
				}
				setBotLocation(newBotPosition);
				checkBotWin();
				return "SUCCESS";
			} else {
				this.setPlayerLocation(newPlayerPosition);
				checkPlayerWin();
				checkBotWin();
				return "SUCCESS";
			}
		} else if (direction == 'E') {
			int[] newPlayerPosition = { getPlayerLocation()[0], getPlayerLocation()[1] + 1 };
			int[] newBotPosition = { getBotLocation()[0], getBotLocation()[1] + 1 };
			if (map.getMap()[newPlayerPosition[0]][newPlayerPosition[1]] == '#') {
				return "FAIL";
			} else if (getPlayerTurn() == true) {
				if (map.getMap()[newBotPosition[0]][newBotPosition[1]] == '#') {
					return "FAIL";
				}
				setBotLocation(newBotPosition);
				checkBotWin();
				return "SUCCESS";
			} else {
				this.setPlayerLocation(newPlayerPosition);
				checkPlayerWin();
				checkBotWin();
				return "SUCCESS";
			}
		} else if (direction == 'S') {
			int[] newPlayerPosition = { getPlayerLocation()[0] + 1, getPlayerLocation()[1] };
			int[] newBotPosition = { getBotLocation()[0] + 1, getBotLocation()[1] };
			if (map.getMap()[newPlayerPosition[0]][newPlayerPosition[1]] == '#') {
				return "FAIL";
			} else if (getPlayerTurn() == true) {
				if (map.getMap()[newBotPosition[0]][newBotPosition[1]] == '#') {
					return "FAIL";
				}
				setBotLocation(newBotPosition);
				checkBotWin();
				return "SUCCESS";
			} else {
				this.setPlayerLocation(newPlayerPosition);
				checkPlayerWin();
				checkBotWin();
				return "SUCCESS";
			}
		} else if (direction == 'W') {
			int[] newPlayerPosition = { getPlayerLocation()[0], getPlayerLocation()[1] - 1 };
			int[] newBotPosition = { getBotLocation()[0], getBotLocation()[1] - 1 };
			if (map.getMap()[newPlayerPosition[0]][newPlayerPosition[1]] == '#') {
				return "FAIL";
			} else if (getPlayerTurn() == true) {
				if (map.getMap()[newBotPosition[0]][newBotPosition[1]] == '#') {
					return "FAIL";
				}
				setBotLocation(newBotPosition);
				checkBotWin();
				return "SUCCESS";
			} else {
				this.setPlayerLocation(newPlayerPosition);
				checkPlayerWin();
				checkBotWin();
				return "SUCCESS";
			}
		}
		return null;
	}

	/**
	 * Converts the map from a 2D char array to a single string.
	 *
	 * @return : A String representation of the game map.
	 */
	protected String look() {
		int xStartPosition = this.getPlayerLocation()[1] - 2;
		int yStartPosition = this.getPlayerLocation()[0] - 2;
		String returnString = "";

		for (int countOne = yStartPosition; countOne < yStartPosition + 5; countOne++) {

			for (int countTwo = xStartPosition; countTwo < xStartPosition + 5; countTwo++) {
				if (countOne >= 0 && countOne < map.getMap().length - 1 && countTwo >= 0
						&& countTwo < map.getMap()[0].length) {
					returnString = returnString + map.getMap()[countOne][countTwo];
				} else {
					returnString = returnString + "#";
				}

			}
			returnString = returnString + "\n";
		}

		return returnString.substring(0, 29);

	}

	/**
	 * Processes the player's pickup command, updating the map and the player's gold
	 * amount.
	 *
	 * @return If the player successfully picked-up gold or not.
	 */
	protected String pickup() {
		if (checkForGold() == true) {
			// map.getMap()[getPlayerLocation()[0]][getPlayerLocation()[1]] = '.';
			setPreviousSpace('.');
			incrementGoldOwned();
			map.setGoldRequired(map.getGoldRequired()-1);
			return "SUCCESS";
		}
		return "FAIL";
	}

	/**
	 * Quits the game, shutting down the application.
	 */
	protected void quitGame() {
		setGameRunning(false);
	}

	/**
	 * accessor method for goldOwned
	 * 
	 * @return the amount of gold the player has
	 */
	protected String getGoldOwned() {
		return goldOwned;
	}

	/**
	 * mutator method for goldOwned
	 * 
	 * @param newGoldOwned
	 */
	protected void setGoldOwned(String GoldOwned) {
		this.goldOwned = GoldOwned;
	}

	/**
	 * method to increment goldOwned
	 */
	protected void incrementGoldOwned() {
		int temp = Integer.parseInt(goldOwned);
		temp++;
		goldOwned = Integer.toString(temp);
	}

	/**
	 * accessor method for playerLocation
	 * 
	 * @return current location of the player
	 */
	protected int[] getPlayerLocation() {
		return playerLocation;
	}

	/**
	 * mutator method for player location sets the space you are moving from to
	 * previousSpace updates playerLocation then sets previous space to store the
	 * original value of the new position. New position set to 'P'
	 * 
	 * @param newPosition
	 */
	protected void setPlayerLocation(int[] playerLocation) {
		map.getMap()[getPlayerLocation()[0]][getPlayerLocation()[1]] = getPreviousSpace();
		this.playerLocation = playerLocation;
		setPreviousSpace(map.getMap()[getPlayerLocation()[0]][getPlayerLocation()[1]]);
		map.getMap()[getPlayerLocation()[0]][getPlayerLocation()[1]] = 'P';
	}

	/**
	 * accessor method for botLocation
	 * 
	 * @return current location of the bot
	 */
	protected int[] getBotLocation() {
		return botLocation;
	}

	/**
	 * mutator method for botLocation sets the space it is moving from to
	 * previousSpace updates botLocation then sets previous space to store the
	 * original value of the new position. New position set to 'B'
	 * 
	 * @param newPosition
	 */

	protected void setBotLocation(int[] botLocation) {
		map.getMap()[getBotLocation()[0]][getBotLocation()[1]] = getPreviousSpaceBot();
		this.botLocation = botLocation;
		setPreviousSpaceBot(map.getMap()[getBotLocation()[0]][getBotLocation()[1]]);
		map.getMap()[getBotLocation()[0]][getBotLocation()[1]] = 'B';
	}

	/**
	 * method to check if the players current space used to contain 'G' before it
	 * was set to 'P'
	 * 
	 * @return whether there is gold on the tile where the player is
	 */
	protected boolean checkForGold() {
		if (getPreviousSpace() == 'G') {
			return true;
		}
		return false;
	}

	/**
	 * check if the conditions for the player to win the game have been met. If so,
	 * print a win message and quit the game.
	 */
	protected void checkPlayerWin() {
		if (getGoldOwned().equals(Integer.toString(map.getGoldRequired())) && getPreviousSpace() == 'E') {
			System.out.println("Congratulations, you have won!");
			setGameRunning(false);
		}

	}

	/*
	 * check if the conditions for the bot to win the game have been met. If so,
	 * print a loss message and quit the game
	 */
	protected void checkBotWin() {
		if (Arrays.equals(getBotLocation(), getPlayerLocation())) {
			System.out.println("Unlucky! You've been caught by the bot!");
			setGameRunning(false);
		}
	}

	/**
	 * accessor method for previousSpace
	 * 
	 * @return the character that was in the space where the player is
	 */
	protected char getPreviousSpace() {
		return previousSpace;
	}

	/**
	 * mutator method for previousSpace
	 * 
	 * @param currentSpace
	 */
	protected void setPreviousSpace(char previousSpace) {
		this.previousSpace = previousSpace;
	}

	/**
	 * accessor method for previousSpaceBot
	 * 
	 * @return the character that was in the space where the bot is
	 */
	protected char getPreviousSpaceBot() {
		return previousSpaceBot;
	}

	/**
	 * mutator method for previousSpaceBot
	 * 
	 * @param currentSpace
	 */
	protected void setPreviousSpaceBot(char previousSpaceBot) {
		this.previousSpaceBot = previousSpaceBot;
	}

	/**
	 * accessor method for playerTurn
	 * 
	 * @return whether it is the player's turn
	 */
	protected boolean getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * mutator method for playerTurn
	 * 
	 * @param newTurn
	 */
	protected void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	/**
	 * sets the boolean variable playerTurn to its opposite
	 */
	protected void switchPlayerTurn(boolean playerTurn) {
		this.playerTurn = !playerTurn;
	}

	/**
	 * main method. Creates objects of classes GameLogic, HumanPlayer and Bot runs a
	 * loop, determining if it is the players turn or not and calling methods to get
	 * input and process it for either the player or the bot
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameLogic game = new GameLogic();
		Bot bot = new Bot(game);
		HumanPlayer humanplayer = new HumanPlayer(game);
		
		String command = "";

		while (game.gameRunning() == true) {
			if (game.getPlayerTurn() == true) {
				command = humanplayer.getInputFromConsole();
				System.out.println(humanplayer.getNextAction(command));

			} else {
				command = bot.getInputFromBot();
				System.out.println(bot.getNextAction(command));
			}

		}
		System.out.println("GAME OVER");

	}
}