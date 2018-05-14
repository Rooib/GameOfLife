package GUI;

import javafx.scene.control.Alert;

/**
 * Simple Class to create various PopUp Dialogs
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
class PopUpInfo {

    private final static String TITLE_TEXT  = "Diese Eingabe stimmt so nicht!";

    /**
     * Creates a information pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     */
    static void createInformationPopup(final String message) {
        createGeneralPoput(Alert.AlertType.INFORMATION,message);
    }

    /**
     * Creates a warning pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     */
    static void createWarningPopup(final String message) {
        createGeneralPoput(Alert.AlertType.WARNING,message);
    }

    private static void createGeneralPoput(Alert.AlertType alertType,final String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(TITLE_TEXT);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
