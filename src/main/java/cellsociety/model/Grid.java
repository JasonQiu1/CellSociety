package cellsociety.model;

public interface Grid {

  // simple grid interface
  void update();

  int getCellState(int row, int column);

  int getNumRows();

  int getNumCols();

  void setGrid(Cell[][] cells);

  int getNumStates();

  void setVonNeuman(boolean vonNeuman);

  void setNeighborSize(int neighborSize);

  void setToroidalNeighbor (boolean toroidalNeighbor);
}
