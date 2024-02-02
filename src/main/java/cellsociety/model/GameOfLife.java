package cellsociety.model;

public class GameOfLife extends RuleSet {

  public GameOfLife(Cell[][] grid) {
    super(grid);
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
      int liveNeighborCount = 0;
      boolean isAlive = c1.getCurrentState()==1;
      for (int i = 0; i<neighbors.length;i++) {
        for (int j = 0; j<neighbors[0].length; j++) {
          if (neighbors[i][j]!=null) {
            if (neighbors[i][j].getCurrentState()==1) {
              liveNeighborCount++;
            }
          }
        }
      }
      if (isAlive && (liveNeighborCount<2 || liveNeighborCount>3)) {
        c1.setFlag(true);
      }
      else if (!isAlive && liveNeighborCount==3) {
        c1.setFlag(true);
      }
  }

  @Override
  public void applyUpdates() {
    for (int i = 0; i< getGrid().length;i++) {
      for (int j = 0; j < getGrid()[0].length; j++) {
        if (getGrid()[i][j].getFlag()) {
          if (getGrid()[i][j].getCurrentState() == 0) {
            getGrid()[i][j].setCurrentState(1);
          }
          else {
            getGrid()[i][j].setCurrentState(0);
          }
        }
      }
    }
  }
}

