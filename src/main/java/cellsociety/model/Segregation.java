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

  public Segregation() {
    super();
    // finds all empty cells
    segregationFraction = 0.3;
  }

  public Segregation(double segregationFactor) {
    super();
    this.segregationFraction = segregationFactor;
  }

  public Segregation(double segregationFactor,
      int neighborSize, boolean
      vonNeuman, boolean toroidalNeighbor) {
    super();
    this.segregationFraction = segregationFactor;
    this.neighborSize = neighborSize;
    this.vonNeuman = vonNeuman;
    this.toroidalNeighbor = toroidalNeighbor;
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
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].getCurrentState() == 0) {
          empty.add(grid[i][j]);
        }
      }
    }
    return empty;
  }

  @Override
  public void setGrid(Cell[][] grid) {
    if (this.grid == null) {
      this.grid = grid;
      emptyCells = findEmptyCells();
    }
  }

}
