package cellsociety.model;

public interface Rules {

  Cell[][] findNeighbors(int cordX, int cordY);

  void setUpdateFlag(Cell[][] neighbors, Cell c1);

  void applyRules();

  void applyUpdate(Cell c1);

  void update();

  int countLoop(Cell[][] neighbor, int state);

  void neighborLoop(Cell[][] neighbor, Cell c1);

  void nextLogic(Cell currentCell, Cell neighborCell);

}
