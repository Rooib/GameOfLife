package Main;

import GUI.MainWindow;
import javafx.application.Application;

/**
 * Main Class of the project. Mainly used for launching the Application
 * @author Jan Leuschner
 * @version 2018-05-14
 */
public class Main {


    public static void main(String[] args) {
        Application.launch(MainWindow.class, args);

        System.exit(0);
    }
}
