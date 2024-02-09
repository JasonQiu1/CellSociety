package cellsociety.model;

public interface Rules {

  public Cell[][] findNeighbors(int cordX, int cordY);

  public void setUpdateFlag(Cell[][] neighbors, Cell c1);

  public Cell[][] applyRules();

  public Cell[][] getGrid();

  public void setGrid(Cell[][] grid);

  public void applyUpdate(Cell c1);

  public void update();

  public int countLoop(Cell[][] neighbor, int state);

  public void neighborLoop(Cell[][] neighbor, Cell c1);

  public void nextLogic(Cell currentCell, Cell neighborCell);
}
