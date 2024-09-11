import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

  public void setGridState(File config) {
    for (int i = 0; i < height; i++) {
      this.currentState[i] = csvToArray(config, i);
    }
  }

  public void printGrid() {
    for(int i = 0; i < currentState.length; i++) {
      for(int j = 0; j < currentState[i].length; j++) {
        System.out.print(currentState[i][j] + "  ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private int[] csvToArray(File file, int lineNumber) {
    ArrayList<Integer> values = new ArrayList<Integer>();
    String[] rawData;
    int[] outputData;

    try(Scanner scan1 = new Scanner(file);) {
      for (int i = 0; i < lineNumber; i++) {
        scan1.nextLine();
      }

      rawData = scan1.nextLine().split(",", -1);

      for(String num : rawData) {
        if(!num.equals("") && !num.isBlank() && !num.isEmpty()) {
          values.add(Integer.parseInt(num));
        }
      }

      outputData = new int[values.size()];
      for(int i = 0; i < values.size(); i++) {
        outputData[i] = values.get(i);
      }
    } catch (FileNotFoundException e) {
      System.err.println(e);
      outputData = new int[0];
    }

    if(outputData.length != width) {
      System.out.println("The configuration file is the wrong dimensions");
    }

    return outputData;
  }

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
