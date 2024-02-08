package cellsociety.model;

public class InitializeGrid {

  // class used to initialize the grid to later be passed to finite grid and Rulset
  Cell[][] grid;

  public InitializeGrid(int rows, int columns) {
    Cell[][] cells = new Cell[rows][columns];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        cells[row][col] = new Cell(0); // Initialize each cell with a state of 0
      }
    }
    this.grid = cells;
//    return grid;
//    this.grid = new FiniteGrid();
  }

  public Cell[][] getGrid() {
    return grid;
  }

  public void setCellState(int row, int column, int currentState) {
    grid[row][column] = new Cell(currentState);
  }
}
