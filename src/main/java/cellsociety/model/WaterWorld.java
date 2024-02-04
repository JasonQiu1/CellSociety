package cellsociety.model;

import java.util.ArrayList;

public class WaterWorld extends RuleSet {
  // 0 nothing
  // 1 fish
  // 2 shark
  private static final int STATES = 2;
  private static final int REPRODUCTION_MOVES = 15;
  private static final int ENERGY_FROM_FISH = 5;
  public WaterWorld(Cell[][] grid) {
    super(grid);
    Cell[][] updatedGrid = new Cell[grid.length][grid[0].length];
    for (int i = 0; i<grid.length; i++) {
      for (int j = 0; j<grid[0].length; j++) {
        updatedGrid[i][j] = new FishOrShark(grid[i][j].getCurrentState());
      }
    }
   setGrid(updatedGrid);
  }
  @Override
  public Cell[][] applyRules(Cell[][] grid) {
    setGrid(grid);
    // first for loop ensures that shark logic occurs before fish logic (sharks move before fish)
    for (int k = STATES; k>0; k--) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (k == getGrid()[i][j].getCurrentState()) {
            Cell[][] neighbors = findNeighbors(i, j);
            setUpdateFlag(neighbors, grid[i][j]);
          }
        }
      }
    }
    update();
    return grid;
  }
  @Override
  public Cell[][] findNeighbors(int xCord, int yCord) {
    // only adjacent count
    Cell[][] neighbors = new Cell[4][1];
    neighbors[0][0] = getGrid()[xCord-1][yCord];
    neighbors[1][0] = getGrid()[xCord+1][yCord];
    neighbors[2][0] = getGrid()[xCord][yCord-1];
    neighbors[3][0] = getGrid()[xCord][yCord+1];
    return neighbors;
  }
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    int currentState = c1.getCurrentState();
    if (currentState==2) {
      sharkLogic((FishOrShark)c1, neighbors);
    }
    if (currentState==1 && c1.getNextState()==1) {
      fishLogic((FishOrShark)c1, neighbors);
    }
  }
  private ArrayList<FishOrShark> generateList(Cell[][] neighbors, int state) {
    ArrayList<FishOrShark> empty = new ArrayList<>();
    for (int i = 0; i<neighbors.length; i++) {
      if (neighbors[i][0]!= null && neighbors[i][0].getCurrentState() == state && neighbors[i][0].getNextState() == state) {
        empty.add((FishOrShark)neighbors[i][0]);
      }
    }
    return empty;
  }
  private void Reproduction(FishOrShark animal, FishOrShark animalMove) {
      animalMove.setNextState(animal.getCurrentState());
      animalMove.setChrononsSurvived(animal.getChrononsSurvived());
      if (animal.getChrononsSurvived()>=REPRODUCTION_MOVES) {
        animalMove.setChrononsSurvived(0);
      }
      else {
        animal.setNextState(0);
        animalMove.setChrononsSurvived(animal.getChrononsSurvived()+1);
      }
    }
  private void sharkLogic(FishOrShark f1, Cell[][] neighbors) {
    if (f1.getEnergy() == 0) {
      f1.setNextState(0);
    }
    else {
      ArrayList<FishOrShark> fish = generateList(neighbors, 1);
      ArrayList<FishOrShark> empty = generateList(neighbors, 0);
      if (!fish.isEmpty()) {
        int randomChooser = (int) (Math.random() * fish.size());
        Reproduction(f1, fish.get(randomChooser));
        fish.get(randomChooser).setEnergy(f1.getEnergy()+ENERGY_FROM_FISH-1);
      }
      else if (!empty.isEmpty()) {
        int randomChooser = (int) (Math.random() * fish.size());
        Reproduction(f1, fish.get(randomChooser));
        empty.get(randomChooser).setEnergy(f1.getEnergy()-1);
      }
      else {
        f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
        f1.setEnergy(f1.getEnergy()-1);
      }
    }
  }
  private void fishLogic(FishOrShark f1, Cell[][] neighbors) {
    ArrayList<FishOrShark> empty = generateList(neighbors, 0);

    if (!empty.isEmpty()) {
      int randomChooser = (int) (Math.random() * empty.size());
      Reproduction(f1, empty.get(randomChooser));
    }
    else {
      f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
    }
  }
}
