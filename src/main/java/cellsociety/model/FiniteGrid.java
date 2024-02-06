package cellsociety.model;

import java.util.HashSet;
import java.util.Set;

public class FiniteGrid implements Grid {

  private Cell[][] grid;
  private RuleSet rules;

  public FiniteGrid(RuleSet rules) {
    this.rules = rules;
    grid = rules.getGrid();
  }

  public Cell[][] getGrid() {
    return grid;
  }

  public void setGrid(Cell[][] grid) {
    this.grid = grid;
  }

  @Override
  public void update() {
    grid = rules.applyRules(grid);
  }

  @Override
  public int getCellState(int row, int column) {
    return grid[row][column].getCurrentState() ;
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
    return uniqueStates.size();
  }
}