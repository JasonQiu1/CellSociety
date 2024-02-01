public abstract class RuleSet implements Rules {
  private Cell[][] grid;
  private static final int NEIGHBOR_SIZE = 3;
  public RuleSet(Cell[][] grid) {
    this.grid = grid;
  }
  public Cell[][] applyRules(Cell[][] grid) {
    setGrid(grid);
    for (int i = 0; i<grid.length; i++) {
      for (int j = 0; j<grid[0].length; j++) {
        Cell[][] neighbors = findNeighbors(i,j);
        setUpdateFlag(neighbors, grid[i][j]);
      }
      applyUpdates();
    }
    return grid;
  }
  public abstract void setUpdateFlag(Cell[][] neighbors, Cell c1);

  public abstract void applyUpdates();
  public Cell[][] findNeighbors (int xCord, int yCord) {
    Cell[][] neighbors = new Cell[NEIGHBOR_SIZE][NEIGHBOR_SIZE];
    int loopInt = NEIGHBOR_SIZE-2;
    for (int x = xCord-loopInt; x<=xCord+loopInt; x++) {
      for (int y = yCord-loopInt; y<=yCord+loopInt; y++) {
        if (x==xCord && y==yCord) {
          neighbors[xCord][yCord] = null;
        }
        else if (x>=0 && x<=grid.length && y>=0 && y<=grid[0].length) {
          neighbors[x-xCord+loopInt][y-yCord+loopInt] = grid[x][y];
        }
        else {
          neighbors[x-xCord+loopInt][y-yCord+loopInt] = null;
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
