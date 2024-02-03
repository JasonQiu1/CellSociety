package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.layout.Pane;

/**
 * Responsible for drawing the user interface onto the given pane and detecting user input.
 *
 * @author Jason Qiu (jq48)
 */
class UserInterfaceDrawer extends UserInterfacePanel {

  private ConfigurationPanel configPanel;
  private SimulationControlPanel simulationPanel;

  /**
   * Splits the user interface in half for the config panel and the simulation panel.
   *
   * @param pane       the pane to draw the user interface onto.
   * @param controller the controller from which to hook user input handlers
   */
  public UserInterfaceDrawer(Pane pane, Controller controller) {
    super(pane, "user-interface");
    // TODO: split the user interface in half horizontally for the config panel and simulation panels
  }

  public void update() {
    // TODO: update any components if needed
  }
}
