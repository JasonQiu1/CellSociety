package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.layout.Pane;

/**
 * Responsible for drawing the user interface onto the given pane and detecting user input.
 *
 * @author Jason Qiu (jq48)
 */
class UserInterfaceDrawer extends UserInterfacePanel {

  public static final double CONFIG_TO_SIMULATION_PANEL_RATIO = 0.6;
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

    Pane configRoot = new Pane();
    configRoot.setPrefSize(pane.getPrefWidth() * CONFIG_TO_SIMULATION_PANEL_RATIO,
        pane.getPrefHeight());
    configPanel = new ConfigurationPanel(configRoot, controller);

    Pane simulationRoot = new Pane();
    simulationRoot.setLayoutX(pane.getPrefWidth() * CONFIG_TO_SIMULATION_PANEL_RATIO);
    simulationRoot.setPrefSize(pane.getPrefWidth() * (1 - CONFIG_TO_SIMULATION_PANEL_RATIO),
        pane.getPrefHeight());
    simulationPanel = new SimulationControlPanel(simulationRoot, controller);

    pane.getChildren().addAll(configRoot, simulationRoot);
  }

  public void update() {
    // TODO: update any components if needed
  }
}
