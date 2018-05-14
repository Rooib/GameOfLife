package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root, 850, 619));
        Controller controller =  loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }


}
