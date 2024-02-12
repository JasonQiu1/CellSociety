package cellsociety.model;

public class GameOfLife extends RuleSet {

  public GameOfLife() {
    // default constructor
    super();
  }

  public GameOfLife(int neighborSize, boolean vonNeuman, boolean toroidalNeighbor) {
    // default constructor
    super();
    this.neighborSize = neighborSize;
    this.vonNeuman = vonNeuman;
    this.toroidalNeighbor = toroidalNeighbor;
  }

  // 0 dead
  // 1 alive

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // overide setUpdateflag
    // count number of neighbors who are alive and update cell state accordingly
    boolean isAlive = c1.getCurrentState() == 1;
    int liveNeighborCount = countLoop(neighbors, 1);
    if (isAlive && (liveNeighborCount < 2 || liveNeighborCount > 3)) {
      c1.setNextState(0);
    } else if (!isAlive && liveNeighborCount == 3) {
      c1.setNextState(1);
    }
  }
}

