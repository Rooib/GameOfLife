package Main;

import GUI.MainWindow;
import Game.GameOfLife;
import javafx.application.Application;

/**
 * Main Class of the project. Mainly used for launching the Application
 * @author Jan Leuschner
 * @version 2018-05-14
 */
public class Main {


    public static void main(String[] args) {
        //launch(args);
        GameOfLife game = new GameOfLife(5, 5);
        game.markCellAsAlife(1, 2);
        game.markCellAsAlife(2, 2);
        game.markCellAsAlife(3, 2);
        game.printGeneration();
        game.calcNGenerations(5, true);
        Application.launch(MainWindow.class, args);

        System.exit(0);
    }
}
