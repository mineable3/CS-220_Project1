import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

  private Grid grid;
  private TestCases example;

  public void run() {
    try {
      intializeProgram();

      if(example == TestCases.PERFORMANCE) {
        iterateGrid(100, 200);
      } else {
        iterateGrid(10);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
      System.out.println("Exiting game");
    } catch (NumberFormatException e) {
      System.out.println("The configuration file was set up incorrectly");
    } catch (IllegalArgumentException e) {
      System.out.println("The configuration file was set up incorrectly");
    }
  }

  private void iterateGrid(int generations) {
    iterateGrid(generations, 800);
  }

  private void iterateGrid(int generations, int delay) {
    try {
      // grid.printGrid();
      // Thread.sleep(delay);

      for (int i = 0; i < generations; i++) {
        // Clears the screen and prints this banner
        System.out.println("\033[H\033[2J");
        System.out.println("   ______                               _          ______                              ____   __    _ ____   ");
        System.out.println("  / ____/___  ____ _      ______ ___  _( )_____   / ____/___ _____ ___  ___     ____  / __/  / /   (_) __/__ ");
        System.out.println(" / /   / __ \\/ __ \\ | /| / / __ `/ / / /// ___/  / / __/ __ `/ __ `__ \\/ _ \\   / __ \\/ /_   / /   / / /_/ _ \\");
        System.out.println("/ /___/ /_/ / / / / |/ |/ / /_/ / /_/ / (__  )  / /_/ / /_/ / / / / / /  __/  / /_/ / __/  / /___/ / __/  __/");
        System.out.println("\\____/\\____/_/ /_/|__/|__/\\__,_/\\__, / /____/   \\____/\\__,_/_/ /_/ /_/\\___/   \\____/_/    /_____/_/_/  \\___/ ");
        System.out.println("                               /____/                                                                        ");
        System.out.println("==============================================================================================================");

        grid.printGrid();
        Thread.sleep(delay);
        grid.updateGridState();
      }
    } catch(InterruptedException e) {
      System.out.println("The program was stopped unexpectedly");
    }
  }

  private void intializeProgram() throws FileNotFoundException {
    System.out.println("\033[H\033[2J");
    System.out.println("   ______                               _          ______                              ____   __    _ ____   ");
    System.out.println("  / ____/___  ____ _      ______ ___  _( )_____   / ____/___ _____ ___  ___     ____  / __/  / /   (_) __/__ ");
    System.out.println(" / /   / __ \\/ __ \\ | /| / / __ `/ / / /// ___/  / / __/ __ `/ __ `__ \\/ _ \\   / __ \\/ /_   / /   / / /_/ _ \\");
    System.out.println("/ /___/ /_/ / / / / |/ |/ / /_/ / /_/ / (__  )  / /_/ / /_/ / / / / / /  __/  / /_/ / __/  / /___/ / __/  __/");
    System.out.println("\\____/\\____/_/ /_/|__/|__/\\__,_/\\__, / /____/   \\____/\\__,_/_/ /_/ /_/\\___/   \\____/_/    /_____/_/_/  \\___/ ");
    System.out.println("                               /____/                                                                        ");
    System.out.println("==============================================================================================================");
    System.out.println("Select one the following examples:\n");
    System.out.println("\t[1] |  Flyer");
    System.out.println("\t[2] |  Blinker");
    System.out.println("\t[3] |  Empty grid");
    System.out.println("\t[4] |  Full grid");
    System.out.println("\t[5] |  Singular cell");
    System.out.println("\t[6] |  Boundaries");
    System.out.println("\t[7] |  Random");
    System.out.println("\t[8] |  Performace");
    System.out.println("\t[9] |  Invalid inputs\n");
    System.out.print("Enter here: ");

    // Initializing the Grid and setting its state to the correct file
    try(Scanner scan = new Scanner(System.in)) {
      switch (scan.nextInt()) {
        case 1:
          example = TestCases.FLYER;
          grid = new Grid(6, 7);
          grid.setGridState(new File("Flyer.csv"));
          break;
        case 2:
          example = TestCases.BLINKER;
          grid = new Grid(5, 5);
          grid.setGridState(new File("Blinker.csv"));
          break;
        case 3:
          example = TestCases.EMPTY_GRID;
          grid = new Grid(4, 4);
          grid.setGridState(new File("Empty.csv"));
          break;
        case 4:
          example = TestCases.FULL_GRID;
          grid = new Grid(5, 5);
          grid.setGridState(new File("Full.csv"));
          break;
        case 5:
          example = TestCases.SINGLE_CELL;
          grid = new Grid(3, 3);
          grid.setGridState(new File("Single.csv"));
          break;
        case 6:
          example = TestCases.EDGES;
          grid = new Grid(5, 5);
          grid.setGridState(new File("Edges.csv"));
          break;
        case 7:
          example = TestCases.RANDOM;
          int[][] randomGame = new int[10][10];
          for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
              randomGame[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
          }
          grid = new Grid(10, 10);
          grid.setGridState(randomGame);
          break;
        case 8:
          // Same as the RANDOM test case, but bigger and the game runs longer
          example = TestCases.PERFORMANCE;
          int[][] performance = new int[20][20];
          for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
              performance[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
          }
          grid = new Grid(20, 20);
          grid.setGridState(performance);
          break;
        case 9:
          example = TestCases.INVALID_INPUTS;
          grid = new Grid(2, 2);
          grid.setGridState(new File("Invalid.csv"));
          break;

        default:
          break;
      }
    }
  }

  // A full list of all the required test cases
  public enum TestCases {
    FLYER,
    BLINKER,
    EMPTY_GRID,
    FULL_GRID,
    SINGLE_CELL,
    EDGES,
    RANDOM,
    PERFORMANCE,
    INVALID_INPUTS;
  }
}
