package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.layout.Pane;

/**
 * Responsible for - showing the information of the currently loaded configuration file -
 * implementing the load, save (and edit save) buttons
 *
 * @author Jason Qiu
 */
class ConfigurationPanel extends UserInterfacePanel implements UserInputable {

  /**
   * Create all config components, including the config info panel, input field, and buttons
   *
   * @param pane       the root to draw to.
   * @param controller the controller to hook handlers.
   */
  public ConfigurationPanel(Pane pane, Controller controller) {
    super(pane);
    // TODO: create and hook all ui components
  }

  public void update() {
    // TODO: update any components if needed
  }
}
