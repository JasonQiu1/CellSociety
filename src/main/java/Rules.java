public interface Rules {

  public Cell[][] findNeighbors();
  public void setUpdateFlag(Cell[][] neighbors, Cell c1);
  public Cell[][] applyRules();
  public Cell[][] getGrid();
  public void setGrid(Cell[][] grid);
  public void applyUpdates();
}
