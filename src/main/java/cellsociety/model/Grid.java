package cellsociety.model;

public interface Grid {

  public void update();

  public int getCellState(int row, int column);

  public int getNumRows();

  public int getNumCols();

}
