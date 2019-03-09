README

DUNGEONS OF DOOM

DoD is a map based game you move your player around the map, attempting to pick up gold
and reach an exit point while avoiding walls and a bot who will move randomly around the map.
The user must input commands (listed below) and will be told whether they are successful.
The game has a number of rules:
1. The player can move north, south, east or west.
2. The player cannot move through walls.
3. The player cannot start on the exit tile or a gold tile.
4. A player turn is only taken up if the user moves or picks up gold.
The player can win by:
Picking up the required amount of gold (Gold required = 0) and moving to the exit tile.
The player loses if:
It is caught by the bot (They share the same tile at any point).

INSTALLATION

1. Extract CW2-an666.zip to your preffered directory.
2. Open a java-compatible command line window.
3. Navigate to the project folder using the command "cd" followed by the directory path
4. Compile the code using the command "javac *.java"

You can edit the map and required gold by opening the example_map.txt file in the project
folder. Providing the map is rectangular and is surrounded by a border of '#' and tiles have
either '.', 'G', 'E' or '#' you can edit as you wish. You can also replace this file providing
your new file is of the same format and is named example_map.txt.

RUN AND PLAY THE GAME

1. To run the game, enter the command "java GameLogic" after installation. If the command line
   window has been closed after installation, you will need to navigate to the path of the project
   folder but will not need to reinstall the program.

2. The game will now prompt you to enter a command. You can choose to enter the following:
    HELLO: This will output how much gold you are required to collect to be eligible for exit.
           This amount will reduce to 0 as you collect gold, when it is 0 you should 
    MOVE: This must be followed by a space and a direction: N, E, S or W e.g. MOVE N.
          N=NORTH
          E=WEST
          S=SOUTH
          W=WEST
          This will move the position of your player in the specified direction.
    LOOK: This will show a 5x5 view of the game map, with your player at the centre. You can
          use it to find out where the gold or exit tiles are.
    PICKUP: If you are on a tile containing gold, you will pick it up and get closer to the 
            required gold to win.
    EXIT: This command quits the game.
    After each command either SUCCESS or FAIL will be output depending on whether your command 
    was successful.
    
3. The bot will now move in a random direction. Either SUCCESS or FAIL will be output to show
   whether the bot made a valid or invalid move.
   
4. The game will prompt you to enter another command. This continues until you either win or lose
   the game. The game will output GAME OVER when it has finished, and you can quit the terminal
   window to finish.