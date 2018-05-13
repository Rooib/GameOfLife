package GUI;

import Game.GameOfLife;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private TextField widthInput;

    @FXML
    private TextField heightInput;

    @FXML
    private Button start;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private GameOfLife gameOfLife;


    public void onStartClick() {
        int x = Integer.parseInt(widthInput.getText());
        int y = Integer.parseInt(heightInput.getText());
        gameOfLife = new GameOfLife(x, y);
        drawConfigOnCanvas(gameOfLife.getCurrentConfiguration());
    }

    /**
     * Handles the event when the user clicks on the canvas. It switches
     * the state of the clicked cell on the canvas. It also updates the current
     * game of life instance and redraws the canvas
     *
     * @param event - the MouseClickEvent
     */
    public void clickCanvas(MouseEvent event) {

        if (canvas.isDisable()) {
            return;
        }

        boolean[][] gameConfig = gameOfLife.getCurrentConfiguration();
        System.out.println(event.getX());
        System.out.println(event.getY());


        double clickX = event.getX();
        double clickY = event.getY();
        //Calculate the width of a rectangle on the canvas
        int rectWidth = (int) Math.ceil(canvas.getHeight() / gameConfig.length);
        int rectHeight = (int) Math.ceil(canvas.getWidth() / gameConfig[0].length);
        //Calculate how many rows and cols are even possible
        int colCount = (int) Math.ceil(canvas.getWidth() / rectWidth);
        int rowCount = (int) Math.ceil(canvas.getHeight() / rectHeight);

        int xIndex = 0;
        int yIndex = 0;

        for (int i = 1; i <= colCount; i++) {
            if (i * rectWidth >= clickX) {
                xIndex = i - 1;
                break;
            }
        }

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

    private void drawConfigOnCanvas(boolean[][] gameConfig) {
        canvas.setDisable(false);
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getWidth());
        int rectWidth = (int) Math.ceil(canvas.getHeight() / gameConfig.length);
        int rectHeight = (int) Math.ceil(canvas.getWidth() / gameConfig[0].length);
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

}
