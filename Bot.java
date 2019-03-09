import java.util.concurrent.ThreadLocalRandom;

public class Bot extends Player {
	
	/**
	 * default constructor
	 */
	public Bot(GameLogic game) {
		super(game);
	}

	/**
	 * generates a random direction for the bot to move in
	 * 
	 * @return MOVE command with a random direction
	 */
	protected String getInputFromBot() {

		System.out.println("The bot's turn:");

		int randomNumber = ThreadLocalRandom.current().nextInt(1, 5);
		String direction = "";
		switch (randomNumber) {
		case 1:
			direction = "N";
			break;
		case 2:
			direction = "E";
			break;
		case 3:
			direction = "S";
			break;
		case 4:
			direction = "W";
			break;
		}

		String command = "MOVE " + direction;
		return command;
	}

}
