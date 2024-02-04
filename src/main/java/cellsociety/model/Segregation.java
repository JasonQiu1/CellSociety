package cellsociety.model;

import java.util.ArrayList;

public class Segregation extends RuleSet {
  // 0 empty
  // 1 group 1
  // 2 group 2
  // ...
  public static final double segregationFraction = 0.3;

  private ArrayList<Cell> emptyCells;

  public Segregation(Cell[][] grid) {
    super(grid);
    emptyCells = findEmptyCells();
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int sameGroupCounter = 0;
    int otherGroupCounter = 0;
    for (int i = 0; i < neighbors.length; i++) {
      for (int j = 0; j < neighbors[0].length; j++) {
        if (neighbors[i][j]!=null) {
          if (neighbors[i][j].getCurrentState() == c1.getCurrentState()) {
            sameGroupCounter++;
          } else if (neighbors[i][j].getCurrentState() == 0) {
            continue;
          } else {
            otherGroupCounter++;
          }
        }
      }
    }
    if (((double)sameGroupCounter)/((double)otherGroupCounter) < segregationFraction && c1.getCurrentState()!=0) {
      c1.setNextState(-1); // it is unclear what this will change to yet
    }
  }

  @Override
  public void applyUpdate(Cell c1) {
    int randomSpot = (int)(Math.random()*emptyCells.size());
    emptyCells.get(randomSpot).setCurrentState(c1.getCurrentState());
    emptyCells.get(randomSpot).setNextState(c1.getCurrentState());
    c1.setCurrentState(0);
    c1.setNextState(0);
    emptyCells.remove(randomSpot);
    emptyCells.add(c1);
  }
  public ArrayList<Cell> findEmptyCells () {
    ArrayList<Cell> empty = new ArrayList<>();
    for (int i = 0; i<getGrid().length; i++ ) {
      for (int j = 0; j<getGrid()[0].length;j++) {
        if (getGrid()[i][j].getCurrentState()==0) {
          empty.add(getGrid()[i][j]);
        }
      }
    }
    return empty;
  }
}
