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
public class SimulationWindow {

  public static final String STYLESHEET = "default.css";
  private static final int WINDOW_WIDTH = 750;
  private static final int WINDOW_HEIGHT = 500;
  private static final Paint STAGE_COLOR = Color.WHITE;
  private static final double GRID_TO_UI_RATIO = 0.8;
  // make resources accessible to all ui components, so they don't have to grab their own each time
  // make this private and change to getter instead
  private final UserInterfaceDrawer uiDrawer;
  private final GridDrawer gridDrawer;
  private final Controller controller;
  private Simulation simulation;

  public SimulationWindow() {
    controller = new Controller();

    Pane gridPane = new Pane();
    gridPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT * GRID_TO_UI_RATIO);
    gridDrawer = new GridDrawer(gridPane);

    Pane uiPane = new Pane();
    uiPane.setLayoutY(WINDOW_HEIGHT * GRID_TO_UI_RATIO);
    uiPane.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT * (1 - GRID_TO_UI_RATIO));
    uiDrawer = new UserInterfaceDrawer(uiPane, controller);

    Group sceneRoot = new Group(uiPane, gridPane);
    Scene scene = new Scene(sceneRoot, WINDOW_WIDTH, WINDOW_HEIGHT, STAGE_COLOR);
    // stylesheets line from nanobrowser lab:
    // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
    scene.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource(View.DEFAULT_RESOURCE_FOLDER + STYLESHEET))
            .toExternalForm());
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Cell Society");
    stage.show();
  }

  public void update() {
    if (simulation != null) {
      gridDrawer.update(simulation.getGrid());
    }
    uiDrawer.update();
  }

  public void setSimulation(Simulation simulation) {
    this.simulation = simulation;
    gridDrawer.setNumStates(simulation.getNumStates());
    controller.setSimulation(simulation);
  }
}