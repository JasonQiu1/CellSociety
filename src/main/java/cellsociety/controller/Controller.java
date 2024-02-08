package cellsociety.controller;

import cellsociety.Game;
import cellsociety.model.Simulation;
import java.util.Map;

/**
 * Responsible for telling cellsociety. Game or Simulation what to do given user input. Doesn't need
 * to be instantiated.
 *
 * @author Jason Qiu (jq48)
 */
public class Controller {

  // how much to adjust updates per second
  public static final int SPEED_ADJUSTMENT = 1;
  Simulation currentSimulation;

  public Controller() {
    currentSimulation = null;
  }

  /**
   * Updates the internal reference to the current simulation.
   *
   * @param simulation the new simulation to send messages to.
   */
  public void setSimulation(Simulation simulation) {
    currentSimulation = simulation;
  }

  public void handleLoadConfigurationFileButtonPress(String configurationFileName) {
    Game.loadNewSimulation(configurationFileName);
  }

  public void handleSaveConfigurationFileButtonPress(String saveFileName,
      Map<String, String> saveFileMetadata) {
    // send a message to Game to save the simulation with the given metadata
  }

  public void handleStartSimulationButtonPress() {
    if (currentSimulation == null) {
      return;
    }
    currentSimulation.unpause();
  }

  public void handlePauseSimulationButtonPress() {
    if (currentSimulation == null) {
      return;
    }
    currentSimulation.pause();
  }

  public void handleSpeedUpSimulationButtonPress() {
    if (currentSimulation == null) {
      return;
    }
    currentSimulation.setSimulationSpeed(currentSimulation.getSimulationSpeed() + SPEED_ADJUSTMENT);
  }

  public void handleSlowDownSimulationButtonPress() {
    if (currentSimulation == null) {
      return;
    }
    currentSimulation.setSimulationSpeed(currentSimulation.getSimulationSpeed() - SPEED_ADJUSTMENT);
  }

  public void handleResetGridButtonPress() {
    // send a message to Game to load the initial grid into the same simulation
    // without resetting its parameters
  }
}
