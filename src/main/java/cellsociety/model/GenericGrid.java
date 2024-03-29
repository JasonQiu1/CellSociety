package cellsociety.model;

import java.util.HashSet;
import java.util.Set;

public class GenericGrid implements Grid {

  protected Cell[][] grid;
  protected RuleSet rules;

  public GenericGrid(RuleSet rules, Cell[][] grid) {
    // takes in ruleset to best used to update the grid
    // grid is initially taken from the Rulset as some changes need to be made to the initial grid
    // for some implementations
    this.rules = rules;
    rules.setGrid(grid);
    this.grid = rules.getGrid();
  }

  public void setGrid(Cell[][] grid) {
    this.grid = grid;
  }

  public void update() {
    rules.applyRules();
  }

  public void print(Cell[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        System.out.print(grid[i][j].getCurrentState() + " ");
      }
      System.out.println(" ");
    }
  }

  public int getCellState(int row, int column) {
    return grid[row][column].getCurrentState();
  }

  @Override
  public int getNumRows() {
    return grid[0].length;
  }

  @Override
  public int getNumCols() {
    return grid.length;
  }

  public int getNumStates() {
    Set<Integer> uniqueStates = new HashSet<>();
//    System.out.println(grid.length);
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
//        System.out.println("aaaaaa\n\n\n\n");
//        System.out.println(row);
//        System.out.println(grid[row][col]);
//        uniqueStates.add(grid[row][col].getCurrentState());
        if (grid[row][col] != null) {
          // Get the current state of the cell
//          System.out.println(row);
//          System.out.println(col);
//          System.out.println(grid[row][col].getCurrentState());
          Integer currentState = grid[row][col].getCurrentState();
          uniqueStates.add(currentState);
        }
      }
    }
    return 6;
  }

  @Override
  public void setVonNeuman(boolean vonNeuman) {
    rules.setVonNeuman(vonNeuman);
  }

  @Override
  public void setNeighborSize(int neighborSize) {
    rules.setNeighborSize(neighborSize);
  }

  @Override
  public void setToroidalNeighbor(boolean toroidalNeighbor) {
    rules.setToroidalNeighbor(toroidalNeighbor);
  }
}
