package cellsociety.model;

public class FallingSandWater extends RuleSet {
  // 0 nothing
  // 1 water
  // 2 sand
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    if (c1.getCurrentState() != 1) {

    }
  }
}
