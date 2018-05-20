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
public class FileOperator {

    private static String currentFile;

    private final static String INPUT_INVALID_TITLE = "Diese Eingabe stimmt so nicht!";

    private final static String SUCCESS = "Erfolgreich";

    //TODO Strings als konstanten!

    /**
     * If a current file is selected the Game will automatically
     * safe to this file if the save option is clicked.
     * Else it will open a dialog to choose a file to save to
     *
     * @param rootWindow - parent Window
     * @param gameOfLife - gameOfLife Instance that should be saved
     */
    public static void saveFile(final Stage rootWindow, final GameOfLife gameOfLife) {
        if (currentFile == null) {
            saveAsFile(rootWindow, gameOfLife);
        } else {
            try {
                FileParser.saveGameToFile(gameOfLife, currentFile);
            } catch (IOException e) {
                PopUpInfo.createWarningPopup("Fehler während speichern der Datei!",INPUT_INVALID_TITLE);
            }

        }
    }

    /**
     * Opens a dialog for selection/ creating a file the configurations
     * should be saved to
     *
     * @param rootWindow - parent Window
     * @param gameOfLife - gameOfLife Instance that should be saved
     */
    public static void saveAsFile(final Stage rootWindow, final GameOfLife gameOfLife) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Game of Life speichern");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
        File file = fileChooser.showSaveDialog(rootWindow);
        if (file != null) {
            currentFile = file.getAbsolutePath();
            try {
                FileParser.saveGameToFile(gameOfLife, file.getAbsolutePath());
                PopUpInfo.createInformationPopup("Die Datei wurde gespeichert",SUCCESS);
            } catch (IOException e) {
                PopUpInfo.createWarningPopup("Fehler während speichern der Datei!",INPUT_INVALID_TITLE);
            }
        }
    }

    public static boolean[][] openFile(final Stage rootWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
        try {

            File file = fileChooser.showOpenDialog(rootWindow);

            if (file == null) {
                return new boolean[0][0];
            }

            boolean[][] configuration = FileParser.createConfigurationFromFile(file.getAbsolutePath());
            currentFile = file.getAbsolutePath();

            if (configuration == null) {
                PopUpInfo.createInformationPopup("Die Datei konnte nicht richtig gelesen werden!",INPUT_INVALID_TITLE);
            }

            return configuration;

        } catch (IllegalStateException e) {
            PopUpInfo.createWarningPopup("Die Datei enthält kein rechteckiges Spielfeld!",INPUT_INVALID_TITLE);
        } catch (IOException e) {
            PopUpInfo.createWarningPopup("Die Datei konnte nicht gefunden oder geladen werden!",INPUT_INVALID_TITLE);
        }

        return new boolean[0][0];
    }

    /**
     * Opens a file from the given File path and returns, if successful, the files
     * game configuration
     *
     * @param filePath - path to the wanted file
     * @return game configuration described in the file
     */
    public static boolean[][] openFileFromPath(final String filePath) {
        try {
            boolean[][] configuration = FileParser.createConfigurationFromFile(filePath);
            currentFile = filePath;
            return configuration;
        } catch (IOException e) {
            PopUpInfo.createWarningPopup("Die Datei konnte nicht gefunden oder geladen werden!",INPUT_INVALID_TITLE);
        } catch (IllegalStateException e) {
            PopUpInfo.createWarningPopup("Die Datei enthält kein rechteckiges Spielfeld!",INPUT_INVALID_TITLE);
        }
        return new boolean[0][0];
    }

    public static void resetFile() {
        currentFile = null;
    }
}
