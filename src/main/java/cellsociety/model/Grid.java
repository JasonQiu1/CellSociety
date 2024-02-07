package cellsociety.model;

public interface Grid {

  // simple grid interface
  public void update();

  public int getCellState(int row, int column);

  public int getNumRows();

  public int getNumCols();

  public Cell[][] getGrid();

  public void setGrid(Cell[][] cells);

  public int getNumStates();
}
