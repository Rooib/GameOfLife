package Game;


/**
 * Represents the Game of Life and includes
 * the API for communicating with it
 *
 * @author Jan Leuschner
 * @version 2018-05-10
 */
public class GameOfLife {


    private boolean[][] gameField;

    private boolean[][] referenceField;

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

    }


    public void markCellAsAlife(int x, int y) {
        gameField[y][x] = true;
    }

    public void markCellAsDead(int x, int y) {
        gameField[y][x] = false;
    }

    public void calculateNextGeneration() {

        //Deep copy the gameField
        for (int i = 0; i < gameField.length; i++) {
            boolean[] copyRow = gameField[i];
            int rowLength = gameField[i].length;
            System.arraycopy(copyRow, 0, referenceField[i], 0, rowLength);
        }
    }

    /**
     * Checks for surrounding neighbours
     *
     * @param x - coordinate of the cell
     * @param y - coordinate of the cell
     * @return number of neighbors at (x,y)
     */
    public int numberOfNeighbours(int x, int y) {
        int neighbourCount = 0;

        for (int yOff = -1; yOff < 2; yOff++) {
            for (int xOff = -1; xOff < 2; xOff++) {
                if (yOff == 0 && xOff == 0) {
                    continue;
                }
                int newX = Math.floorMod(x - xOff, gameField.length);
                int newY = Math.floorMod(y - yOff, gameField.length);
                if (gameField[newY][newX]) {
                    neighbourCount++;
                }
            }
        }

        return neighbourCount;
    }
}
