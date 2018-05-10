package sample;

import Game.GameOfLife;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
       // launch(args);
        GameOfLife game = new GameOfLife(20,20);
        game.markCellAsAlife(0,0);
        game.markCellAsAlife(0,19);
        game.markCellAsAlife(19,0);
        System.out.println(game.numberOfNeighbours(0,0));
        System.exit(0);
    }
}
