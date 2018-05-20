package GUI;

import javafx.scene.control.Alert;

/**
 * Simple Class to create various PopUp Dialogs
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
class PopUpInfo {



    /**
     * Creates a information pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     * @param title   - title to be set for the window
     */
    static void createInformationPopup(final String message, final String title) {
        createGeneralPopup(Alert.AlertType.INFORMATION, message, title);
    }

    /**
     * Creates a warning pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     * @param title   - title to be set for the window
     */
    static void createWarningPopup(final String message, final String title) {
        createGeneralPopup(Alert.AlertType.WARNING, message, title);
    }

    private static void createGeneralPopup(final Alert.AlertType alertType, final String message, final String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
