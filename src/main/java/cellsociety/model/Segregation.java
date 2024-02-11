package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class Segregation extends RuleSet {

  // 0 empty
  // 1 group 1
  // 2 group 2
  // ...
  private final double segregationFraction;

  private List<Cell> emptyCells;

  public Segregation(Cell[][] grid) {
    super(grid);
    // finds all empty cells
    emptyCells = findEmptyCells();
    segregationFraction = 0.3;
  }

  public Segregation(Cell[][] grid, double segregationFactor) {
    super(grid);
    emptyCells = findEmptyCells();
    this.segregationFraction = segregationFactor;
  }

  public Segregation(Cell[][] grid, double segregationFactor, int neighborSize) {
    super(grid);
    emptyCells = findEmptyCells();
    this.segregationFraction = segregationFactor;
    this.neighborSize = neighborSize;
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // finds ratio of samegroup to othergroup
    int sameGroupCounter = countLoop(neighbors, c1.getCurrentState());
    int otherState = 1;
    if (c1.getCurrentState() == 1) {
      otherState = 2;
    }
    int otherGroupCounter = countLoop(neighbors, otherState);
    if (((double) sameGroupCounter) / ((double) otherGroupCounter) < segregationFraction
        && c1.getCurrentState() != 0) {
      c1.setNextState(0);
    }
  }

  @Override
  public void applyUpdate(Cell c1) {
    // switches unhappy cell with random empty cell
    int randomSpot = (int) (Math.random() * emptyCells.size());
    emptyCells.get(randomSpot).setCurrentState(c1.getCurrentState());
    emptyCells.get(randomSpot).setNextState(c1.getCurrentState());
    c1.setCurrentState(c1.getNextState());
    emptyCells.remove(randomSpot);
    emptyCells.add(c1);
  }

  public List<Cell> findEmptyCells() {
    // creates ArrayList of empty cells (no group there)
    List<Cell> empty = new ArrayList<>();
    for (int i = 0; i < getGrid().length; i++) {
      for (int j = 0; j < getGrid()[0].length; j++) {
        if (getGrid()[i][j].getCurrentState() == 0) {
          empty.add(getGrid()[i][j]);
        }
      }
    }
    return empty;
  }
}
