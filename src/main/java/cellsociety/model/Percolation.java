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
    if (c1.getCurrentState() == 0 || c1.getCurrentState()==2) {
      c1.setNextState(c1.getCurrentState());
      if (c1.getCurrentState()==2) {
      for (int i = 0; i < neighbors.length; i++) {
        for (int j = 0; j < neighbors[0].length; j++) {
          if (neighbors[i][j]!= null && neighbors[i][j].getCurrentState() != 1) {
            neighbors[i][j].setNextState(2);
          }
          }
        }
      }
    }
  }
}
