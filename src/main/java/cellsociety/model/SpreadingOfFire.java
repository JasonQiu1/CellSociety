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
      c1.setFlag(true);
    }
    if (currentState == 1) {
      c1.setFlag(Math.random()<probabilityIgnite);
      boolean leaveLoops = false;
      for (int i = 0; i < neighbors.length; i++) {
        for (int j = 0; j < neighbors[0].length; j++) {
          if (neighbors[i][j].getCurrentState()==2) {
            c1.setFlag(true);
            leaveLoops = true;
            break;
          }
        }
        if(leaveLoops) {
          break;
        }
      }
    }
    if(currentState == 0) {
      c1.setFlag(Math.random()<probabilityTree);
    }
  }

  @Override
  public void applyUpdate(Cell c1) {
    switch(c1.getCurrentState()) {
      case 0:
        c1.setCurrentState(1);
      case 1:
        c1.setCurrentState(2);
      case 2:
        c1.setCurrentState(0);
    }
  }
}

