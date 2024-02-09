package cellsociety.controller;

import cellsociety.Game;
import cellsociety.model.Simulation;

/**
 * Responsible for telling cellsociety. Game or Simulation what to do given user input. Doesn't need
 * to be instantiated.
 *
 * @author Jason Qiu (jq48)
 */
public class Controller {

  // how much to adjust updates per second
  public static final int SPEED_ADJUSTMENT = 1;
  private final Simulation simulation;

  public Controller(Simulation simulation) {
    this.simulation = simulation;
  }

  public static void handleLoadConfigurationFileButtonPress(String configurationFileName) {
    Game.loadNewSimulation(configurationFileName);
  }

  /**
   * Send a message to Game to remove the simulation this controller is responsible for.
   */
  public void removeSimulation() {
    Game.removeSimulation(simulation);
  }

  public void handleSaveConfigurationFileButtonPress(String saveFileName) {
    // send a message to Game to save the simulation with the given metadata
    Game.saveSimulationToConfig(simulation, saveFileName);
  }

  public void handleStartSimulationButtonPress() {
    if (simulation == null) {
      return;
    }
    simulation.unpause();
  }

  public void handlePauseSimulationButtonPress() {
    if (simulation == null) {
      return;
    }
    simulation.pause();
  }

  public void handleSpeedUpSimulationButtonPress() {
    if (simulation == null) {
      return;
    }
    simulation.setSimulationSpeed(simulation.getSimulationSpeed() + SPEED_ADJUSTMENT);
  }

  public void handleSlowDownSimulationButtonPress() {
    if (simulation == null) {
      return;
    }
    simulation.setSimulationSpeed(simulation.getSimulationSpeed() - SPEED_ADJUSTMENT);
  }

  public void handleResetGridButtonPress() {
    // send a message to Game to load the initial grid into the same simulation
    // without resetting its parameters
  }
}
