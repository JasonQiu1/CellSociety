package cellsociety.model;

public class FallingSandWater extends RuleSet {
  // 0 nothing
  // 1 water
  // 2 sand

  public FallingSandWater() {
    super();
    toroidalNeighbor = false;
    neighborSize = 1;
    vonNeuman = false;
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    Cell neighborUnder = neighbors[(neighbors.length / 2) + 1][(neighbors.length / 2)];
    Cell neighborUnderRight = neighbors[(neighbors.length / 2) + 1][(neighbors.length / 2) + 1];
    Cell neighborUnderLeft = neighbors[(neighbors.length / 2) + 1][(neighbors.length / 2) - 1];
    if (c1.getCurrentState() == 2) {
      if (neighborUnder!= null && neighborUnder.getNextState() == 0) {
        c1.setNextState(0);
        neighborUnder.setNextState(2);
      }
      else {
        c1.setNextState(2);
      }
    }
    if (c1.getCurrentState() == 1) {
      if (neighborUnder != null && neighborUnder.getNextState() == 0) {
        c1.setNextState(0);
        neighborUnder.setNextState(1);
      }
      else if (neighborUnderRight != null && neighborUnderRight.getNextState() == 0) {
        c1.setNextState(0);
        neighborUnderRight.setNextState(1);
      }
      else if (neighborUnderLeft != null && neighborUnderLeft.getNextState() == 0) {
        c1.setNextState(0);
        neighborUnderLeft.setNextState(1);
      } else {
        c1.setNextState(1);
      }
    }
  }
}
