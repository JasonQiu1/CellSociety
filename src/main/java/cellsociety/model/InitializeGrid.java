package cellsociety.model;

public class InitializeGrid {
  Cell[][] grid;
  public InitializeGrid(int rows, int columns) {
    Cell[][] cells = new Cell[rows][columns];
    this.grid = cells;
//    return grid;
//    this.grid = new FiniteGrid();
  }
  public Cell[][] getGrid () {
    return grid;
  }
  public void setCellState(int row, int column, int currentState) {
    grid[row][column] = new Cell(currentState);
  }
}
