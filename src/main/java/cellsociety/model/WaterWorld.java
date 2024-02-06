package cellsociety.model;

import java.util.ArrayList;

public class WaterWorld extends RuleSet {
  // 0 nothing
  // 1 fish
  // 2 shark
  private static final int STATES = 2;
  private final int REPRODUCTION_MOVES;
  private final int ENERGY_FROM_FISH ;
  private final int START_ENERGY;
  public WaterWorld(Cell[][] grid) {
    setGrid(makeFishOrShark(grid));
    REPRODUCTION_MOVES = 15;
    ENERGY_FROM_FISH = 10;
    START_ENERGY = 5;
  }

  public WaterWorld(Cell[][] grid, int REPRODUCTION_MOVES, int ENERGY_FROM_FISH, int START_ENERGY) {
    // makes cells into FishOrShark objects which extends Cell
    setGrid(makeFishOrShark(grid));
    this.REPRODUCTION_MOVES = REPRODUCTION_MOVES;
    this.ENERGY_FROM_FISH = ENERGY_FROM_FISH;
    this.START_ENERGY = START_ENERGY;

  }
  public Cell[][] makeFishOrShark(Cell[][] grids) {
    // takes in cell array and make it into FishOrShark cell array
    Cell[][] updatedGrid = new Cell[grids.length][grids[0].length];
    for (int i = 0; i<grids.length; i++) {
      for (int j = 0; j<grids[0].length; j++) {
        updatedGrid[i][j] = new FishOrShark(grids[i][j].getCurrentState(), START_ENERGY);
      }
    }
    return updatedGrid;
  }
  @Override
  public Cell[][] applyRules(Cell[][] grid) {
    setGrid(grid);
    // first for loop ensures that shark logic occurs before fish logic (sharks move before fish)
    for (int k = STATES; k>0; k--) {
      for (int i = 0; i < getGrid().length; i++) {
        for (int j = 0; j < getGrid()[0].length; j++) {
          if (k == getGrid()[i][j].getCurrentState()) {
            // find neighbors of sharks and fish
            Cell[][] neighbors = findNeighbors(i, j);
            setUpdateFlag(neighbors, getGrid()[i][j]);
          }
        }
      }
    }
    update(grid);
    return grid;
  }
  @Override
  public Cell[][] findNeighbors(int xCord, int yCord) {
    Cell[][] neighbors = super.findNeighbors(xCord,yCord);
    // only adjacent count
    Cell[][] adjNeighbors = new Cell[4][1];
    adjNeighbors[0][0] = neighbors[(neighbors.length/2)-1][(neighbors.length/2)];
    adjNeighbors[1][0] = neighbors[(neighbors.length/2)+1][(neighbors.length/2)];
    adjNeighbors[2][0] = neighbors[(neighbors.length/2)][(neighbors.length/2)-1];
    adjNeighbors[3][0] = neighbors[(neighbors.length/2)][(neighbors.length/2)+1];
    return adjNeighbors;
  }
  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // applies fish or shark logic
    int currentState = c1.getCurrentState();
    if (currentState==2) {
      sharkLogic((FishOrShark)c1, neighbors);
    }
    if (currentState==1 && c1.getNextState()==1) {
      fishLogic((FishOrShark)c1, neighbors);
    }
  }
  private ArrayList<FishOrShark> generateList(Cell[][] neighbors, int state) {
    // generates list of all neighbor cells of a current state
    ArrayList<FishOrShark> list = new ArrayList<>();
    for (int i = 0; i<neighbors.length; i++) {
      if (neighbors[i][0]!= null && neighbors[i][0].getCurrentState() == state && neighbors[i][0].getNextState() == state) {
        list.add((FishOrShark)neighbors[i][0]);
      }
    }
    return list;
  }
  private void reproduction(FishOrShark animal, FishOrShark animalMove) {
    // switches animal with animalMove. Leaves behind animal if it was supposed to reproduce
      animalMove.setNextState(animal.getCurrentState());
      animalMove.setChrononsSurvived(animal.getChrononsSurvived());
      if (animal.getChrononsSurvived()>=REPRODUCTION_MOVES) {
        animalMove.setChrononsSurvived(0);
        animal.setChrononsSurvived(0);
        animal.setEnergy(START_ENERGY);
      }
      else {
        // increase the amount of turns the animal has survived
        animal.setNextState(0);
        animalMove.setChrononsSurvived(animal.getChrononsSurvived()+1);
      }
    }
  private void sharkLogic(FishOrShark f1, Cell[][] neighbors) {
    // if out of energy, kill shark
    if (f1.getEnergy() <= 0) {
      f1.setNextState(0);
    }
    else {
      int currentEnergy = f1.getEnergy();
      ArrayList<FishOrShark> fish = generateList(neighbors, 1);
      ArrayList<FishOrShark> empty = generateList(neighbors, 0);
      if (!fish.isEmpty()) {
        // if there is a neighbor fish, eat it
        int randomChooser = (int)(Math.random() * fish.size());
        reproduction(f1, fish.get(randomChooser));
        fish.get(randomChooser).setEnergy(currentEnergy+ENERGY_FROM_FISH-1);
      }
      else if (!empty.isEmpty()) {
        // if there is no neighbor fish, move to empty neighbor cell
        int randomChooser = (int) (Math.random() * empty.size());
        reproduction(f1, empty.get(randomChooser));
        empty.get(randomChooser).setEnergy(currentEnergy-1);
      }
      else {
        // if surrounded by sharks stay put.
        f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
        f1.setEnergy(currentEnergy-1);
      }
    }
  }
  private void fishLogic(FishOrShark f1, Cell[][] neighbors) {
    ArrayList<FishOrShark> empty = generateList(neighbors, 0);

    if (!empty.isEmpty()) {
      // move to random empty spot around it
      int randomChooser = (int) (Math.random() * empty.size());
      reproduction(f1, empty.get(randomChooser));
    }
    else {
      // if no empty spots, then stay put
      f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
    }
  }
}
