package cellsociety.model;

public class HexGrid extends GenericGrid {
int neighborSize = 1;
boolean toroidalNeighbor = true;
boolean vonNeuman = false;

  public HexGrid(RuleSet rules) {
    super(rules);
    this.grid = makeHexGrid(rules.getGrid());
    rules.setGrid(this.grid);
  }

  public Cell[][] makeHexGrid (Cell[][] grid) {
    Cell[][] hexGrid = new Cell[grid.length][grid[0].length*2];
    for (int i = 0; i<hexGrid.length; i++) {
      for (int j = 0; j<hexGrid[0].length; j++) {
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
    return hexGrid;
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
    }
    else {
      return vonNeumanNeighbors(neighbors);
    }
  }

  public void toroidalNeighbor(Cell[][] neighbors, int cordX, int cordY, int x, int y) {
    // fills in NEIGHBOR_SIZE by NEIGHBOR_SIZE array of neighbors
    int newX = x;
    int newY = y;
    if (x < 0) {
      newX = grid.length + x;
    }
    else if (x >= grid.length) {
      newX = grid.length - x;
    }
    if (y < 0) {
      newY = grid.length + y;
    }
    else if (y >= grid[0].length) {
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
}
