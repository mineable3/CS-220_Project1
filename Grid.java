import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Grid {

  private int height, width;
  private int[][] currentState, nextState;

  public Grid(int height, int width) {
    this.height = height;
    this.width = width;

    currentState = new int[height][width];
    nextState = new int[height][width];
  }

  /**
   * Parses the input file and it assumes it is in CSV format.
   * Sets the current state of the grid to the data inside the file.
   * @param config A File representing the configuration state.
   * @throws FileNotFoundException If the file doesn't exist
   * @throws IllegalArgumentException If the array dimensions are incorrect or has values other than 0 or 1
   * @throws NumberFormatException If the file contains values that are not numbers
   */
  public void setGridState(File config) throws FileNotFoundException {
    for (int i = 0; i < height; i++) {
      this.currentState[i] = csvToArray(config, i);
    }
  }

  /**
   * Directly sets the current grid state to the input array
   * @param config A two dimensional array representing a game state
   * @throws IllegalArgumentException If the array dimensions are incorrect or has values other than 0 or 1
   */
  public void setGridState(int[][] config) {
    // Checking array dimensions
    if(config.length != height || config[0].length != width) {
      throw new IllegalArgumentException("The configuration is the wrong dimensions");
    }

    // Checking the array has only 1's or 0's
    for (int i = 0; i < config.length; i++) {
      for (int j = 0; j < config[i].length; j++) {
        if(config[i][j] != 1 && config[i][j] != 0) {
          throw new IllegalArgumentException("Only 1 or 0 is accepted as a cell state");
        }
      }
    }
    this.currentState = config;
  }

  /**
   * Displays the grid's current state
   */
  public void printGrid() {
    for(int i = 0; i < currentState.length; i++) {
      for(int j = 0; j < currentState[i].length; j++) {
        System.out.print(currentState[i][j] + "  ");
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * Parses one line of a CSV file and turns it into an array
   * @param file A CSV file to be parsed
   * @param lineNumber The line to parse
   * @return The given line as an array
   * @throws FileNotFoundException If the File does not exist
   * @throws IllegalArgumentException If the array dimensions are incorrect or has values other than 0 or 1
   * @throws NumberFormatException If the file contains values that are not numbers
   */
  private int[] csvToArray(File file, int lineNumber) throws FileNotFoundException {
    ArrayList<Integer> values = new ArrayList<Integer>();
    String[] rawData;
    int[] outputData;

    try(Scanner scan1 = new Scanner(file);) {
      // Skipping to the desired line
      for (int i = 0; i < lineNumber; i++) {
        scan1.nextLine();
      }

      rawData = scan1.nextLine().split(",", -1);

      // Converting the Strings into integers
      for(String num : rawData) {
        if(!num.equals("") && !num.isBlank() && !num.isEmpty()) {
          int integer = Integer.parseInt(num.trim());

          if(integer != 1 && integer != 0) {
            throw new IllegalArgumentException("Only 1 or 0 is accepted as a cell state");
          }

          values.add(integer);
        }
      }

      // Converting the ArrayList holding the values into an array
      outputData = new int[values.size()];
      for(int i = 0; i < values.size(); i++) {
        outputData[i] = values.get(i);
      }
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Only 1 or 0 is accepted as a cell state");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("The file is shorter that expected");
    }

    if(outputData.length != width) {
      throw new IllegalArgumentException("The configuration file is the wrong dimensions");
    }

    return outputData;
  }

  /**
   * Updates the cells and sets the current state to the next generation
   */
  public void updateGridState() {
    for (int i = 0; i < currentState.length; i++) {
      for (int j = 0; j < currentState[0].length; j++) {
        updateCellState(i, j);
      }
    }

    for (int i = 0; i < currentState.length; i++) {
      for (int j = 0; j < currentState[0].length; j++) {
        currentState[i][j] = nextState[i][j];
      }
    }
  }

  /**
   * Applies all of the rules of Conway's Game of Life and stores the results in the nextState[][]
   * @param row The row of the cell
   * @param column The column of the cell
   */
  private void updateCellState(int row, int column) {
    int cellValue = sumNeighbors(row, column);

    if(cellValue < 2) {
      nextState[row][column] = 0;
    } else if(cellValue == 2 && currentState[row][column] == 1) {
      nextState[row][column] = 1;
    } else if(cellValue == 3) {
      nextState[row][column] = 1;
    } else if(cellValue > 3) {
      nextState[row][column] = 0;
    }
  }

  /**
   * Sums the number of alive cells surrounding the target cell in the current state
   * @param row The row of the cell
   * @param column The column of the cell
   * @return The sum of surrounding alive cells
   */
  private int sumNeighbors(int row, int column) {
    int sum = 0;

    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = column - 1; j <= column + 1; j++) {
        if(i < 0 || j < 0 || i > currentState.length - 1 || j > currentState[row].length - 1) {
          continue;
        } else if(i == row && j == column) {
          continue;
        } else {
          sum += currentState[i][j];
        }
      }
    }

    return sum;
  }
}
