package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Responsible for implementing the start, pause, speed up, slow down, reset, and save simulation
 * buttons
 *
 * @author Jason Qiu
 */
class SimulationControlPanel extends UserInterfacePanel {

  private static final String PROPERTY_SUFFIX = "SimulationControl";
  private static final int INPUT_FIELD_WIDTH = 10;

  /**
   * Create all simulation control buttons and lay them out.
   *
   * @param pane       the root to draw to.
   * @param controller the controller to hook handlers.
   */
  public SimulationControlPanel(Pane pane, Controller controller) {
    super(pane, "simulation-control-panel");

    Button start = Util.makeButton("start" + PROPERTY_SUFFIX,
        event -> controller.handleStartSimulationButtonPress());
    Button pause = Util.makeButton("pause" + PROPERTY_SUFFIX,
        event -> controller.handlePauseSimulationButtonPress());
    Button speedUp = Util.makeButton("speedUp" + PROPERTY_SUFFIX,
        event -> controller.handleSpeedUpSimulationButtonPress());
    Button slowDown = Util.makeButton("slowDown" + PROPERTY_SUFFIX,
        event -> controller.handleSlowDownSimulationButtonPress());
    Button reset = Util.makeButton("reset" + PROPERTY_SUFFIX,
        event -> controller.handleResetGridButtonPress());

    TextField configPathInput = new TextField();
    configPathInput.setPrefColumnCount(INPUT_FIELD_WIDTH);
    Button save = Util.makeButton("saveConfigurationButton",
        event -> controller.handleSaveConfigurationFileButtonPress(configPathInput.getText()));

    GridPane layout = new GridPane();
    layout.addRow(0, start, pause);
    layout.addRow(1, speedUp, slowDown);
    layout.addRow(2, reset);
    layout.addRow(3, configPathInput, save);
    getRoot().getChildren().add(layout);
  }
}
