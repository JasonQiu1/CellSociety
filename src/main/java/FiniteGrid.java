public class FiniteGrid {

  private Cell[][] grid;
  private RuleSet rules;

  public FiniteGrid(Cell[][] grid, RuleSet rules) {
    this.grid = grid;
    this.rules = rules;
  }

  public Cell[][] getGrid() {
    return grid;
  }

  public void setGrid(Cell[][] grid) {
    this.grid = grid;
  }

  public void updateGrid() {
    grid = rules.applyRules(grid);
  }

}
