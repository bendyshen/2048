package project.weicheng.yuan;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Controller class responsible for handling interactions and logic in the welcome view of the game.
 */
@Generated
public class WelcomeController {

    /** Input field for the desired board size. */
    @FXML
    private TextField boardSizeInput;

    /** Dropdown selection for game modes. */
    @FXML
    private ComboBox<String> gameMode;

    /** Static variable to store the user-selected board size. */
    public static int selectedBoardSize;

    /** Static variable to store the user-selected game mode. */
    public static String selectedMode;

    /**
     * Initializes the components in the welcome view.
     * Populates the game modes for the user to choose from.
     */
    @FXML
    public void initialize() {
        gameMode.getItems().addAll("Easy", "Hard");
    }

    /**
     * Displays an alert dialog to the user.
     * 
     * @param title   The title of the alert.
     * @param content The main content message of the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Begins the game using the user-selected board size and game mode.
     * Defaults to a board size of 4 if no input is provided.
     * Shows an alert if the board size is less than 4.
     */
    @FXML
    public void startGame() {
        if (boardSizeInput.getText().isEmpty()) {
            selectedBoardSize = 4;
        } else {
            selectedBoardSize = Integer.parseInt(boardSizeInput.getText());
        }

        selectedMode = gameMode.getSelectionModel().getSelectedItem();
        if (selectedBoardSize >= 4) {
            // Close the welcome window
            Stage stage = (Stage) boardSizeInput.getScene().getWindow();
            stage.close();

            new Controller().openGameWindow();
        } else {
            showAlert("Warning", "Grid dimension must be at least 4! ");
        }
    }

    @FXML
    private HBox timerBox;

    @FXML
    private Label timerLabel;

}
