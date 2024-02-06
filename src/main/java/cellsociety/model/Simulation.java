package cellsociety.model;

public class Simulation {

  private cellsociety.model.RuleSet ruleSet;
  private cellsociety.model.Grid grid;
  private boolean isPaused;
  private double simulationSpeed;
  private double lastUpdateTime;
  private String author;
  private String description;


  public Simulation(cellsociety.model.RuleSet ruleSet, cellsociety.model.Grid initialGrid) {
//  public Simulation() {
    this.ruleSet = ruleSet;
    this.grid = initialGrid;
    this.isPaused = true;
    this.simulationSpeed = 1.0;
    this.lastUpdateTime = 0;
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
//      System.out.println(lastUpdateTime);
//      System.out.println(1.0 / simulationSpeed);
      // Apply the rules to the grid
      // Note: You would need to implement the logic to update the grid based on the elapsed time and simulation speed
      if (lastUpdateTime >= 1.0 / simulationSpeed) {

        // Apply the rules to the grid
        // Note: Implement the logic to update the grid based on the elapsed time and simulation speed
         grid.setGrid(ruleSet.applyRules(grid.getGrid()));

        // Update the last update time
        System.out.println("I am updating the gid");
        System.out.println(elapsedTime);
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
      throw new IllegalArgumentException("Speed must be positive.");
    }
  }

  public int getNumStates() {
    return 0;
//    return grid.getNumStates();
  }

  public Grid getGrid() {
    return grid;
//    return grid;
  }

  public void setAuthor(String author) {
    if (author != null && !author.trim().isEmpty()) {
      this.author = author;
    } else {
      throw new IllegalArgumentException("Author cannot be null or empty.");
    }
  }

  public void setDescription(String description) {
    if (description != null && !description.trim().isEmpty()) {
      this.description = description;
    } else {
      throw new IllegalArgumentException("Description cannot be null or empty.");
    }
  }

  // Additional getters for author and description if needed
  public String getAuthor() {
    return author;
  }

  public String getDescription() {
    return description;
  }

//    public void setGrid(cellsociety.model.Grid grid) {
//        if (grid != null) {
//            this.grid = grid;
//        } else {
//            throw new IllegalArgumentException("cellsociety.model.Grid cannot be null.");
//        }
//    }

  // Additional methods to interact with the simulation might be needed here
  // For example, getting the current state of the grid for rendering purposes
}

