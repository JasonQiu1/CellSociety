package cellsociety.model;

public class SpreadingOfFire extends RuleSet {

  private final double probabilityTree;
  private final double probabilityIgnite;

  public SpreadingOfFire(Cell[][] grid) {
    super(grid);
    probabilityIgnite = 0.1;
    probabilityTree = 0.1;
  }

  public SpreadingOfFire(double probabilityIgnite, double probabilityTree) {
    super();
    this.probabilityIgnite = probabilityIgnite;
    this.probabilityTree = probabilityTree;
  }

  public SpreadingOfFire(double
      probabilityIgnite, double probabilityTree,
      int neighborSize, boolean vonNeuman, boolean toroidalNeighbor) {
    super();
    this.probabilityIgnite = probabilityIgnite;
    this.probabilityTree = probabilityTree;
    this.neighborSize = neighborSize;
    this.vonNeuman = vonNeuman;
    this.toroidalNeighbor = toroidalNeighbor;
  }
  // 0 empty
  // 1 tree
  // 2 burning

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int currentState = c1.getCurrentState();
    if (currentState == 2) {
      // if burning remove tree on next iteration
      c1.setNextState(0);
    }
    if (currentState == 1) {
      if (Math.random() < probabilityIgnite) {
        // randomly ignite some trees
        c1.setNextState(2);
      }
      neighborLoop(neighbors, c1);
    }
    if (currentState == 0 && (Math.random() < probabilityTree)) {
      // plant some trees in empty spots
      c1.setNextState(1);
    }
  }

  @Override
  public void nextLogic(Cell currentCell, Cell neighborCell) {
    if (neighborCell.getCurrentState() == 2) {
      currentCell.setNextState(2);
    }
  }
}
