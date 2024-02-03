package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.layout.Pane;

/**
 * Responsible for implementing the start, pause, speed up, slow down, and reset simulation buttons
 *
 * @author Jason Qiu
 */
class SimulationControlPanel extends UserInterfacePanel implements UserInputable {

  /**
   * Create all simulation control buttons.
   *
   * @param pane       the root to draw to.
   * @param controller the controller to hook handlers.
   */
  public SimulationControlPanel(Pane pane, Controller controller) {
    super(pane);
    // TODO: create and hook all buttons
  }
}
