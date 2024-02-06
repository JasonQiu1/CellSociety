package cellsociety.model;

public class FiniteGrid implements Grid {

  private Cell[][] grid;
  private RuleSet rules;

  public FiniteGrid(RuleSet rules) {
    // takes in ruleset to best used to update the grid
    // grid is initially taken from the Rulset as some changes need to be made to the initial grid
    // for some implementations
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
}