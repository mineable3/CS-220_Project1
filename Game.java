import java.io.File;

public class Game {

  public static void main(String[] args) {
    Grid game = new Grid(6, 7);
    game.setGridState(new File("Flyer.csv"));
    try {
      game.printGrid();
      Thread.sleep(1000);
      game.updateGridState();
      game.printGrid();
      Thread.sleep(1000);
      game.updateGridState();
      game.printGrid();
      Thread.sleep(1000);
      game.updateGridState();
      game.printGrid();
      Thread.sleep(1000);
      game.updateGridState();
      game.printGrid();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
