package GUI;

import Game.GameOfLife;
import Util.FileParser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Helper class for saving a GameOfLife to a file
 * Keeps track of the current file to save to and if
 * any file is selected
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
public class GameSaver {

    private static String currentFile;

    /**
     * If a current file is selected the Game will automatically
     * safe to this file if the save option is clicked.
     * Else it will open a dialog to choose a file to save to
     */
    public static void saveFile(final Stage rootWindow, final GameOfLife gameOfLife) {
        if (currentFile == null) {
            saveAsFile(rootWindow,gameOfLife);
        } else {
            try {
                FileParser.saveGameToFile(gameOfLife,currentFile);
            } catch (IOException e) {
                PopUpInfo.createInformationPopup("Fehler während speichern der Datei!");
            }

        }
    }

    public static void saveAsFile(final Stage rootWindow, final GameOfLife gameOfLife) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Game of Life speichern");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showSaveDialog(rootWindow);
        if(file != null) {
            currentFile = file.getAbsolutePath();
            try {
                FileParser.saveGameToFile(gameOfLife,file.getAbsolutePath());
                PopUpInfo.createInformationPopup("Die Datei wurde gespeichert");
            } catch (IOException e) {
                PopUpInfo.createInformationPopup("Fehler während speichern der Datei!");
            }
        }

    }
}
