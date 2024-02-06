package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Responsible for implementing the start, pause, speed up, slow down, and reset simulation buttons
 *
 * @author Jason Qiu
 */
class SimulationControlPanel extends UserInterfacePanel implements UserInputable {

  private static final String PROPERTY_SUFFIX = "SimulationControl";
  private Button start;
  private Button pause;
  private Button speedUp;
  private Button slowDown;
  private Button reset;

  /**
   * Create all simulation control buttons and lay them out.
   *
   * @param pane       the root to draw to.
   * @param controller the controller to hook handlers.
   */
  public SimulationControlPanel(Pane pane, Controller controller) {
    super(pane, "simulation-control-panel");

    start = makeButton("start" + PROPERTY_SUFFIX,
        event -> controller.handleStartSimulationButtonPress());
    pause = makeButton("pause" + PROPERTY_SUFFIX,
        event -> controller.handlePauseSimulationButtonPress());
    speedUp = makeButton("speedUp" + PROPERTY_SUFFIX,
        event -> controller.handleSpeedUpSimulationButtonPress());
    slowDown = makeButton("slowDown" + PROPERTY_SUFFIX,
        event -> controller.handleSlowDownSimulationButtonPress());
    reset = makeButton("reset" + PROPERTY_SUFFIX,
        event -> controller.handleResetGridButtonPress());

    GridPane layout = new GridPane();
    layout.addRow(0, start, pause);
    layout.addRow(1, speedUp, slowDown);
    layout.addRow(2, reset);
    getRoot().getChildren().add(layout);
  }
}
