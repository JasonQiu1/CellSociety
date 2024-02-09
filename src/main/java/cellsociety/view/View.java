package cellsociety.view;

import cellsociety.model.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Responsible for drawing anything visible on the screen. Delegates user input handling to a
 * Controller instance.
 *
 * @author Jason Qiu (jq48)
 */
public class View {

  // resource code from nanobrowser lab:
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  public static ResourceBundle resources;
  public List<SimulationWindow> simulationWindows;

  /**
   * Splits the given Stage into two portions for the cell grid and the user interface. Sets up the
   * drawers for the cell grid, ui, and controller.
   *
   * @param stage the stage to draw everything on.
   */
  public View(Stage stage, String resourcesFileName) {
    // resource bundle loading line from nanobrowser lab:
    // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser
    resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + resourcesFileName);
    simulationWindows = new ArrayList<SimulationWindow>();

    Pane mainWindowRoot = Util.makeLoadConfigurationPanel(10);
    stage.setScene(new Scene(mainWindowRoot));
    stage.setTitle("Cell Society");
    stage.show();
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
   * Update's the grid reference that gridDrawer draws from. Also points the controller's user input
   * handling functions to the correct simulation.
   * TODO: refactor later so that main just passes cellsociety.model.Grid
   *    into draw for the GridDrawer
   * TODO: make main create the controller instead and pass it into View constructor to hook
   *    Removes the need for this function in View, will be responsibility of main
   *
   * @param simulation the new simulation.
   */
  public void addSimulation(Simulation simulation) {
    simulationWindows.add(new SimulationWindow(simulation));
  }
}