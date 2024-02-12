package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class AntCell extends Cell {

  private int foodPheromones;

  private int nestPheromones;

  private boolean isNest;

  private boolean foodSource;

  private static final double EVAPORATION_RATE = 0.1;

  private List<Ants> antList;

  public AntCell(int currentState, int foodPheromones, int nestPheremones, List<Ants> antList) {
    super(currentState);
    this.foodPheromones = foodPheromones;
    this.nestPheromones = nestPheremones;
    isNest = false;
    foodSource = false;
    this.antList = antList;
  }


  public int getFoodPheromones() {
    return foodPheromones;
  }

  public void setFoodPheromones(int foodPheromones) {
    this.foodPheromones = foodPheromones;
  }

  public int getNestPheromones() {
    return nestPheromones;
  }

  public void setNestPheromones(int nestPheromones) {
    this.nestPheromones = nestPheromones;
  }

  public List<Ants> getAntList() {
    return antList;
  }

  public void setAntList(List<Ants> antList) {
    this.antList = antList;
  }

  public boolean isNest() {
    return isNest;
  }

  public void setNest(boolean nest) {
    isNest = nest;
  }

  public boolean isFoodSource() {
    return foodSource;
  }

  public void setFoodSource(boolean foodSource) {
    this.foodSource = foodSource;
  }

  public void employEvaporation() {
    foodPheromones = (int)(foodPheromones * (1 - EVAPORATION_RATE));
    nestPheromones = (int)(nestPheromones * (1 - EVAPORATION_RATE));
  }
}
