package cellsociety.model;

public interface Rules {

  public Cell[][] findNeighbors(int xCord, int yCord);

  public void setUpdateFlag(Cell[][] neighbors, Cell c1);

  public Cell[][] applyRules(Cell[][] grid);

  public Cell[][] getGrid();

  public void setGrid(Cell[][] grid);

  public void applyUpdates();
}
