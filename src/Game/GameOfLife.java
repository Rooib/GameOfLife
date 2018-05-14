package Game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains the Game of Life logic and includes
 * the API for communicating with it
 * The game is a two dimensional boolean array where
 * true represents that the cell is alive and false
 * that the cell is dead
 *
 * @author Jan Leuschner
 * @version 2018-05-10
 */
public class GameOfLife {


    private boolean[][] gameField;

    private boolean[][] referenceField;

    private List<boolean[][]> oldConfigurations;

    /**
     * Constructor for a new Game of life
     *
     * @param width  - Width in number of Cells
     * @param height - Height in number of Cells
     */
    public GameOfLife(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Game field cannot have negative sizes!");
        }
        gameField = new boolean[height][width];
        referenceField = new boolean[height][width];
        oldConfigurations = new ArrayList<>();

    }

    /**
     * Creates a new game of life with an already given configuration of the cells
     *
     * @param gameConfiguration - array containing the configuration
     */
    public GameOfLife(final boolean[][] gameConfiguration) {
        this.gameField = gameConfiguration;
        this.referenceField = gameConfiguration;
        oldConfigurations = new ArrayList<>();
    }


    /**
     * Marks the cell at the given coordinates as alife
     *
     * @param x - coordinate of cell
     * @param y - coordinate of cell
     */
    public void markCellAsAlife(int x, int y) {
        gameField[y][x] = true;
    }

    /**
     * Marks the cell at the given coordinates as dead
     *
     * @param x - coordinate of cell
     * @param y - coordinate of cell
     */
    public void markCellAsDead(int x, int y) {
        gameField[y][x] = false;
    }

    /**
     * Calculates the next Generation and prints the field if
     * the user wants it to
     * Saves the old Configurations
     *
     * @param display - if the generation calculated should be printed on the terminal
     */
    private void calculateNextGeneration(final boolean display) {

        //create a new old config so we don't save the reference to the array
        boolean[][] oldConfig = new boolean[gameField.length][gameField[0].length];

        //Deep copy the gameField to reference and oldConfig
        for (int i = 0; i < gameField.length; i++) {
            boolean[] copyRow = gameField[i];
            int rowLength = gameField[i].length;
            System.arraycopy(copyRow, 0, referenceField[i], 0, rowLength);
            System.arraycopy(copyRow, 0, oldConfig[i], 0, rowLength);
        }
        addToOldConfig(oldConfig);

        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[y].length; x++) {
                int neigbhourCount = getNumberOfNeighbours(x, y);

                //Cell lives
                if (referenceField[y][x]) {
                    if (neigbhourCount < 2 || neigbhourCount > 3) {
                        this.markCellAsDead(x, y);
                    }
                } else {
                    if (neigbhourCount == 3) {
                        this.markCellAsAlife(x, y);
                    }
                }
            }
        }

        if (display) {
            printGeneration();
        }

    }

    /**
     * Checks for surrounding neighbours of a given cell
     *
     * @param x - coordinate of the cell
     * @param y - coordinate of the cell
     * @return number of neighbors at (x,y)
     */
    private int getNumberOfNeighbours(final int x, final int y) {
        int neighbourCount = 0;

        for (int yOff = -1; yOff < 2; yOff++) {
            for (int xOff = -1; xOff < 2; xOff++) {
                if (yOff == 0 && xOff == 0) {
                    continue;
                }
                int newX = Math.floorMod(x - xOff, gameField.length);
                int newY = Math.floorMod(y - yOff, gameField.length);
                if (referenceField[newY][newX]) {
                    neighbourCount++;
                }
            }
        }

        return neighbourCount;
    }

    private void printGeneration() {
        for (boolean[] row : gameField) {
            for (boolean col : row) {
                System.out.print(col + ",");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Calculates n many next Generations and displays all of them
     * if the display flag is set
     *
     * @param n       - number of generations to calculate
     * @param display - wether to print the generation on the terminal
     */
    public void calcNGenerations(final int n, final boolean display) {
        if (n < 1) {
            throw new IllegalArgumentException("You must at least calculate one next generation!");
        }
        for (int i = 0; i < n; i++) {
            calculateNextGeneration(display);
        }
    }

    /**
     * Tells if there are no more further generations to calculate
     *
     * @return - true if the game is over, else false
     */
    public boolean isDone() {
        boolean[][] oldConfig = getCurrentConfiguration();
        calculateNextGeneration(false);
        if (Arrays.equals(oldConfig, gameField)) {
            return true;
        } else {
            this.gameField = oldConfig;
            return false;
        }
    }


    /**
     * Returns the current configuration of the Game
     *
     * @return two-dimensional boolean array as configuration
     */
    public boolean[][] getCurrentConfiguration() {
        return this.gameField;
    }

    /**
     * Saves an old Configuration of the game to a list
     * containing maximum 50 old configurations
     * <p>
     * Parameter won't be checked since this is a private method
     *
     * @param gameConfig - the configuration to be saved
     */
    private void addToOldConfig(boolean[][] gameConfig) {
        if (oldConfigurations.size() == 50) {
            oldConfigurations.remove(0);
        }
        oldConfigurations.add(gameConfig);
    }

    /**
     * Returns the previous generation of this game and
     * also sets the current managed generation to the previous one
     *
     * @return - if existing, the previous generation else the current generation
     */
    private boolean[][] getAndSetPreviousGeneration() {
        if (oldConfigurations.size() == 0) {
            return gameField;
        } else {
            gameField = oldConfigurations.get(oldConfigurations.size() - 1);
            oldConfigurations.remove(oldConfigurations.size() - 1);
            return gameField;
        }

    }

    /**
     * Sets the game back to the n generations before
     *
     * @param n - how many generations to go back
     * @return the calculated n oldest generation
     */
    public boolean[][] getNPreviousGenerations(final int n) {
        for (int i = 0; i < n; i++) {
            gameField = getAndSetPreviousGeneration();
        }
        return gameField;
    }

}
