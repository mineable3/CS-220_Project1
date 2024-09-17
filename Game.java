import java.util.Scanner;

public class Game {

  private Grid grid;
  private TestCases example;

  public void run() {

  }

  private void iterateGrid(int generations) {
    for (int i = 0; i < generations; i++) {

    }
  }

  private void intializeProgram() {
    try(Scanner scan = new Scanner(System.in)) {
      System.out.println("Welcome to Conway's Game of Life!\n");
      System.out.println("Select one the following examples: ");
      System.out.println("\t[1] Flyer");
      System.out.println("\t[2] Blinker");
      System.out.println("\t[3] Empty grid");
      System.out.println("\t[4] Full grid");
      System.out.println("\t[5] Singular cell");
      System.out.println("\t[6] Boundaries");
      System.out.println("\t[7] Random");
      System.out.println("\t[8] Performace");
      System.out.println("\t[9] Invalid inputs");

      switch (scan.nextInt()) {
        case 1:
          example = TestCases.FLYER;
          break;
        case 2:
          example = TestCases.BLINKER;
          break;
        case 3:
          example = TestCases.EMPTY_GRID;
          break;
        case 4:
          example = TestCases.FULL_GRID;
          break;
        case 5:
          example = TestCases.SINGLE_CELL;
          break;
        case 6:
          example = TestCases.EDGES;
          break;
        case 7:
          example = TestCases.RANDOM;
          break;
        case 8:
          example = TestCases.PERFORMACE;
          break;
        case 9:
          example = TestCases.INVALID_INPUTS;
          break;

        default:
          break;
      }
    }
  }

  public enum TestCases {
    FLYER,
    BLINKER,
    EMPTY_GRID,
    FULL_GRID,
    SINGLE_CELL,
    EDGES,
    RANDOM,
    PERFORMACE,
    INVALID_INPUTS;
  }
}
