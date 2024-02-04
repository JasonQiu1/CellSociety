package cellsociety.model;

public class SpreadingOfFire extends RuleSet {

  public SpreadingOfFire(Cell[][] grid) {
    super(grid);
  }
  // 0 empty
  // 1 tree
  // 2 burning
  private static final double probabilityTree = 0.5;
  private static final double probabilityIgnite = 0.5;
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int currentState = c1.getCurrentState();
    if (currentState == 2) {
      c1.setNextState(0);
    }
    if (currentState == 1) {
      if (Math.random()<probabilityIgnite) {
        c1.setNextState(2);
      }
      boolean leaveLoops = false;
      for (int i = 0; i < neighbors.length; i++) {
        for (int j = 0; j < neighbors[0].length; j++) {
          if (neighbors[i][j]!= null && neighbors[i][j].getCurrentState()==2) {
            c1.setNextState(2);
            leaveLoops = true;
            break;
          }
        }
        if(leaveLoops) {
          break;
        }
      }
    }
    if(currentState == 0 && (Math.random()<probabilityTree)) {
      c1.setNextState(1);
    }
  }
}

