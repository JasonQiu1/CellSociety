package cellsociety.model;

public abstract class RuleSet implements Rules {

  // determines size of neighbors
  protected int neighborSize = 1;
  protected boolean vonNeuman = false;
  protected boolean toroidalNeighbor = false;
  protected Cell[][] grid;


  public RuleSet(Cell[][] grid) {
    this.grid = grid;
  }

  public RuleSet() {
    grid = null;
  }

  public Cell[][] getGrid() {
    return grid;
  }

  public void setGrid(Cell[][] grid) {
    if (this.grid == null) {
      this.grid = grid;
    }
  }

  public void applyRules() {
    // default applyRules. used by almost all children
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        Cell[][] neighbors = findNeighbors(i, j);
        // finds neighbors and sends to SetUpdateFlag.
        // setUpdateFlag is written in the children classes
        setUpdateFlag(neighbors, grid[i][j]);
      }
    }
    update();
  }

  public abstract void setUpdateFlag(Cell[][] neighbors, Cell c1);

  public void applyUpdate(Cell c1) {
    // used by the majority of children
    c1.setCurrentState(c1.getNextState());
  }

  public int countLoop(Cell[][] neighbors, int state) {
    int counter = 0;
    for (int i = 0; i < neighbors.length; i++) {
      for (int j = 0; j < neighbors[0].length; j++) {
        if (neighbors[i][j] != null) {
          if (neighbors[i][j].getCurrentState() == state) {
            counter++;
          }
        }
      }
    }
    return counter;
  }

  public void update() {
    // used by all children
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].getCurrentState() != grid[i][j].getNextState()) {
          applyUpdate(grid[i][j]);
        }
      }
    }
  }

  public Cell[][] findNeighbors(int cordX, int cordY) {
    // fills in NEIGHBOR_SIZE by NEIGHBOR_SIZE array of neighbors

    int neighborArraySize = (neighborSize * 2) + 1;
    Cell[][] neighbors = new Cell[neighborArraySize][neighborArraySize];
    for (int x = cordX - neighborSize; x <= cordX + neighborSize; x++) {
      for (int y = cordY - neighborSize; y <= cordY + neighborSize; y++) {
        if (toroidalNeighbor) {
          toroidalNeighbor(neighbors, cordX, cordY, x, y);
        } else {
          normalNeighbor(neighbors, cordX, cordY, x, y);
        }
      }
    }
    if (!vonNeuman) {
      return neighbors;
    } else {
      return vonNeumanNeighbors(neighbors);
    }
  }

  public void toroidalNeighbor(Cell[][] neighbors, int cordX, int cordY, int x, int y) {
    // fills in NEIGHBOR_SIZE by NEIGHBOR_SIZE array of neighbors
    int newX = x;
    int newY = y;
    if (x < 0) {
      newX = grid.length + x;
    } else if (x >= grid.length) {
      newX = grid.length - x;
    }
    if (y < 0) {
      newY = grid.length + y;
    } else if (y >= grid[0].length) {
      newY = grid[0].length - y;
    }
    if (x == cordX && y == cordY) {
      neighbors[neighborSize][neighborSize] = null;
    } else {
      neighbors[x - cordX + neighborSize][y - cordY + neighborSize] = grid[newX][newY];
    }
  }

  public void normalNeighbor(Cell[][] neighbors, int cordX, int cordY, int x, int y) {
    if (x == cordX && y == cordY) {
      neighbors[neighborSize][neighborSize] = null;
    } else if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
      neighbors[x - cordX + neighborSize][y - cordY + neighborSize] = grid[x][y];
    } else {
      neighbors[x - cordX + neighborSize][y - cordY + neighborSize] = null;
    }
  }

  public Cell[][] vonNeumanNeighbors(Cell[][] neighbors) {

    // only adjacent count
    Cell[][] adjNeighbors = new Cell[4][1];
    adjNeighbors[0][0] = neighbors[(neighbors.length / 2) - 1][(neighbors.length / 2)];
    adjNeighbors[1][0] = neighbors[(neighbors.length / 2) + 1][(neighbors.length / 2)];
    adjNeighbors[2][0] = neighbors[(neighbors.length / 2)][(neighbors.length / 2) - 1];
    adjNeighbors[3][0] = neighbors[(neighbors.length / 2)][(neighbors.length / 2) + 1];
    return adjNeighbors;
  }

  public void neighborLoop(Cell[][] neighbor, Cell c1) {
    for (int i = 0; i < neighbor.length; i++) {
      for (int j = 0; j < neighbor[0].length; j++) {
        if (neighbor[i][j] != null) {
          nextLogic(c1, neighbor[i][j]);
        }
      }
    }
  }

  public void nextLogic(Cell currentCell, Cell neighborCell) {
  }

  public void setVonNeuman(boolean vonNeuman) {
    this.vonNeuman = vonNeuman;
  }

  public void setNeighborSize(int neighborSize) {
    this.neighborSize = neighborSize;
  }

  public void setToroidalNeighbor(boolean toroidalNeighbor) {
    this.toroidalNeighbor = toroidalNeighbor;
  }

}
