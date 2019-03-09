import java.util.concurrent.ThreadLocalRandom;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	/* Representation of the map */
	private char[][] map;

	/* Map name */
	private String mapName;

	/* Gold required for the human player to win */
	private int goldRequired;

	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 1;
		map = new char[][] {
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', 'G', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'E', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', 'E', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
						'#' } };
	}

	/**
	 * Constructor that accepts a map to read in from.
	 *
	 * @param :
	 *            The filename of the map file.
	 */
	public Map(String fileName) {
		readMap(fileName);
	}

	/**
	 * @return : Gold required to exit the current map.
	 */
	protected int getGoldRequired() {
		return goldRequired;
	}

	/**
	 * mutator method for goldRequired
	 * 
	 * @param goldRequired
	 */
	protected void setGoldRequired(int goldRequired) {
		if (goldRequired >= 0) {
			this.goldRequired=goldRequired;
		}
	}
	
	/**
	 * @return : The map as stored in memory.
	 */
	protected char[][] getMap() {
		return map;
	}

	/**
	 * @return : The name of the current map.
	 */
	protected String getMapName() {
		return mapName;
	}

	/**
	 * Reads the map from file.
	 *
	 * @param :
	 *            Name of the map's file.
	 */
	protected void readMap(String fileName) {

		File f = new File(fileName);

		/* checks that the file exists, if so reads from it */
		if (f.exists() && !f.isDirectory()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

				ArrayList<String> a = new ArrayList<String>();
				String line;

				// read each line into an array until the end of the file is reached
				while ((line = reader.readLine()) != null) {
					a.add(line);
				}

				mapName = a.get(0).toString().substring(5);

				String goldRequiredString = a.get(1).toString().substring(4);
				goldRequired = Integer.parseInt(goldRequiredString);

				int mapRows = a.size() - 2;
				int mapColumns = a.get(2).toString().length();

				map = new char[mapRows][mapColumns];

				/**
				 * for each array element, read each individual character into the 2D char array
				 */
				for (int i = 2; i < mapRows + 2; i++) {
					for (int count = 0; count < mapColumns; count++) {
						map[i - 2][count] = a.get(i).toString().charAt(count);
					}
				}

			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("No map found with filename 'example_map.txt'");
			System.exit(0);
		}
	}

	/**
	 * generates a random start position for the player or bot
	 * 
	 * @return randomPosition
	 */
	protected int[] generateStartPosition() {
		int width = getMap()[0].length;
		int height = getMap().length;
		int[] randomPosition = { 1, 1 };

		/**
		 * loop while the random position generated is not a '#' or 'G' on the map, copy
		 * each position to a 1D array to return
		 */
		do {
			int startX = ThreadLocalRandom.current().nextInt(1, width);
			int startY = ThreadLocalRandom.current().nextInt(1, height);
			randomPosition[0] = startY;
			randomPosition[1] = startX;
		} while (this.getMap()[randomPosition[0]][randomPosition[1]] == '#'
				|| this.getMap()[randomPosition[0]][randomPosition[1]] == 'G');

		return randomPosition;

	}

}
