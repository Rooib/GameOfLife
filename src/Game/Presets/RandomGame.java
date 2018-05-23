package Game.Presets;

import Game.GameOfLife;

/**
 * Class to generate random instances of the Game of Life
 *
 * @author Jan Leuschner
 * @version 2018-05-23
 */
public class RandomGame {

    private RandomGame() {
        System.exit(-1);
    }

    /**
     * Returns a new quadratic Game of life with random cells being alive and dead.
     *
     * @param percentToBeAlive - Number between 0-1 specifying the percentage of cells being alive
     * @return - Game of Life with a random config and size
     */
    public static GameOfLife createRandomGame(final double percentToBeAlive) {
        // random size between 10x10 and 100x100
        int size = (int) (Math.random() * 90 + 10);
        GameOfLife gameOfLife = new GameOfLife(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() > percentToBeAlive) {
                    gameOfLife.markCellAsAlive(i, j);
                    gameOfLife.markCellAsNotVisited(i, j);
                }
            }
        }

        return gameOfLife;
    }
}
