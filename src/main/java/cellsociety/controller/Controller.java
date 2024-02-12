package cellsociety.controller;

import cellsociety.Game;
import cellsociety.model.Simulation;
import cellsociety.view.View;

/**
 * Responsible for telling Game or Simulation what to do given user input. Always checks for
 * exceptions.
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

  public Simulation getSimulation() {
    return simulation;
  }

  /**
   * Send a message to Game to load up a new simulation based on the given file name.
   *
   * @param configurationFileName the configuration file to load the new simulation from.
   */
  public static void handleLoadConfigurationFileButtonPress(String configurationFileName) {
    try {
      Game.loadNewSimulation(configurationFileName);
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  /**
   * Send a message to Game to remove the simulation this controller is responsible for.
   */
  public void removeSimulation() {
    try {
      Game.removeSimulation(simulation);
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handleSaveConfigurationFileButtonPress(String saveFileName) {
    try {
      // send a message to Game to save the simulation with the given metadata
      Game.saveSimulationToConfig(simulation, saveFileName);
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handleStartSimulationButtonPress() {
    try {
      simulation.unpause();
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handlePauseSimulationButtonPress() {
    try {
      simulation.pause();
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handleSpeedUpSimulationButtonPress() {
    try {
      simulation.setSimulationSpeed(simulation.getSimulationSpeed() + SPEED_ADJUSTMENT);
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handleSlowDownSimulationButtonPress() {
    try {
      simulation.setSimulationSpeed(simulation.getSimulationSpeed() - SPEED_ADJUSTMENT);
    } catch (Exception e) {
      View.showError(e.getMessage());
    }
  }

  public void handleResetGridButtonPress() {
    // send a message to Game to load the initial grid into the same simulation
    // without resetting its parameters
  }
}
