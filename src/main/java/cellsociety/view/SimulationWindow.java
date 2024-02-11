package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.Simulation;
import java.util.Objects;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Represents the view of a simulation, including its display and controls.
 *
 * @author Jason Qiu
 */
class SimulationWindow {

  public static final String STYLESHEET = "default.css";
  public static final int WINDOW_WIDTH = 750;
  public static final int WINDOW_HEIGHT = 500;
  public static final Paint STAGE_COLOR = Color.WHITE;
  public static final double GRID_TO_UI_RATIO = 0.8;
  private final SimulationView simulationView;

  public SimulationWindow(Simulation simulation) {
    Controller controller = new Controller(simulation);

    Pane gridPane = new Pane();
    gridPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT * GRID_TO_UI_RATIO);
    simulationView = new SimulationGridView(simulation, gridPane);

    Pane controlPane = new Pane();
    controlPane.setLayoutY(WINDOW_HEIGHT * GRID_TO_UI_RATIO);
    controlPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT * (1 - GRID_TO_UI_RATIO));
    new SimulationControlPanel(controlPane, controller);

    Group sceneRoot = new Group(controlPane, gridPane);
    Scene scene = new Scene(sceneRoot, WINDOW_WIDTH, WINDOW_HEIGHT, STAGE_COLOR);
    // stylesheets line from nanobrowser lab:
    // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
    scene.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource(View.DEFAULT_RESOURCE_FOLDER + STYLESHEET))
            .toExternalForm());

    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Cell Society");
    stage.setOnCloseRequest(event -> controller.removeSimulation());
    stage.show();
  }

  /**
   * Updates the simulation view.
   */
  public void update() {
    simulationView.update();
  }
}
