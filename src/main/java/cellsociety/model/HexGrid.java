package cellsociety.model;

public class HexGrid extends GenericGrid {

  public HexGrid(RuleSet rules) {
    super(rules);
    this.grid = makeHexGrid(rules.getGrid());
    rules.setGrid(this.grid);
  }

  public Cell[][] makeHexGrid (Cell[][] grid) {
    Cell[][] hexGrid = new Cell[grid.length][grid[0].length*2];
    for (int i = 0; i<hexGrid.length; i++) {
      for (int j = 0; j<hexGrid[0].length; j++) {
        hexGridLogic(hexGrid, i, j);
      }
    }
    return hexGrid;
  }

  public void hexGridLogic(Cell[][] hexGrid, int i, int j) {
    if (i % 2 == 0) {
      if (j%2 == 0) {
        hexGrid[i][j] = grid[i][j/2];
      }
      else {
        hexGrid[i][j] = null;
      }
    }
    else {
      if (j%2 == 0) {
        hexGrid[i][j] = null;
      }
      else {
        hexGrid[i][j] = grid[i][(j/2)];
      }
    }
  }
}
