package cellsociety.model;

public class InitializeGrid {
  Cell[][] grid;
  public InitializeGrid(int rows, int columns) {
    grid = new Cell[rows][columns];
  }
  public Cell[][] getGrid () {
    return grid;
  }
  public void setCellState(int row, int column, int currentState) {
    grid[row][column] = new Cell(currentState);
  }
}
