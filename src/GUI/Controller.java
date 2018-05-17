package GUI;

import Game.GameOfLife;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;



/**
 * Contains the logic for the Game and the different methods
 * that get triggered by the inputs of the GUI
 *
 * @author Jan Leuschner
 * @version 2018-05-14
 */
public class Controller {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField widthInput;

    @FXML
    private TextField heightInput;

    @FXML
    private Button start;

    @FXML
    private Canvas canvas;

    @FXML
    private Button genForward;

    @FXML
    private Button genBackward;

    @FXML
    private TextField nGenInput;

    @FXML
    private Slider slider;

    /**
     * The primary stage
     */
    private Stage stage;

    private Timeline timer;

    private GameOfLife gameOfLife;

    private final String INPUT_VALID_NUMBER = "Bitte eine gültige Zahl > 1 eingeben!";


    /**
     * Initialises the Canvas with the given game of life dimensions
     * Always creates a new GameOfLife
     */
    public void onStartClick() {
        stopTimer();
        FileOperator.resetFile();
        try {
            int x = Integer.parseInt(widthInput.getText());
            int y = Integer.parseInt(heightInput.getText());
            gameOfLife = new GameOfLife(x, y);
            drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
        } catch (IllegalArgumentException e) {
            PopUpInfo.createInformationPopup(INPUT_VALID_NUMBER);
        }

    }

    /**
     * Handles the event when the user clicks on the canvas. It switches
     * the state of the clicked cell on the canvas. It also updates the current
     * game of life instance and redraws the canvas
     *
     * @param event - the MouseClickEvent
     */
    public void clickCanvas(MouseEvent event) {

        //Stop if there is no game of life instance
        if ( gameOfLife == null) {
            return;
        }

        boolean[][] gameConfig = gameOfLife.getCurrentConfiguration();

        double clickX = event.getX();
        double clickY = event.getY();
        //Calculate the width of a rectangle on the canvas
        int rectWidth = (int) Math.ceil(canvas.getWidth() / gameConfig[0].length);
        int rectHeight = (int) Math.ceil(canvas.getHeight() / gameConfig.length);
        //Calculate how many rows and cols are even possible
        int colCount = (int) Math.ceil(canvas.getWidth() / rectWidth);
        int rowCount = (int) Math.ceil(canvas.getHeight() / rectHeight);

        int xIndex = 0;
        int yIndex = 0;

        //Calc x coord of cell
        for (int i = 1; i <= colCount; i++) {
            if (i * rectWidth >= clickX) {
                xIndex = i - 1;
                break;
            }
        }

        //Calc y coord of cell
        for (int i = 1; i <= rowCount; i++) {
            if (i * rectHeight >= clickY) {
                yIndex = i - 1;
                break;
            }
        }
        //switch cell state
        if (gameConfig[yIndex][xIndex]) {
            gameOfLife.markCellAsDead(xIndex, yIndex);
        } else {
            gameOfLife.markCellAsAlife(xIndex, yIndex);
        }

        drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
    }

    /**
     * Determine the calculation per minute when the slider
     * is moved and set a timer to do so.
     * Stops every other timer from before
     */
    public void onSlide() {
        if (gameOfLife == null) {
            return;
        }

        stopTimer();
        int genPerMin = (int) slider.getValue();
        //System.out.println(genPerMin);
        timer = new Timeline(new KeyFrame(Duration.millis(1000 * 60 / genPerMin),
                event -> calcAndDrawNextGeneration()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    /**
     * Method to be called by the timer each time it executes.
     * Calc and draw next generation
     */
    private void calcAndDrawNextGeneration() {
        gameOfLife.calcNGenerations(1, false);
        drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
    }

    /**
     * When the user clicks on the nextGen Button this method
     * will calc the next n Generations and draws them on the canvas.
     * Sets the generation per minute option to 0 so it won't calculate
     */
    public void calcNextGenerations() {
        if (gameOfLife == null) {
            return;
        }

        stopTimer();

        try {
            int n = Integer.parseInt(nGenInput.getText());
            gameOfLife.calcNGenerations(n, false);
            drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
        } catch (IllegalArgumentException e) {
            PopUpInfo.createInformationPopup(INPUT_VALID_NUMBER);
        }

    }

    public void goNGenerationsBack() {

        if (gameOfLife == null) {
            return;
        }
        stopTimer();

        try {
            int n = Integer.parseInt(nGenInput.getText());
            gameOfLife.getNPreviousGenerations(n);
            drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
        } catch (IllegalArgumentException e) {
            PopUpInfo.createInformationPopup(INPUT_VALID_NUMBER);
        }
    }

    private void drawConfigOnCanvas(boolean[][] gameConfig) {
        canvas.setDisable(false);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //System.out.println(canvas.heightProperty().get());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getWidth());
        int rectHeight = (int) Math.ceil(canvas.getHeight() / gameConfig.length);
        int rectWidth = (int) Math.ceil(canvas.getWidth() / gameConfig[0].length);
        int currentX = 0;
        int currentY = 0;

        for (boolean[] row : gameConfig) {
            for (boolean value : row) {

                if (value) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(currentX, currentY, rectWidth - 1, rectHeight - 1);
                } else {
                    gc.setFill(Color.RED);
                    gc.fillRect(currentX, currentY, rectWidth - 1, rectHeight - 1);
                }
                currentX += rectWidth;

            }
            currentX = 0;
            currentY += rectHeight;
        }

    }

    /**
     * Stops the timer if it still calculates
     */
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    /**
     * Saves the current game config to the previously
     * selected file. If that does not exist, save as dialog will
     * pop up
     */
    public void saveConfig() {
        FileOperator.saveFile(stage, gameOfLife);
    }

    /**
     * Creates a save dialog to save the file
     */
    public void saveAsConfig() {
        FileOperator.saveAsFile(stage, gameOfLife);
    }

    public void openConfiguration() {
        stopTimer();
        gameOfLife = new GameOfLife(FileOperator.openFile(stage));
        drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
    }

    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    /**
     * Constructs a game of life from a file dragged onto the canvas
     *
     * @param dragEvent - the Event provided by the Window
     */
    public void loadFileOnDragDropped(DragEvent dragEvent) {
        System.out.println("dragEvent");
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasFiles() ) {
            List<File> files = dragboard.getFiles();
            if(files.size()>1 || !files.get(0).getName().endsWith(".csv")) {
                return;
            }
            String filePath = files.get(0).getAbsolutePath();
            boolean[][] configuration = FileOperator.openFileFromPath(filePath);
            gameOfLife = new GameOfLife(configuration);
            drawConfigOnCanvas(configuration);
        }

    }


}
