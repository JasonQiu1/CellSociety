package cellsociety.model;

public class Simulation {

  //    private cellsociety.model.RuleSet ruleSet;
//    private cellsociety.model.Grid grid;
  private boolean isPaused;
  private double simulationSpeed;

  //    public Simulation(cellsociety.model.RuleSet ruleSet, cellsociety.model.Grid initialGrid) {
  public Simulation() {
//        this.ruleSet = ruleSet;
//        this.grid = initialGrid;
    this.isPaused = false;
    this.simulationSpeed = 1.0;
  }

  public void pause() {
    isPaused = true;
  }

  public void unpause() {
    isPaused = false;
  }

  public void update(double elapsedTime) {
    if (!isPaused) {
      // Apply the rules to the grid
      // Note: You would need to implement the logic to update the grid based on the elapsed time and simulation speed

//            grid = ruleSet.applyRules(grid);

      return;
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
  }

  public Grid getGrid() {
    return null;
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
