package cellsociety.model;

public class Simulation {

  private RuleSet ruleSet;
  private Grid grid;
  private boolean isPaused;
  private double simulationSpeed;
  private double lastUpdateTime;
  private String author;
  private String description;


  public Simulation(RuleSet ruleSet, Cell[][] initialGrid) {
//  public Simulation() {
    this.ruleSet = ruleSet;
    this.grid = new FiniteGrid(ruleSet);
    this.isPaused = true;
    this.simulationSpeed = 1.0;
    this.lastUpdateTime = 0;
  }

  public String getSimulationType(){
    if (ruleSet != null) {
      return ruleSet.getClass().getSimpleName();
    } else {
      return "Unknown";
    }
  }

  public void pause() {
    isPaused = true;
  }

  public void unpause() {
    isPaused = false;
  }

  public void update(double elapsedTime) {
    lastUpdateTime += elapsedTime;
    if (!isPaused) {
      if (lastUpdateTime >= 1.0 / simulationSpeed) {
        grid.update();
        System.out.println("I am updating the gid");
        lastUpdateTime = 0;
      }
    }
  }

  public double getSimulationSpeed() {
    return simulationSpeed;
  }

  public void setSimulationSpeed(double speed) {
    if (speed > 0) {
      simulationSpeed = speed;
    } else {
      simulationSpeed = 0.1;
//      throw new IllegalArgumentException("Speed must be positive.");
    }
  }

  public int getNumStates() {
//    return 0;
    return grid.getNumStates();
  }

  public Grid getGrid() {
    return grid;
  }

  // Additional getters for author and description if needed
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    if (author != null && !author.trim().isEmpty()) {
      this.author = author;
    } else {
      throw new IllegalArgumentException("Author cannot be null or empty.");
    }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description != null && !description.trim().isEmpty()) {
      this.description = description;
    } else {
      throw new IllegalArgumentException("Description cannot be null or empty.");
    }
  }

}

