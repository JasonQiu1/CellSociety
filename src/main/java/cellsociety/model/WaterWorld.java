package cellsociety.model;

import java.util.ArrayList;

public class WaterWorld extends RuleSet {

  public static void main (String[] args) {
    Cell[][] grid = new Cell[10][10];
    int x = 0;
    for (int i = 0; i<10; i++) {
      for (int j = 0; j<10; j++) {
        double random = Math.random();
        if (random<0.1) {
          x = 1;
        }
        else if (random<0.2) {
          x = 2;
        }
        else {
          x = 0;
        }
        grid[i][j] = new FishOrShark(x);
      }
    }
    WaterWorld w1 = new WaterWorld(grid);
    for (int y = 0; y<1000; y++) {
      w1.print(grid);
      System.out.println(" ");
      System.out.println(" ");
      grid = w1.applyRules(grid);

    }
  }
  public void print(Cell[][] grid) {
    for (int i = 0; i<grid.length; i++) {
      for (int j = 0; j<grid.length; j++) {
        System.out.print(((FishOrShark)grid[i][j]).getCurrentState() + " ");
      }
      System.out.println(" ");
    }
  }
  // 0 nothing
  // 1 fish
  // 2 shark
  private static final int STATES = 2;
  private static final int REPRODUCTION_MOVES = 50;
  private static final int ENERGY_FROM_FISH = 20;

  private static final int BABY_ENERGY = 5;
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
    Cell[][] neighbors = super.findNeighbors(xCord,yCord);
    // only adjacent count
    Cell[][] adjNeighbors = new Cell[4][1];
    adjNeighbors[0][0] = neighbors[(neighbors.length/2)-1][(neighbors.length/2)];
    adjNeighbors[1][0] = neighbors[(neighbors.length/2)+1][(neighbors.length/2)];
    adjNeighbors[2][0] = neighbors[(neighbors.length/2)][(neighbors.length/2)-1];
    adjNeighbors[3][0] = neighbors[(neighbors.length/2)][(neighbors.length/2)+1];
    return adjNeighbors;

//    Cell[][] neighbors = new Cell[4][1];
//    if (xCord>0) {
//      neighbors[0][0] = getGrid()[xCord - 1][yCord];
//    }
//    else {
//      neighbors[0][0] = null;
//    }
//    if (xCord<getGrid().length-1) {
//      neighbors[1][0] = getGrid()[xCord + 1][yCord];
//    }
//    else{
//      neighbors[1][0] = null;
//    }
//    if (yCord>0) {
//      neighbors[2][0] = getGrid()[xCord][yCord-1];
//    }
//    else{
//      neighbors[2][0] = null;
//    }
//    if (yCord<getGrid()[0].length-1) {
//      neighbors[3][0] = getGrid()[xCord][yCord+1];
//    }
//    else{
//      neighbors[3][0] = null;
//    }
//    return neighbors;
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
    ArrayList<FishOrShark> list = new ArrayList<>();
    for (int i = 0; i<neighbors.length; i++) {
      if (neighbors[i][0]!= null && neighbors[i][0].getCurrentState() == state && neighbors[i][0].getNextState() == state) {
        list.add((FishOrShark)neighbors[i][0]);
      }
    }
    return list;
  }
  private void reproduction(FishOrShark animal, FishOrShark animalMove) {
      animalMove.setNextState(animal.getCurrentState());
      animalMove.setChrononsSurvived(animal.getChrononsSurvived());
      if (animal.getChrononsSurvived()>=REPRODUCTION_MOVES) {
        animalMove.setChrononsSurvived(0);
        animal.setChrononsSurvived(0);
        animal.setEnergy(BABY_ENERGY);
      }
      else {
        animal.setNextState(0);
        animalMove.setChrononsSurvived(animal.getChrononsSurvived()+1);
      }
    }
  private void sharkLogic(FishOrShark f1, Cell[][] neighbors) {
    System.out.println(f1.getEnergy());
    if (f1.getEnergy() <= 0) {
      f1.setNextState(0);
    }
    else {
      int currentEnergy = f1.getEnergy();
      ArrayList<FishOrShark> fish = generateList(neighbors, 1);
      ArrayList<FishOrShark> empty = generateList(neighbors, 0);
      if (!fish.isEmpty()) {
        int randomChooser = (int)(Math.random() * fish.size());
        reproduction(f1, fish.get(randomChooser));
        fish.get(randomChooser).setEnergy(currentEnergy+ENERGY_FROM_FISH-1);
      }
      else if (!empty.isEmpty()) {
        int randomChooser = (int) (Math.random() * empty.size());
        reproduction(f1, empty.get(randomChooser));
        empty.get(randomChooser).setEnergy(currentEnergy-1);
      }
      else {
        f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
        f1.setEnergy(currentEnergy-1);
      }
    }
  }
  private void fishLogic(FishOrShark f1, Cell[][] neighbors) {
    ArrayList<FishOrShark> empty = generateList(neighbors, 0);

    if (!empty.isEmpty()) {
      int randomChooser = (int) (Math.random() * empty.size());
      reproduction(f1, empty.get(randomChooser));
    }
    else {
      f1.setChrononsSurvived(f1.getChrononsSurvived()+1);
    }
  }
}
