package GUI;

import javafx.scene.control.Alert;

/**
 * Simple Class to create various PopUp Dialogs
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
public class PopUpInfo {

    /**
     * Since this is a helper class instances are not
     * reasonable
     */
    private PopUpInfo() {
        //You shall not instantiate this class!!
        System.exit(-1);
    }

    /**
     * Creates a information pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     * @param title   - title to be set for the window
     */
    public static void createInformationPopup(final String message, final String title) {
        createGeneralPopup(Alert.AlertType.INFORMATION, message, title);
    }

    /**
     * Creates a warning pop up dialog with the given message
     *
     * @param message - shown as content by this dialog
     * @param title   - title to be set for the window
     */
    public static void createWarningPopup(final String message, final String title) {
        createGeneralPopup(Alert.AlertType.WARNING, message, title);
    }

    /**
     * Crates a general popup window with any alert type, message and title
     *
     * @param alertType - Type of the Popup window
     * @param message - message to bbe shown to the user
     * @param title - title of the window
     */
    private static void createGeneralPopup(final Alert.AlertType alertType, final String message, final String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
