package cellsociety.view;

import cellsociety.model.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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
    simulationWindows = new ArrayList<>();

    Pane mainWindowRoot = Util.makeLoadConfigurationPanel(10);
    stage.setScene(new Scene(mainWindowRoot, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));

    stage.setTitle("Cell Society");
    stage.setAlwaysOnTop(true);
    stage.show();
  }

  public static ResourceBundle getResources() {
    return resources;
  }

  // showError function from nanobrowser lab
  // https://coursework.cs.duke.edu/compsci308_2024spring/lab_browser/-/blob/main/src/main/java/browser/view/NanoBrowserView.java
  // Display given message as an error in the GUI
  public static void showError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(resources.getString("errorWindowTitle"));

    Label messageText;
    try {
      messageText = new Label(resources.getString("error" + message));
    } catch (Exception e) {
      messageText = new Label(message);
    }
    messageText.setWrapText(true);
    alert.getDialogPane().setContent(messageText);

    alert.show();
  }

  /**
   * Updates all simulation windows.
   */
  public void update() {
    for (SimulationWindow window : simulationWindows) {
      try {
        window.update();
      } catch (RuntimeException e) {
        // Window updates could depend on Simulation object, which may be unreliable
        View.showError(e.getMessage());
      }
    }
  }

  /**
   * Creates a new simulation window for the given simulation.
   *
   * @param simulation the new simulation.
   */
  public void addSimulation(Simulation simulation) {
    if (simulation == null) {
      showError("ViewNullSimulation");
      return;
    }

    try {
      simulationWindows.add(new SimulationWindow(simulation));
    } catch (Exception e) {
      // creation of all ui components rely on the Simulation object, which may not be reliable
      showError(e.getMessage());
    }
  }
}