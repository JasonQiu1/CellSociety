package cellsociety.model;

public class Percolation extends RuleSet {

  public Percolation(Cell[][] grid) {
    super(grid);
  }

  // 0 is nothing
  // 1 is block
  // 2 is percolate
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // check if an empty cell is in the Moore Neighborhood of a percolated cell and make that cell percolated on next iteration
    if (c1.getCurrentState() == 1 || c1.getCurrentState() == 2) {
      c1.setNextState(c1.getCurrentState());
      if (c1.getCurrentState() == 2) {
        neighborLoop(neighbors, c1);
      }
    }
  }

  public void nextLogic(Cell currentCell, Cell neighborCell) {
      if (neighborCell.getCurrentState()!= 0) {
        neighborCell.setNextState(2);
      }
  }
}
