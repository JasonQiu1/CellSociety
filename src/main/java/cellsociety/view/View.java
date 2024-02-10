package cellsociety.view;

import cellsociety.model.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main controller for drawing anything visible on the screen.
 *
 * @author Jason Qiu (jq48)
 */
public class View {

  // resource code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  public static final int MAIN_WINDOW_WIDTH = 400;
  public static final int MAIN_WINDOW_HEIGHT = 100;
  private static ResourceBundle resources;

  public List<SimulationWindow> simulationWindows;

  /**
   * Sets up resources and creates the main window which loads new simulations.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage, String resourcesFileName) {
    // resource bundle loading line from nanobrowser lab:
    // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
    resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + resourcesFileName);
    simulationWindows = new ArrayList<SimulationWindow>();

    Pane mainWindowRoot = Util.makeLoadConfigurationPanel(10);
    stage.setScene(new Scene(mainWindowRoot, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
    stage.setTitle("Cell Society");
    stage.setAlwaysOnTop(true);
    stage.show();
  }

  public static ResourceBundle getResources() {
    return resources;
  }

  /**
   * Updates all simulation windows.
   */
  public void update() {
    for (SimulationWindow window : simulationWindows) {
      window.update();
    }
  }

  /**
   * Creates a new simulation window for the given simulation.
   *
   * @param simulation the new simulation.
   */
  public void addSimulation(Simulation simulation) {
    simulationWindows.add(new SimulationWindow(simulation));
  }
}