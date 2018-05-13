package Util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

    /**
     * Reads comma seperated file which includes the game configuration
     * i.e. dead and alive cells. Then it creates a new GameOfLive Instance
     * with the given configuration
     *
     * @param fileName - name of file containing the configuration
     * @return new GameOfLife configuration if the file was valid, else null
     */
    public boolean[][] createConfigurationFromFile(final String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int colCounter = 0;
            int rowCounter = 0;
            int oldColCount = 0;
            // Stores every processed Line
            List<Boolean[]> tempStorage = new ArrayList<>();
            String line;

            /* Process single line to verify that the number of cols stays the same throughout the file */
            if ((line = bufferedReader.readLine()) != null) {
                Boolean[] parsedLine = Arrays.stream(line.split(","))
                        .map(Boolean::parseBoolean).toArray(Boolean[]::new);
                oldColCount = parsedLine.length;
                rowCounter++;
                tempStorage.add(parsedLine);
            }

            while ((line = bufferedReader.readLine()) != null) {

                Boolean[] parsedLine = Arrays.stream(line.split(","))
                        .map(Boolean::parseBoolean).toArray(Boolean[]::new);
                colCounter = parsedLine.length;

                if (oldColCount != colCounter) {
                    throw new IllegalStateException("The file has not a rectangular format!");
                }
                oldColCount = colCounter;
                rowCounter++;
                tempStorage.add(parsedLine);
            }

            boolean[][] gameConfig = new boolean[rowCounter][colCounter];

            for(int i = 0; i< tempStorage.size(); i++) {
                for(int j =0; j< tempStorage.get(i).length; j++) {
                    gameConfig[i][j] = tempStorage.get(i)[j];
                }
            }

            return gameConfig;

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file does not exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
