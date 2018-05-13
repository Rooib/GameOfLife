package sample;

import Game.GameOfLife;
import Util.FileParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        GameOfLife game = new GameOfLife(5, 5);
        game.markCellAsAlife(1, 2);
        game.markCellAsAlife(2, 2);
        game.markCellAsAlife(3, 2);
        game.printGeneration();
        game.calcNGenerations(5, true);
        FileParser fileParser = new FileParser();
        fileParser.createConfigurationFromFile("test.csv");
        System.exit(0);
    }
}
