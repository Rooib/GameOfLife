package GUI;

import javafx.scene.control.Alert;

/**
 * Simple Class to create various PopUp Dialogs
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
class PopUpInfo {
    // TODO String als Konstante

    /**
     * Creates a information pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     */
    static void createInformationPopup(final String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Diese Eingabe stimmt so nicht!");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Creates a warning pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     */
    static void createWarningPopup(final String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Diese Eingabe stimmt so nicht!");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
