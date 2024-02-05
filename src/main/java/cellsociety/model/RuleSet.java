package cellsociety.model;

public abstract class RuleSet implements Rules {

  private static final int NEIGHBOR_SIZE = 3;
  private Cell[][] grid;

  public RuleSet(Cell[][] grid) {
    this.grid = grid;
  }
  public RuleSet() {
    grid = null;
  }

  public Cell[][] applyRules(Cell[][] grid) {
    setGrid(grid);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        Cell[][] neighbors = findNeighbors(i, j);
        setUpdateFlag(neighbors, grid[i][j]);
      }
    }
    update();
    return grid;
  }

  public abstract void setUpdateFlag(Cell[][] neighbors, Cell c1);

  public void applyUpdate(Cell c1) {
    c1.setCurrentState(c1.getNextState());
  }

  public void update() {
    for (int i = 0; i<grid.length;i++) {
      for (int j = 0; j <grid[0].length; j++) {
        if (grid[i][j].getCurrentState()!=grid[i][j].getNextState()) {
          applyUpdate(grid[i][j]);
        }
      }
    }
  }

  public Cell[][] findNeighbors(int xCord, int yCord) {
    Cell[][] neighbors = new Cell[NEIGHBOR_SIZE][NEIGHBOR_SIZE];
    int loopInt = NEIGHBOR_SIZE - 2;
    for (int x = xCord - loopInt; x <= xCord + loopInt; x++) {
      for (int y = yCord - loopInt; y <= yCord + loopInt; y++) {
        if (x == xCord && y == yCord) {
          neighbors[loopInt][loopInt] = null;
        } else if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
          neighbors[x - xCord + loopInt][y - yCord+loopInt] = grid[x][y];
        } else {
          neighbors[x - xCord+loopInt][y - yCord+loopInt] = null;
        }
      }
    }
    return neighbors;
  }

  public Cell[][] getGrid() {
    return grid;
  }

  public void setGrid(Cell[][] grid) {
    this.grid = grid;
  }
}
