package cellsociety.model;

public class GameOfLife extends RuleSet {

  public GameOfLife(Cell[][] grid) {
    // default constructor
    super(grid);
  }
  // 0 dead
  // 1 alive

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // overide setUpdateflag
    // count number of neighbors who are alive and update cell state accordingly
    int liveNeighborCount = 0;
    boolean isAlive = c1.getCurrentState() == 1;
    for (int i = 0; i < neighbors.length; i++) {
      for (int j = 0; j < neighbors[0].length; j++) {
        if (neighbors[i][j] != null) {
          if (neighbors[i][j].getCurrentState() == 1) {
            liveNeighborCount++;
          }
        }
      }
    }
    if (isAlive && (liveNeighborCount < 2 || liveNeighborCount > 3)) {
      c1.setNextState(0);
    } else if (!isAlive && liveNeighborCount == 3) {
      c1.setNextState(1);
    }
  }
}

