package cellsociety.model;

import java.util.ArrayList;

public class WaterWorld extends RuleSet {
  // 0 nothing
  // 1 fish
  // 2 shark
  private static final int STATES = 2;
  private static final int REPRODUCTION_MOVES = 15;
  public WaterWorld(Cell[][] grid) {
    super(grid);
  }

  @Override
  public Cell[][] applyRules(Cell[][] grid) {
    setGrid(grid);
    // first for loop ensures that shark logic occurs before fish logic (sharks move before fish)
    for (int k = STATES; k>0; k--) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (k == getGrid()[i][j].getCurrentState()) {
            Cell[][] neighbors = findNeighbors(i, j);
            setUpdateFlag(neighbors, grid[i][j]);
          }
        }
      }
    }
    update();
    return grid;
  }
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int currentState = c1.getCurrentState();
    Cell[] adjNeighbors = {neighbors[(neighbors.length/2)-1][neighbors[0].length/2],neighbors[(neighbors.length/2)+1][neighbors[0].length/2],
        neighbors[neighbors.length/2][(neighbors[0].length/2)-1],neighbors[neighbors.length/2][(neighbors[0].length/2)+1]};
    if (currentState==2) {
      sharkLogic();
    }
    if (currentState==1) {
      fishLogic();
    }
  }
  @Override
  public void applyUpdate(Cell c1) {

  }
}
