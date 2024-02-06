package project.weicheng.yuan;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class responsible for managing the game board and its interactions.
 */
@Generated
public class Controller {
    // Member variables
    private int boardSize = WelcomeController.selectedBoardSize;
    private Label[][] boardLabels = new Label[boardSize][boardSize];
    private Board board = new Board(boardSize);
    private EndCheck endCheck = new EndCheck(board);
    private Rectangle[][] boardRectangles = new Rectangle[boardSize][boardSize];
    private String selectedMode = WelcomeController.selectedMode;
    private int currScore;
    private int bestScore = 0;
    @FXML
    private GridPane gameGrid;

    /**
     * Initializes the game board upon creation.
     */
    public void initialize() {
        generateGameBoard(boardSize);
        board.generateTwoRandomTiles();
        updateBoardLabels();
        if (selectedMode.equals("Hard")) {
            setupTimer();
        }
    }


    /**
     * Handles the action when the "Left" button or arrow key is pressed.
     */
    public void left() {
        board.slideTile(Direction.LEFT);
        update();
        if (selectedMode.equals("Hard")) {
            setupTimer();
        }
    }

    /**
     * Handles the action when the "Right" button or arrow key is pressed.
     */
    public void right() {
        board.slideTile(Direction.RIGHT);
        update();
        if (selectedMode.equals("Hard")) {
            setupTimer();
        }
    }

    /**
     * Handles the action when the "Up" button or arrow key is pressed.
     */
    public void up() {
        board.slideTile(Direction.UP);
        update();
        if (selectedMode.equals("Hard")) {
            setupTimer();
        }
    }

    /**
     * Handles the action when the "Down" button or arrow key is pressed.
     */
    public void down() {
        board.slideTile(Direction.DOWN);
        update();
        if (selectedMode.equals("Hard")) {
            setupTimer();
        }
    }

    /**
     * Updates the game state and UI components based on the current board status and selected game
     * mode.
     * This method performs the following sequence of actions:
     *     Check for empty positions on the board.
     *     If there are any empty positions, generate two random tiles.
     *     Check the current game state to determine if the game has ended or if any other
     * significant events have occurred.
     *     Update the board's UI representation.
     *     Update the displayed score.
     *     Update the displayed best score.
     *     If the game mode is set to "Hard", reset the timer for added challenge.
     */
    public void update() {
        board.checkEmptyPositions();
        if (board.getEmptyPositions().size() != 0) {
            board.generateTwoRandomTiles();
        }
        updateBoardLabels();
        updateScoreDisplay();
        updateBestScoreDisplay();
        checkGameState();
        if (selectedMode.equals("Hard")) {
            resetTimer();
        }
    }

    // Track Scores
    @FXML
    private Label bestScoreLabel;

    @FXML
    private Label scoreLabel;

    /**
     * Updates the score display with the current score.
     */
    public void updateScoreDisplay() {
        currScore = board.getScore();
        scoreLabel.setText(Integer.toString(currScore));
    }

    /**
     * Updates the best score display with the highest score.
     */
    public void updateBestScoreDisplay() {
        if (currScore > bestScore) {
            bestScore = currScore;
        }
        bestScoreLabel.setText(String.valueOf(bestScore));
    }



    // Check game state after everymove
    @FXML
    private Label gameStatus;

    /**
     * Checks the current game state (win/lose) and responds accordingly.
     */
    public void checkGameState() {
        if (endCheck.hasWon()) {
            System.out.println("Detected win condition."); // Diagnostic print
            showMessage("Congratulations!", "You have won the game!");
        } else if (endCheck.hasLost()) {
            System.out.println("Detected loss condition."); // Diagnostic print
            showMessage("Oops!", "You have lost the game. Try again!");
        }
        if (endCheck.hasWon() || endCheck.hasLost()) {
            resetGame();
            countdownTimer.stop();
        }
    }

    /**
     * Displays a given message as a dialog box.
     * 
     * @param title The title of the dialog box.
     * @param message The message to be displayed inside the dialog box.
     */
    public void showMessage(String title, String message) {
        System.out.println("Attempting to show alert...");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays the win message.
     */
    public void displayWinMessage() {
        gameStatus.setText("Congratulations! You Won!");
    }

    /**
     * Displays the loss message.
     */
    public void displayLossMessage() {
        gameStatus.setText("Sorry, You Lost. Try Again!");
    }

    /**
     * Determines and returns the color corresponding to a specific tile value.
     *
     * @param value The value of the tile.
     * @return A string representation of the color.
     */
    private String getColorForValue(int value) {
        switch (value) {
            case 2:
                return "#eee4da";
            case 4:
                return "#ede0c8";
            case 8:
                return "#f2b179";
            case 16:
                return "#f59563";
            case 32:
                return "#f67c5f";
            case 64:
                return "#f65e3b";
            case 128:
                return "#edcf72";
            case 256:
                return "#edcc61";
            case 512:
                return "#edc850";
            case 1024:
                return "#edc53f";
            case 2048:
                return "#edc22e";
            default:
                return "#cdc1b4"; // This is for 0 or any value not in the above list
        }
    }

    /**
     * Updates the board's labels based on the tiles' values.
     */
    public void updateBoardLabels() {
        Tile[][] tiles = board.getTiles();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int value = tiles[row][col].getValue();
                String color = getColorForValue(value);

                if (value == 0) {
                    boardLabels[row][col].setText("");
                } else {
                    boardLabels[row][col].setText(String.valueOf(value));
                }

                // Change the rectangle color
                boardRectangles[row][col].setFill(Color.valueOf(color));
            }
        }
    }


    /**
     * Generates the visual representation of the game board.
     *
     * @param boardSize The size of the board (e.g., 4 for a 4x4 board).
     */
    public void generateGameBoard(int boardSize) {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                // Create rectangle and label
                Rectangle rect = new Rectangle(60, 60);
                rect.setArcHeight(10);
                rect.setArcWidth(10);
                rect.setFill(Color.valueOf("#f2ebd3"));

                // Store rectangle in array
                boardRectangles[row][col] = rect;

                Label label = new Label(""); // default value
                label.setStyle("-fx-font-size: 18pt;");

                // Store label in array
                boardLabels[row][col] = label;

                StackPane stack = new StackPane();
                stack.getChildren().addAll(rect, label);

                gameGrid.add(stack, col, row);
            }
        }
    }

    /**
     * Opens a new game window.
     */
    public void openGameWindow() {

        // Use WelcomeController.selectedBoardSize and WelcomeController.selectedMode
        // Initialize the game and open the main game window (from primary.fxml)
        // ... (your game initialization code here)
        try {
            FXMLLoader loader =
                    new FXMLLoader(WelcomeController.class.getResource("/primary.fxml"));
            loader.setController(this); // Set the controller instance
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("Game Window");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the game board to its initial state.
     */
    @FXML
    private void resetGame() {
        board = new Board(boardSize);
        endCheck = new EndCheck(board);
        board.setScore(0);
        initialize();
        updateScoreDisplay();
        updateBestScoreDisplay();
        if (selectedMode.equals("Hard")) {
            resetTimer();
        }
    }

    @FXML
    private Label timerLabel;
    private Timeline countdownTimer;
    private static final int START_TIME = 5; // in seconds
    private int timeLeft = START_TIME;

    private void setupTimer() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        countdownTimer =
                new Timeline(new KeyFrame(Duration.seconds(1), event -> updateCountdown()));
        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    private void updateCountdown() {
        timeLeft--;
        timerLabel.setText(String.valueOf(timeLeft));
        if (timeLeft == 0) {
            update();
        }
    }

    private void resetTimer() {
        timeLeft = START_TIME;
        timerLabel.setText(String.valueOf(timeLeft));
    }


}
