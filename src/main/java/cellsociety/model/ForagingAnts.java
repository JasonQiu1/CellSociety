package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class ForagingAnts extends RuleSet {

  // ideally this should be a very large grid as this
  // doesnt make a lot of sense without room for a lot of ants
  // initial states
  // 0 = no ant here
  // 1 = 0-25% max ant size
  // 2 = 25% - 50% max ant size
  // 3 = 50% - 75% max ant size
  // 4 = 75% - 100% max ant size

  public ForagingAnts () {
    super();
  }
  //
  private static final int MAX_ANTS = 10;

  private static final int MAX_PHEREMONES = 100;

  public Cell[][] makeAntCells(Cell[][] grids) {
    // takes in cell array and make it into FishOrShark cell array
    Cell[][] newGrid = new Cell[grids.length][grids[0].length];
    for (int i = 0; i < grids.length; i++) {
      for (int j = 0; j < grids[0].length; j++) {
        int randomAnts = (int)(MAX_ANTS * (Math.random()) * (Math.random() * (0.25 * grids[i][j].getCurrentState())));

        newGrid[i][j] = new AntCell(grids[i][j].getCurrentState(), 5, 5, generateAnts(randomAnts));
      }
    }
    int setRandomNest = (int)(Math.random() * grids.length);
    int setRandomNest2 = (int)(Math.random() * grids[0].length);
    ((AntCell)(newGrid[setRandomNest][setRandomNest2])).setNest(true);
    ((AntCell)(newGrid[setRandomNest][setRandomNest2])).setNestPheromones(MAX_PHEREMONES);
    int setRandomFoodSource = (int)(Math.random() * grids.length);
    int setRandomFoodSource2 = (int)(Math.random() * grids[0].length);
    ((AntCell)(newGrid[setRandomFoodSource][setRandomFoodSource2])).setFoodSource(true);
    ((AntCell)(newGrid[setRandomFoodSource][setRandomFoodSource2])).setFoodPheromones(MAX_PHEREMONES);
    return newGrid;
  }

  private List<Ants> generateAnts(int randomAnts) {
    List<Ants> antsHere = new ArrayList<>();
    for (int i = 0; i < randomAnts; i++) {
      antsHere.add(new Ants());
    }
    return antsHere;
  }

  @Override
  public void setUpdateFlag(Cell[][] neighbors, Cell c1) {
    List<Cell> most = findMostFoodOrNest(neighbors);
    Cell mostFood = most.get(0);
    Cell mostNest = most.get(1);
    for (int i = 0; i < ((AntCell)c1).getAntList().size(); i++) {
      updateAnts(c1, mostFood, mostNest, i);
    }
  }

  public void updatePheromones(int fPher, int nPher, AntCell c1) {
    int addFoodPheromones = fPher - 2;
    int addNestPheromones = nPher - 2;
        // - c1.getNestPheromones();
    if (c1.getFoodPheromones() + addFoodPheromones < MAX_PHEREMONES && addFoodPheromones > 0) {
      c1.setFoodPheromones(c1.getFoodPheromones() + addFoodPheromones);
    }
    if (c1.getNestPheromones() + addNestPheromones < MAX_PHEREMONES && addNestPheromones > 0) {
      c1.setNestPheromones(c1.getNestPheromones() + addNestPheromones);
    }
  }

  public void updateNextState(Cell c1) {
    if (c1 != null) {
      evaporation(((AntCell)c1));
      if (((AntCell)c1).getAntList().isEmpty()) {
        c1.setNextState(0);
      }
      else if (((AntCell)c1).getAntList().size() < (0.25 * MAX_ANTS)) {
        c1.setNextState(1);
      }
      else if (((AntCell)c1).getAntList().size() < (0.5 * MAX_ANTS)) {
        c1.setNextState(2);
      } else if (((AntCell)c1).getAntList().size() < (0.75 * MAX_ANTS)) {
        c1.setNextState(3);
      }
      else {
        c1.setNextState(4);
      }
    }
  }

  public void evaporation (AntCell a1) {
    a1.employEvaporation();
  }

  public void updateAnts (Cell c1, Cell mostFood, Cell mostNest, int i) {
    int maxFPher = 0;
    int maxNPher = 0;
    if (((AntCell) c1).getAntList().get(i).isHasFoodItem()) {
      if (mostNest != null) {
        maxNPher = ((AntCell)mostNest).getNestPheromones();
        if (((AntCell) mostNest).isNest()) {
          ((AntCell) c1).getAntList().get(i).setHasFoodItem(false);
          ((AntCell)mostNest).setNestPheromones(MAX_PHEREMONES);
        }
        ((AntCell) mostNest).getAntList().add(((AntCell) c1).getAntList().get(i));
        ((AntCell) c1).getAntList().remove(i);
      }
    } else {
      if (mostFood != null) {
        maxFPher = ((AntCell)mostFood).getFoodPheromones();
        if (((AntCell) mostFood).isFoodSource()) {
          ((AntCell) c1).getAntList().get(i).setHasFoodItem(true);
          ((AntCell)mostFood).setFoodPheromones(MAX_PHEREMONES);
        }
        ((AntCell) mostFood).getAntList().add(((AntCell) c1).getAntList().get(i));
        ((AntCell) c1).getAntList().remove(i);
      }
    }
    updatePheromones(maxFPher, maxNPher,((AntCell)c1));
  }


  public List<Cell> findMostFoodOrNest(Cell[][] neighbors) {
    Cell mostFood = null;
    Cell mostNest = null;
    List<Cell> most = new ArrayList<>();
    most.add(mostFood);
    most.add(mostNest);
    for (int i = 0; i < neighbors.length; i++) {
      for (int j = 0; j < neighbors[0].length; j++) {
        most = mostLogic(most.get(0), most.get(1), neighbors[i][j]);
        }
      }
    return most;
    }

  public List<Cell> mostLogic(Cell mostFood, Cell mostNest, Cell check) {
    if (check != null && ((AntCell)(check)).getAntList().size() < MAX_ANTS) {
      if (mostFood == null || ((AntCell)check).getFoodPheromones() > ((AntCell)mostFood).getFoodPheromones()) {
        mostFood = check;
      }
      if (mostNest == null || ((AntCell)check).getNestPheromones() > ((AntCell)mostNest).getNestPheromones()) {
        mostNest = check;
      }
    }
    List<Cell> most = new ArrayList<>();
    most.add(mostFood);
    most.add(mostNest);
    return most;
  }

  @Override
  public void setGrid(Cell[][] grid) {
    if (this.grid == null) {
      this.grid = makeAntCells(grid);
    }
  }

  @Override
  public void update() {
    // used by all children
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        updateNextState(grid[i][j]);
        if (grid[i][j].getCurrentState() != grid[i][j].getNextState()) {
          applyUpdate(grid[i][j]);
        }
      }
    }
  }
}
