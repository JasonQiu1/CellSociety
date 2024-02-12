package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class WaterWorld extends RuleSet {

  // 0 nothing
  // 1 fish
  // 2 shark

  private static final int STATES = 2;
  private final int reproductionMoves;
  private final int energyFromFish;
  private final int startEnergy;

  public WaterWorld(Cell[][] grid) {
    super();
    vonNeuman = true;
    reproductionMoves = 10;
    energyFromFish = 15;
    startEnergy = 5;
    this.grid = (makeFishOrShark(grid));
  }

  public WaterWorld(Cell[][] grid, int reproductionMoves, int energyFromFish, int startEnergy) {
    super();
    vonNeuman = true;
    // makes cells into FishOrShark objects which extends Cell
    this.reproductionMoves = reproductionMoves;
    this.energyFromFish = energyFromFish;
    this.startEnergy = startEnergy;
    this.grid = (makeFishOrShark(grid));
  }
  public WaterWorld(Cell[][] grid, int reproductionMoves, int energyFromFish, int startEnergy,
      int neighborSize, boolean vonNeuman, boolean toroidalNeighbor) {
    super();
    // makes cells into FishOrShark objects which extends Cell
    this.reproductionMoves = reproductionMoves;
    this.energyFromFish = energyFromFish;
    this.startEnergy = startEnergy;
    this.neighborSize = neighborSize;
    this.vonNeuman = vonNeuman;
    this.toroidalNeighbor = toroidalNeighbor;
    this.grid = (makeFishOrShark(grid));
  }

  @Override
  public void setGrid(Cell[][] grid) {
    if (this.grid == null) {
      this.grid = makeFishOrShark(grid);
    }
    else {
      return;
    }
  }

  public Cell[][] makeFishOrShark(Cell[][] grids) {
    // takes in cell array and make it into FishOrShark cell array
    Cell[][] updatedGrid = new Cell[grids.length][grids[0].length];
    for (int i = 0; i < grids.length; i++) {
      for (int j = 0; j < grids[0].length; j++) {
        updatedGrid[i][j] = new FishOrShark(grids[i][j].getCurrentState(), startEnergy);
      }
    }
    return updatedGrid;
  }

  @Override
  public void applyRules() {
    // first for loop ensures that shark logic occurs before fish logic (sharks move before fish)
    for (int k = STATES; k > 0; k--) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (k == grid[i][j].getCurrentState()) {
            // find neighbors of sharks and fish
            Cell[][] neighbors = findNeighbors(i, j);
            setUpdateFlag(neighbors, grid[i][j]);
          }
        }
      }
    }
    update();
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    // applies fish or shark logic
    int currentState = c1.getCurrentState();
    if (currentState == 2) {
      sharkLogic((FishOrShark) c1, neighbors);
    }
    if (currentState == 1 && c1.getNextState() == 1) {
      fishLogic((FishOrShark) c1, neighbors);
    }
  }

  private List<FishOrShark> generateList(Cell[][] neighbors, int state) {
    // generates list of all neighbor cells of a current state
    List<FishOrShark> list = new ArrayList<>();
    for (int i = 0; i < neighbors.length; i++) {
      if (neighbors[i][0] != null && neighbors[i][0].getCurrentState() == state
          && neighbors[i][0].getNextState() == state) {
        list.add((FishOrShark) neighbors[i][0]);
      }
    }
    return list;
  }

  private void reproduction(FishOrShark animal, FishOrShark animalMove) {
    // switches animal with animalMove. Leaves behind animal if it was supposed to reproduce
    animalMove.setNextState(animal.getCurrentState());
    animalMove.setChrononsSurvived(animal.getChrononsSurvived());
    if (animal.getChrononsSurvived() >= reproductionMoves) {
      animalMove.setChrononsSurvived(0);
      animal.setChrononsSurvived(0);
      animal.setEnergy(startEnergy);
    } else {
      // increase the amount of turns the animal has survived
      animal.setNextState(0);
      animalMove.setChrononsSurvived(animal.getChrononsSurvived() + 1);
    }
  }

  private void sharkLogic(FishOrShark f1, Cell[][] neighbors) {
    // if out of energy, kill shark
    if (f1.getEnergy() <= 0) {
      f1.setNextState(0);
    } else {
      int currentEnergy = f1.getEnergy();
      List<FishOrShark> fish = generateList(neighbors, 1);
      List<FishOrShark> empty = generateList(neighbors, 0);
      if (!fish.isEmpty()) {
        // if there is a neighbor fish, eat it
        int randomChooser = (int) (Math.random() * fish.size());
        reproduction(f1, fish.get(randomChooser));
        fish.get(randomChooser).setEnergy(currentEnergy + energyFromFish - 1);
      } else if (!empty.isEmpty()) {
        // if there is no neighbor fish, move to empty neighbor cell
        int randomChooser = (int) (Math.random() * empty.size());
        reproduction(f1, empty.get(randomChooser));
        empty.get(randomChooser).setEnergy(currentEnergy - 1);
      } else {
        // if surrounded by sharks stay put.
        f1.setChrononsSurvived(f1.getChrononsSurvived() + 1);
        f1.setEnergy(currentEnergy - 1);
      }
    }
  }

  private void fishLogic(FishOrShark f1, Cell[][] neighbors) {
    List<FishOrShark> empty = generateList(neighbors, 0);

    if (!empty.isEmpty()) {
      // move to random empty spot around it
      int randomChooser = (int) (Math.random() * empty.size());
      reproduction(f1, empty.get(randomChooser));
    } else {
      // if no empty spots, then stay put
      f1.setChrononsSurvived(f1.getChrononsSurvived() + 1);
    }
  }
}
