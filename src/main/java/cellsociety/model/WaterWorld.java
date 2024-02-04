package cellsociety.model;

public class WaterWorld extends RuleSet {
  // 0 nothing
  // 1 fish
  // 2 shark
  public WaterWorld(Cell[][] grid) {
    super(grid);
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int currentState = c1.getCurrentState();
    Cell[] adjNeighbors = {neighbors[(neighbors.length/2)-1][neighbors.length]};
    if (currentStat)
    if (currentState == 1) {

    }
    }

  @Override
  public void applyUpdate(Cell c1) {

  }
}
