package cellsociety.controller;

import cellsociety.model.Simulation;

/**
 * Responsible for telling cellsociety.Game or Simulation what to do given user input. Doesn't need
 * to be instantiated.
 *
 * @author Jason Qiu (jq48)
 */
public class Controller {

  Simulation currentSimulation;

  /**
   * No initialization required, doesn't hold state.
   */
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
}
